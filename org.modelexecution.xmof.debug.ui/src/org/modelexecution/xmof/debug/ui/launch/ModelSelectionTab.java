/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.debug.ui.launch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.debug.XMOFDebugPlugin;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.registry.IProfileRegistry;

public class ModelSelectionTab extends AbstractLaunchConfigurationTab {

	private Text modelResourceText;
	private Button browseModelResourceButton;

	private Text configurationMetamodelResourceText;
	private Button browseConfigurationMetamodelButton;

	private Text initializationResourceText;
	private Button browseInitializationResourceButton;

	private Combo profileResourceCombo;
	private HashMap<String, Profile> runtimeProfiles;

	@Override
	public void createControl(Composite parent) {
		Font font = parent.getFont();
		Composite comp = createContainerComposite(parent, font);
		createVerticalSpacer(comp, 3);
		createResourceSelectionControls(font, comp);
		createVerticalSpacer(comp, 10);
		createConfMMResourceControls(font, comp);
		createVerticalSpacer(comp, 10);
		createInitializationResourceControls(font, comp);
		createVerticalSpacer(comp, 10);
		createRuntimeProfileResourceControls(font, comp);
	}

	private Composite createContainerComposite(Composite parent, Font font) {
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		GridLayout topLayout = new GridLayout();
		topLayout.verticalSpacing = 0;
		topLayout.numColumns = 3;
		comp.setLayout(topLayout);
		comp.setFont(font);
		return comp;
	}

	private void createResourceSelectionControls(Font font, Composite comp) {
		createModelResourceLabel(font, comp);
		createModelResourceTextControl(font, comp);
		createModelResourceBrowseButton(comp);
	}

