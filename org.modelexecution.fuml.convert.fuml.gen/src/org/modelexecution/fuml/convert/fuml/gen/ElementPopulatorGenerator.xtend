package org.modelexecution.fuml.convert.fuml.gen

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.common.util.TreeIterator

class ElementPopulatorGenerator implements IGenerator {
	
	/* Saves the names of generated populator classes */
	List<String> syntaxClassNames
	List<String> semanticsClassNames
	Resource resource;
		
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		this.resource = resource;
		initializeClassNamesList()
		generatePopulatorClasses(resource, fsa)
		generatePopulatorSuiteClass(fsa)
		generateSemanticsPopulatorSuiteClass(fsa)
		generateElementFactory(resource, fsa)		
		generateSemanticsElementFactory(resource, fsa)
    }
    
    def initializeClassNamesList() {
    	syntaxClassNames = new ArrayList()
    	semanticsClassNames = new ArrayList()
    }
    
    def generatePopulatorClasses(Resource resource, IFileSystemAccess fsa) {
    	for (EObject o : resource.contents) {
			o.compile(fsa)
		}
    }
    
    def dispatch void compile(EPackage p, IFileSystemAccess fsa) {
    	for (EClassifier classifiers : p.getEClassifiers) {
			classifiers.compile(fsa)
		}
    	for (EPackage subPackage : p.getESubpackages) {
			subPackage.compile(fsa)
		}
    }
    
    def dispatch void compile(EClassifier eClassifier, IFileSystemAccess fsa) {
    	switch (eClassifier) {
  			EClass : eClassifier.compile(fsa)
  		}
    }
    
    def dispatch void compile(EClass eClass, IFileSystemAccess fsa) {
    	if (eClass.getEStructuralFeatures.size > 0 && !eClass.ignoreClass) {
    	
    	if(eClass.semanticsElement)
    		semanticsClassNames.add(eClass.populatorClassName)
    	else
    		syntaxClassNames.add(eClass.populatorClassName)
    	
    	fsa.generateFile(eClass.populatorClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.fuml.internal.gen;
			«imports(eClass.isSemanticsElement)»
			
			«genAnnotation»
			public class «eClass.populatorClassName» implements «IF eClass.isSemanticsElement»IValuePopulator«ELSE»IElementPopulator«ENDIF» {
			
				@Override
				public void populate(«IF eClass.isSemanticsElement»Object«ELSE»fUML.Syntax.Classes.Kernel.Element«ENDIF» fumlElement,
					«IF eClass.isSemanticsElement»Object«ELSE»org.modelexecution.fuml.Syntax.Classes.Kernel.Element«ENDIF» fumlElement_, 
					«IF eClass.isSemanticsElement»IConversionResult«ELSE»ConversionResultImpl«ENDIF» result
					«IF eClass.isSemanticsElement», IValueConversionResult valueConversionResult«ENDIF») {
						
					if (!(fumlElement_ instanceof «eClass.qualifiedNameGeneratedFUML») ||
						«IF !eClass.isMultiplicityElement»!(fumlElement instanceof «eClass.qualifiedNameFUML»)«ELSE»«printMultiplicityTypeQuery("fumlElement")»«ENDIF») {
						return;
					}
					
					«IF !eClass.isMultiplicityElement»«eClass.qualifiedNameFUML» «fumlElementVar» = («eClass.qualifiedNameFUML») fumlElement;
					«ELSE»«printMultiplicityElementAssignment("fumlElement")»«ENDIF»
					
					«eClass.qualifiedNameGeneratedFUML» «fumlElementVar_» = («eClass.qualifiedNameGeneratedFUML») fumlElement_;
					
					«FOR feature : eClass.getEStructuralFeatures»
					«feature.printAssingment»
					«ENDFOR»	
										
					«IF eClass.name.equals('Element')»
						for (org.modelexecution.fuml.Syntax.Classes.Kernel.Comment value : fumlElement_.getOwnedComment()) {
						        fUML.Syntax.Classes.Kernel.Comment comment = new fUML.Syntax.Classes.Kernel.Comment();
						        comment.body = value.getBody();
						        «fumlElementVar».ownedComment.add(comment);
						}
					«ENDIF»									
				}
				
				«FOR feature : eClass.getEStructuralFeatures»
					«IF feature.getEType instanceof EEnum»
					«feature.printCastMethod»
					«ENDIF»
				«ENDFOR»
			}
			''')			
			}
    }
	def String printMultiplicityElementAssignment(String variablename) '''
		fUML.Syntax.Classes.Kernel.MultiplicityElement fumlNamedElement = null;
		«FOR eClass : multiplicitySubClasses SEPARATOR " else "»
			if («variablename» instanceof «eClass.qualifiedNameFUML») {
				«fumlElementVar» = ((«eClass.qualifiedNameFUML»)«variablename»).multiplicityElement;
			}«ENDFOR»
	'''

    
	def String printMultiplicityTypeQuery(String variablename) {
		var String query = " ! ("; 		
		var int i = 0;
		for(EClass eClass : multiplicitySubClasses) {
			if (i != 0) {
				query = query + " || " 
			} else {
				i = 1;
			}
    		query = query + variablename + " instanceof " + eClass.qualifiedNameFUML;
		}    	
    	query = query + " )"
    	query
	}
	
	def List<EClass> getMultiplicitySubClasses() {
		var List<EClass> classes = new ArrayList<EClass>();
		var TreeIterator<EObject> iterator = resource.allContents
    	while(iterator.hasNext) {
    		var EObject o = iterator.next 
    		if(o instanceof EClass) {
    			var eClass = o as EClass
    			for(EClass supertype : eClass.ESuperTypes) {
    				if(supertype.name.equals("MultiplicityElement")) {
    					classes.add(eClass);
    				}
    			}
    		}
    	}    
    	classes
	}
	
	

	def boolean isMultiplicityElement(EClass eClass) { 
		eClass.name.equals("MultiplicityElement")
	}
	
    
    def ignoreClass(EClass eClass) {
		eClass.name.equals('Comment')
	}
	
	 def isSemanticsElement(EClassifier eClass) {
		eClass.EPackage.qualifiedName.contains("Semantic")
	}
    
    def String populatorClassFilePath(EClass eClass) {
    	targetPath + eClass.populatorClassName + javaExtension
    }
    
    def String populatorClassName(EClass eClass) {
    	eClass.name + "Populator"
    }
    
    def String qualifiedName(EClassifier eClassifier) {
    	eClassifier.getEPackage.qualifiedName + "." + eClassifier.name
    }
    
    def String qualifiedNameFUML(EClassifier eClassifier) {
    	eClassifier.getEPackage.qualifiedName + "." + eClassifier.name.maskNameFUML
    }
    
     def String qualifiedNameGeneratedFUML(EClassifier eClassifier) {
    	("org.modelexecution.fuml" + eClassifier.getEPackage.qualifiedName + "." + eClassifier.name).replaceAll("fUML","")
    }
    
    def String qualifiedName(EPackage ePackage) {
    	var qName = ""
    	if (ePackage.getESuperPackage != null) {
    		qName = ePackage.getESuperPackage.qualifiedName + "."
    	}
    	qName = qName + ePackage.getName
    	return qName
    }
    
    def dispatch String printAssingment(EStructuralFeature feature) {
    	switch (feature) {
	    	EAttribute : return feature.printAssingment
	    	EReference : return feature.printAssingment
	    	default : ""
    	}
    }
    
    def shouldIgnore(EStructuralFeature feature) {
    	feature.getName.equals("ownedComment")
    }
    
    def dispatch String printAssingment(EAttribute attribute) {
    	if (attribute.shouldIgnore) return ""
    	if (attribute.isMany) return attribute.printMultiValuedAssingment
    	
    	var assignment = ""
    	
    	// assignee
    	if (attribute.useSetter) {
    		assignment = '''«fumlElementVar».«attribute.setter»('''.toString
    	} else {
    		assignment = '''«fumlElementVar».«attribute.assignmentName» = '''.toString
    	}
    	
    	// assignment value
    	if (attribute.getEType instanceof EEnum) {
    		assignment = '''«assignment»«attribute.castEnumMethodName»(«fumlElementVar_».«attribute.getter»)'''.toString
    	} else if( (attribute.EContainingClass.name.equals("UnlimitedNaturalValue") || attribute.EContainingClass.name.equals("LiteralUnlimitedNatural"))
    		 && attribute.name.equals("value") ) {
    		assignment = '''«assignment»new UMLPrimitiveTypes.UnlimitedNatural(«fumlElementVar_».«attribute.getter»)'''.toString
    	} else {
    		assignment = '''«assignment»«fumlElementVar_».«attribute.getter»'''.toString
    	}
    	
    	// close assignment
    	if (attribute.useSetter) {
    		assignment = '''«assignment»);'''.toString
    	} else {
    		assignment = '''«assignment»;'''.toString
    	}
    	
    	return assignment
    }
    
    def dispatch String printAssingment(EReference reference) {
    	if (reference.shouldIgnore) return ""
    	if (reference.isMany) return reference.printMultiValuedAssingment
    	
    	return '''«fumlElementVar».«reference.assignmentName» = («reference.getEType.qualifiedNameFUML») «IF reference.getEType.isSemanticsElement»valueConversionResult«ELSE»result«ENDIF».getFUMLElement(«fumlElementVar_».«reference.getter»);'''.toString
    }
    
    def dispatch String printMultiValuedAssingment(EAttribute attribute) {
    	'''

    	for («attribute.getEType.instanceClassName» value : «fumlElementVar_».«attribute.getter») {
    		«fumlElementVar».«attribute.name.maskNameFUML».add(value);
    	}

'''.toString
    }
    
    def dispatch String printMultiValuedAssingment(EReference reference) {
    	'''
    	
    	for («reference.getEType.qualifiedNameGeneratedFUML» value : «fumlElementVar_».«reference.getter») {
			«fumlElementVar».«reference.name.maskNameFUML».add((«reference.getEType.qualifiedNameFUML») «IF reference.getEType.isSemanticsElement»valueConversionResult«ELSE»result«ENDIF».getFUMLElement(value));
}

'''.toString
    }
    
    
    def String getMappedName(EStructuralFeature feature) {
    	if (feature.name == "structuredNodeOutput") {
    		return "output"
    	} else if (feature.name == "structuredNodeInput") {
    		return "input"
    	} else if (feature.name == "node" && feature.getContainingClassName == "Activity") {
    		return "ownedNode"
    	} else {
    		return feature.name
    	}
    }
    
    def String getContainingClassName(EStructuralFeature feature) {
    	var container = feature.eContainer
    	switch (container) {
    		EClass : return container.name
    	}
    	return ""
    }
    
    
    def String assignmentName(EStructuralFeature feature) {
    	if (feature.getName() == "upper") {
    		return "upper.naturalValue"
    	} else {
    		if (feature.getEType.getName() == "EBoolean" && !feature.omitIsInAssignment) {
    			return "is" + feature.getName().toFirstUpper
    		}
    		return feature.getName().maskNameFUML
    	}
    }
    
	def omitIsInAssignment(EStructuralFeature feature) {
		return feature.getName().equals('value') || feature.getName().equals('mustIsolate');
	}

    
    def String getter(EStructuralFeature feature) {
    	if (feature.getEType.getName() == "EBoolean") {
    		if (feature.getName().startsWith("is")) {
    			return feature.getName() + "()"
    		} else {
    			return "is" + feature.getName().toFirstUpper + "()"	
    		}
    	} else {
    		return "get" + feature.getName().toFirstUpper + "()"
    	}
    }
    
    def String castEnumMethodName(EAttribute attribute) {
    	'''cast«attribute.getEType.name»'''.toString
    }
    
    def dispatch String printCastMethod(EStructuralFeature feature) {
    	switch(feature) {
    		EAttribute : return feature.printCastMethod
    	}
    	return ""
    }
    
    def dispatch String printCastMethod(EAttribute attribute) {
    	'''
    	private «attribute.getEType.qualifiedNameFUML» «attribute.castEnumMethodName»(«attribute.getEType.qualifiedNameGeneratedFUML» enumVal) {
    		switch (enumVal.getValue()) {
    			«attribute.getEType.printEnumLiteralCases»
    		}
    		return null;
    	}'''.toString
    }
    
    def dispatch String printEnumLiteralCases(EClassifier eClassifier) {
    	switch (eClassifier) {
    		EEnum : return eClassifier.printEnumLiteralCases
    	}
    	return null
    }
    
    def dispatch String printEnumLiteralCases(EEnum eEnum) {
    	'''
    	«FOR enumLiteral : eEnum.getELiterals»
    	case «eEnum.qualifiedNameGeneratedFUML».«enumLiteral.getLiteral.toUpperCase»_VALUE:
    		return «eEnum.qualifiedName».«enumLiteral.name.maskNameFUML»;
    	«ENDFOR»
    	'''.toString
    }
    
    def String maskNameFUML(String name) {
    	if (name == "package" || name == "private" || name == "protected" ||
    		name == "public" || name == "return" || name == "Class" ||
    		name == "class" || name =="Object"
    	) {
    		return '''«name»_'''.toString
    	} else {
    		return name
    	}
    }
        
    def boolean useSetter(EStructuralFeature feature) {
    	if (feature.getEType.getName() == "EBoolean" ||
    		feature.getName == "qualifiedName" ||
    		feature.getName == "lower" ||
    		feature.getName == "upper" ||
    		feature.getName == "value" ||
    		feature.getName == "position" ||
    		feature.getName == "body" ||
    		feature.getEType instanceof EEnum) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    def String setter(EStructuralFeature feature) {
    	if (feature.getEType.getName() == "EBoolean") {
    		if (feature.getName().startsWith("is")) {
    			return "set" + feature.getName().toFirstUpper
    		} else {
    			return "setIs" + feature.getName().toFirstUpper	
    		}
    	} else {
    		return "set" + feature.getName().toFirstUpper
    	}
    }
    
    def String fumlElementVar_() {
    	"fumlNamedElement_"
    }
    
    def String fumlElementVar() {
    	"fumlNamedElement"
    }
    
    def generatePopulatorSuiteClass(IFileSystemAccess fsa) {
    	fsa.generateFile(populatorSuiteClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.fuml.internal.gen;
			«imports(false)»
			import java.util.Collection;
			import java.util.ArrayList;
			
			«genAnnotation»
			public class ElementPopulatorSuite {

				private Collection<IElementPopulator> elementPopulators = new ArrayList<>();
			
				private ConversionResultImpl result;
			
				public ElementPopulatorSuite(ConversionResultImpl result) {
					this.result = result;
					initializePopulators();
				}
			
				private void initializePopulators() {
					«FOR className : syntaxClassNames»
					elementPopulators.add(new «className»());
			    	«ENDFOR»
				}
			
				public void populate(fUML.Syntax.Classes.Kernel.Element fUMLElement,
						org.modelexecution.fuml.Syntax.Classes.Kernel.Element fumlElement_) {
					for (IElementPopulator populator : elementPopulators) {
						populator.populate(fUMLElement, fumlElement_, result);
					}
				}
			
			}
			''')
    }
    
     def generateSemanticsPopulatorSuiteClass(IFileSystemAccess fsa) {
    	fsa.generateFile(semanticsPopulatorSuiteClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.fuml.internal.gen;
			«imports(true)»
			import java.util.Collection;
			import java.util.ArrayList;
			
			«genAnnotation»
			public class ValuePopulatorSuite {

				private Collection<IValuePopulator> elementPopulators = new ArrayList<>();
			
				private IConversionResult conversionResult;
				private IValueConversionResult valueConversionResult;
			
				public ValuePopulatorSuite(IConversionResult conversionResult, IValueConversionResult valueConversionResult) {
					this.conversionResult = conversionResult;
					this.valueConversionResult = valueConversionResult;
					initializePopulators();
				}
			
				private void initializePopulators() {
					«FOR className : semanticsClassNames»
					elementPopulators.add(new «className»());
			    	«ENDFOR»
				}
			
				public void populate(Object fUMLElement,
						Object fumlElement_) {
					for (IValuePopulator populator : elementPopulators) {
						populator.populate(fUMLElement, fumlElement_, conversionResult, valueConversionResult);
					}
				}
			
			}
			''')
    }
    
    def String populatorSuiteClassFilePath() {
    	targetPath + "ElementPopulatorSuite" + javaExtension
    }
    
    def String semanticsPopulatorSuiteClassFilePath() {
    	targetPath + "ValuePopulatorSuite" + javaExtension
    }
    
    def generateElementFactory(Resource resource, IFileSystemAccess fsa) {
    	fsa.generateFile(elementFactoryClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.fuml.internal.gen;
			
			import javax.annotation.Generated;
			import org.eclipse.emf.ecore.EObject;
			
			«genAnnotation»
			public class ElementFactory {
			
				public fUML.Syntax.Classes.Kernel.Element create(EObject element) {
					String className = element.eClass().getName();
					switch(className) {
					«resource.printElementFactoryCaseStatements(false)»
					}
					return null;
				}
				«resource.printElementFactoryCreateOperations(false)»
				
			}			
			''')
    }
    
    def String printElementFactoryCaseStatements(Resource resource, boolean semantics) {
    	var String statements = "";
    	var TreeIterator<EObject> iterator = resource.allContents
    	while(iterator.hasNext) {
    		var EObject o = iterator.next 
    		if(o instanceof EClass) {
    			var eClass = o as EClass
    			if(!eClass.isAbstract && !eClass.ignoreClass && semantics == eClass.semanticsElement) {
    				statements = statements + eClass.printElementFactoryCaseStatement;
    			}
    		}
    	}    	
    	statements
    }
    
     def String printElementFactoryCaseStatement(EClass eClass) {
		'''
		case "«eClass.name»":
			return create((«eClass.qualifiedNameGeneratedFUML») element);
		'''.toString
    }
    
     def String printElementFactoryCreateOperations(Resource resource, boolean semantics) {
    	var String statements = "";
    	var TreeIterator<EObject> iterator = resource.allContents
    	while(iterator.hasNext) {
    		var EObject o = iterator.next 
    		if(o instanceof EClass) {
    			var eClass = o as EClass
    			if(!eClass.isAbstract && !eClass.ignoreClass && semantics == eClass.semanticsElement) {
    				statements = statements + (o as EClass).printElementFactoryCreateOperation;
    			}
    		}
    	}    	
    	statements
    }   
    
     def String printElementFactoryCreateOperation(EClass eClass) {
     	'''
     		public «eClass.qualifiedNameFUML» create(«eClass.qualifiedNameGeneratedFUML» element) {
     			return new «eClass.qualifiedNameFUML»();
     		}
		'''.toString
    }
    
    def String elementFactoryClassFilePath() {
    	targetPath + "ElementFactory" + javaExtension
    }
    
    def generateSemanticsElementFactory(Resource resource, IFileSystemAccess fsa) {
    	fsa.generateFile(semanticsElementFactoryClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.fuml.internal.gen;
			
			import javax.annotation.Generated;
			import org.eclipse.emf.ecore.EObject;
			
			«genAnnotation»
			public class ValueFactory {
			
				public Object create(EObject element) {
					String className = element.eClass().getName();
					switch(className) {
					«resource.printElementFactoryCaseStatements(true)»
					}
					return null;
				}
				«resource.printElementFactoryCreateOperations(true)»
				
			}			
			''')
    }
    
    def String semanticsElementFactoryClassFilePath() {
    	targetPath + "ValueFactory" + javaExtension
    }
        
    def String copyright() {
    	'''
		 /*
		 * Copyright (c) 2013 Vienna University of Technology.
		 * All rights reserved. This program and the accompanying materials are made 
		 * available under the terms of the Eclipse Public License v1.0 which accompanies 
		 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
		 * 
		 * Contributors:
		 * Philip Langer - initial API and generator
		 * Tanja Mayerhofer - generator
		 */
		 '''.toString
    }
    
    def String imports(boolean semantics) {
    	'''
		    	
		import javax.annotation.Generated;
		import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
		«IF !semantics»import org.modelexecution.fuml.convert.fuml.internal.IElementPopulator;«ELSE»import org.modelexecution.fuml.convert.fuml.internal.IValuePopulator;«ENDIF»
		«IF semantics»import org.modelexecution.fuml.convert.IValueConversionResult;«ENDIF»
		«IF semantics»import org.modelexecution.fuml.convert.IConversionResult;«ENDIF»
    	'''.toString
    }
    
    def String genAnnotation() {
    	'@Generated(value="Generated by org.modelexecution.fuml.convert.fuml.gen.ElementPopulatorGenerator.xtend")'
    }
    
    def String targetPath() {
    	"org/modelexecution/fuml/convert/fuml/internal/gen/"
    }
    
    def String javaExtension() {
    	".java"
    }
    
    def dispatch void compile(EObject m, IFileSystemAccess fsa) {
    	// no operation
    }  
	
}