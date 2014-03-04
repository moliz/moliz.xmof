import "../trace.eol";

rule MatchStateSystems
	match left : Left!StateSystem
	with right : Right!StateSystem
	{
		compare : compareStateSystems(left, right)
	}

operation compareStateSystems(left : Left!StateSystem, right : Right!StateSystem) : Boolean {
	var mainActivityExeLeft : ActivityExecution = left.trace.getActivityExecutions("classesConfiguration.ModelConfiguration.main").asSequence().at(0);
	var mainActivityExeRight : ActivityExecution = right.trace.getActivityExecutions("classesConfiguration.ModelConfiguration.main").asSequence().at(0);

	var mainActivityOutputLeft : ValueSnapshot = mainActivityExeLeft.getActivityOutputs().get(0).getParameterValues().get(0).getValueSnapshot();
	var mainActivityOutputRight : ValueSnapshot = mainActivityExeRight.getActivityOutputs().get(0).getParameterValues().get(0).getValueSnapshot();
	
	"left".println();
	mainActivityOutputLeft.getValue().println();
	"right".println();
	mainActivityOutputRight.getValue().println();
	
	return mainActivityOutputLeft.getValue().equals(mainActivityOutputRight.getValue());
}