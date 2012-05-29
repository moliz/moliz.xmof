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

import org.eclipse.core.resources.IResource;
import org.modelexecution.fumldebug.debugger.provider.internal.CompoundActivityProviderFactory;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

/**
 * Factory for creating {@link IActivityProvider activity providers} for
 * {@link IResource resources}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public interface IActivityProviderFactory {

	/**
	 * A factory that uses the global instance to create activity providers.
	 */
	public static final IActivityProviderFactory instance = new CompoundActivityProviderFactory();

	/**
	 * Specifies whether this factory can create an {@link IActivityProvider}
	 * for the specified <code>resource</code>.
	 * 
	 * @param resource
	 *            to check.
	 * @return <code>true</code> if a factory can be created, otherwise
	 *         <code>false</code>.
	 */
	boolean supports(IResource resource);

	/**
	 * Creates and returns an {@link IActivityProvider} for the specified
	 * <code>resource</code>.
	 * 
	 * @param resource
	 *            to get {@link Activity activities} from.
	 * @return the obtained {@link Activity activities}.
	 */
	IActivityProvider createActivityProvider(IResource resource);

}
