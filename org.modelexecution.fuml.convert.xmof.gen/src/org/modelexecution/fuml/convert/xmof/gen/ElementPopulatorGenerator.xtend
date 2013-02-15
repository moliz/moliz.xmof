/*
* Copyright (c) 2012 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API and generator
*/
package org.modelexecution.fuml.convert.xmof.gen

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
import org.eclipse.xtext.xbase.lib.Pair
import org.eclipse.emf.ecore.EcorePackage
import org.modelexecution.xmof.Syntax.Classes.Kernel.KernelPackage
import org.modelexecution.xmof.Syntax.Actions.BasicActions.BasicActionsPackage
import org.modelexecution.xmof.Syntax.Actions.IntermediateActions.IntermediateActionsPackage
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage

class ElementPopulatorGenerator implements IGenerator {
	
	val ECORE_PACKAGE = EcorePackage::eINSTANCE
	val E_MODEL_ELEMENT = ECORE_PACKAGE.EModelElement
	val E_NAMED_ELEMENT = ECORE_PACKAGE.ENamedElement
	val E_TYPED_ELEMENT = ECORE_PACKAGE.ETypedElement
	val E_DATA_TYPE = ECORE_PACKAGE.EDataType
	val E_CLASSIFIER = ECORE_PACKAGE.EClassifier
	val E_CLASS = ECORE_PACKAGE.EClass
	val E_REFERENCE = ECORE_PACKAGE.EReference
	val E_STRUCTURAL_FEATURE = ECORE_PACKAGE.EStructuralFeature
	val E_PARAMETER = ECORE_PACKAGE.EParameter
	val E_ATTRIBUTE = ECORE_PACKAGE.EAttribute
	val E_ENUMERATION = ECORE_PACKAGE.EEnum
	val E_ENUMERATION_LITERAL = ECORE_PACKAGE.EEnumLiteral
	val BEHAVIOURED_E_OPERATION = KernelPackage::eINSTANCE.behavioredEOperation
	val DIRECTED_PARAMTER = KernelPackage::eINSTANCE.directedParameter
	
	val CALL_OPERATION_ACTION_REF_OPERATION = BasicActionsPackage::eINSTANCE.callOperationAction_Operation
	val LINK_END_DATA_REF_END = IntermediateActionsPackage::eINSTANCE.linkEndData_End
	val BEHAVIOR_REF_SPECIFICATION = BasicBehaviorsPackage::eINSTANCE.behavior_Specification
	val BEHAVIOR_REF_OWNED_PARAMETER = BasicBehaviorsPackage::eINSTANCE.behavior_OwnedParameter
	val ACTIVITY_PARA_NODE_REF_PARAMETER = IntermediateActivitiesPackage::eINSTANCE.activityParameterNode_Parameter
	
	/* Saves the names of generated populator classes */
	List<String> classNames
	
	/* Saves the Ecore classes that are used in xMOF instead of fUML classes */
	var replacedClasses = newHashMap(
		Pair::of(E_MODEL_ELEMENT.qualifiedName, "fUML.Syntax.Classes.Kernel.Element"),
		Pair::of(E_NAMED_ELEMENT.qualifiedName, "fUML.Syntax.Classes.Kernel.NamedElement"),
		Pair::of(E_TYPED_ELEMENT.qualifiedName, "fUML.Syntax.Classes.Kernel.TypedElement"),
		Pair::of(E_DATA_TYPE.qualifiedName, "fUML.Syntax.Classes.Kernel.DataType"),
		Pair::of(E_CLASSIFIER.qualifiedName, "fUML.Syntax.Classes.Kernel.Classifier"),
		Pair::of(E_CLASS.qualifiedName, "fUML.Syntax.Classes.Kernel.Class_"),
		Pair::of(E_REFERENCE.qualifiedName, "fUML.Syntax.Classes.Kernel.Association"),
		Pair::of(E_STRUCTURAL_FEATURE.qualifiedName, "fUML.Syntax.Classes.Kernel.StructuralFeature"),
		Pair::of(DIRECTED_PARAMTER.qualifiedName, "fUML.Syntax.Classes.Kernel.Parameter"),
		Pair::of(E_PARAMETER.qualifiedName, "fUML.Syntax.Classes.Kernel.Parameter"),
		Pair::of(BEHAVIOURED_E_OPERATION.qualifiedName, "fUML.Syntax.Classes.Kernel.BehavioralFeature"),
		Pair::of(E_ENUMERATION.qualifiedName, "fUML.Syntax.Classes.Kernel.Enumeration"),
		Pair::of(E_ENUMERATION_LITERAL.qualifiedName, "fUML.Syntax.Classes.Kernel.EnumerationLiteral"),
		Pair::of(E_ATTRIBUTE.qualifiedName, "fUML.Syntax.Classes.Kernel.Property")
	)
	
