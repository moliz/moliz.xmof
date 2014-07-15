package org.modelexecution.fumldebug.eval.extensions;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.eval.BuyScenarioAssert;
import org.modelexecution.fumldebug.eval.FindItemScenarioAssert;
import org.modelexecution.fumldebug.eval.LoginScenarioAssert;
import org.modelexecution.fumldebug.eval.PerformanceMeasurement;
import org.modelexecution.fumldebug.eval.extensions.internal.ModelExecutor;

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

	private static String turnOffLogging() {
		return System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	@Before
	public void setupBeforeTest() {
		getExecutionContext().reset();
	}

	@Test
	public void buyScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, BUY_SCENARIO);
		int rootExecutionID = executor.executeStepwise();
		ParameterValueList output = getExecutionContext().getActivityOutput(
				rootExecutionID);
		BuyScenarioAssert confirmOrderScenarioAssert = new BuyScenarioAssert(
				getExecutionContext().getLocus(), output);
		confirmOrderScenarioAssert.doAssert();
	}

	@Test
	public void loginScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, LOGIN_SCENARIO);
		int rootExecutionID = executor.executeStepwise();
		ParameterValueList output = getExecutionContext().getActivityOutput(
				rootExecutionID);
		LoginScenarioAssert loginScenarioAssert = new LoginScenarioAssert(
				getExecutionContext().getLocus(), output);
		loginScenarioAssert.doAssert();
	}

	@Test
	public void findItemScenario() {
		ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, FIND_ITEM_SCENARIO);
		int rootExecutionID = executor.executeStepwise();
		ParameterValueList output = getExecutionContext().getActivityOutput(
				rootExecutionID);
		FindItemScenarioAssert findItemScenarioAssert = new FindItemScenarioAssert(
				getExecutionContext().getLocus(), output);
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

	public ExecutionContext getExecutionContext() {
		return ExecutionContext.getInstance();
	}

	private void doPerformanceEvaluation(String modelPath, String activityName, int numberMeasurements, String outputPath) {
		PerformanceMeasurement measurement = new PerformanceMeasurement(numberMeasurements);
		for(int i=0;i<numberMeasurements;++i) {
			ModelExecutor executor = new ModelExecutor(PETSTORE_MODEL_PATH, BUY_SCENARIO);			
			measurement.measureStarts();
			executor.executeStepwise();
			measurement.measureEnds();
		}
		measurement.printToFile(outputPath);
	}
}
