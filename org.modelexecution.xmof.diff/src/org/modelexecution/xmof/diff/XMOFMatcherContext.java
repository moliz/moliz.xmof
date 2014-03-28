package org.modelexecution.xmof.diff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.modelexecution.xmof.vm.util.EMFUtil;

public class XMOFMatcherContext {

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;

	private Resource metamodelResource;
	private Resource configurationMetamodelResource;

	private Resource modelResourceLeft;
	private Resource modelResourceRight;
	
	private List<Resource> parameterResourcesLeft;
	private List<Resource> parameterResourcesRight;

	private File eclFileSyntax;
	private File eclFileSemantics;

	private ClassLoader nativeTypeDelegate;
	
	public boolean isComplete() {
		return resourceSet != null && editingDomain != null
				&& metamodelResource != null
				&& configurationMetamodelResource != null
				&& modelResourceLeft != null && modelResourceRight != null
				&& eclFileSyntax != null && eclFileSemantics != null
				&& getParameterResourcesLeft().size() == getParameterResourcesRight().size();
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public Resource getMetamodelResource() {
		return metamodelResource;
	}

	public void setMetamodelResource(Resource metamodelResource) {
		this.metamodelResource = metamodelResource;
	}
	
	public void setMetamodelResource(URI uri) {
		this.metamodelResource = loadMetamodel(uri);
	}

	public void setMetamodelResource(String filePath) {
		setMetamodelResource(EMFUtil.createFileURI(filePath));
	}
	
	public Resource getConfigurationMetamodelResource() {
		return configurationMetamodelResource;
	}

	public void setConfigurationMetamodelResource(
			Resource configurationMetamodelResource) {
		this.configurationMetamodelResource = configurationMetamodelResource;
	}
	
	public void setConfigurationMetamodelResource(URI uri) {
		this.configurationMetamodelResource = loadModel(uri);
	}
	
	public void setConfigurationMetamodelResource(String filePath) {
		setConfigurationMetamodelResource(EMFUtil.createFileURI(filePath));
	}

	public Resource getModelResourceLeft() {
		return modelResourceLeft;
	}

	public void setModelResourceLeft(Resource modelResourceLeft) {
		this.modelResourceLeft = modelResourceLeft;
	}
	
	public void setModelResourceLeft(URI uri) {
		this.modelResourceLeft = loadModel(uri);
	}
	
	public void setModelResourceLeft(String filePath) {
		setModelResourceLeft(EMFUtil.createFileURI(filePath));
	}

	public Resource getModelResourceRight() {
		return modelResourceRight;
	}

	public void setModelResourceRight(Resource modelResourceRight) {
		this.modelResourceRight = modelResourceRight;
	}

	public void setModelResourceRight(URI uri) {
		this.modelResourceRight = loadModel(uri);
	}
	
	public void setModelResourceRight(String filePath) {
		setModelResourceRight(EMFUtil.createFileURI(filePath));
	}

	public File getEclFileSyntax() {
		return eclFileSyntax;
	}
	
	public void setEclFileSyntax(File eclFileSyntax) {
		this.eclFileSyntax = eclFileSyntax;
	}
	
	public void setEclFileSyntax(String filePath) {
		setEclFileSyntax(EMFUtil.createFile(filePath));
	}
	
	public File getEclFileSemantics() {
		return eclFileSemantics;
	}
	
	public void setEclFileSemantics(File eclFileSemantics) {
		this.eclFileSemantics = eclFileSemantics;
	}
	
	public void setEclFileSemantics(String filePath) {
		setEclFileSemantics(EMFUtil.createFile(filePath));
	}

	private Resource loadMetamodel(URI uri) {
		if (resourceSet != null) {
			Resource metamodelResource = EMFUtil
					.loadMetamodel(resourceSet, uri);
			return metamodelResource;
		}
		return null;
	}

	private Resource loadModel(URI uri) {
		if (resourceSet != null) {
			Resource modelResource = EMFUtil.loadResource(resourceSet, uri);
			return modelResource;
		}
		return null;
	}

	public ClassLoader getNativeTypeDelegate() {
		return nativeTypeDelegate;
	}

	public void setNativeTypeDelegate(ClassLoader nativeTypeDelegate) {
		this.nativeTypeDelegate = nativeTypeDelegate;
	}
	
	public void addParameterResourceLeft(Resource resource) {
		this.getParameterResourcesLeft().add(resource);
	}
	
	public void addParameterResourceLeft(URI uri) {
		this.getParameterResourcesLeft().add(loadModel(uri));
	}
	
	public void addParameterResourceLeft(String filePath) {
		addParameterResourceLeft(EMFUtil.createFileURI(filePath));
	}
	
	public List<Resource> getParameterResourcesLeft() {
		if(parameterResourcesLeft == null)
			parameterResourcesLeft = new ArrayList<Resource>();
		return parameterResourcesLeft;
	}
	
	public void addParameterResourceRight(Resource resource) {
		this.getParameterResourcesRight().add(resource);
	}
	
	public void addParameterResourceRight(URI uri) {
		this.getParameterResourcesRight().add(loadModel(uri));
	}
	
	public void addParameterResourceRight(String filePath) {
		addParameterResourceRight(EMFUtil.createFileURI(filePath));
	}
	
	public List<Resource> getParameterResourcesRight() {
		if(parameterResourcesRight == null)
			parameterResourcesRight = new ArrayList<Resource>();
		return parameterResourcesRight;
	}
}