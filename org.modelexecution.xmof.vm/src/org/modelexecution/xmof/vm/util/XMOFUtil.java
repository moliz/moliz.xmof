package org.modelexecution.xmof.vm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue;
import org.modelexecution.xmof.Semantics.Classes.Kernel.Value;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueDefinition;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.XMOFBasedModel;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

public class XMOFUtil {

	public static Resource createConfigurationModelResource(
			ResourceSet resourceSet, EditingDomain editingDomain,
			Resource configurationMetamodelResource, Resource modelResource) {
		Collection<EObject> configurationObjects = XMOFUtil
				.createConfigurationObjects(configurationMetamodelResource,
						modelResource);
		Resource configurationModelResource = EMFUtil.createResource(
				resourceSet, editingDomain,
				EMFUtil.createFileURI("configurationmodel.xmi"),
				configurationObjects);
		return configurationModelResource;
	}
	
	public static XMOFVirtualMachine createXMOFVirtualMachine(
			ResourceSet resourceSet, EditingDomain editingDomain,
			Resource configurationModelResource) {
		XMOFBasedModel model = new XMOFBasedModel(
				configurationModelResource.getContents(), editingDomain);
		return createXMOFVirtualMachine(model);
	}
	
	public static XMOFVirtualMachine createXMOFVirtualMachine(
			ResourceSet resourceSet, EditingDomain editingDomain,
			Resource configurationModelResource, List<ParameterValue> parameterValues) {
		XMOFBasedModel model = new XMOFBasedModel(
				configurationModelResource.getContents(), parameterValues, editingDomain);
		return createXMOFVirtualMachine(model);
	}
	
	private static XMOFVirtualMachine createXMOFVirtualMachine(XMOFBasedModel model) {
		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		return vm;
	}

	private static Collection<EObject> createConfigurationObjects(
			Resource configurationMetamodelResource, Resource modelResource) {
		ConfigurationObjectMap configurationMap = createConfigurationObjectMap(
				configurationMetamodelResource, modelResource);
		Collection<EObject> configurationObjects = configurationMap
				.getConfigurationObjects();
		return configurationObjects;
	}

	public static ConfigurationObjectMap createConfigurationObjectMap(
			Resource configurationMetamodelResource, Resource modelResource) {
		return createConfigurationObjectMap(configurationMetamodelResource, modelResource, null);
	}
	
	public static ConfigurationObjectMap createConfigurationObjectMap(
			Resource configurationMetamodelResource, Resource modelResource, Resource parameterResource) {
		return createConfigurationObjectMap(configurationMetamodelResource, modelResource, parameterResource, (Resource[])null);
	}
	
	public static ConfigurationObjectMap createConfigurationObjectMap(Resource configurationMetamodelResource, Resource modelResource, Resource parameterResource, Resource... additionalResources) {
		Collection<EObject> parameterValueObjects = getParameterValueObjects(parameterResource);
		
		Collection<EObject> inputElements = new ArrayList<EObject>();
		inputElements.addAll(modelResource.getContents());
		inputElements.addAll(parameterValueObjects);
		
		if(additionalResources != null) {
			for(Resource resource : additionalResources) {
				inputElements.addAll(resource.getContents());
			}
		}
		
		Collection<EPackage> configurationPackages = EMFUtil
				.getEPackages(configurationMetamodelResource);
		ConfigurationObjectMap configurationMap = new ConfigurationObjectMap(
				inputElements, configurationPackages);
		return configurationMap;
	}
	
	private static Collection<EObject> getParameterValueObjects(
			Resource parameterResource) {
		Collection<ParameterValue> parameterValues = getParameterValues(parameterResource);
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
	
	private static Collection<ParameterValue> getParameterValues(
			Resource parameterResource) {
		EList<ParameterValue> parameterValues = new BasicEList<ParameterValue>();
		if (parameterResource != null) {
			for (EObject eObject : parameterResource.getContents()) {
				if (eObject instanceof ParameterValueDefinition) {
					ParameterValueDefinition parameterValueDefinition = (ParameterValueDefinition) eObject;
					parameterValues.addAll(parameterValueDefinition
							.getParameterValues());
				}
			}
		}
		return parameterValues;
	}

	public static List<ParameterValue> getParameterValueConfiguration(
			Resource parameterResource,
			ConfigurationObjectMap configurationMap) {
		Collection<ParameterValue> parameterValues = getParameterValues(parameterResource);
		Copier copier = new EcoreUtil.Copier(true, false);
		copier.copyAll(parameterValues);
		copier.copyReferences();

		List<ParameterValue> parameterValueConfiguration = new ArrayList<ParameterValue>();
		for (ParameterValue parameterValue : parameterValues) {
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
}
