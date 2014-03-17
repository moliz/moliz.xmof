package org.modelexecution.xmof.diff.performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class TimeMeasurement {

	public static final TimeMeasurement INSTANCE = new TimeMeasurement();
	
	private HashSet<MatchingTime> matchingTimes = new HashSet<MatchingTime>();
	
	private MatchingTime currentMatchingTime = null;
	private int index = -1;
	
	private TimeMeasurement() {
		
	}
	
	public void addMatchingTime() {
		currentMatchingTime = new MatchingTime(++index);
		Date date = new Date();
		long time = date.getTime();
		currentMatchingTime.setStartTime(time);
	}
	
	public void addSyntacticMatchingTime() {
		Date date = new Date();
		long time = date.getTime();
		currentMatchingTime.setSyntacticMatchingFinishTime(time);
	}
	
	public void addExecutionTime() {
		Date date = new Date();
		long time = date.getTime();
		currentMatchingTime.addExecutionFinishTime(time);
	}
	
	public void addSemanticMatchingTime() {
		Date date = new Date();
		long time = date.getTime();
		currentMatchingTime.addSemanticMatchingFinishTime(time);
	}
	
	public void finishMatchingTime() {
		matchingTimes.add(currentMatchingTime);
		currentMatchingTime = null;
	}
	
	public HashSet<MatchingTime> getMatchingTimes() {
		return matchingTimes;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("syntacticMatchingTime;executionTime;semanticMatchingTime");
		List<MatchingTime> timesSorted = new ArrayList<MatchingTime>(matchingTimes);
		Collections.sort(timesSorted);
		for(MatchingTime matchingTime : timesSorted) {
			str.append(System.getProperty("line.separator"));
			str.append(matchingTime.toString());
		}
		return str.toString();
	}
	
	public String printSingleExecutionTimes() {
		StringBuffer str = new StringBuffer();
		str.append("singleExecutionTimes");
		List<MatchingTime> timesSorted = new ArrayList<MatchingTime>(matchingTimes);
		for(MatchingTime matchingTime : timesSorted) {
			str.append(System.getProperty("line.separator"));
			str.append(matchingTime.printSingleExecutionTimes());
		}
		return str.toString();
	}
	
	public String printSingleSemanticMatchingTimes() {
		StringBuffer str = new StringBuffer();
		str.append("singleSemanticMatchingTimes");
		List<MatchingTime> timesSorted = new ArrayList<MatchingTime>(matchingTimes);
		for(MatchingTime matchingTime : timesSorted) {
			str.append(System.getProperty("line.separator"));
			str.append(matchingTime.printSingleSemanticMatchingTimes());
		}
		return str.toString();
	}
}
