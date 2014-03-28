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
	
//	"== START ==".println();
//	"= Left =".println();
//	left.debugPrintln();
//	"= Right =".println();
//	right.debugPrintln();
	
	if(doActivityExeLeft.size() == doActivityExeRight.size()) {
		for(i in Sequence{0..doActivityExeLeft.size()-1}) {
			var exeLeft : ActivityExecution = doActivityExeLeft.at(i);
			var exeRight : ActivityExecution = doActivityExeRight.at(i);

			var contextSyntaxLeft = exeLeft.getContextSyntax();
			var contextSyntaxRight = exeRight.getContextSyntax();
			
			if (not contextSyntaxLeft.matches(contextSyntaxRight))
				return false;
		}
		return true;
	}  
	return false;
}	

operation ActivityExecution getContextSyntax() : EObject {
	var contextValueSnapshot : ValueSnapshot = self.getContext();
	var contextRuntimeValue = contextValueSnapshot.getRuntimeValue();		
	var context = getEObjectFromInstanceMap(contextRuntimeValue);
	var contextSyntax = getOriginalObjectFromConfigurationObjectMap(context);
	return contextSyntax;
}

operation StateSystem debugPrintln() {
	var doActivityExe : OrderedSet = sortChronologically(self.trace.getActivityExecutions("activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction"));
	for(i in Sequence{0..doActivityExe.size()-1}) {
		var exe : ActivityExecution = doActivityExe.at(i);
		var contextSyntax = exe.getContextSyntax();
		(i + ": " + contextSyntax.eGet(contextSyntax.eClass().getEStructuralFeature("name"))).println();
	}
}

operation StateSystem debugPrintlnErrorAware() {
	var doActivityExe : OrderedSet = sortChronologically(self.trace.getActivityExecutions("activitydiagramConfiguration.OpaqueActionConfiguration.doAction_opaqueAction"));
	for(i in Sequence{0..doActivityExe.size()-1}) {
		var exe : ActivityExecution = doActivityExe.at(i);
		("i="+i).println();
		var contextValueSnapshot : ValueSnapshot = exe.getContext();
		if(contextValueSnapshot == null)
			"contextValueSnapshot null".println();
		var contextRuntimeValue = contextValueSnapshot.getRuntimeValue();
		if(contextRuntimeValue == null)
			"contextRuntimeValue null".println();	
		var context = getEObjectFromInstanceMap(contextRuntimeValue);
		if(context == null)
			"context null".println();
		var contextSyntax = getOriginalObjectFromConfigurationObjectMap(context);
		if(contextSyntax == null)
			"contextSyntax null".println();
		("contextSyntax ="+contextSyntax).println(); 
	}
}