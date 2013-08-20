/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.launch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.Value;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueDefinition;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.configuration.profile.ProfileApplicationGenerator;
import org.modelexecution.xmof.configuration.profile.XMOFConfigurationProfilePlugin;
import org.modelexecution.xmof.debug.XMOFDebugPlugin;
import org.modelexecution.xmof.debug.internal.process.InternalXMOFProcess;
import org.modelexecution.xmof.debug.internal.process.InternalXMOFProcess.Mode;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileRegistry;

public class XMOFLaunchDelegate extends LaunchConfigurationDelegate {

	private static final String XMOF_EXEC_LABEL = "xMOF Execution Process";
	private ResourceSet resourceSet;
	private ConfigurationObjectMap configurationMap;

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		resourceSet = new ResourceSetImpl();

		XMOFBasedModel model = getXMOFBasedModel(configuration);
		InternalXMOFProcess xMOFProcess = new InternalXMOFProcess(model,
				getProcessMode(mode));

		installConfigurationProfileApplicationGenerator(configuration,
				xMOFProcess);

		IProcess process = DebugPlugin.newProcess(launch, xMOFProcess,
				XMOF_EXEC_LABEL);

		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			// TODO set debug target
			System.out.println("Should debug:" + process);
		}
	}

	private XMOFBasedModel getXMOFBasedModel(ILaunchConfiguration configuration)
			throws CoreException {

		Collection<EObject> inputModelElements = loadInputModelElements(configuration);
		List<ParameterValue> inputParameterValues = loadInputParameterValueElements(configuration);
		Collection<EObject> inputParameterValueObjects = getParameterValueObjects(inputParameterValues);

		Collection<EObject> inputElements = new ArrayList<EObject>();
		inputElements.addAll(inputModelElements);
		inputElements.addAll(inputParameterValueObjects);

		if (useConfigurationMetamodel(configuration)) {
			String confMetamodelPath = getConfigurationMetamodelPath(configuration);
			Collection<EPackage> configurationPackages = loadConfigurationMetamodel(confMetamodelPath);
			configurationMap = new ConfigurationObjectMap(inputElements,
					configurationPackages);
			return new XMOFBasedModel(
					configurationMap.getConfigurationObjects(),
					getParameterValueConfiguration(inputParameterValues));
		} else {
			return new XMOFBasedModel(inputModelElements, inputParameterValues);
		}
	}

	private boolean useConfigurationMetamodel(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(
				XMOFDebugPlugin.ATT_USE_CONFIGURATION_METAMODEL, false);
	}

	private String getConfigurationMetamodelPath(
			ILaunchConfiguration configuration) throws CoreException {
		return configuration
				.getAttribute(XMOFDebugPlugin.ATT_CONFIGURATION_METAMODEL_PATH,
						(String) null);
	}

	private Collection<EPackage> loadConfigurationMetamodel(
			String confMetamodelPath) {
		Resource resource = loadResource(confMetamodelPath);
		Collection<EPackage> confMMPackages = new ArrayList<EPackage>();
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				if (EPackage.Registry.INSTANCE.containsKey(ePackage.getNsURI())) {
					EPackage registeredPackage = (EPackage) EPackage.Registry.INSTANCE
							.get(ePackage.getNsURI());
					reloadPackage(registeredPackage);
					confMMPackages.add(registeredPackage);
				} else {
					confMMPackages.add(ePackage);
				}
			}
		}
		return confMMPackages;
	}

	private void reloadPackage(EPackage registeredPackage) {
		try {
			registeredPackage.eResource().unload();
			registeredPackage.eResource().load(null);
		} catch (IOException e) {
			// do not reload if IO exception
		}
	}

	private Resource loadResource(String path) {
		return resourceSet.getResource(
				URI.createPlatformResourceURI(path, true), true);
	}

	private Collection<EObject> loadInputModelElements(
			ILaunchConfiguration configuration) throws CoreException {
		String modelPath = getModelPath(configuration);
		Collection<EObject> inputModelElements = getInputModelElements(modelPath);
		return inputModelElements;
	}

	private List<ParameterValue> loadInputParameterValueElements(
			ILaunchConfiguration configuration) throws CoreException {
		String modelPath = getParameterValueDefinitionModelPath(configuration);
		List<ParameterValue> parameterValues = getParameterValues(modelPath);
		return parameterValues;
	}

	private String getParameterValueDefinitionModelPath(
			ILaunchConfiguration configuration) throws CoreException {
		return configuration.getAttribute(XMOFDebugPlugin.ATT_INIT_MODEL_PATH,
				(String) null);
	}

	private List<ParameterValue> getParameterValues(String modelPath) {
		EList<ParameterValue> parameterValues = new BasicEList<ParameterValue>();
		if (!(modelPath == null || modelPath == "")) {
			Resource resource = loadResource(modelPath);
			EList<EObject> parameterValueDefinitions = resource.getContents();
			for (EObject eObject : parameterValueDefinitions) {
				if (eObject instanceof ParameterValueDefinition) {
					ParameterValueDefinition parameterValueDefinition = (ParameterValueDefinition) eObject;
					parameterValues.addAll(parameterValueDefinition
							.getParameterValues());
				}
			}
		}
		return parameterValues;
	}

	private Collection<EObject> getParameterValueObjects(
			Collection<ParameterValue> parameterValues) {
		Collection<EObject> parameterValueObjects = new BasicEList<EObject>();
		for (ParameterValue parameterValue : parameterValues) {
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					ObjectValue objectValue = (ObjectValue) value;
					EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						parameterValueObjects.add(referencedEObject);
					}
				}
			}
		}
		return parameterValueObjects;
	}

	private List<ParameterValue> getParameterValueConfiguration(
			List<ParameterValue> inputParameterValues) {
		List<ParameterValue> parameterValueConfiguration = new ArrayList<ParameterValue>();

		Copier copier = new EcoreUtil.Copier(true, false);
		copier.copyAll(inputParameterValues);
		copier.copyReferences();

		for (ParameterValue parameterValue : inputParameterValues) {
			ParameterValue parameterValueConf = (ParameterValue) copier
					.get(parameterValue);
			parameterValueConf.setParameter(parameterValue.getParameter());
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					ObjectValue objectValue = (ObjectValue) value;
					EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						EObject referencedEObjectConf = configurationMap
								.getConfigurationObject(referencedEObject);
						ObjectValue objectValueConf = (ObjectValue) copier
								.get(value);
						objectValueConf.setEObject(referencedEObjectConf);
					}
				}
			}
			parameterValueConfiguration.add(parameterValueConf);
		}
		return parameterValueConfiguration;
	}

	private String getModelPath(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(XMOFDebugPlugin.ATT_MODEL_PATH,
				(String) null);
	}

	private Collection<EObject> getInputModelElements(String modelPath) {
		Resource resource = loadResource(modelPath);
		return resource.getContents();
	}

	private Mode getProcessMode(String mode) {
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return Mode.DEBUG;
		} else {
			return Mode.RUN;
		}
	}

	private void installConfigurationProfileApplicationGenerator(
			ILaunchConfiguration configuration, InternalXMOFProcess xMOFProcess)
			throws CoreException {
		Collection<Profile> configurationProfiles = getConfigurationProfile(configuration);
		if (configurationProfiles.size() > 0 && configurationMap != null) {
			ProfileApplicationGenerator generator = new ProfileApplicationGenerator(
					xMOFProcess.getModel(), configurationProfiles,
					configurationMap, xMOFProcess.getVirtualMachine()
							.getInstanceMap());
			URI profileApplicationURI = getConfigurationProfileApplicationURI(
					configuration, xMOFProcess.getModel());
			generator.setProfileApplicationURI(profileApplicationURI);
			generator.setResourceSet(resourceSet);
			xMOFProcess.getVirtualMachine()
					.addVirtualMachineListener(generator);
		}
	}

	private Collection<Profile> getConfigurationProfile(
			ILaunchConfiguration configuration) throws CoreException {
		Collection<Profile> configProfiles = new ArrayList<Profile>();
		String runtimeProfileNsUri = getRuntimeProfileNsUri(configuration);
		if (runtimeProfileNsUri != null) {
			Collection<Profile> registeredProfiles = IProfileRegistry.INSTANCE
					.getRegisteredProfiles();
			for (Profile profile : registeredProfiles) {
				if (profile.getNsURI().equals(runtimeProfileNsUri))
					configProfiles.add(profile);
			}
		}
		return configProfiles;
	}

	private String getRuntimeProfileNsUri(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(
				XMOFDebugPlugin.ATT_RUNTIME_PROFILE_NSURI, (String) null);
	}

	private URI getConfigurationProfileApplicationURI(
			ILaunchConfiguration configuration, XMOFBasedModel model)
			throws CoreException {
		URI uri = getConfigurationProfileApplicationURI(configuration);
		if (uri == null) {
			uri = createConfigurationProfileApplicationURI(configuration);
		}
		return uri;
	}

	private URI getConfigurationProfileApplicationURI(
			ILaunchConfiguration configuration) {
		// TODO load from configuration once we have it there
		return null;
	}

	private URI createConfigurationProfileApplicationURI(
			ILaunchConfiguration configuration) throws CoreException {
		String modelPath = getModelPath(configuration);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(modelPath));
		return URI.createFileURI(modelFile.getLocation().toString()
				+ XMOFConfigurationProfilePlugin.RUNTIME_EMFPROFILE_EXTENSION);
	}

	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		return false;
	}

}
