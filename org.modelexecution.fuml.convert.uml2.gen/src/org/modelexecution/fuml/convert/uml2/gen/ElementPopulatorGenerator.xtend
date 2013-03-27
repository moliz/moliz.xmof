package org.modelexecution.fuml.convert.uml2.gen

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

class ElementPopulatorGenerator implements IGenerator {
	
	/* Saves the names of generated populator classes */
	List<String> classNames
		
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		initializeClassNamesList()
		generatePopulatorClasses(resource, fsa)
		generatePopulatorSuiteClass(fsa)
    }
    
    def initializeClassNamesList() {
    	classNames = new ArrayList()
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
    	
    	classNames.add(eClass.populatorClassName)
    	
    	fsa.generateFile(eClass.populatorClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.uml2.internal.gen;
			«imports»
			
			«genAnnotation»
			public class «eClass.populatorClassName» implements IElementPopulator {
			
				@Override
				public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
					org.eclipse.uml2.uml.Element uml2Element, ConversionResultImpl result) {
						
					if (!(uml2Element instanceof org.eclipse.uml2.uml.«eClass.name») ||
						!(fumlElement instanceof «eClass.qualifiedNameFUML»)) {
						return;
					}
					
					«eClass.qualifiedNameFUML» «fumlElementVar» = («eClass.qualifiedNameFUML») fumlElement;
					org.eclipse.uml2.uml.«eClass.name» «uml2ElementVar» = (org.eclipse.uml2.uml.«eClass.name») uml2Element;
					
					«FOR feature : eClass.getEStructuralFeatures»
					«feature.printAssingment»
					«ENDFOR»
					
					«IF eClass.name.equals('Element')»
						for (org.eclipse.uml2.uml.Comment value : uml2NamedElement.getOwnedComments()) {
						        fUML.Syntax.Classes.Kernel.Comment comment = new fUML.Syntax.Classes.Kernel.Comment();
						        comment.body = value.getBody();
						        fumlNamedElement.ownedComment.add(comment);
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
    
	def ignoreClass(EClass eClass) {
		eClass.name.equals('Comment')
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
    		assignment = '''«assignment»«attribute.castEnumMethodName»(«uml2ElementVar».«attribute.getter»)'''.toString
    	} else {
    		assignment = '''«assignment»«uml2ElementVar».«attribute.getter»'''.toString
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
    	
    	return '''«fumlElementVar».«reference.assignmentName» = («reference.getEType.qualifiedNameFUML») result
					.getFUMLElement(«uml2ElementVar».«reference.getter»);'''.toString
    }
    
    def dispatch String printMultiValuedAssingment(EAttribute attribute) {
    	'''

    	for («attribute.getEType.instanceClassName» value : «uml2ElementVar».«attribute.getterPlural») {
    		«fumlElementVar».«attribute.name.maskNameFUML».add(value);
    	}

'''.toString
    }
    
    def dispatch String printMultiValuedAssingment(EReference reference) {
    	'''
    	
    	for (org.eclipse.uml2.uml.«reference.getEType.name» value : «uml2ElementVar».«reference.getterPlural») {
			«fumlElementVar».«reference.name.maskNameFUML».add((«reference.getEType.qualifiedNameFUML») result.getFUMLElement(value));
}

'''.toString
    }
    
    def String getterPlural(EStructuralFeature feature) {
    	var name = feature.getMappedName.pluralize
    	if (feature.getEType.getName() == "EBoolean") {
    		if (feature.getName().startsWith("is")) {
    			return name + "()"
    		} else {
    			return "is" + name.toFirstUpper + "()"	
    		}
    	} else {
    		return "get" + name.toFirstUpper + "()"
    	}
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
    	private «attribute.getEType.qualifiedNameFUML» «attribute.castEnumMethodName»(org.eclipse.uml2.uml.«attribute.getEType.name» enumVal) {
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
    	case org.eclipse.uml2.uml.«eEnum.getName».«enumLiteral.getLiteral.toUpperCase»:
    		return «eEnum.qualifiedName».«enumLiteral.name.maskNameFUML»;
    	«ENDFOR»
    	'''.toString
    }
    
    def String maskNameFUML(String name) {
    	if (name == "package" || name == "private" || name == "protected" ||
    		name == "public" || name == "return" || name == "Class" ||
    		name == "class"
    	) {
    		return '''«name»_'''.toString
    	} else {
    		return name
    	}
    }
    
    def String pluralize(String name) {
    	if (name.endsWith("s")) {
    		return '''«name»es'''.toString
    	} else if (name.endsWith("y")) {
    		return '''«name.substring(0, name.length - 1)»ies'''.toString
    	} else if (name.endsWith("Data")) {
    		return '''«name»'''.toString
    	} else {
    		return '''«name»s'''.toString
    	}
    }
    
    def boolean useSetter(EStructuralFeature feature) {
    	if (feature.getEType.getName() == "EBoolean" ||
    		feature.getName == "qualifiedName" ||
    		feature.getName == "lower" ||
    		feature.getName == "upper" ||
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
    
    def String uml2ElementVar() {
    	"uml2NamedElement"
    }
    
    def String fumlElementVar() {
    	"fumlNamedElement"
    }
    
    def generatePopulatorSuiteClass(IFileSystemAccess fsa) {
    	fsa.generateFile(populatorSuiteClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.uml2.internal.gen;
			«imports»
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
				«FOR className : classNames»
				elementPopulators.add(new «className»());
			    «ENDFOR»
				}
			
				public void populate(fUML.Syntax.Classes.Kernel.Element fUMLElement,
						org.eclipse.uml2.uml.Element uml2Element) {
					for (IElementPopulator populator : elementPopulators) {
						populator.populate(fUMLElement, uml2Element, result);
					}
				}
			
			}
			''')
    }
    
    def String populatorSuiteClassFilePath() {
    	targetPath + "ElementPopulatorSuite" + javaExtension
    }
    
    def String copyright() {
    	'''
		 /*
		 * Copyright (c) 2012 Vienna University of Technology.
		 * All rights reserved. This program and the accompanying materials are made 
		 * available under the terms of the Eclipse Public License v1.0 which accompanies 
		 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
		 * 
		 * Contributors:
		 * Philip Langer - initial API and generator
		 */
		 '''.toString
    }
    
    def String imports() {
    	'''
		    	
		import javax.annotation.Generated;
		import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
		import org.modelexecution.fuml.convert.uml2.internal.IElementPopulator;
    	'''.toString
    }
    
    def String genAnnotation() {
    	'@Generated(value="Generated by org.modelexecution.fuml.convert.uml2.gen.ElementPopulatorGenerator.xtend")'
    }
    
    def String targetPath() {
    	"org/modelexecution/fuml/convert/uml2/internal/gen/"
    }
    
    def String javaExtension() {
    	".java"
    }
    
    def dispatch void compile(EObject m, IFileSystemAccess fsa) {
    	// no operation
    }
	
}