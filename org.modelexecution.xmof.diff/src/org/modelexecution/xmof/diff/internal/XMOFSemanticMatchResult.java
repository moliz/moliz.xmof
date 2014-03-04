package org.modelexecution.xmof.diff.internal;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.vm.XMOFInstanceMap;

public class XMOFSemanticMatchResult {

	private Resource parameterResourceLeft;
	private Resource parameterResourceRight;
	
	private ConfigurationObjectMap configurationObjectMapLeft;
	private ConfigurationObjectMap configurationObjectMapRight;

	private StateSystem stateSystemLeft;
	private StateSystem stateSystemRight;

	private Resource stateSystemResourceLeft;
	private Resource stateSystemResourceRight;

	private XMOFInstanceMap instanceMapLeft;
	private XMOFInstanceMap instanceMapRight;
	
	private MatchTrace matchTraceSemantics;

	public ConfigurationObjectMap getConfigurationObjectMapLeft() {
		return configurationObjectMapLeft;
	}

	public void setConfigurationObjectMapLeft(
			ConfigurationObjectMap configurationObjectMapLeft) {
		this.configurationObjectMapLeft = configurationObjectMapLeft;
	}

	public ConfigurationObjectMap getConfigurationObjectMapRight() {
		return configurationObjectMapRight;
	}

	public void setConfigurationObjectMapRight(
			ConfigurationObjectMap configurationObjectMapRight) {
		this.configurationObjectMapRight = configurationObjectMapRight;
	}

	public StateSystem getStateSystemLeft() {
		return stateSystemLeft;
	}

	public void setStateSystemLeft(StateSystem stateSystemLeft) {
		this.stateSystemLeft = stateSystemLeft;
	}

	public StateSystem getStateSystemRight() {
		return stateSystemRight;
	}

	public void setStateSystemRight(StateSystem stateSystemRight) {
		this.stateSystemRight = stateSystemRight;
	}

	public Resource getStateSystemResourceLeft() {
		return stateSystemResourceLeft;
	}

	public void setStateSystemResourceLeft(Resource stateSystemResourceLeft) {
		this.stateSystemResourceLeft = stateSystemResourceLeft;
	}

	public Resource getStateSystemResourceRight() {
		return stateSystemResourceRight;
	}

	public void setStateSystemResourceRight(Resource stateSystemResourceRight) {
		this.stateSystemResourceRight = stateSystemResourceRight;
	}

	public XMOFInstanceMap getInstanceMapLeft() {
		return instanceMapLeft;
	}

	public void setInstanceMapLeft(XMOFInstanceMap instanceMapLeft) {
		this.instanceMapLeft = instanceMapLeft;
	}

	public XMOFInstanceMap getInstanceMapRight() {
		return instanceMapRight;
	}

	public void setInstanceMapRight(XMOFInstanceMap instanceMapRight) {
		this.instanceMapRight = instanceMapRight;
	}

	public MatchTrace getMatchTraceSemantics() {
		return matchTraceSemantics;
	}

	public void setMatchTraceSemantics(MatchTrace matchTraceSemantics) {
		this.matchTraceSemantics = matchTraceSemantics;
	}

	public Resource getParameterResourceLeft() {
		return parameterResourceLeft;
	}

	public void setParameterResourceLeft(Resource parameterResourceLeft) {
		this.parameterResourceLeft = parameterResourceLeft;
	}

	public Resource getParameterResourceRight() {
		return parameterResourceRight;
	}

	public void setParameterResourceRight(Resource parameterResourceRight) {
		this.parameterResourceRight = parameterResourceRight;
	}
	
}
