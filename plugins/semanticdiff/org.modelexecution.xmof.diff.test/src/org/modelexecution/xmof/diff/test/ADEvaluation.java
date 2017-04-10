/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.diff.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.modelexecution.xmof.diff.XMOFSemanticMatchResult;
import org.modelexecution.xmof.diff.test.internal.TraceUtil;


/**
 * @author Tanja
 * 
 */
public abstract class ADEvaluation extends Evaluation {
	
	public void printTraceInformationToFile(String filepath, List<XMOFSemanticMatchResult> semanticMatchResults) {
		File file = new File(filepath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(printTraceInformation(semanticMatchResults));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printTraceInformationToConsole(List<XMOFSemanticMatchResult> semanticMatchResults) {
		System.out.println(printTraceInformation(semanticMatchResults));
	}
	
	public String printTraceInformation(List<XMOFSemanticMatchResult> semanticMatchResults) {
		StringBuffer str = new StringBuffer();
		str.append("input;#states left;#states right;#considered states left;#considered states right;#activity executions left;#activity executions right");
		for(int i=0;i<semanticMatchResults.size();++i) {
			str.append(System.getProperty("line.separator"));
			XMOFSemanticMatchResult semanticMatchResult = semanticMatchResults.get(i);			
			str.append((i+1) + ";");
			str.append(semanticMatchResult.getStateSystemLeft().getStates().size()  + ";");
			str.append(semanticMatchResult.getStateSystemRight().getStates().size()  + ";");
			str.append(TraceUtil.getActivityExecutions(semanticMatchResult.getStateSystemLeft().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size()  + ";");
			str.append(TraceUtil.getActivityExecutions(semanticMatchResult.getStateSystemRight().getTrace(), "activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction").size()  + ";");
			str.append(semanticMatchResult.getStateSystemLeft().getTrace().getActivityExecutions().size()  + ";");
			str.append(semanticMatchResult.getStateSystemRight().getTrace().getActivityExecutions().size());
		}
		return str.toString();
	}
	
}