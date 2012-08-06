/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.provider.internal;

import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * Util class offering convenience methods for {@link IActivityProvider activity
 * providers}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ActivityProviderUtil {

	public static ActivityNode getActivityNodeByName(String name,
			IActivityProvider provider) {
		for (Activity activity : provider.getActivities()) {
			ActivityNode activityNode = getActivityNodeByName(name, activity);
			if (activityNode != null) {
				return activityNode;
			}
		}
		return null;
	}

	private static ActivityNode getActivityNodeByName(String name,
			Activity activity) {
		for (ActivityNode node : activity.node) {
			if (hasName(node, name)) {
				return node;
			}
		}
		return null;
	}

	private static boolean hasName(ActivityNode node, String name) {
		return name.equals(node.name) || name.equals(node.qualifiedName);
	}

}
