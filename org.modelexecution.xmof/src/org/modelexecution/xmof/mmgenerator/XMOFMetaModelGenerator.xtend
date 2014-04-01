package org.modelexecution.xmof.mmgenerator

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature$Setting
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.EcoreUtil$UsageCrossReferencer
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.common.util.BasicEList

class XMOFMetaModelGenerator implements IWorkflowComponent {
	EClass directedParameterClass
	EEnum parameterDirectionKind
	EPackage ownKernelPackage
	
	EClass behavioredEOperation
	EClass behavioredEClass
	EPackage rootPackage
	
	EClass eEnumLiteralSpecification
	
	val BASE_URI = "http://www.modelexecution.org"
	val BASE_PACKAGE_NAME = "xmof"
	
	val ECORE_PACKAGE = EcorePackage::eINSTANCE
	val E_MODEL_ELEMENT = ECORE_PACKAGE.EModelElement
	val E_NAMED_ELEMENT = ECORE_PACKAGE.ENamedElement
	val E_TYPED_ELEMENT = ECORE_PACKAGE.ETypedElement
	val E_DATA_TYPE = ECORE_PACKAGE.EDataType
	val E_CLASSIFIER = ECORE_PACKAGE.EClassifier
	val E_CLASS = ECORE_PACKAGE.EClass
	val E_REFERENCE = ECORE_PACKAGE.EReference
	val E_STRUCTURAL_FEATURE = ECORE_PACKAGE.EStructuralFeature
	val E_OPERATION = ECORE_PACKAGE.EOperation
	val E_PARAMETER = ECORE_PACKAGE.EParameter
	val E_ENUMERATION = ECORE_PACKAGE.EEnum
	val E_ENUMERATION_LITERAL = ECORE_PACKAGE.EEnumLiteral
	val E_ATTRIBUTE = ECORE_PACKAGE.EAttribute
	val E_OBJECT = ECORE_PACKAGE.EObject
	
	IWorkflowContext context

	override invoke(IWorkflowContext ctx) {
		this.context = ctx
		rootPackage = ctx.get("inputModel") as EPackage
		transform
	}
	
	override postInvoke() {	}
	
	override preInvoke() { }
	
	def transform() {
		rootPackage.createOwnKernelPackage
		rootPackage.addXMOFClasses
		rootPackage.renameAll
		rootPackage.replaceClassesPackageWithEcore
		rootPackage.removeUnnecessarySemanticsClasses	
		rootPackage.addXMOFSemanticsClasses
		rootPackage.removeEmptyPackages;		
		ownKernelPackage.name = "Kernel"
		context.put("outputModel", rootPackage)
	}
	

	def createOwnKernelPackage(EPackage rootPackage) {
		ownKernelPackage = EcoreFactory::eINSTANCE.createEPackage
		ownKernelPackage.name = "myKernel"
		ownKernelPackage.nsPrefix = "kernel"
		ownKernelPackage.nsURI = "http://www.modelexecution.org/xmof/syntax/classes/kernel"
		syntaxClasses.ESubpackages.add(ownKernelPackage)
	}

	
	def addXMOFClasses(EPackage rootPackage) {
		behavioredEOperation = createBehavioredEOperation()
		behavioredEClass = createBehavioredEClass()
		ownKernelPackage.EClassifiers.add(behavioredEOperation)
		ownKernelPackage.EClassifiers.add(behavioredEClass)
		ownKernelPackage.EClassifiers.add(createParameterDirectionKind)
		ownKernelPackage.EClassifiers.add(createDirectedParameter)
		ownKernelPackage.EClassifiers.add(createEEnumLiteralSpecification)
		ownKernelPackage.EClassifiers.add(createEnumValue)
	}	
	
	def EClass createBehavioredEOperation() {
		var behavioredEOperationClass = EcoreFactory::eINSTANCE.createEClass
		behavioredEOperationClass.name = "BehavioredEOperation"
		behavioredEOperationClass.ESuperTypes.add(E_OPERATION)
		behavioredEOperationClass.EStructuralFeatures.add(createMethodReference())
		return behavioredEOperationClass
	}
	
