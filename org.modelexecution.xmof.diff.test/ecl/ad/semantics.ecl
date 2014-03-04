import "../trace.eol";

rule MatchStateSystems
	match left : Left!StateSystem
	with right : Right!StateSystem
	{
		compare : compareStateSystems(left, right)
	}

operation compareStateSystems(left : Left!StateSystem, right : Right!StateSystem) : Boolean {	
	var doActivityExeLeft : OrderedSet = sortChronologically(left.trace.getActivityExecutions("activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction"));
	var doActivityExeRight : OrderedSet = sortChronologically(right.trace.getActivityExecutions("activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction"));
	
	if(doActivityExeLeft.size() == doActivityExeRight.size()) {
		for(i in Sequence{0..doActivityExeLeft.size()-1}) {
			var exeLeft : ActivityExecution = doActivityExeLeft.at(i);
			var exeRight : ActivityExecution = doActivityExeRight.at(i);

			var contextLeft = exeLeft.getContext();
			var contextRight = exeRight.getContext();			

			var contextSyntaxLeft = getOriginalObjectFromConfigurationObjectMap(contextLeft);
			var contextSyntaxRight = getOriginalObjectFromConfigurationObjectMap(contextRight);

			if (contextSyntaxLeft.matches(contextSyntaxRight)) {
				var stateLeft : State = left.getStateAfterActivityExecution(exeLeft);
				var stateRight : State = right.getStateAfterActivityExecution(exeRight);
				if (not stateLeft.matches(stateRight)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} 
	return true;
}

@lazy
rule MatchStates
	match left : Left!State
	with right : Right!State
	{
		compare : compareStates(left, right)
	}
	
operation compareStates(left : Left!State, right : Right!State) : Boolean {
	var stateSystemLeft : StateSystem = left.eContainer;
	var stateSystemRight : StateSystem = right.eContainer;

	var varConfsLeft : Set = left.collectVariableConfigurations();
	var varConfsRight : Set = right.collectVariableConfigurations();
	for(varConfLeft : VariableConfiguration in varConfsLeft) {
		var varConfSyntaxLeft = getOriginalObjectFromConfigurationObjectMap(stateSystemLeft.getOriginalObjectState(left, varConfLeft));
		for(varConfRight : VariableConfiguration in varConfsRight) {
			var varConfSyntaxRight = getOriginalObjectFromConfigurationObjectMap(stateSystemRight.getOriginalObjectState(right, varConfRight));

			if(varConfSyntaxLeft==null or varConfSyntaxRight==null) {
				return false;
			}
			if(varConfSyntaxLeft.matches(varConfSyntaxRight)) {
				if(not varConfLeft.currentValue.matches(varConfRight.currentValue)) {
					return false;
				}
			}
		}
	}
	return true;
}

operation State collectVariableConfigurations() : Set {
	var varConfs : Set = new Set();
	for (object : Any in self.objects) {
		if (object.isKindOf(VariableConfiguration)) {
			varConfs.add(object);
		}	
	}
	return varConfs;
}

@lazy
rule MatchIntegerValueConfigurations
	match v1 : Left!IntegerValueConfiguration
	with v2 : Right!IntegerValueConfiguration
	{
		compare: v1.value = v2.value
	}	