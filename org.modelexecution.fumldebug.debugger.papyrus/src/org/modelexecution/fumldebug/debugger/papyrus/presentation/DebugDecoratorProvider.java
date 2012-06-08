/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.presentation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.ActivityNode;
import org.modelexecution.fumldebug.debugger.FUMLDebuggerPlugin;

/**
 * A decorator provider for debug decorators.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class DebugDecoratorProvider extends AbstractProvider implements
		IDecoratorProvider {

	protected static final String KEY = "debugStatus"; //$NON-NLS-1$

	private static Map<String, IDecorator> allDecorators = new HashMap<String, IDecorator>();

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateDecoratorsOperation) {
			CreateDecoratorsOperation cdOperation = (CreateDecoratorsOperation) operation;
			IDecoratorTarget decoratorTarget = cdOperation.getDecoratorTarget();
			View view = (View) decoratorTarget.getAdapter(View.class);
			return provideDecorationForView(view);
		}
		return false;
	}

	private boolean provideDecorationForView(View view) {
		return view != null && isActivityNodeView(view);
	}

	private boolean isActivityNodeView(View view) {
		return view.getElement() instanceof ActivityNode;
	}

	@Override
	public void createDecorators(IDecoratorTarget decoratorTarget) {
		decoratorTarget.installDecorator(KEY, new DebugDecorator(
				decoratorTarget));
	}

	public static void addDecorator(String viewID, IDecorator decorator) {
		allDecorators.put(viewID, decorator);
	}

	public static void removeDecorator(String viewID) {
		allDecorators.remove(viewID);
	}

	public static void refreshDecorators(View view) {
		refreshDecorators(ViewUtil.getIdStr(view),
				TransactionUtil.getEditingDomain(view));
	}

	private static void refreshDecorators(String viewId,
			TransactionalEditingDomain domain) {
		final IDecorator decorator = getDecorator(viewId);
		if (decorator != null && domain != null) {
			refreshExclusively(domain, decorator);
		}
	}

	private static IDecorator getDecorator(String viewId) {
		return allDecorators.get(viewId);
	}

	private static void refreshExclusively(
			final TransactionalEditingDomain domain, final IDecorator decorator) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				try {
					domain.runExclusive(new Runnable() {
						public void run() {
							decorator.refresh();
						}
					});
				} catch (Exception e) {
					FUMLDebuggerPlugin.log(e);
				}
			}
		});
	}

}