	def EReference createMethodReference() {
		var reference = EcoreFactory::eINSTANCE.createEReference
		reference.name = "method"
		reference.lowerBound = 0
		reference.upperBound = -1
		reference.containment = false
		reference.EType = umlBehavior
		return reference
	}
	
	def EClass createBehavioredEClass() {
		var behavioredEOperationClass = EcoreFactory::eINSTANCE.createEClass
		behavioredEOperationClass.name = "BehavioredEClass"
		behavioredEOperationClass.ESuperTypes.add(E_CLASS)
		behavioredEOperationClass.ESuperTypes.add(umlBehavioredClassifier)
		return behavioredEOperationClass
	}
	
	def EClassifier createParameterDirectionKind() {
		parameterDirectionKind = EcoreFactory::eINSTANCE.createEEnum
		parameterDirectionKind.name = "ParameterDirectionKind"
		parameterDirectionKind.ELiterals.add(createParameterDiectionKindLiteral("in", 0))
		parameterDirectionKind.ELiterals.add(createParameterDiectionKindLiteral("out", 1))
		parameterDirectionKind.ELiterals.add(createParameterDiectionKindLiteral("inout", 2))
		parameterDirectionKind.ELiterals.add(createParameterDiectionKindLiteral("return", 3))
		return parameterDirectionKind
	}
	
	def EEnumLiteral createParameterDiectionKindLiteral(String name, int value) {
		var literal = EcoreFactory::eINSTANCE.createEEnumLiteral
		literal.literal = name.toUpperCase
		literal.name = name
		literal.value = value
		return literal
	}
	
	def EClass createDirectedParameter() {
		directedParameterClass = EcoreFactory::eINSTANCE.createEClass
		directedParameterClass.name = "DirectedParameter"
		directedParameterClass.ESuperTypes.add(E_PARAMETER)
		directedParameterClass.EStructuralFeatures.add(createParameterDirectionAttribute)
		return directedParameterClass
	}	
	
	def EAttribute createParameterDirectionAttribute() {
		var parameterDirectionAttribute = EcoreFactory::eINSTANCE.createEAttribute
		parameterDirectionAttribute.name = "direction"
		parameterDirectionAttribute.lowerBound = 1
		parameterDirectionAttribute.upperBound = 1
		parameterDirectionAttribute.EType = parameterDirectionKind
		return parameterDirectionAttribute
	}
	
	def createEEnumLiteralSpecification() {
		eEnumLiteralSpecification = EcoreFactory::eINSTANCE.createEClass
		eEnumLiteralSpecification.name = "EEnumLiteralSpecification"
		eEnumLiteralSpecification.ESuperTypes.add(umlInstanceSpecification)
		eEnumLiteralSpecification.EStructuralFeatures.add(createEEnumLiteralReference)
		return eEnumLiteralSpecification
	}
	
	def createEEnumLiteralReference() {
		var eEnumLiteralReference = EcoreFactory::eINSTANCE.createEReference
		eEnumLiteralReference.name = "eEnumLiteral"
		eEnumLiteralReference.EType = E_ENUMERATION_LITERAL
		eEnumLiteralReference.lowerBound = 1
		eEnumLiteralReference.upperBound = 1
		return eEnumLiteralReference
	}
	
	def createEnumValue() {
		var enumValue = EcoreFactory::eINSTANCE.createEClass
		enumValue.name = "EnumValue"
		enumValue.ESuperTypes.add(umlValueSpecification)
		enumValue.EStructuralFeatures.add(createEEnumLiteralSpecificationReference)
		return enumValue
	}
	
	def createEEnumLiteralSpecificationReference() {
		var eEnumLiteralSpecificationReference = EcoreFactory::eINSTANCE.createEReference
		eEnumLiteralSpecificationReference.name = "eEnumLiteralSpecification"
		eEnumLiteralSpecificationReference.EType = eEnumLiteralSpecification
		eEnumLiteralSpecificationReference.lowerBound = 1
		eEnumLiteralSpecificationReference.upperBound = 1
		eEnumLiteralSpecificationReference.containment = true
		return eEnumLiteralSpecificationReference
	}
	
