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
import fUML.Syntax.Classes.Kernel.NamedElement;

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
	 * Loads and returns all {@link Activity activities} for the specified
	 * <code>resource</code>.
	 * 
	 * @param resource
	 *            to get {@link Activity activities} from.
	 * @return the obtained {@link Activity activities}.
	 */
	Collection<Activity> loadActivities(IResource resource);

	/**
	 * Returns the source file name associated with the given
	 * {@code namedElement} that source needs to be found for, or
	 * <code>null</code> if none.
	 * 
	 * @param namedElement
	 *            the {@link NamedElement} for which source needs to be found.
	 * @return the source file name associated with the given debug artifact, or
	 *         <code>null</code> if none.
	 */
	String getSourceFileName(NamedElement namedElement);

	/**
	 * Notifies this activity provider to give free all resources associated
	 * with the specified activity.
	 * 
	 * If there are more activities than the one specified {@code activity},
	 * providers may still unload all of them.
	 * 
	 * @param activity
	 *            to be unloaded.
	 */
	void unload(Activity activity);

	/**
	 * Notifies this activity provider that the specified resource is not longer
	 * needed.
	 * 
	 * @param iResource
	 *            to be unloaded.
	 */
	void unload(IResource iResource);

}
