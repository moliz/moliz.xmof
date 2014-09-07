import "../trace.eol";
import "syntax.ecl";

//--------------------------------------------------------

rule MatchStateSystem
	match left : Left!StateSystem
	with right : Right!StateSystem {
		compare: true
	}
	
//--------------------------------------------------------

rule MatchTrace
	match left : Left!Trace
	with right : Right!Trace {
		compare {
			var finalTokenHoldingPlaceLinksLeft : Set = left.getFinalTokenHoldingPlaceLinks();
			var finalTokenHoldingPlaceLinksRight : Set = right.getFinalTokenHoldingPlaceLinks();
			return matches(finalTokenHoldingPlaceLinksLeft, finalTokenHoldingPlaceLinksRight) and
				matches(finalTokenHoldingPlaceLinksRight, finalTokenHoldingPlaceLinksLeft);
		}
	}

operation Trace getFinalTokenHoldingPlaceLinks() : Set {
	return self.valueInstances
		.select(vi | vi.destroyer = null and vi.runtimeValue.isTypeOf(Link)) // select value instances of all links existing at termination
		.select(vi | vi.runtimeValue.type.name = "holdingPlace") // select value instances of links being instances of association with name "holdingPlace"
		.collect(vi | vi.snapshots.get(vi.snapshots.size()-1)) // collect last snapshots of value instances
		.collect(s | s.value); // collect values of snapshots (Link instances)
}

@lazy
rule MatchLink
	match left : Left!Link
	with right : Right!Link {
		compare {
			if(matchExistsAndMatches(left, right) or matchExistsAndMatches(right, left))
				return true;
			var placeLeft : Object = left.getLinkedObject("holdingPlace");
			var placeRight : Object = right.getLinkedObject("holdingPlace");
			if (not matchExistsAndMatches(placeLeft) and not matchExistsAndMatches(placeRight))
				return matches(placeLeft, placeRight);
			return false;
		}
	}
	
operation matchExistsAndMatches(left : Any, right : Any) : Boolean {
	var existingMatch : Match = matchTrace.getMatch(left, right);
	if (existingMatch <> null) 
		return existingMatch.isMatching();
	return false;
}

operation matchExistsAndMatches(object : Any) : Boolean {
	return matchTrace.getMatches(object).select(m | m.matching).size() > 0;
}

operation Link getLinkedObject(endName : String) : Object {
	var end : Property = self.type.memberEnd.select(me | me.name = endName);
	var objectReference : Reference = self.getFeatureValue(end).values.get(0);
	return objectReference.referent;
}

@lazy
rule MatchPlace
	match left : Left!Object
	with right : Right!Object {
		guard : left.isPlaceObject() and right.isPlaceObject()
		compare {
			var placeLeft : Place = conversionResult.getInputObject(left);
			var placeRight : Place = conversionResult.getInputObject(right);
			return placeLeft.matches(placeRight); // correspondences established in syntactic matching
		}
	}
	
operation Object isPlaceObject() : Boolean {
	return self.types.select(t | t.name = "PlaceConfiguration").size() <> 0;
}
//operation Associatoin