	def EClass createObjectValue() {
		var objectValue = EcoreFactory::eINSTANCE.createEClass
		objectValue.name = "ObjectValue"
		objectValue.ESuperTypes.add(umlValue)
		objectValue.EStructuralFeatures.add(createEObjectReference)
		objectValue
	}
	
	def createEObjectReference() {
		var eObjectReference = EcoreFactory::eINSTANCE.createEReference
		eObjectReference.name = "eObject"
		eObjectReference.EType = E_OBJECT
		eObjectReference.lowerBound = 1
		eObjectReference.upperBound = 1
		return eObjectReference
	}
	
	def EClass createParameterValueDefinition() {
		var objectValue = EcoreFactory::eINSTANCE.createEClass
		objectValue.name = "ParameterValueDefinition"
		objectValue.EStructuralFeatures.add(createParameterValuesReference)
		objectValue
	}
	
	def createParameterValuesReference() {
		var eParameterValueReference = EcoreFactory::eINSTANCE.createEReference
		eParameterValueReference.name = "parameterValues"
		eParameterValueReference.EType = umlParameterValue
		eParameterValueReference.lowerBound = 0
		eParameterValueReference.upperBound = -1
		eParameterValueReference.containment = true;
		return eParameterValueReference
	}
	
	def renameAll(EPackage rootPackage) {
		rootPackage.name = BASE_PACKAGE_NAME
		rootPackage.nsPrefix = BASE_PACKAGE_NAME
		rootPackage.nsURI = BASE_URI + "/" + BASE_PACKAGE_NAME
		for (childPackage : rootPackage.ESubpackages) childPackage.rename
	}
	
	def void rename(EPackage ePackage) {
		ePackage.nsURI = BASE_URI + ePackage.computePackageName.toLowerCase
		for (child : ePackage.ESubpackages) child.rename
	}
	
	def String computePackageName(EPackage ePackage) {
		if (ePackage.ESuperPackage != null) {
			return ePackage.ESuperPackage.computePackageName + "/" + ePackage.name
		} else {
			return "/" + ePackage.name
		}
	}
	
