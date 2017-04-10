package org.modelexecution.xmof.debug.internal.process;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationManager;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationRegistry;

public class XMOFProfileAnnotationDisplayHandler implements Runnable {

	private static final String SAMPLE_ECORE_MODEL_EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID";

	private String modelPath;
	private String paPath;
	private String editorID;
	private IEditorPart activeEditor;

	public XMOFProfileAnnotationDisplayHandler(String modelPath, String paPath) {
		this.modelPath = modelPath;
		this.paPath = paPath;
	}

	private boolean openModel() {
		IEditorInput editorInput = getEditorInput();
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			activeEditor = page.openEditor(editorInput, editorID);
		} catch (PartInitException e) {
			return false;
		}
		return true;
	}

	private IEditorInput getEditorInput() {
		URI modelPathURI = getURI(modelPath);
		if (editorID != null) {
			URI editorInputURI = getEditorInputFileURI(editorID, modelPath);
			return new URIEditorInput(editorInputURI);
		}
		// IEditorDescriptor editorDescriptor = PlatformUI.getWorkbench()
		// .getEditorRegistry()
		// .getDefaultEditor(modelPathURI.toFileString());
		editorID = SAMPLE_ECORE_MODEL_EDITOR_ID;
		return new URIEditorInput(modelPathURI);
	}

	private URI getEditorInputFileURI(String editorID, String modelPath) {
		URI modelPathURI = getURI(modelPath);
		String modelFileExtension = modelPathURI.fileExtension();
		Set<String> fileExtensions = getFileExtensions(editorID);
		for (String fileExtension : fileExtensions) {
			String editorInputPathFileString = replaceFileExtension(modelPath,
					modelFileExtension, fileExtension);
			URI editorInputURI = getURI(editorInputPathFileString);
			if (editorInputURI != null) {
				return editorInputURI;
			}
		}
		return null;
	}

	private String replaceFileExtension(String filePath,
			String oldFileExtension, String newFileExtension) {
		String editorInputPathFileString = filePath.substring(0,
				filePath.length() - oldFileExtension.length());
		editorInputPathFileString = editorInputPathFileString
				+ newFileExtension;
		return editorInputPathFileString;
	}

	private Set<String> getFileExtensions(String editorID) {
		Set<String> extensions = new HashSet<String>();
		IFileEditorMapping[] fileEditorMappings = PlatformUI.getWorkbench()
				.getEditorRegistry().getFileEditorMappings();
		for (int i = 0; i < fileEditorMappings.length; ++i) {
			IFileEditorMapping fileEditorMapping = fileEditorMappings[i];
			IEditorDescriptor[] editorDescriptors = fileEditorMapping
					.getEditors();
			for (int j = 0; j < editorDescriptors.length; ++j) {
				IEditorDescriptor editorDescriptor = editorDescriptors[j];
				if (editorDescriptor.getId().equals(editorID)) {
					extensions.add(fileEditorMapping.getExtension());
				}
			}
		}
		return extensions;
	}

	private URI getURI(String path) {
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(path));
		if (file != null && file.exists())
			return URI.createFileURI(file.getLocation().toString());
		return null;
	}

	private boolean loadProfileApplication() {
		IFile paFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(paPath));
		try {
			ResourceSet resourceSet = getResourceSet(activeEditor);
			if (resourceSet == null)
				throw new RuntimeException(
						"Could not find the ResourceSet of this editor part: "
								+ activeEditor);

			ProfileApplicationManager manager = ProfileApplicationRegistry.INSTANCE
					.getProfileApplicationManager(resourceSet);
			manager.bindProfileApplicationDecorator(editorID);
			manager.loadProfileApplication(paFile);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private ResourceSet getResourceSet(IEditorPart editorPart)
			throws NullPointerException {
		Object adapter = editorPart.getAdapter(IEditingDomainProvider.class);
		if (adapter != null && adapter instanceof IEditingDomainProvider) {
			IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) adapter;
			if (editingDomainProvider.getEditingDomain() != null)
				return editingDomainProvider.getEditingDomain()
						.getResourceSet();
		}
		return null;
	}

	public void setEditorID(String editorID) {
		this.editorID = editorID;
	}

	@Override
	public void run() {
		boolean modelOpenend = false;
		boolean profileLoaded = false;

		modelOpenend = openModel();
		if (modelOpenend) {
			profileLoaded = loadProfileApplication();
		}

		if (!(modelOpenend && profileLoaded)) {
			MessageDialog.openInformation(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(),
					"No result display!",
					"Was not able to load execution result.");
		}
	}
}
