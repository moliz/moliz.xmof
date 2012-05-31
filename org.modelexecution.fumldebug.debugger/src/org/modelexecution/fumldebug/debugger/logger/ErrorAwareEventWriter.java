/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.logger;

import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.writer.EventWriter;
import org.modelexecution.fumldebug.debugger.process.internal.ErrorEvent;

/**
 * Subclasses {@link EventWriter} to also allow for printing {@link ErrorEvent}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class ErrorAwareEventWriter extends EventWriter {

	@Override
	protected String writeUnkownEvent(Event event) {
		if (event instanceof ErrorEvent) {
			ErrorEvent errorEvent = (ErrorEvent) event;
			StackTraceElement[] stackTrace = errorEvent.getError()
					.getStackTrace();
			StringBuffer sb = new StringBuffer();
			appendPrefix(sb, event);
			sb.append(errorEvent.getError().toString() + NL);
			for (StackTraceElement element : stackTrace) {
				sb.append(element.toString() + NL);
			}
			return sb.toString();
		} else {
			return super.writeUnkownEvent(event);
		}
	}

}
