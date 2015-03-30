/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.provider;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.uml2.uml.NamedElement;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.papyrus.util.PapyrusResourceUtil;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class PapyrusActivityProvider implements IActivityProvider {

	public static final String MODEL_TYPE_IDENTIFIER = "org.modelexecution.fumldebug.debugger.papyrus"; //$NON-NLS-1$

	private final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();

	private IFile iFile;
	private UmlModel umlModel;

	private IConversionResult conversionResult;

	protected PapyrusActivityProvider(IFile iFile) {
		this.iFile = iFile;
		loadModel(iFile);
		convertModel();
	}

	private void loadModel(IFile iFile) {
		umlModel = PapyrusResourceUtil.loadModel(createURI(iFile));
	}

	private URI createURI(IFile file) {
		return URI.createURI("platform:/resource/" //$NON-NLS-1$
				+ file.getProject().getName() + "/" //$NON-NLS-1$
				+ file.getProjectRelativePath());
	}

	private void convertModel() {
		NamedElement namedElement = PapyrusResourceUtil.obtainFirstNamedElement(umlModel);
		IConverter converter = converterRegistry.getConverter(namedElement);
		conversionResult = converter.convert(namedElement);
	}

	@Override
	public String getModelTypeIdentifier() {
		return MODEL_TYPE_IDENTIFIER;
	}

	@Override
	public IResource getResource() {
		return iFile;
	}

	@Override
	public Collection<Activity> getActivities() {
		return conversionResult.getActivities();
	}

	@Override
	public Activity getActivity(String name) {
		for (Activity activity : conversionResult.getAllActivities()) {
			if (equalsName(name, activity)) {
				return activity;
			}
		}
		return null;
	}

	private boolean equalsName(String name, Activity activity) {
		return name.equals(activity.name)
				|| name.equals(activity.qualifiedName);
	}

	@Override
	public String getSourceFileName(
			fUML.Syntax.Classes.Kernel.NamedElement namedElement) {
		return iFile.getName();
	}

	@Override
	public void unload() {
		umlModel.getResource().unload();
	}

	public IConversionResult getConversionResult() {
		return conversionResult;
	}
	
}
