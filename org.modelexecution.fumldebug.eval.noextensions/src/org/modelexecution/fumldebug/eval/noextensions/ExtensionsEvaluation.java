package org.modelexecution.fumldebug.eval.noextensions;

import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.eval.BuyScenarioAssert;
import org.modelexecution.fumldebug.eval.FindItemScenarioAssert;
import org.modelexecution.fumldebug.eval.LoginScenarioAssert;
import org.modelexecution.fumldebug.eval.PerformanceMeasurement;
import org.modelexecution.fumldebug.eval.noextensions.internal.ModelExecutor;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

public class ExtensionsEvaluation {

	private static final String PETSTORE_MODEL_PATH = "platform:/plugin/org.modelexecution.fumldebug.eval/model/petstore.uml";
	private static final String BUY_SCENARIO = "buyScenario";
	private static final String LOGIN_SCENARIO = "loginScenario";
	private static final String FIND_ITEM_SCENARIO = "findItemScenario";

	@BeforeClass
	public static void setupBeforeClass() {
		 turnOffLogging();
	}

	private static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	@Test
	public void buyScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, BUY_SCENARIO);
		ParameterValueList output = executor.execute();
		BuyScenarioAssert confirmOrderScenarioAssert = new BuyScenarioAssert(
				executor.getLocus(), output);
		confirmOrderScenarioAssert.doAssert();
	}
	
	@Test
	public void loginScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, LOGIN_SCENARIO);
		ParameterValueList output = executor.execute();
		LoginScenarioAssert loginScenarioAssert = new LoginScenarioAssert(
				executor.getLocus(), output);
		loginScenarioAssert.doAssert();
	}
	
	@Test
	public void findItemScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, FIND_ITEM_SCENARIO);
		ParameterValueList output = executor.execute();
		FindItemScenarioAssert findItemScenarioAssert = new FindItemScenarioAssert(
				executor.getLocus(), output);
		findItemScenarioAssert.doAssert();
	}
	
	@Test
	public void buyScenarioPerformance() {
		doPerformanceEvaluation(PETSTORE_MODEL_PATH, BUY_SCENARIO, 20, "result/buyScenarioPerformance.csv");
	}

	@Test
	public void loginScenarioPerformance() {
		doPerformanceEvaluation(PETSTORE_MODEL_PATH, LOGIN_SCENARIO, 20, "result/loginScenarioPerformance.csv");
	}
	
	@Test
	public void findItemScenarioPerformance() {
		doPerformanceEvaluation(PETSTORE_MODEL_PATH, FIND_ITEM_SCENARIO, 20, "result/findItemScenarioPerformance.csv");
	}
	
	private void doPerformanceEvaluation(String modelPath, String activityName, int numberMeasurements, String outputPath) {
		PerformanceMeasurement measurement = new PerformanceMeasurement(numberMeasurements);
		for(int i=0;i<numberMeasurements;++i) {
			ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, BUY_SCENARIO);			
			measurement.measureStarts();
			executor.execute();
			measurement.measureEnds();
		}
		measurement.printToFile(outputPath);
	}
}
