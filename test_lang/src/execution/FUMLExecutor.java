package execution;

import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.StepEvent;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class FUMLExecutor {
	
	public FUMLExecutor(){}
	
	/**
	 * Converts the specified {@code activity} into fUML Activity and executes it.
	 */
	public void executeActivity(org.eclipse.uml2.uml.Activity activity){
		IConverter converter = ConverterRegistry.getInstance().getConverter(activity);
		Activity fumlActivity = (Activity)converter.convert(activity).getActivity(activity.getName());
		executeActivity(fumlActivity);
	}
	
	/**
	 * Executes the specified {@code activity}.
	 */
	private void executeActivity(Activity activity) {
		// register an anonymous event listener that prints the events
		// to system.out directly and calls resume after each step event.
		getExecutionContext().getExecutionEventProvider().addEventListener(
				new ExecutionEventListener() {
					@Override
					public void notify(Event event) {
						System.out.println("\tEvent: " + event.getClass().getSimpleName());
						if (event instanceof StepEvent) {
							StepEvent stepEvent = (StepEvent) event;
							getExecutionContext().resume(
									stepEvent.getActivityExecutionID());
						}
					}
				});

		// start the execution
		getExecutionContext().debug(activity, null, new ParameterValueList());
	}
	
	/**
	 * Obtains the singleton {@link ExecutionContext}.
	 * 
	 * @return the {@link ExecutionContext}.
	 */
	private ExecutionContext getExecutionContext() {
		return ExecutionContext.getInstance();
	}
}