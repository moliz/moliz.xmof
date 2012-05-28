/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger;

import java.util.Collection;

import org.eclipse.core.resources.IResource;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Provider for obtaining {@link Activity activities} from {@link IResource
 * resources}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public interface IActivityProvider {

	/**
	 * Specifies whether this provider can provide {@link Activity activities}
	 * for the specified <code>resource</code>.
	 * 
	 * @param resource
	 *            to check.
	 * @return <code>true</code> if one or more {@link Activity activities} can
	 *         be provided, otherwise <code>false</code>.
	 */
	boolean canProvide(IResource resource);

	/**
	 * Obtains the {@link Activity activities} for the specified
	 * <code>resource</code>.
	 * 
	 * @param resource
	 *            to get {@link Activity activities} from.
	 * @return the obtained {@link Activity activities}.
	 */
	Collection<Activity> getActivities(IResource resource);

}
