/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation;

/**
 * A model that conforms to an xMOF-based metamodel.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFBasedModel {

	protected final static String MAIN = "main";

	private List<EObject> modelElements;
	private List<ParameterValue> parameterValues;
	private List<EPackage> metamodelPackages = new ArrayList<EPackage>();
	private List<EObject> mainClassObjects = new ArrayList<EObject>();
	
	private EditingDomain editingDomain;

	public XMOFBasedModel(Collection<EObject> modelElements) {
		this(modelElements, (List<ParameterValue>)null);
	}
	
	public XMOFBasedModel(Collection<EObject> modelElements, EditingDomain editingDomain) {
		this(modelElements, (List<ParameterValue>)null, editingDomain);
	}
	
	public XMOFBasedModel(Collection<EObject> modelElements, List<ParameterValue> parameterValues) {
		initializeXMOFBasedModel(modelElements, parameterValues);
	}

	public XMOFBasedModel(Collection<EObject> modelElements, List<ParameterValue> parameterValues,
			EditingDomain editingDomain) {
		initializeXMOFBasedModel(modelElements, parameterValues);
		this.editingDomain = editingDomain;
	}

	private void initializeXMOFBasedModel(Collection<EObject> modelElements, List<ParameterValue> parameterValues) {
		setModelElements(modelElements);
		setParameterValues(parameterValues);
		obtainMetamodelPackagesAndMainClassObjects(modelElements);
	}

	/**
	 * @param parameterValues
	 */
	private void setParameterValues(List<ParameterValue> parameterValues) {
		this.parameterValues = new ArrayList<ParameterValue>();
		if(parameterValues != null)
			this.parameterValues.addAll(parameterValues);
	}

	private void setModelElements(Collection<EObject> modelElements) {
		this.modelElements = new ArrayList<EObject>(modelElements);
	}

	private void obtainMetamodelPackagesAndMainClassObjects(
			Collection<EObject> modelElements) {
		Assert.isTrue(modelElements.size() > 0,
				"Must contain at least one element");

		for (EObject modelElement : modelElements) {
			obtainMetamodelPackageAndMainClassObject(modelElement);
			for (TreeIterator<EObject> treeIterator = modelElement
					.eAllContents(); treeIterator.hasNext();) {
				EObject next = treeIterator.next();
				obtainMetamodelPackageAndMainClassObject(next);
			}
		}
	}

	private void obtainMetamodelPackageAndMainClassObject(EObject modelElement) {
		obtainMainClassObject(modelElement);
		obtainMetamodelPackage(modelElement.eClass().getEPackage());
	}

	private void obtainMetamodelPackage(EPackage ePackage) {
		EPackage rootPackage = getRootEPackage(ePackage);
		metamodelPackages.add(rootPackage);
	}

	private EPackage getRootEPackage(EPackage ePackage) {
		EPackage rootPackage = ePackage;
		while (rootPackage.getESuperPackage() != null)
			rootPackage = rootPackage.getESuperPackage();
		return rootPackage;
	}

	private void obtainMainClassObject(EObject modelElement) {
		if (hasMainOperation(modelElement)) {
			mainClassObjects.add(modelElement);
		}
	}

	private boolean hasMainOperation(EObject eObject) {
		EClass eClass = eObject.eClass();
		for(EOperation eOperation : eClass.getEAllOperations()) {
			if(eOperation instanceof BehavioredEOperation && eOperation.getName().equals(XMOFBasedModel.MAIN)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the root packages of the xMOF-based metamodel.
	 * 
	 * @return the root packages of the xMOF-based metamodel.
	 */
	public List<EPackage> getMetamodelPackages() {
		return Collections.unmodifiableList(metamodelPackages);
	}

	/**
	 * Returns the root elements of the model.
	 * 
	 * The model is an instance of the xMOF-based metamodel, which can be
	 * obtained from {@link #getMetamodelPackages()}.
	 * 
	 * @return the root elements of the model.
	 */
	public List<EObject> getModelElements() {
		return Collections.unmodifiableList(modelElements);
	}

	/**
	 * Returns the {@link MainEClass main class} objects.
	 * 
	 * That is, instances of instances of {@link MainEClass} in this model.
	 * 
	 * @return the {@link MainEClass main class} objects.
	 */
	public List<EObject> getMainEClassObjects() {
		return Collections.unmodifiableList(mainClassObjects);
	}

	/**
	 * Returns the editing domain which manages changes of this model.
	 * 
	 * @return the editing domain.
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * Returns the {@link Resource} containing this model.
	 * @return the model {@link Resource resource}.
	 */
	public Resource getModelResource() {
		return getModelElements().get(0).eResource();
	}

	/**
	 * Returns the defined parameter values
	 * @return the parameter values
	 */
	public List<ParameterValue> getParameterValues() {
		return Collections.unmodifiableList(parameterValues);
	}

}
