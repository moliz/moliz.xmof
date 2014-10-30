/*
* Copyright (c) 2014 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Tanja Mayerhofer - initial API and implementation
*/
package org.modelexecution.fuml.trace.uml2.tracemodel.provider;

import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution;
import org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterSetting;
import org.modelexecution.fuml.trace.uml2.tracemodel.Output;
import org.modelexecution.fuml.trace.uml2.tracemodel.Trace;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance;
import org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at) 
 *
 */
public class TraceElementTextUtil {

	public static String getTraceElementString(ValueSnapshot valueSnapshot) {
		int valueInstanceIndex = getIndexOfValueInstance(valueSnapshot);
		int valueSnapshotIndex = getIndexInValueInstance(valueSnapshot);
		return "vs" + valueInstanceIndex + "." + valueSnapshotIndex;
	}
	
	private static int getIndexOfValueInstance(ValueSnapshot valueSnapshot) {
		ValueInstance valueInstance = valueSnapshot.getValueInstance();
		Trace trace = (Trace)valueInstance.eContainer();
		int indexOfValueInstance = trace.getValueInstances().indexOf(valueInstance);
		return indexOfValueInstance;
	}

	private static int getIndexInValueInstance(ValueSnapshot valueSnapshot) {
		ValueInstance valueInstance = valueSnapshot.getValueInstance();
		int indexInValueInstance = valueInstance.getSnapshots().indexOf(valueSnapshot);
		return indexInValueInstance;
	}

	public static String getActivityNodeIdText(ActivityNodeExecution nodeExecution) {
		ActivityExecution activityExecution = nodeExecution.getActivityExecution();
		int activityExecutionID = activityExecution.getActivityExecutionID();
		int activityNodeExecutionIndex = activityExecution.getNodeExecutions().indexOf(nodeExecution); 
		return activityExecutionID + "." + activityNodeExecutionIndex;
	}
	
	public static String getTraceElementString(Output output) {
		OutputPin outputPin = output.getOutputPin();
		String outputString = "";
		if(outputPin != null)
			outputString += outputPin.getName();
		return outputString;
	}

	public static String getTraceElementString(InputParameterSetting inputParameterSetting) {
		Parameter inputParameter = inputParameterSetting.getParameter();
		String inputParameterSettingString = "";
		if(inputParameter != null)
			inputParameterSettingString += inputParameter.getName();
		return inputParameterSettingString;
	}

}
