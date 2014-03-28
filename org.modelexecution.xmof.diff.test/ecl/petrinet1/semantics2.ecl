import "../trace.eol";
import "syntax.ecl";

// final marking equivalence
rule MatchStateSystem
	match left : Left!StateSystem
	with right : Right!StateSystem {
		compare {
			"== Left ==".println();
			left.debugPrintln();
			"== Right ==".println();
			right.debugPrintln();
		
			var markingStatesLeft : Set = left.getMarkingStates();
			var markingStatesRight : Set = right.getMarkingStates();
			var leftMatchesRight : Boolean = markingStatesLeft.matches(markingStatesRight);
			var rightMatchesLeft : Boolean = markingStatesRight.matches(markingStatesLeft);
			return leftMatchesRight and rightMatchesLeft;
		}
	}

operation StateSystem getMarkingStates() : Set {
	return self.states.at(self.states.size()-1).asSet();
}

@lazy
rule MatchState
	match left : Left!State
	with right : Right!State {
		compare {
			var placeConfigurationsLeft : Set = left.getPlaceConfigurations();
			var placeConfigurationsRight : Set = right.getPlaceConfigurations();			
			var samePlaceSet : Boolean = placeConfigurationsLeft.size() == placeConfigurationsRight.size();
			var markingsMatch : Boolean = placeConfigurationsLeft.matches(placeConfigurationsRight);
			return samePlaceSet and markingsMatch;
		}
	}

operation State getPlaceConfigurations() : Set {
	var placeConfs : Set = new Set();
	for (object : Any in self.objects)
		if (object.isKindOf(PlaceConfiguration))
			placeConfs.add(object);	
	return placeConfs;
}

@lazy
rule MatchPlaceConfiguration
	match left : Left!PlaceConfiguration
	with right : Right!PlaceConfiguration
	extends MatchPlace {
		compare : left.tokens = right.tokens
	}
	
operation StateSystem debugPrintln() {
	var markingStates : Set = self.getMarkingStates();
	var i : Integer = 1;
	for(markingState : State in markingStates) {
		("Marking State" + i).println();
		markingState.debugPrintln();
		i = i + 1;
	}
}

operation State debugPrintln() {
	var placeConfigurations: Set = self.getPlaceConfigurations();
	for(placeConfiguration : PlaceConfiguration in placeConfigurations) {
		placeConfiguration.debugPrintln();
	}
}

operation PlaceConfiguration debugPrintln() {
	if(self.tokens > 0)
		(self.name + "=" + self.tokens).println();
}

operation Set matches(targetSet : Set) : Boolean {
	for (source : Any in self) {
		var sourceTargetMatchFound : Boolean = false;
		for (target : Any in targetSet) {
			if (source.matches(target)) 
				sourceTargetMatchFound = true;
		}
		if (not sourceTargetMatchFound) {
			return false;
		}
	}
	return true;
}