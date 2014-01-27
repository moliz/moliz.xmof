package org.modelexecution.xmof.diff.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.states.builder.StatesBuilder;
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
		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		return vm;
	}

	public static StatesBuilder createStatesBuilder(XMOFVirtualMachine vm,
			Resource configurationModelResource) {
		StatesBuilder statesBuilder = new StatesBuilder(
				configurationModelResource);
		statesBuilder.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);
		return statesBuilder;
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
		Collection<EPackage> configurationPackages = EMFUtil
				.getEPackages(configurationMetamodelResource);
		ConfigurationObjectMap configurationMap = new ConfigurationObjectMap(
				modelResource.getContents(), configurationPackages);
		return configurationMap;
	}
}
