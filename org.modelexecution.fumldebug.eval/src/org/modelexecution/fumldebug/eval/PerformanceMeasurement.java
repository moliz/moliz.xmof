package org.modelexecution.fumldebug.eval;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PerformanceMeasurement {

	private Long[] executionTimes;
	private int counter = 0;

	private long start = -1;
	private long end = -1;

	public PerformanceMeasurement(int numberMeasurements) {
		executionTimes = new Long[numberMeasurements];
	}

	public void measureStarts() {
		start = new Date().getTime();
	}

	public void measureEnds() {
		end = new Date().getTime();
		long executionTime = end - start;
		executionTimes[counter++] = executionTime;
	}

	@Override
	public String toString() {
		String lineSeparator = System.getProperty("line.separator");

		StringBuffer str = new StringBuffer();
		str.append("index;executionTime");
		str.append(lineSeparator);
		for (int i = 0; i < executionTimes.length; ++i) {
			str.append(i);
			str.append(";");
			str.append(executionTimes[i]);
			str.append(lineSeparator);
		}

		return str.toString();
	}

	public void printToFile(String filepath) {
		File file = new File(filepath);
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(this.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
