/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.provider;

import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.model.IDebugTarget;

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
	 * Returns an identifier for the provided model type.
	 * 
	 * For instance, &quot;org.modelexecution.fumldebug.debugger.papyrus&quot;
	 * to refer to models coming from the UML2 Papyrus editor.
	 * 
	 * This ID is used as {@link IDebugTarget#getModelIdentifier()}. Thus
	 * presentation extensions for this type of models should use this ID too in
	 * the extension point declaration.
	 * 
	 * @return the model type identifier.
	 */
	String getModelTypeIdentifier();

	/**
	 * Returns the resource for which this provider provides activities.
	 * 
	 * @return the resource.
	 */
	IResource getResource();

	/**
	 * Returns all {@link Activity activities}.
	 * 
	 * @return all {@link Activity activities}.
	 */
	Collection<Activity> getActivities();

	/**
	 * Returns the {@link Activity} with the specified {@code name} or
	 * <code>null</code> if no {@link Activity} with the specified {@code name}
	 * exists.
	 * 
	 * @param name
	 *            the name of the {@link Activity} to obtain.
	 * @return the {@link Activity} having the specified name or
	 *         <code>null</code> if not existing.
	 */
	Activity getActivity(String name);

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
	 * Notifies this activity provider to give free all resources.
	 */
	void unload();

}
