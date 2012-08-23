/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.event.writer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;

import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.NamedElement;

/**
 * Writes human readable strings for {@link Event events}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class EventWriter {

	private static final String DATE_FORMAT = "HH:mm:ss:SSS";
	private static final String EXITING = "Exiting ";
	private static final String ENTERING = "Entering ";
	private static final String STEP = "Step ";
	private static final String BREAKPOINT = "Hit breakpoint ";
	private static final String VALUE_CHANGE = "Value has changed: ";
	protected static final Object NL = "\n";

	protected DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * Returns a human-readable string representation of the specified
	 * {@code event}.
	 * 
	 * @param event
	 *            to get a human-readable representation of.
	 * @return the human-readable representation.
	 */
	public String write(Event event) {
		if (event instanceof ActivityNodeEvent) {
			return write((ActivityNodeEvent) event);
		} else if (event instanceof ActivityEvent) {
			return write((ActivityEvent) event);
		} else if (event instanceof SuspendEvent) {
			return write((SuspendEvent) event);
		} else if (event instanceof BreakpointEvent) {
			return write((BreakpointEvent) event);
		} else if (event instanceof ExtensionalValueEvent) {
			return write((ExtensionalValueEvent) event);
		} else {
			return writeUnkownEvent(event);
		}
	}

	private String write(ActivityNodeEvent event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		if (event instanceof ActivityNodeEntryEvent) {
			sb.append(ENTERING);
		} else {
			sb.append(EXITING);
		}
		sb.append(event.getNode().qualifiedName
				+ " (" + event.getActivityExecutionID() //$NON-NLS-1$
				+ ")"); //$NON-NLS-1$
		sb.append(NL);
		return sb.toString();
	}

	private String write(ActivityEvent event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		if (event instanceof ActivityEntryEvent) {
			sb.append(ENTERING);
		} else {
			sb.append(EXITING);
		}
		sb.append(event.getActivity().qualifiedName
				+ " (" + event.getActivityExecutionID() //$NON-NLS-1$
				+ ")"); //$NON-NLS-1$
		sb.append(NL);
		return sb.toString();
	}

	private String write(SuspendEvent event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		sb.append(STEP + "at " + getName(event.getLocation()));
		sb.append(NL);
		return sb.toString();
	}

	private String write(BreakpointEvent event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		sb.append(BREAKPOINT + "at ");
		for(int i=0;i<event.getBreakpoints().size();++i) {
			sb.append(getName(event.getBreakpoints().get(i).getActivityNode()) + " ");
		}
		
		sb.append(NL);
		return sb.toString();
	}

	private String write(ExtensionalValueEvent event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		sb.append(VALUE_CHANGE);
		switch (event.getType()) {
		case CREATION:
			sb.append("Created " + event.getExtensionalValue());
			break;
		case DESTRUCTION:
			sb.append("Destroyed " + event.getExtensionalValue());
			break;
		case TYPE_ADDED:
			sb.append("Added type " + event.getExtensionalValue());
			break;
		case TYPE_REMOVED:
			sb.append("Removed type " + event.getExtensionalValue());
			break;
		case VALUE_CHANGED:
			sb.append("Changed value " + event.getExtensionalValue());
			break;
		case VALUE_CREATION:
			sb.append("Created value " + event.getExtensionalValue());
			break;
		case VALUE_DESTRUCTION:
			sb.append("Destroyed value " + event.getExtensionalValue());
			break;
		}
		sb.append(NL);
		return sb.toString();
	}

	protected String writeUnkownEvent(Event event) {
		StringBuffer sb = new StringBuffer();
		appendPrefix(sb, event);
		sb.append(event.toString() + NL);
		return sb.toString();
	}

	protected String getName(Element element) {
		if (element instanceof NamedElement) {
			return ((NamedElement) element).qualifiedName;
		}
		return element.toString();
	}

	protected void appendPrefix(StringBuffer sb, Event event) {
		Date date = new Date(event.getTimestamp());
		sb.append("[" + dateFormat.format(date) + "] ");
	}

}