	def replaceClassesPackageWithEcore(EPackage rootPackage) {
		// replacements of references to fUML kernel classes to references to Ecore Classes
		umlElement.replaceWith(E_MODEL_ELEMENT)
		umlNamedElement.replaceWith(E_NAMED_ELEMENT)
		umlPackageableElement.replaceWith(E_NAMED_ELEMENT)
		umlTypedElement.replaceWith(E_TYPED_ELEMENT)
		umlMultiplicityElement.replaceWith(E_TYPED_ELEMENT)
		umlDataType.replaceWith(E_DATA_TYPE)
		umlRedefinableElement.replaceWith(E_CLASSIFIER)
		umlClassifier.replaceWith(E_CLASSIFIER)
		umlClass.replaceWith(behavioredEClass)
		umlStructuralFeature.replaceWith(E_STRUCTURAL_FEATURE)
		umlOperation.replaceWith(behavioredEOperation)
		umlParameter.replaceWith(directedParameterClass)
		umlProperty.replaceWith(E_REFERENCE)
		umlAssociation.replaceWith(E_REFERENCE)
		umlEnumeration.replaceWith(E_ENUMERATION)
		umlEnumerationLiteral.replaceWith(E_ENUMERATION_LITERAL)
		umlBehavioralFeature.replaceWith(behavioredEOperation)
		
		// turn data types of LiteralSpecifications into something more specific
		umlLiteralBoolean.value.EType = ECORE_PACKAGE.EBoolean
		umlLiteralInteger.value.EType = ECORE_PACKAGE.EInt
		umlLiteralString.value.EType = ECORE_PACKAGE.EString
		umlLiteralUnlimitedNatural.value.EType = ECORE_PACKAGE.EInt
		
		// replace wrong reference to EReference into EAttribute
		umlSignal.getEStructuralFeature("ownedAttribute").EType = E_ATTRIBUTE
		
		// change super type of ActivityNode and ActivityEdge to ENamedElement
		umlActivityNode.ESuperTypes.clear
		umlActivityNode.ESuperTypes.add(E_NAMED_ELEMENT)
		umlActivityEdge.ESuperTypes.clear
		umlActivityEdge.ESuperTypes.add(E_NAMED_ELEMENT)
		
		// retained fUML kernel classes that are moved to our kernel package
		umlValueSpecification.moveToOwnKernelPackage
		umlInstanceSpecification.moveToOwnKernelPackage
		umlSlot.moveToOwnKernelPackage
		umlInstanceValue.moveToOwnKernelPackage
		umlLiteralBoolean.moveToOwnKernelPackage
		umlLiteralSpecification.moveToOwnKernelPackage
		umlLiteralInteger.moveToOwnKernelPackage
		umlLiteralNull.moveToOwnKernelPackage
		umlLiteralString.moveToOwnKernelPackage
		umlLiteralUnlimitedNatural.moveToOwnKernelPackage
		umlPrimitiveType.moveToOwnKernelPackage		
		
		// changes in opposite relationships
		umlBehaviorSpecification.EOpposite = behavioredEOperation.methodReference
		behavioredEOperation.methodReference.EOpposite = umlBehaviorSpecification
		
		// set Action.input and Action.output to derived
		umlAction.getEStructuralFeature("input").setToDerived
		umlAction.getEStructuralFeature("output").setToDerived
		
		// remove the syntax/classes/kernel package (it is replaced by the Ecore package)
		syntaxClassesKernel.remove
	}	
	
	def value(EClass literalSpecification) {
		literalSpecification.EStructuralFeatures.byName("value") as EAttribute
	}
	
	def replaceWith(EClass originalClass, EClass replacementClass) {
		var settings = EcoreUtil$UsageCrossReferencer::find(originalClass, originalClass.eResource);
		for (Setting setting : settings) {
			if (setting.EStructuralFeature.changeable && setting.notContains(replacementClass)) {
				EcoreUtil::replace(setting, originalClass, replacementClass)
			}
		}
	}
	
	def boolean notContains(Setting setting, EObject eClass) {
		var value = setting.get(true)
		if (value instanceof EList<?>) {
			var list = value as EList<?>
			return !list.contains(eClass)
		} else {
			return value != eClass
		}
	}
	
	def moveToOwnKernelPackage(EClass eClass) {
		ownKernelPackage.EClassifiers.add(eClass)
	}
	
	def removeUnnecessarySemanticsClasses(EPackage rootPackage) {
		var EList<EClass> umlSemanticsEClassesToRemove = new BasicEList<EClass>(umlSemanticsEClasses(semantics));
		umlSemanticsEClassesToRemove.removeAll(umlSemanticsEClassesToKeep);		
		for(EClass eClass : umlSemanticsEClassesToRemove) {
			eClass.EPackage.EClassifiers.remove(eClass);
		} 
	}	
	
	def void removeEmptyPackages(EPackage ePackage) {
		for(EPackage subPackage : new BasicEList<EPackage>(ePackage.ESubpackages)) {
			subPackage.removeEmptyPackages
		}
		if(ePackage.EClassifiers.empty && ePackage.ESubpackages.empty) {
			ePackage.ESuperPackage.ESubpackages.remove(ePackage);
		}
	}
	
	def void addXMOFSemanticsClasses(EPackage rootPackage) {
		semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").EClassifiers.add(createObjectValue);
		semantics.getSubPackageByName("CommonBehaviors").getSubPackageByName("BasicBehaviors").EClassifiers.add(createParameterValueDefinition);
	}
	
	def EClass umlSignal() {
		syntax.getSubPackageByName("CommonBehaviors").getSubPackageByName("Communications").getEClassifier("Signal") as EClass
	}
	
