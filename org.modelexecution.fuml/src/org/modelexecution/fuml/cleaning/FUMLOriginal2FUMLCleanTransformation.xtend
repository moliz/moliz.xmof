package org.modelexecution.fuml.cleaning

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.util.EcoreUtil

class FUMLOriginal2FUMLCleanTransformation implements IWorkflowComponent {
	
	IWorkflowContext context

	override invoke(IWorkflowContext ctx) {
		this.context = ctx
		var inputPackage = ctx.get("inputModel") as EPackage
		transform(inputPackage)
	}
	
	override postInvoke() {	}
	
	override preInvoke() { }
	
	def transform(EPackage ePackage) {
		for (child : ePackage.eContents) child.clean
		context.put("outputModel", ePackage)
	}
	
	def clean(EObject object) {
		switch (object) {
			EPackage : object.clean
			EClass : object.clean
		}
	}
	
	def clean(EPackage ePackage) {
		for (child : ePackage.eContents) child.clean
	}
	
	def clean(EClass eClass) {
		// remove all operations
		eClass.EOperations.clear
		val features = new BasicEList<EStructuralFeature> (eClass.EStructuralFeatures)
		for (feature : features) {
			// unset defaultValueLiteral
			feature.removeDefaultLiteralValue
			// remove duplicate features if inherited
			if (feature.isInhertiedDuplicate) {
			 	EcoreUtil::delete(feature)
			 }
		}
	}
	
	def removeDefaultLiteralValue(EStructuralFeature feature) {
		feature.defaultValueLiteral = null
	}
	
	def boolean isInhertiedDuplicate(EStructuralFeature feature) {
		var count = 0
		for (oneOfAllFeatures : feature.EContainingClass.EAllStructuralFeatures) {
			if (feature.name.equals(oneOfAllFeatures.name)) {
				count = count + 1
			}
		}
		return count > 1;
	}
	
}