	private void createModelResourceBrowseButton(Composite comp) {
		browseModelResourceButton = createPushButton(comp, "&Browse", null);
		browseModelResourceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseModelResource();
			}
		});
	}

	private void createModelResourceTextControl(Font font, Composite comp) {
		GridData gd;
		modelResourceText = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		modelResourceText.setLayoutData(gd);
		modelResourceText.setFont(font);
		modelResourceText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}

	private void createModelResourceLabel(Font font, Composite comp) {
		Label programLabel = new Label(comp, SWT.NONE);
		programLabel.setText("&Model:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);
	}

	protected void browseModelResource() {
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Model Resource");
		dialog.setMessage("Select a model resource");
		if (dialog.open() == Window.OK) {
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			modelResourceText.setText(file.getFullPath().toString());
		}
	}

	private void createConfMMResourceControls(Font font, Composite comp) {
		createConfMMResourceLabel(font, comp);
		createConfMMResourceTextControl(font, comp);
		createConfMMResourceBrowseButton(comp);
	}

	private void createConfMMResourceLabel(Font font, Composite comp) {
		Label programLabel = new Label(comp, SWT.NONE);
		programLabel.setText("xMOF-Based &Configuration:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);
	}

	private void createConfMMResourceTextControl(Font font, Composite comp) {
		GridData gd;
		configurationMetamodelResourceText = new Text(comp, SWT.SINGLE
				| SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		configurationMetamodelResourceText.setLayoutData(gd);
		configurationMetamodelResourceText.setFont(font);
		configurationMetamodelResourceText
				.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent e) {
						updateRuntimeProfiles();
						updateLaunchConfigurationDialog();
					}
				});
	}

	private void createConfMMResourceBrowseButton(Composite comp) {
		browseConfigurationMetamodelButton = createPushButton(comp, "&Browse",
				null);
		browseConfigurationMetamodelButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						browseConfMMResource();
					}
				});
	}

	private void browseConfMMResource() {
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("xMOF Configuration Model");
		dialog.setMessage("Select a xMOF configuation model");
		if (dialog.open() == Window.OK) {
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			configurationMetamodelResourceText.setText(file.getFullPath()
					.toString());
		}
	}

	private void createInitializationResourceControls(Font font, Composite comp) {
		createInitializationResourceLabel(font, comp);
		createInitializationResourceTextControl(font, comp);
		createInitializationResourceBrowseButton(comp);
	}

	private void createInitializationResourceBrowseButton(Composite comp) {
		browseInitializationResourceButton = createPushButton(comp, "&Browse",
				null);
		browseInitializationResourceButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						browseInitializationResource();
					}
				});
	}

	private void createInitializationResourceTextControl(Font font,
			Composite comp) {
		GridData gd;
		initializationResourceText = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		initializationResourceText.setLayoutData(gd);
		initializationResourceText.setFont(font);
		initializationResourceText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}

	private void createInitializationResourceLabel(Font font, Composite comp) {
		Label programLabel = new Label(comp, SWT.NONE);
		programLabel.setText("&Initialization Model:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);
	}

	private void browseInitializationResource() {
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Initialization Model Resource");
		dialog.setMessage("Select an initialization model resource");
		if (dialog.open() == Window.OK) {
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			initializationResourceText.setText(file.getFullPath().toString());
		}
	}

	private void createRuntimeProfileResourceControls(Font font, Composite comp) {
		createRuntimeProfileResourceLabel(font, comp);
		createRuntimeProfileResourceTextControl(font, comp);
	}

	private void createRuntimeProfileResourceLabel(Font font, Composite comp) {
		Label label = new Label(comp, SWT.NONE);
		label.setText("&Runtime Profile:");
		GridData gd = new GridData(GridData.BEGINNING);
		label.setLayoutData(gd);
		label.setFont(font);

	}

	private void createRuntimeProfileResourceTextControl(Font font,
			Composite comp) {
		GridData gd;
		profileResourceCombo = new Combo(comp, SWT.READ_ONLY);
		updateRuntimeProfiles();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		profileResourceCombo.setLayoutData(gd);
		profileResourceCombo.setFont(font);
		profileResourceCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
	}

	protected void updateRuntimeProfiles() {
		String[] profileNames = getRuntimeProfileNames(getConfModelResource());
		profileResourceCombo.setItems(profileNames);
		if (profileNames.length > 0) {
			profileResourceCombo.select(0);
		}
	}

	private String[] getRuntimeProfileNames(IResource configurationResource) {
		Collection<Profile> validRuntimeProfiles = getValidRuntimeProfiles(configurationResource);
		runtimeProfiles = new HashMap<String, Profile>();
		List<String> profileNames = new ArrayList<String>();
		for (Profile profile : validRuntimeProfiles) {
			String profileName = profile.getName() + " - " + profile.getNsURI();
			runtimeProfiles.put(profileName, profile);
			profileNames.add(profileName);
		}
		return (String[]) profileNames.toArray(new String[profileNames.size()]);
	}

	private Collection<Profile> getValidRuntimeProfiles(
			IResource configurationResource) {
		Collection<Profile> runtimeProfiles = new ArrayList<Profile>();
		Collection<Profile> registeredProfiles = IProfileRegistry.INSTANCE
				.getRegisteredProfiles();
		if (configurationResource == null) {
			runtimeProfiles.addAll(registeredProfiles);
		} else {
			for (Profile profile : registeredProfiles) {
				if (isConfigurationProfile(profile, configurationResource)) {
					runtimeProfiles.add(profile);
				}
			}
		}
		return runtimeProfiles;
	}

	private boolean isConfigurationProfile(Profile profile,
			IResource configurationResource) {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource confResource = resourceSet.getResource(
				URI.createURI(configurationResource.getFullPath().toString()),
				true);
		Collection<BehavioredEClass> configurationClasses = getConfigurationClasses(confResource);
		for (BehavioredEClass configurationClass : configurationClasses) {
			if (!existsApplicableStereotype(configurationClass, profile)) {
				return false;
			}
		}
		return true;
	}

	private Collection<BehavioredEClass> getConfigurationClasses(
			Resource confResource) {
		Collection<BehavioredEClass> configurationClasses = new ArrayList<BehavioredEClass>();
		for (TreeIterator<EObject> allContents = confResource.getAllContents(); allContents
				.hasNext();) {
			EObject eObject = allContents.next();
			if (eObject instanceof BehavioredEClass
					&& !(eObject instanceof Behavior)) {
				BehavioredEClass configurationClass = (BehavioredEClass) eObject;
				configurationClasses.add(configurationClass);
			}
		}
		return configurationClasses;
	}

	private boolean existsApplicableStereotype(
			BehavioredEClass configurationClass, Profile profile) {
		for (Stereotype stereotype : profile.getStereotypes()) {
			boolean isStereotypeApplicable = false;
			try {
				isStereotypeApplicable = stereotype
						.isApplicable(configurationClass);
			} catch (Exception e) {
				// exception might happen if referenced ecore resource (from
				// xmof resource) cannot be resolved
			}
			if (isStereotypeApplicable) {
				return true;
			}
		}
		return false;
	}

	protected IResource getResource() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(modelResourceText.getText());
	}

	protected IResource getInitializationResource() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(initializationResourceText.getText());
	}

	private IResource getConfModelResource() {
		if (configurationMetamodelResourceText.getText().equals(""))
			return null;
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(configurationMetamodelResourceText.getText());
	}

	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		if (isResourceEmpty()) {
			setErrorMessage("Select a model resource.");
			return false;
		} else if (!isXMOFBasedResourceSelected() && !haveXMOFConfiguration()) {
			setErrorMessage("Selected resource is not an xMOF-based configuration. "
					+ "Either select a valid xMOF-based configuration "
					+ "or select another model.");
			return false;
		} else if (!isXMOFBasedResourceSelected()
				&& !haveValidXMOFConfiguration()) {
			setErrorMessage("Selected xMOF-based configuration "
					+ "is not valid.");
			return false;
		} else if (!isRuntimeProfileSelected()) {
			setErrorMessage("Select a runtime profile.");
			return false;
		} else {
			setErrorMessage(null);
			setMessage(null);
			return super.isValid(launchConfig);
		}
	}

	private boolean isRuntimeProfileSelected() {
		return profileResourceCombo.getSelectionIndex() != -1;
	}

	private boolean haveValidXMOFConfiguration() {
		Resource confModelResource = loadConfModelResource();
		return confModelResource != null
				&& containsBehavioredEClass(confModelResource);
	}

	private boolean containsBehavioredEClass(Resource confModelResource) {
		for (EObject eObject : confModelResource.getContents()) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				if (containsBehavioredEClass(ePackage)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean containsBehavioredEClass(EPackage ePackage) {
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier instanceof BehavioredEClass) {
				return true;
			}
		}
		for (EPackage subPackage : ePackage.getESubpackages()) {
			if (containsBehavioredEClass(subPackage)) {
				return true;
			}
		}
		return false;
	}

	private boolean isResourceEmpty() {
		return modelResourceText.getText().isEmpty();
	}

	private boolean isXMOFBasedResourceSelected() {
		Resource resource = loadModelResource();
		return resource != null && containsBehavioredEClassInstance(resource);
	}

	private boolean containsBehavioredEClassInstance(Resource resource) {
		TreeIterator<EObject> allContents = resource.getAllContents();
		while (allContents.hasNext()) {
			EObject eObject = allContents.next();
			if (eObject.eClass() instanceof BehavioredEClass) {
				return true;
			}
		}
		return false;
	}

	private Resource loadModelResource() {
		IResource iResource = getResource();
		if (iResource != null) {
			String modelPath = "/" + iResource.getProject().getName() + "/"
					+ iResource.getProjectRelativePath().toString();
			Resource resource = loadResource(modelPath);
			return resource;
		}
		return null;
	}

	private Resource loadConfModelResource() {
		IResource iResource = getConfModelResource();
		if (iResource != null) {
			String modelPath = "/" + iResource.getProject().getName() + "/"
					+ iResource.getProjectRelativePath().toString();
			Resource resource = loadResource(modelPath);
			return resource;
		}
		return null;
	}

	private Resource loadResource(String modelPath) {
		Resource resource = null;
		try {
			resource = new ResourceSetImpl().getResource(
					URI.createPlatformResourceURI(modelPath, true), true);
		} catch (Exception e) {

		}
		return resource;
	}

	private boolean haveXMOFConfiguration() {
		return configurationMetamodelResourceText.getText().trim().length() > 0;
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(DebugPlugin.ATTR_PROCESS_FACTORY_ID,
				XMOFDebugPlugin.PROCESS_FACTORY_ID);
		configuration.setAttribute(XMOFDebugPlugin.ATT_MODEL_PATH,
				modelResourceText.getText().trim());
		configuration.setAttribute(
				XMOFDebugPlugin.ATT_USE_CONFIGURATION_METAMODEL,
				haveXMOFConfiguration());
		configuration.setAttribute(
				XMOFDebugPlugin.ATT_CONFIGURATION_METAMODEL_PATH,
				(String) configurationMetamodelResourceText.getText().trim());
		configuration.setAttribute(XMOFDebugPlugin.ATT_INIT_MODEL_PATH,
				initializationResourceText.getText().trim());
		configuration.setAttribute(XMOFDebugPlugin.ATT_RUNTIME_PROFILE_NSURI,
				getSelectedRuntimeProfileNsUri());
	}

	private String getSelectedRuntimeProfileNsUri() {
		String[] items = profileResourceCombo.getItems();
		int selectionIndex = profileResourceCombo.getSelectionIndex();
		if (items.length > 0 && selectionIndex > -1
				&& selectionIndex < items.length) {
			String selectedProfileString = items[selectionIndex];
			Profile selectedRuntimeProfile = runtimeProfiles
					.get(selectedProfileString);
			return selectedRuntimeProfile.getNsURI();
		}
		return "";
	}

	@Override
	public String getName() {
		return "Model Resource";
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		String modelResource = "";
		String metamodelResource = "";
		String initializationModelResource = "";
		String runtimeProfileNsUri = "";

		try {
			modelResource = configuration.getAttribute(
					XMOFDebugPlugin.ATT_MODEL_PATH, "");
			metamodelResource = configuration.getAttribute(
					XMOFDebugPlugin.ATT_CONFIGURATION_METAMODEL_PATH, "");
			initializationModelResource = configuration.getAttribute(
					XMOFDebugPlugin.ATT_INIT_MODEL_PATH, "");
			runtimeProfileNsUri = configuration.getAttribute(
					XMOFDebugPlugin.ATT_RUNTIME_PROFILE_NSURI, "");
		} catch (CoreException e) {
		}

		modelResourceText.setText(modelResource);
		configurationMetamodelResourceText.setText(metamodelResource);
		initializationResourceText.setText(initializationModelResource);

		selectRuntimeProfile(runtimeProfileNsUri);
	}

	private void selectRuntimeProfile(String runtimeProfileNsUri) {
		if (runtimeProfileNsUri.equals(""))
			return;
		for (String profileString : runtimeProfiles.keySet()) {
			Profile profile = runtimeProfiles.get(profileString);
			if (profile.getNsURI().equals(runtimeProfileNsUri)) {
				for (int i = 0; i < profileResourceCombo.getItems().length; ++i) {
					if (profileResourceCombo.getItems()[i]
							.equals(profileString)) {
						profileResourceCombo.select(i);
						return;
					}
				}
			}
		}
	}

}
