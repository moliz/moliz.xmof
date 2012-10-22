package org.modelexecution.xmof.mmgenerator

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.EcoreUtil$UsageCrossReferencer
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext
import org.eclipse.emf.ecore.EStructuralFeature$Setting

class XMOFMetaModelGenerator implements IWorkflowComponent {
	
	val BASE_URI = "http://www.modelexecution.org"
	val BASE_PACKAGE_NAME = "xmof"
	
	val ECORE_PACKAGE = EcorePackage::eINSTANCE
	val E_MODEL_ELEMENT = ECORE_PACKAGE.EModelElement
	val E_NAMED_ELEMENT = ECORE_PACKAGE.ENamedElement
	val E_CLASSIFIER = ECORE_PACKAGE.EClassifier
	val E_CLASS = ECORE_PACKAGE.EClass
	val E_STRUCTURAL_FEATURE = ECORE_PACKAGE.EStructuralFeature
	
	IWorkflowContext context

	override invoke(IWorkflowContext ctx) {
		this.context = ctx
		var inputPackage = ctx.get("inputModel") as EPackage
		transform(inputPackage)
	}
	
	override postInvoke() {	}
	
	override preInvoke() { }
	
	def transform(EPackage ePackage) {
		ePackage.addXMOFClasses
		ePackage.renameAll
		ePackage.replaceClassesPackageWithEcore
		context.put("outputModel", ePackage)
	}
	
	def addXMOFClasses(EPackage rootPackage) {
		// TODO add BehavioredEOperation
		// TODO add BehavioredEClass
		// TODO add MainEClass
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
		rootPackage.umlElement.replaceWith(E_MODEL_ELEMENT)
		rootPackage.umlNamedElement.replaceWith(E_NAMED_ELEMENT)
		rootPackage.umlClassifier.replaceWith(E_CLASSIFIER)
		rootPackage.umlClass.replaceWith(E_CLASS)
		rootPackage.umlStructuralFeature.replaceWith(E_STRUCTURAL_FEATURE)
		EcoreUtil::remove(rootPackage.syntaxClassesKernel)
	}
	
	def replaceWith(EClass originalClass, EClass replacementClass) {
		var settings = EcoreUtil$UsageCrossReferencer::find(originalClass, originalClass.eResource);
		for (Setting setting : settings) {
			if (setting.EStructuralFeature.changeable) {
				EcoreUtil::replace(setting, originalClass, replacementClass)
			}
		}
	}
	
	def umlElement(EPackage rootPackage) {
		rootPackage.syntaxClassesKernel.getEClassifier("Element") as EClass
	}
	
	def umlNamedElement(EPackage rootPackage) {
		rootPackage.syntaxClassesKernel.getEClassifier("NamedElement") as EClass
	}
	
	def umlClassifier(EPackage rootPackage) {
		rootPackage.syntaxClassesKernel.getEClassifier("Classifier") as EClass
	}
	
	def EClass umlClass(EPackage rootPackage) {
		rootPackage.syntaxClassesKernel.getEClassifier("Class") as EClass
	}
	
	def EClass umlStructuralFeature(EPackage rootPackage) {
		rootPackage.syntaxClassesKernel.getEClassifier("StructuralFeature") as EClass
	}
	
	def EPackage syntaxClassesKernel(EPackage rootPackage) {
		return rootPackage.getSubPackagebyName("Syntax").getSubPackagebyName("Classes").getSubPackagebyName("Kernel")
	}
	
	def EPackage getSubPackagebyName(EPackage ePackage, String name) {
		return ePackage.ESubpackages.getByName(name)
	}
	
	def EPackage getByName(EList<EPackage> packageList, String name) {
		for (ePackage : packageList) {
			if (ePackage.name.equals(name)) return ePackage
		}
		return null
	}
	
}