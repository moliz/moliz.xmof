package org.modelexecution.xmof.diff.test.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.modelexecution.xmof.diff.performance.TimeMeasurement;

public class Report {

	private TimeMeasurement time;

	public Report(TimeMeasurement time) {
		this.time = time;
	}

	public void printReportToFile(String filepath) {
		File file = new File(filepath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(printReport());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printReportToConsole() {
		System.out.println(printReport());
	}

	private String printReport() {
		StringBuffer str = new StringBuffer();
		str.append(printTimeMeasurement());
		return str.toString();
	}

	private String printTimeMeasurement() {
		StringBuffer str = new StringBuffer();
		str.append(time);
		str.append(System.getProperty("line.separator"));
		str.append(time.printSingleExecutionTimes());
		str.append(System.getProperty("line.separator"));
		str.append(time.printSingleSemanticMatchingTimes());
		return str.toString();
	}

}