	/* Saves references that have a more specialized type mapping */
	var specializedReferenceTypes = newHashMap(
		Pair::of(CALL_OPERATION_ACTION_REF_OPERATION.qualifiedName, "fUML.Syntax.Classes.Kernel.Operation"),
		Pair::of(LINK_END_DATA_REF_END.qualifiedName, "fUML.Syntax.Classes.Kernel.Property"),
		Pair::of(BEHAVIOR_REF_SPECIFICATION.qualifiedName, "fUML.Syntax.Classes.Kernel.Operation"),
		Pair::of(BEHAVIOR_REF_OWNED_PARAMETER.qualifiedName, "fUML.Syntax.Classes.Kernel.Parameter"),
		Pair::of(ACTIVITY_PARA_NODE_REF_PARAMETER.qualifiedName, "fUML.Syntax.Classes.Kernel.Parameter")
	)
		
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
    	// special XMOF classes and Ecore classes are handled separately
    	if (eClass.name.equals("BehavioredEOperation") || eClass.name.equals("DirectedParameter")) return;
    	
    	if (eClass.hasFeatures) {
    	
    	classNames.add(eClass.populatorClassName)
    	
    	fsa.generateFile(eClass.populatorClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.xmof.internal.gen;
			«imports»
			«IF "StructuralFeatureAction".equals(eClass.name)»
				import fUML.Syntax.Classes.Kernel.Property;
				import fUML.Syntax.Classes.Kernel.PropertyList;
				import fUML.Syntax.Classes.Kernel.StructuralFeature;
			«ENDIF»
			
			«genAnnotation»
			public class «eClass.populatorClassName» implements IElementPopulator {
			
				@Override
				public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
					org.eclipse.emf.ecore.EModelElement element, ConversionResultImpl result) {
						
					if (!(element instanceof «eClass.qualifiedName») ||
						!(fumlElement instanceof «eClass.qualifiedNameFUML»)) {
						return;
					}
					
					«eClass.qualifiedNameFUML» «fumlElementVar» = («eClass.qualifiedNameFUML») fumlElement;
					«eClass.qualifiedName» «xmofElementVar» = («eClass.qualifiedName») element;
					
					«FOR feature : eClass.EStructuralFeatures»
					«feature.printAssingment»
					«ENDFOR»
					
				}
				
				«FOR feature : eClass.EStructuralFeatures»
					«IF feature.getEType instanceof EEnum»
					«feature.printCastMethod»
					«ENDIF»
				«ENDFOR»
				«IF "StructuralFeatureAction".equals(eClass.name)»
					«getMemberEndByNameMethod»
				«ENDIF»
			}
			''')
			
		}
    }
    
	def getMemberEndByNameMethod() '''
		private StructuralFeature getMemberEndByName(PropertyList memberEnd, String name) {
			for (Property property : memberEnd) {
				if (property.name.equals(name)) {
					return property;
				}
			}
			return null;
		}'''
    
	def boolean hasFeatures(EClass eClass) {
		return !eClass.EStructuralFeatures.empty
	}
    
    def String populatorClassFilePath(EClass eClass) {
    	targetPath + eClass.populatorClassName + javaExtension
    }
    
    def String populatorClassName(EClass eClass) {
    	eClass.name + "Populator"
    }
    
    def String qualifiedName(EClassifier eClassifier) {
    	eClassifier.getEPackage.prefix + eClassifier.getEPackage.qualifiedName + "." + eClassifier.name
    }
    
	def prefix(EPackage ePackage) {
		if (ePackage.name.equals("ecore")) {
			return "org.eclipse.emf."
		}
		return "org.modelexecution."
	}
	
	def qualifiedTypeNameFUML(EReference reference) {
		if (specializedReferenceTypes.containsKey(reference.qualifiedName)) {
			return specializedReferenceTypes.get(reference.qualifiedName)
		} else {
			reference.EType.qualifiedNameFUML
		}
	}

    
    def String qualifiedNameFUML(EClassifier eClassifier) {
    	if (replacedClasses.containsKey(eClassifier.qualifiedName)) {
    		replacedClasses.get(eClassifier.qualifiedName)
    	} else {
    		eClassifier.getEPackage.qualifiedName.replaceFirst("xmof", "fUML") + "." + eClassifier.name.maskNameFUML
    	}
    }
    
    def String qualifiedName(EReference eReference) {
    	return eReference.EContainingClass.name + "." + eReference.name
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
    
    def dispatch String printAssingment(EAttribute attribute) {
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
    		assignment = '''«assignment»«attribute.castEnumMethodName»(«xmofElementVar».«attribute.getter»)'''.toString
    	} else {
    		assignment = '''«assignment»«xmofElementVar».«attribute.getter»'''.toString
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
    	if (reference.isMany)
    		return reference.printMultiValuedAssingment
    	
    	if (reference.assignmentName.equals("structuralFeature"))
    		return printAssignmentForStructuralFeatureActionPopulator(reference)
    	
    	return '''«fumlElementVar».«reference.assignmentName» = («reference.qualifiedTypeNameFUML») result
					.getFUMLElement(«xmofElementVar».«reference.getter»);'''.toString
    }
    
	def String printAssignmentForStructuralFeatureActionPopulator(EReference reference) {
		return '''
		fUML.Syntax.Classes.Kernel.StructuralFeature fumlStructuralFeature;
		if (xmofElement.getStructuralFeature() instanceof org.eclipse.emf.ecore.EReference) {
			fUML.Syntax.Classes.Kernel.Association fumlAssociation = (fUML.Syntax.Classes.Kernel.Association) result
					.getFUMLElement(xmofElement.getStructuralFeature());
			fumlStructuralFeature = getMemberEndByName(fumlAssociation.memberEnd, xmofElement.getStructuralFeature().getName());
		} else {
			fumlStructuralFeature = (fUML.Syntax.Classes.Kernel.StructuralFeature) result
					.getFUMLElement(xmofElement.getStructuralFeature());
		}
		fumlNamedElement.structuralFeature = fumlStructuralFeature;
		'''
	}

    
    def dispatch String printMultiValuedAssingment(EAttribute attribute) {
    	'''

    	for («attribute.getEType.instanceClassName» value : «xmofElementVar».«attribute.getterPlural») {
    		«fumlElementVar».«attribute.name.maskNameFUML».add(value);
    	}

'''.toString
    }
    
    def dispatch String printMultiValuedAssingment(EReference reference) {
    	'''
    	
    	for («reference.getEType.qualifiedName» value : «xmofElementVar».«reference.getterPlural») {
			«fumlElementVar».«reference.name.maskNameFUML».add((«reference.qualifiedTypeNameFUML») result.getFUMLElement(value));
    	}

'''.toString
    }
    
    def String getterPlural(EStructuralFeature feature) {
    	var name = feature.getMappedName
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
    	if (feature.name == "upper") {
    		return "upper.naturalValue"
    	} else {
    		if (feature.EType.name == "EBoolean") {
    			if (feature.name == "mustIsolate" || feature.name == "value") {
    				feature.name
    			} else {
    				return "is" + feature.name.maskNameFUML.toFirstUpper
    			}
    		} else {
    			return feature.name.maskNameFUML
    		}
    	}
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
    	if (feature instanceof EAttribute) {
    		return feature.printCastMethod
    	} else {
    		return ""
    	}
    }
    
    def dispatch String printCastMethod(EAttribute attribute) {
    	'''
    	private «attribute.getEType.qualifiedNameFUML» «attribute.castEnumMethodName»(«attribute.getEType.qualifiedName» enumVal) {
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
    	case «eEnum.qualifiedName».«enumLiteral.getLiteral.toUpperCase»_VALUE:
    		return «eEnum.qualifiedNameFUML».«enumLiteral.name.maskNameFUML»;
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
    
    def String xmofElementVar() {
    	"xmofElement"
    }
    
    def String fumlElementVar() {
    	"fumlNamedElement"
    }
    
    def generatePopulatorSuiteClass(IFileSystemAccess fsa) {
    	fsa.generateFile(populatorSuiteClassFilePath, '''
			«copyright»
			package org.modelexecution.fuml.convert.xmof.internal.gen;
			«imports»
			import java.util.Collection;
			import java.util.ArrayList;
			import org.modelexecution.fuml.convert.xmof.internal.ecore.*;
			
			«genAnnotation»
			public class ElementPopulatorSuite {

				private Collection<IElementPopulator> elementPopulators = new ArrayList<>();
			
				private ConversionResultImpl result;
			
				public ElementPopulatorSuite(ConversionResultImpl result) {
					this.result = result;
					initializePopulators();
				}
			
				private void initializePopulators() {
					elementPopulators.add(new ClassAndAssociationPopulator());
			    	elementPopulators.add(new NamedElementPopulator());
			    	elementPopulators.add(new EnumerationPopulator());
			    	elementPopulators.add(new EnumerationLiteralPopulator());
			    	elementPopulators.add(new TypedElementPopulator());
			    	elementPopulators.add(new MultiplicityElementPopulator());
			    	elementPopulators.add(new StructuralFeaturePopulator());
			    	elementPopulators.add(new DirectedParameterPopulator());
			    	elementPopulators.add(new OperationPopulator());
			    	elementPopulators.add(new PackagePopulator());
					«FOR className : classNames»
					elementPopulators.add(new «className»());
				    «ENDFOR»
				}
			
				public void populate(fUML.Syntax.Classes.Kernel.Element «fumlElementVar»,
						org.eclipse.emf.ecore.EModelElement «xmofElementVar») {
					for (IElementPopulator populator : elementPopulators) {
						populator.populate(«fumlElementVar», «xmofElementVar», result);
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
		import org.modelexecution.fuml.convert.xmof.internal.IElementPopulator;
    	'''.toString
    }
    
    def String genAnnotation() {
    	'@Generated(value="Generated by org.modelexecution.fuml.convert.xmof.gen.ElementPopulatorGenerator.xtend")'
    }
    
    def String targetPath() {
    	"org/modelexecution/fuml/convert/xmof/internal/gen/"
    }
    
    def String javaExtension() {
    	".java"
    }
    
    def dispatch void compile(EObject m, IFileSystemAccess fsa) {
    	// no operation
    }
	
}