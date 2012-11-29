package org.modelexecution.xmof.diagram;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

public class PropertyUtil {

	public static final String ACTION_TEXT_KIND_KEY = "textkind";
	public static final String ACTION_TEXT_KIND_TYPE = "type";
	public static final String ACTION_TEXT_KIND_NAME = "name";

	public static final String CALL_BEHAVIOR_ACTION_BEHAVIOR_KEY = "behavior";
	
	public static final String CALL_OPERATION_ACTION_OPERATION_KEY = "operation";

	public static void setActionTypeTextShape(PictogramElement pe) {
		Graphiti.getPeService().setPropertyValue(pe, ACTION_TEXT_KIND_KEY,
				ACTION_TEXT_KIND_TYPE);
	}

	public static boolean isActionTypeTextShape(PictogramElement pe) {
		return ACTION_TEXT_KIND_TYPE.equals(Graphiti.getPeService()
				.getPropertyValue(pe, ACTION_TEXT_KIND_KEY));
	}

	public static void setActionNameTextShape(PictogramElement pe) {
		Graphiti.getPeService().setPropertyValue(pe, ACTION_TEXT_KIND_KEY,
				ACTION_TEXT_KIND_NAME);
	}

	public static boolean isActionNameTextShape(PictogramElement pe) {
		return ACTION_TEXT_KIND_NAME.equals(Graphiti.getPeService()
				.getPropertyValue(pe, ACTION_TEXT_KIND_KEY));
	}

	public static void setCallBehaviorActionBehavior(PictogramElement pe,
			String behaviorName) {
		Graphiti.getPeService().setPropertyValue(pe,
				CALL_BEHAVIOR_ACTION_BEHAVIOR_KEY, behaviorName);
	}

	public static String getCallBehaviorActionBehavior(PictogramElement pe) {
		return Graphiti.getPeService().getPropertyValue(pe,
				CALL_BEHAVIOR_ACTION_BEHAVIOR_KEY);
	}

	public static void setCallOperationActionOperation(PictogramElement pe,
			String behaviorName) {
		Graphiti.getPeService().setPropertyValue(pe,
				CALL_OPERATION_ACTION_OPERATION_KEY, behaviorName);
	}

	public static String getCallOperationActionOperation(PictogramElement pe) {
		return Graphiti.getPeService().getPropertyValue(pe,
				CALL_OPERATION_ACTION_OPERATION_KEY);
	}
}
