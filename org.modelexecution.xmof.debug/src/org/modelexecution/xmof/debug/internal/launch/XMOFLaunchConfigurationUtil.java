package org.modelexecution.xmof.debug.internal.launch;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.modelexecution.xmof.configuration.profile.XMOFConfigurationProfilePlugin;
import org.modelexecution.xmof.debug.XMOFDebugPlugin;
import org.modelexecution.xmof.vm.util.EMFUtil;

public class XMOFLaunchConfigurationUtil {

	public static String getConfigurationMetamodelPath(
			ILaunchConfiguration configuration) {
		return getConfigurationAttributeString(configuration,
				XMOFDebugPlugin.ATT_CONFIGURATION_METAMODEL_PATH);
	}

	public static String getModelFilePath(ILaunchConfiguration configuration) {
		return getConfigurationAttributeString(configuration,
				XMOFDebugPlugin.ATT_MODEL_PATH);
	}

	public static URI getModelPathURI(ILaunchConfiguration configuration) {
		String modelPath = getModelFilePath(configuration);
		URI modelPathUri = EMFUtil.createPlatformResourceURI(modelPath);
		return modelPathUri;
	}

	public static String getRuntimeProfileNsUri(
			ILaunchConfiguration configuration) {
		return getConfigurationAttributeString(configuration,
				XMOFDebugPlugin.ATT_RUNTIME_PROFILE_NSURI);
	}

	public static String getProfileApplicationFilePath(
			ILaunchConfiguration configuration) {
		return getConfigurationAttributeString(configuration,
				XMOFDebugPlugin.ATT_RUNTIME_PROFILE_APPLICATION_FILE_PATH);
	}

	public static boolean useConfigurationMetamodel(
			ILaunchConfiguration configuration) {
		return getConfigurationAttributeBoolean(configuration,
				XMOFDebugPlugin.ATT_USE_CONFIGURATION_METAMODEL);
	}

	public static String getParameterValueDefinitionModelPath(
			ILaunchConfiguration configuration) {
		return getConfigurationAttributeString(configuration,
				XMOFDebugPlugin.ATT_INIT_MODEL_PATH);
	}

	private static String getConfigurationAttributeString(
			ILaunchConfiguration configuration, String attributeName) {
		String attributeValue = null;
		try {
			attributeValue = configuration.getAttribute(attributeName,
					(String) null);
		} catch (CoreException e) {
		}
		return attributeValue;
	}

	private static boolean getConfigurationAttributeBoolean(
			ILaunchConfiguration configuration, String attributeName) {
		boolean attributeValue = false;
		try {
			attributeValue = configuration.getAttribute(attributeName, false);
		} catch (CoreException e) {
		}
		return attributeValue;
	}

	public static URI getConfigurationProfileApplicationURI(
			ILaunchConfiguration configuration) {
		URI uri = getExistingConfigurationProfileApplicationURI(configuration);
		if (uri == null) {
			uri = createConfigurationProfileApplicationURI(configuration);
		}
		return uri;
	}

	private static URI getExistingConfigurationProfileApplicationURI( // TODO URI required or provide vile or only path
			ILaunchConfiguration configuration) {
		String filePath = XMOFLaunchConfigurationUtil
				.getProfileApplicationFilePath(configuration);
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(filePath));
		return URI.createFileURI(file.getLocation().toString());
		//URI uri = URI.createPlatformResourceURI(filePath, true);
		//return uri;
	}

	private static URI createConfigurationProfileApplicationURI(
			ILaunchConfiguration configuration) {
		String modelPath = XMOFLaunchConfigurationUtil
				.getModelFilePath(configuration);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(modelPath));
		return URI.createFileURI(modelFile.getLocation().toString()
				+ XMOFConfigurationProfilePlugin.RUNTIME_EMFPROFILE_EXTENSION);
	}
}