	def umlElement() {
		syntaxClassesKernel.getEClassifier("Element") as EClass
	}
	
	def umlNamedElement() {
		syntaxClassesKernel.getEClassifier("NamedElement") as EClass
	}
	
	def umlPackageableElement() {
		syntaxClassesKernel.getEClassifier("PackageableElement") as EClass
	}
	
	def umlTypedElement() {
		syntaxClassesKernel.getEClassifier("TypedElement") as EClass
	}
	
	def umlDataType() {
		syntaxClassesKernel.getEClassifier("DataType") as EClass
	}
	
	def umlRedefinableElement() {
		syntaxClassesKernel.getEClassifier("RedefinableElement") as EClass
	}
	
	def umlBehavior() {
		syntaxCommonBehaviorsBasicBehaviors.getEClassifier("Behavior") as EClass
	}
	
	def umlBehavioredClassifier() {
		syntaxCommonBehaviorsBasicBehaviors.getEClassifier("BehavioredClassifier") as EClass
	}
	
	def umlClassifier() {
		syntaxClassesKernel.getEClassifier("Classifier") as EClass
	}
	
	def EClass umlClass() {
		syntaxClassesKernel.getEClassifier("Class") as EClass
	}
	
	def EClass umlStructuralFeature() {
		syntaxClassesKernel.getEClassifier("StructuralFeature") as EClass
	}
	
	def umlOperation() {
		syntaxClassesKernel.getEClassifier("Operation") as EClass
	}
	
	def umlParameter() {
		syntaxClassesKernel.getEClassifier("Parameter") as EClass
	}
	
	def umlProperty() {
		syntaxClassesKernel.getEClassifier("Property") as EClass
	}
	
	def umlAssociation() {
		syntaxClassesKernel.getEClassifier("Association") as EClass
	}
	
	def umlMultiplicityElement() {
		syntaxClassesKernel.getEClassifier("MultiplicityElement") as EClass
	}
	
	def umlEnumerationLiteral() {
		syntaxClassesKernel.getEClassifier("EnumerationLiteral") as EClass
	}

	def umlEnumeration() {
		syntaxClassesKernel.getEClassifier("Enumeration") as EClass
	}
	
	def umlBehavioralFeature() {
		syntaxClassesKernel.getEClassifier("BehavioralFeature") as EClass
	}
	
	def umlValueSpecification() {
		syntaxClassesKernel.getEClassifier("ValueSpecification") as EClass
	}
	
	def umlInstanceSpecification() {
		syntaxClassesKernel.getEClassifier("InstanceSpecification") as EClass
	}
	
	def umlSlot() {
		syntaxClassesKernel.getEClassifier("Slot") as EClass
	}
	
	def umlInstanceValue() {
		syntaxClassesKernel.getEClassifier("InstanceValue") as EClass
	}
	
	def umlLiteralBoolean() {
		syntaxClassesKernel.getEClassifier("LiteralBoolean") as EClass
	}
	
	def umlLiteralSpecification() {
		syntaxClassesKernel.getEClassifier("LiteralSpecification") as EClass
	}
	
	def umlLiteralInteger() {
		syntaxClassesKernel.getEClassifier("LiteralInteger") as EClass
	}
	
	def umlLiteralNull() {
		syntaxClassesKernel.getEClassifier("LiteralNull") as EClass
	}

	def umlLiteralString() {
		syntaxClassesKernel.getEClassifier("LiteralString") as EClass
	}
	
	def umlLiteralUnlimitedNatural() {
		syntaxClassesKernel.getEClassifier("LiteralUnlimitedNatural") as EClass
	}
	
	def umlPrimitiveType() {
		syntaxClassesKernel.getEClassifier("PrimitiveType") as EClass
	}
	
	def EReference umlBehaviorSpecification() {
		umlBehavior.EStructuralFeatures.byName("specification") as EReference
	}
	
	def EReference methodReference(EClass behavioredEOperation) {
		behavioredEOperation.EStructuralFeatures.byName("method") as EReference
	}
	
