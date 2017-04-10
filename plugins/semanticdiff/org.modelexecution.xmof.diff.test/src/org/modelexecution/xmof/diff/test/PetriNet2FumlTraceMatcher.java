package org.modelexecution.xmof.diff.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

public class PetriNet2FumlTraceMatcher {

	private MatchTrace matchTrace = new MatchTrace();

	public boolean matchTrace(Trace left, Trace right) {
		Set<Link> finalTokenHoldingPlaceLinksLeft = getFinalTokenHoldingPlaceLinks(left);
		Set<Link> finalTokendHoldingPlaceLinksRight = getFinalTokenHoldingPlaceLinks(right);
		return matches(finalTokenHoldingPlaceLinksLeft,
				finalTokendHoldingPlaceLinksRight)
				&& matches(finalTokendHoldingPlaceLinksRight,
						finalTokenHoldingPlaceLinksLeft);
	}

	private Set<Link> getFinalTokenHoldingPlaceLinks(Trace trace) {
		Set<Link> finalTokenHoldingPlaceLinks = new HashSet<Link>();
		for (ValueInstance vi : trace.getValueInstances()) {
			if (vi.getDestroyer() == null
					&& vi.getRuntimeValue() instanceof Link) {
				Link link = (Link) vi.getRuntimeValue();
				Association association = link.type;
				if (association.name.equals("holdingPlace")) {
					if (vi.getSnapshots().size() == 0)
						finalTokenHoldingPlaceLinks.add((Link) vi
								.getRuntimeValue());
					else
						finalTokenHoldingPlaceLinks.add((Link) vi
								.getSnapshots()
								.get(vi.getSnapshots().size() - 1).getValue());
				}
			}
		}
		return finalTokenHoldingPlaceLinks;
	}

	private boolean matches(Set<Link> sourceSet, Set<Link> targetSet) {
		for (Link source : sourceSet) {
			boolean sourceTargetMatchFound = false;
			for (Link target : targetSet) {
				if (matchLink(source, target))
					sourceTargetMatchFound = true;
			}
			if (!sourceTargetMatchFound)
				return false;
		}
		return true;
	}

	private boolean matchLink(Link left, Link right) {
		boolean matchLeftRight = matchTrace.getMatch(left, right) != null ? matchTrace
				.getMatch(left, right).matching : false;
		boolean matchRightLeft = matchTrace.getMatch(right, left) != null ? matchTrace
				.getMatch(right, left).matching : false;

		if (matchLeftRight || matchRightLeft)
			return true;

		Match match = new Match(left, right);

		Object_ placeLeft = getLinkedObject(left, "holdingPlace");
		Object_ placeRight = getLinkedObject(right, "holdingPlace");

		if (placeLeft != null && placeRight != null
				&& matches(placeLeft, placeRight)) {
			if (!hasMatchingMatch(left) && !hasMatchingMatch(right))
				match.matching = true;
		}
		matchTrace.matches.add(match);
		return match.matching;
	}

	private boolean matches(Object_ placeLeft, Object_ placeRight) {
		if (placeLeft.types.get(0).name.equals("PlaceConfiguration")
				&& placeRight.types.get(0).name.equals("PlaceConfiguration")) { // guard
			String placeNameLeft = ((StringValue) getFeatureValue(placeLeft,
					"name").values.get(0)).value;
			String placeNameRight = ((StringValue) getFeatureValue(placeRight,
					"name").values.get(0)).value;
			return placeNameLeft.equals(placeNameRight);
		}
		return false;
	}

	private FeatureValue getFeatureValue(Object_ object, String featureName) {
		for (FeatureValue featureValue : object.featureValues) {
			if (featureValue.feature.name.equals(featureName))
				return featureValue;
		}
		return null;
	}

	private boolean hasMatchingMatch(Object object) {
		for (Match match : matchTrace.getMatches(object)) {
			if (match.matching)
				return true;
		}
		return false;
	}

	private Object_ getLinkedObject(Link link, String endName) {
		StructuralFeature end = getStructuralFeature(link.type, endName);
		return ((Reference) link.getFeatureValue(end).values.get(0)).referent;
	}

	private StructuralFeature getStructuralFeature(Association association,
			String featureName) {
		for (Property feature : association.memberEnd) {
			if (feature.name.equals(featureName))
				return feature;
		}
		return null;
	}

	class MatchTrace {
		List<Match> matches = new ArrayList<Match>();

		List<Match> getMatches(Object object) {
			List<Match> matches = new ArrayList<Match>();
			for (Match match : this.matches) {
				if (match.left == object || match.right == object)
					matches.add(match);
			}
			return matches;
		}

		Match getMatch(Object left, Object right) {
			for (Match match : matches) {
				if (match.left == left && match.right == right) {
					return match;
				}
			}
			return null;
		}
	}

	class Match {
		Object left;
		Object right;
		boolean matching = false;

		Match(Object left, Object right) {
			this.left = left;
			this.right = right;
		}
	}
}