package org.modelexecution.xmof.diff.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class EMFUtil {

	public static ResourceSet createResourceSet() {
		return new ResourceSetImpl();
	}

	public static File createFile(String path) {
		return new File(path);
	}

	public static Resource createResource(ResourceSet resourceSet, URI uri) {
		return resourceSet.createResource(uri);
	}

	public static Resource createResource(ResourceSet resourceSet,
			EditingDomain editingDomain, URI uri, Collection<EObject> eObjects) {
		if (editingDomain.getResourceSet() != resourceSet)
			return null;
		Resource resource = EMFUtil.createResource(resourceSet, uri);
		AddCommand addCommand = new AddCommand(editingDomain,
				resource.getContents(), eObjects);
		editingDomain.getCommandStack().execute(addCommand);
		return resource;
	}
	
	public static Resource createResource(ResourceSet resourceSet,
			EditingDomain editingDomain, URI uri, EObject... eObjects) {
		List<EObject> eObjectsAsList = Arrays.asList(eObjects);
		Resource resource = EMFUtil.createResource(resourceSet, editingDomain, uri, eObjectsAsList);
		return resource;
	}

	public static URI createFileURI(String path) {
		return URI.createFileURI(createFile(path).getAbsolutePath());
	}

	public static URI createPlatformPluginURI(String path) {
		return URI.createPlatformPluginURI(path, true);
	}

	public static Resource loadResource(ResourceSet resourceSet, URI uri) {
		return resourceSet.getResource(uri, true);
	}

	public static EPackage getRootEPackage(Resource resource) {
		for (EObject content : resource.getContents()) {
			if (content instanceof EPackage)
				return (EPackage) content;
		}
		return null;
	}

	private static void registerMetamodelPackage(ResourceSet resourceSet,
			EPackage ePackage) {
		resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
	}

	public static void registerXMIFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
	}

	public static void registerEcoreFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());
	}

	public static Resource loadMetamodel(ResourceSet resourceSet, URI uri) {
		Resource resource = EMFUtil.loadResource(resourceSet, uri);
		EPackage rootEPackage = EMFUtil.getRootEPackage(resource);
		EMFUtil.registerMetamodelPackage(resourceSet, rootEPackage);
		return resource;
	}

	public static TransactionalEditingDomain createTransactionalEditingDomain(
			ResourceSet resourceSet) {
		return TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain(resourceSet);
	}
	
	public static void persist(ResourceSet resourceSet, EditingDomain editingDomain, URI uri, EObject... eObjects) throws IOException {
		if(editingDomain.getResourceSet() != resourceSet)
			return;
		Collection<EObject> eObjectsCollection = Arrays.asList(eObjects);
		Resource resource = EMFUtil.createResource(resourceSet, editingDomain, uri, eObjectsCollection);
		persist(resource);
	}

	private static void persist(Resource resource) throws IOException {
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		resource.save(options);
	}
	
	public static Collection<EPackage> getEPackages(Resource resource) {
		Collection<EPackage> confMMPackages = new ArrayList<EPackage>();
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				confMMPackages.add(ePackage);
			}
		}
		return confMMPackages;
	}
}