	def EPackage syntax() {
		return rootPackage.getSubPackageByName("Syntax")
	}
	
	def EPackage syntaxCommonBehaviorsBasicBehaviors() {
		return syntax.getSubPackageByName("CommonBehaviors").getSubPackageByName("BasicBehaviors")
	}
	
	def EPackage syntaxClassesKernel() {
		return syntaxClasses.getSubPackageByName("Kernel")
	}
	
	def EPackage syntaxClasses() {
		return syntax.getSubPackageByName("Classes")
	}
	
	def EClass umlAction() {
		return syntax.getSubPackageByName("Actions").getSubPackageByName("BasicActions").getEClassifier("Action") as EClass
	}
	
	def EClass umlActivityEdge() {
		return syntax.getSubPackageByName("Activities").getSubPackageByName("IntermediateActivities").getEClassifier("ActivityEdge") as EClass
	}
	
	def EClass umlActivityNode() {
		return syntax.getSubPackageByName("Activities").getSubPackageByName("IntermediateActivities").getEClassifier("ActivityNode") as EClass
	}
	
	def EClass umlStringValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("StringValue") as EClass
	}
	
	def EClass umlIntegerValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("IntegerValue") as EClass
	}
	
	def EClass umlEnumerationValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("EnumerationValue") as EClass
	}
	
	def EClass umlBooleanValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("BooleanValue") as EClass
	}
	
	def EClass umlPrimitiveValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("PrimitiveValue") as EClass
	}
	
	def EClass umlValue() {
		return semantics.getSubPackageByName("Classes").getSubPackageByName("Kernel").getEClassifier("Value") as EClass
	}
	
	def EClass umlSemanticVisitor() {
		return semantics.getSubPackageByName("Loci").getSubPackageByName("LociL1").getEClassifier("SemanticVisitor") as EClass
	}
	
	def EClass umlParameterValue() {
		return semantics.getSubPackageByName("CommonBehaviors").getSubPackageByName("BasicBehaviors").getEClassifier("ParameterValue") as EClass
	}
	
	def EPackage semantics() {
		return rootPackage.getSubPackageByName("Semantics")
	}

	
	def EList<EClass> umlSemanticsEClassesToKeep() {
		var EList<EClass> semanticsEClasses = new BasicEList<EClass>();
		semanticsEClasses.add(umlStringValue);
		semanticsEClasses.add(umlIntegerValue);
		semanticsEClasses.add(umlEnumerationValue);
		semanticsEClasses.add(umlBooleanValue);
		semanticsEClasses.add(umlPrimitiveValue);
		semanticsEClasses.add(umlValue);
		semanticsEClasses.add(umlSemanticVisitor);
		semanticsEClasses.add(umlParameterValue);
		semanticsEClasses;
	}
	
	def EList<EClass> umlSemanticsEClasses(EPackage ePackage) {
		var EList<EClass> semanticsEClasses = new BasicEList<EClass>();
		for (EClassifier eClassifier : ePackage.EClassifiers) {
			if (eClassifier instanceof EClass) {
				semanticsEClasses.add(eClassifier as EClass);
			}
		}		
		for (EPackage subPackage : ePackage.ESubpackages) {
			semanticsEClasses.addAll(umlSemanticsEClasses(subPackage));
		}
		semanticsEClasses;
	}
	
	def EPackage getSubPackageByName(EPackage ePackage, String name) {
		return ePackage.ESubpackages.byName(name) as EPackage
	}
	
	def ENamedElement byName(EList<?> list, String name) {
		for (element : list) {
			if (element instanceof ENamedElement){
				var namedElement = element as ENamedElement
				if (namedElement.name.equals(name)) return namedElement
			}
		}
		return null
	}
	
	def remove(EPackage ePackage) { EcoreUtil::remove(ePackage) }
	
	def setToDerived(EStructuralFeature feature) {
		feature.changeable = false
		feature.transient = true
		feature.volatile = true
		feature.derived = true
	}
	
}