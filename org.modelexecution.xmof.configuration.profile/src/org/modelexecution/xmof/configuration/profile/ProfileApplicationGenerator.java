/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration.profile;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.IXMOFVirtualMachineListener;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent.Type;
import org.modelexecution.xmof.vm.internal.XMOFInstanceMap;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.EnumerationValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Classes.Kernel.EnumerationLiteral;

public class ProfileApplicationGenerator implements IXMOFVirtualMachineListener {

	private XMOFBasedModel model;
	private Collection<Profile> configurationProfiles;
	private ConfigurationObjectMap configurationMap;
	private Resource profileApplicationResource;
	private IProfileFacade facade;

	public ProfileApplicationGenerator(XMOFBasedModel model,
			Collection<Profile> configurationProfiles,
			ConfigurationObjectMap configurationMap) {
		this.model = model;
		this.configurationProfiles = configurationProfiles;
		this.configurationMap = configurationMap;
	}

	@Override
	public void notify(XMOFVirtualMachineEvent event) {
		if (Type.STOP.equals(event.getType())) {
			try {
				generateProfileApplication(event.getVirtualMachine());
			} catch (IOException e) {
				XMOFConfigurationProfilePlugin.log(e);
			}
		}
	}

	private void generateProfileApplication(XMOFVirtualMachine virtualMachine)
			throws IOException {
		if (profileApplicationResource == null) {
			return;
		}
		prepareProfileFacade();
		createStereotypeApplications(virtualMachine);
		saveProfileApplication();
	}

	private void prepareProfileFacade() throws IOException {
		facade = new ProfileFacadeImpl();
		if (!profileApplicationResource.isLoaded()) {
			profileApplicationResource.save(null);
		}
		facade.setProfileApplicationResource(profileApplicationResource);
		for (Profile profile : configurationProfiles) {
			facade.makeApplicable(profile);
			facade.loadProfile(profile);
		}
	}

	private void createStereotypeApplications(XMOFVirtualMachine virtualMachine) {
		XMOFInstanceMap instanceMap = virtualMachine.getInstanceMap();
		for (ExtensionalValue value : instanceMap.getExtensionalValues()) {
			if (value instanceof Object_) {
				createStereotypeApplication((Object_) value, instanceMap);
			}
		}
	}

	private void createStereotypeApplication(Object_ object,
			XMOFInstanceMap instanceMap) {
		EObject confObject = instanceMap.getEObject(object);
		EObject eObject = configurationMap.getOriginalObject(confObject);
		Stereotype confStereotype = getConfigurationStereotype(confObject);
		if (shouldApply(confStereotype)
				&& facade.isApplicable(confStereotype, eObject)) {
			StereotypeApplication application = facade.apply(confStereotype,
					eObject);
			for (EStructuralFeature feature : confStereotype
					.getEStructuralFeatures()) {
				Object value = getValue(object, feature);
				if (value != null) {
					facade.setTaggedValue(application, feature, value);
				}
			}
		}
	}

	private boolean shouldApply(Stereotype confStereotype) {
		return confStereotype.getTaggedValues().size() > 0;
	}

	private Stereotype getConfigurationStereotype(EObject eObject) {
		EClass confClass = eObject.eClass();
		for (Profile profile : configurationProfiles) {
			// TODO use extension base class to decide and not the name
			Stereotype stereotype = profile.getStereotype(confClass.getName()
					+ "Stereotype");
			if (stereotype != null)
				return stereotype;
		}
		return null;
	}

	private Object getValue(Object_ object, EStructuralFeature feature) {
		for (Iterator<FeatureValue> iterator = object.featureValues.iterator(); iterator
				.hasNext();) {
			FeatureValue featureValue = iterator.next();
			if (featureValue.feature.name.equals(feature.getName())) {
				if (feature instanceof EAttribute) {
					return getAttributeValue(featureValue, (EAttribute) feature);
				} else if (feature instanceof EReference) {
					// TODO handle references
					return null;
				}
			}
		}
		return null;
	}

	private Object getAttributeValue(FeatureValue featureValue,
			EAttribute eAttribute) {
		if (!eAttribute.isMany()) {
			EDataType attType = eAttribute.getEAttributeType();
			if (featureValue.values.isEmpty()) {
				return null;
			}
			Value value = featureValue.values.get(0);
			if (isEBooleanType(attType)) {
				return ((BooleanValue) value).value;
			} else if (isEIntType(attType)) {
				return (int) ((IntegerValue) value).value;
			} else if (isEStringType(attType)) {
				return ((StringValue) value).value;
			} else if (isCustomEEnumType(attType)) {
				EnumerationValue enumerationValue = (EnumerationValue) value;
				EnumerationLiteral literal = enumerationValue.literal;
				EEnum eEnum = (EEnum) attType;
				return eEnum.getEEnumLiteral(literal.name);
			}
		} else {
			// TODO handle multivalued attribute values
		}
		return null;
	}

	private boolean isEBooleanType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEBoolean().equals(valueType);
	}

	private boolean isEIntType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEInt().equals(valueType);
	}

	private boolean isEStringType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEString().equals(valueType);
	}

	private boolean isCustomEEnumType(EDataType valueType) {
		return valueType instanceof EEnum;
	}

	private void saveProfileApplication() throws IOException {
		facade.save();
	}

	public Resource getProfileApplicationResource() {
		return profileApplicationResource;
	}

	public void setProfileApplicationResource(
			Resource profileApplicationResource) {
		this.profileApplicationResource = profileApplicationResource;
	}

	public XMOFBasedModel getModel() {
		return model;
	}

	public Collection<Profile> getConfigurationProfiles() {
		return configurationProfiles;
	}

}
