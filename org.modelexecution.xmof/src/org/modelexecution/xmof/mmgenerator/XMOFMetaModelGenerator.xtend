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

class XMOFMetaModelGenerator implements IWorkflowComponent {
	EClass directedParameterClass
	EEnum parameterDirectionKind
	EPackage ownKernelPackage
	
	EClass behavioredEOperation
	EClass behavioredEClass
	EPackage rootPackage
	
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
		var mainEClass = createMainEClass(behavioredEClass)		
		ownKernelPackage.EClassifiers.add(behavioredEOperation)
		ownKernelPackage.EClassifiers.add(behavioredEClass)
		ownKernelPackage.EClassifiers.add(mainEClass)
		ownKernelPackage.EClassifiers.add(createParameterDirectionKind)
		ownKernelPackage.EClassifiers.add(createDirectedParameter)
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
	
	def EClass createMainEClass(EClass behavioredEClass) {
		var behavioredEOperationClass = EcoreFactory::eINSTANCE.createEClass
		behavioredEOperationClass.name = "MainEClass"
		behavioredEOperationClass.ESuperTypes.add(behavioredEClass)
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
		directedParameterClass.EStructuralFeatures.add(parameterDirectionAttribute)
		return directedParameterClass
	}
	
	def EAttribute parameterDirectionAttribute() {
		var parameterDirectionAttribute = EcoreFactory::eINSTANCE.createEAttribute
		parameterDirectionAttribute.name = "direction"
		parameterDirectionAttribute.lowerBound = 1
		parameterDirectionAttribute.upperBound = 1
		parameterDirectionAttribute.EType = parameterDirectionKind
		return parameterDirectionAttribute
	}

	
	def renameAll(EPackage rootPackage) {
		rootPackage.name = BASE_PACKAGE_NAME
		rootPackage.nsPrefix = BASE_PACKAGE_NAME
		rootPackage.nsURI = BASE_URI + "/" + BASE_PACKAGE_NAME
		for (childPackage : rootPackage.ESubpackages) childPackage.rename
	}
	
	def rename(EPackage ePackage) {
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
		if (value instanceof EList) {
			var list = value as EList
			return !list.contains(eClass)
		} else {
			return value != eClass
		}
	}
	
	def moveToOwnKernelPackage(EClass eClass) {
		ownKernelPackage.EClassifiers.add(eClass)
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
	
	def EPackage getSubPackageByName(EPackage ePackage, String name) {
		return ePackage.ESubpackages.byName(name) as EPackage
	}
	
	def ENamedElement byName(EList list, String name) {
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