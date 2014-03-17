package org.modelexecution.xmof.diff.performance;

import java.util.ArrayList;


public class MatchingTime implements Comparable<MatchingTime>{

	private int index = -1;
	private long startTime = -1;
	private long syntacticMatchingFinishedTime = -1;
	private ArrayList<Long> executionFinishedTimes = new ArrayList<Long>();
	private ArrayList<Long> semanticMatchingFinishedTimes = new ArrayList<Long>();

	public MatchingTime(int index) {
		this.index = index;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setSyntacticMatchingFinishTime(long syntacticMatchTime) {
		this.syntacticMatchingFinishedTime = syntacticMatchTime;
	}

	public void addExecutionFinishTime(long executionTime) {
		this.executionFinishedTimes.add(executionTime);
	}

	public void addSemanticMatchingFinishTime(long semanticMatchTime) {
		this.semanticMatchingFinishedTimes.add(semanticMatchTime);
	}
	
	public int getIndex() {
		return index;
	}
	
	public long getSyntacticMatchingTime() {
		return syntacticMatchingFinishedTime - startTime;
	}

	public ArrayList<Long> getExecutionFinishedTimes() {
		return executionFinishedTimes;
	}
	
	private long getExecutionFinishedTime() {
		return executionFinishedTimes.get(executionFinishedTimes.size()-1);
	}
		
	public long getExecutionTime() {
		return getExecutionFinishedTime() - syntacticMatchingFinishedTime;
	}
	
	public ArrayList<Long> getSemanticMatchingFinishedTimes() {
		return semanticMatchingFinishedTimes;
	}
	
	private long getSemanticMatchingFinishedTime() {
		return semanticMatchingFinishedTimes.get(semanticMatchingFinishedTimes.size()-1);
	}
		
	public long getSemanticMatchingTime() {
		return getSemanticMatchingFinishedTime() - getExecutionFinishedTime();
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(getSyntacticMatchingTime() + ";" + getExecutionTime() + ";" + getSemanticMatchingTime());
		return str.toString();
	}

	public String printSingleExecutionTimes() {
		StringBuffer str = new StringBuffer();
		long exe1finish = executionFinishedTimes.get(0);
		long exe1start = syntacticMatchingFinishedTime;
		str.append(exe1finish - exe1start);
		if(executionFinishedTimes.size() > 1) {
			for (int i=0;i<executionFinishedTimes.size()-1;++i) {
				str.append(";");
				long finish = executionFinishedTimes.get(i+1);
				long start = executionFinishedTimes.get(i);
				str.append(finish-start);
			}
		}
		return str.toString();
	}
	
	public String printSingleSemanticMatchingTimes() {
		StringBuffer str = new StringBuffer();
		long matching1finish = semanticMatchingFinishedTimes.get(0);
		long matching1start = getExecutionFinishedTime();
		str.append(matching1finish - matching1start);
		if(semanticMatchingFinishedTimes.size() > 1) {
			for (int i=0;i<semanticMatchingFinishedTimes.size()-1;++i) {
				str.append(";");
				long finish = semanticMatchingFinishedTimes.get(i+1);
				long start = semanticMatchingFinishedTimes.get(i);
				str.append(finish-start);
			}
		}
		return str.toString();
	}
	
	@Override
	public int compareTo(MatchingTime o) {
		return index - o.index;
	}
	
}
