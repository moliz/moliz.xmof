/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core;

import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.TokenList;

/**
 * @author  Tanja Mayerhofer
 */
public class ActivationConsumedTokens {
	private ActivityNodeActivation activation;
	private TokenList tokens;

	public ActivationConsumedTokens(ActivityNodeActivation activation, TokenList tokens) {
		this.activation = activation;
		this.tokens = tokens;
	}
	
	public ActivityNodeActivation getActivation() {
		return activation;
	}
	
	public void setActivation(ActivityNodeActivation activation) {
		this.activation = activation;
	}
	
	public TokenList getTokens() {
		return tokens;
	}
	
	public void setTokens(TokenList tokens) {
		this.tokens = tokens;
	}
}