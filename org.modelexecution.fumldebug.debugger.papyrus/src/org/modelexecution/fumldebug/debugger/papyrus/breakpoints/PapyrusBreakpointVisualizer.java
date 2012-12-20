/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.papyrus.breakpoints;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.modelexecution.fumldebug.debugger.breakpoints.ActivityNodeBreakpoint;
import org.modelexecution.fumldebug.debugger.papyrus.decorations.BreakpointDecorator;
import org.modelexecution.fumldebug.debugger.papyrus.decorations.DebugDecoratorProvider;
import org.modelexecution.fumldebug.papyrus.util.DiResourceUtil;

/**
 * Visualizer for existing breakpoints in Papyrus diagrams.
 * 
 * TODO should consider existing breakpoints and also annotate editors that are just about to be opened.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class PapyrusBreakpointVisualizer implements IBreakpointListener {

	private static final String ACTIVITY_NODE_BREAKPOINT_DECORATION_ID_POSTFIX = "_activity_node_breakpoint"; //$NON-NLS-1$

	public static String getActivityNodeBreakpointDecorationId(View view) {
		return ViewUtil.getIdStr(view)
				+ ACTIVITY_NODE_BREAKPOINT_DECORATION_ID_POSTFIX;
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		if (isPapyrusBreakpoint(breakpoint)) {
			String qName = getBreakpointLocationName(breakpoint);
			if (qName != null) {
				addBreakpointAnnotation(qName, breakpoint);
			}
		}
	}

	private boolean isPapyrusBreakpoint(IBreakpoint breakpoint) {
		return breakpoint instanceof PapyrusActivityNodeBreakpoint;
	}

	private String getBreakpointLocationName(IBreakpoint breakpoint) {
		if (breakpoint instanceof ActivityNodeBreakpoint) {
			return ((ActivityNodeBreakpoint) breakpoint)
					.getQualifiedNameOfActivityNode();
		} else {
			return null;
		}
	}

	private void addBreakpointAnnotation(String qName, IBreakpoint breakpoint) {
		DecorationService service = getActiveDecorationService();
		if (service != null) {
			Collection<View> views = getViews(qName, breakpoint);
			for (View view : views) {
				String id = BreakpointDecorator.getId(view);
				service.removeDecoration(id);
				service.addDecoration(id, view, getEnabledBreakpointImage(),
						"BREAKPOINT");
				DebugDecoratorProvider.refreshDecorators(view, id);
			}
		}
	}

	private Collection<View> getViews(String qName, IBreakpoint breakpoint) {
		Collection<View> views = new HashSet<View>();
		try {
			Collection<Resource> diResources = getActiveDiResources();
			for (Resource diResource : diResources) {
				View view = DiResourceUtil
						.getNotationElement(qName, diResource);
				if (view != null) {
					// TODO check if model is in
					// breakpoint.getMarker().getResource()
					views.add(view);
				}
			}
		} catch (ServiceException e) {
		}
		return views;
	}

	private Collection<Resource> getActiveDiResources() throws ServiceException {
		Collection<Resource> resources = new HashSet<Resource>();
		TransactionalEditingDomain domain = getActiveTransactionalEditingDomain();
		if (domain != null) {
			for (Resource resource : domain.getResourceSet().getResources()) {
				if (DiResourceUtil.isDiResource(resource)) {
					resources.add(resource);
				}
			}
		}
		return resources;
	}

	private ImageDescriptor getEnabledBreakpointImage() {
		return DebugUITools
				.getImageDescriptor(IDebugUIConstants.IMG_OBJS_BREAKPOINT);
	}

	private TransactionalEditingDomain getActiveTransactionalEditingDomain()
			throws ServiceException {
		return ServiceUtilsForActionHandlers.getInstance()
				.getTransactionalEditingDomain();
	}

	private DecorationService getActiveDecorationService() {
		try {
			ServicesRegistry serviceRegistry = getActiveServiceRegistry();
			if (serviceRegistry != null) {
				return serviceRegistry.getService(DecorationService.class);
			}
		} catch (ServiceException e) {
		}
		return null;
	}

	private ServicesRegistry getActiveServiceRegistry() throws ServiceException {
		return ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (isPapyrusBreakpoint(breakpoint)) {
			String qName = getBreakpointLocationName(breakpoint);
			if (qName != null) {
				removeBreakpointAnnotation(qName, breakpoint);
			}
		}
	}

	private void removeBreakpointAnnotation(String qName, IBreakpoint breakpoint) {
		DecorationService service = getActiveDecorationService();
		if (service != null) {
			Collection<View> views = getViews(qName, breakpoint);
			for (View view : views) {
				String id = BreakpointDecorator.getId(view);
				service.removeDecoration(id);
				DebugDecoratorProvider.refreshDecorators(view, id);
			}
		}
	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (isPapyrusBreakpoint(breakpoint)) {
			String qName = getBreakpointLocationName(breakpoint);
			if (qName != null) {
				removeBreakpointAnnotation(qName, breakpoint);
				addBreakpointAnnotation(qName, breakpoint);
			}
		}
	}

}
