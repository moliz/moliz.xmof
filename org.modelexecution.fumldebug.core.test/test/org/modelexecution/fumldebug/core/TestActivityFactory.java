package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;


public class TestActivityFactory {
	
	private DecisionNode1TestActivityFactory decisionNode1Test;
	private DecisionNode2TestActivityFactory decisionNode2Test;
	private DecisionNode3TestActivityFactory decisionNode3Test;
	private DecisionNode4TestActivityFactory decisionNode4Test;
	private DecisionNode5TestActivityFactory decisionNode5Test;
	
	public TestActivityFactory() {		
	}
	
	public Activity createDecisionNodeTest1() {
		decisionNode1Test = new DecisionNode1TestActivityFactory();
		return decisionNode1Test.getActivity();
	}
	
	public Activity createDecisionNodeTest2() {
		decisionNode2Test = new DecisionNode2TestActivityFactory();
		return decisionNode2Test.getActivity();
	}
	
	public Activity createDecisionNodeTest3() {
		decisionNode3Test = new DecisionNode3TestActivityFactory();
		return decisionNode3Test.getActivity();
	}
	
	public Activity createDecisionNodeTest4() {
		decisionNode4Test = new DecisionNode4TestActivityFactory();
		return decisionNode4Test.getActivity();
	}
	
	public Activity createDecisionNodeTest5() {
		decisionNode5Test = new DecisionNode5TestActivityFactory();
		return decisionNode5Test.getActivity();
	}
	
	private class DecisionNode1TestActivityFactory {
		protected ValueSpecificationAction vs1;
		protected DecisionNode decision;
		protected ActivityParameterNode parameternode;
		protected Parameter parameter;
		protected ObjectFlow e1;
		protected ObjectFlow e2;
		protected Activity activity;
		
		DecisionNode1TestActivityFactory() {
			activity = ActivityFactory.createActivity("DecisionNode1TestActivity");
			vs1 = ActivityFactory.createValueSpecificationAction(activity, "specify 1", 1);
			decision = ActivityFactory.createDecisionNode(activity, "decision");
			parameter = ActivityFactory.createParameter(activity, "parameter", ParameterDirectionKind.out);
			parameternode = ActivityFactory.createActivityParameterNode(activity, "parameter node", parameter);
			e1 = ActivityFactory.createObjectFlow(activity, vs1.result, decision);
			e2 = ActivityFactory.createObjectFlow(activity, decision, parameternode, 1);
		}

		public ValueSpecificationAction getVs1() {
			return vs1;
		}

		public DecisionNode getDecision() {
			return decision;
		}

		public ActivityParameterNode getParameternode() {
			return parameternode;
		}

		public Parameter getParameter() {
			return parameter;
		}

		public ObjectFlow getE1() {
			return e1;
		}

		public ObjectFlow getE2() {
			return e2;
		}

		public Activity getActivity() {
			return activity;
		}
		
	}
	
	private class DecisionNode2TestActivityFactory extends DecisionNode1TestActivityFactory{
		protected ValueSpecificationAction vs2;
		protected ObjectFlow decisionInputFlow;
		
		DecisionNode2TestActivityFactory() {
			super();
			activity.name = "DecisionNode2TestActivity";
			vs2 = ActivityFactory.createValueSpecificationAction(activity, "specify 2", 2);
			
			LiteralInteger guardliteral = new LiteralInteger();
			guardliteral.value = 2;
			e2.guard = guardliteral;
			
			decisionInputFlow = ActivityFactory.createObjectFlow(activity, vs2.result, decision);
			decision.decisionInputFlow = decisionInputFlow;
		}

		public ValueSpecificationAction getVs2() {
			return vs2;
		}

		public ObjectFlow getDecisionInputFlow() {
			return decisionInputFlow;
		}
		
	}
	
	private class DecisionNode3TestActivityFactory extends DecisionNode2TestActivityFactory{
		
		DecisionNode3TestActivityFactory() {
			super();
			activity.name = "DecisionNode3TestActivity";
			
			LiteralBoolean guardliteral = new LiteralBoolean();
			guardliteral.value = true;
			e2.guard = guardliteral;
			
			decision.decisionInput = ExecutionContext.getInstance().getOpaqueBehavior("less");
		}
		
	}
	
	private class DecisionNode4TestActivityFactory extends DecisionNode3TestActivityFactory{
		protected Activity decisionBehavior;
		protected Parameter parameter_in;
		protected Parameter parameter_out;
		protected ActivityParameterNode parameternode_in;
		protected ActivityParameterNode parameternode_out;
		protected ObjectFlow e3;
		protected ValueSpecificationAction vs3;
		
		DecisionNode4TestActivityFactory() {
			super();
			activity.name = "DecisionNode4TestActivity";
			
			LiteralInteger guardliteral = new LiteralInteger();
			guardliteral.value = 3;
			e2.guard = guardliteral;
			
			decisionBehavior = ActivityFactory.createActivity("DecisionNode4Test DecisionBehavior");
			vs3 = ActivityFactory.createValueSpecificationAction(decisionBehavior, "specify 3", 3);			
			parameter_in = ActivityFactory.createParameter("parameter in", ParameterDirectionKind.in);
			parameter_out = ActivityFactory.createParameter("parameter in", ParameterDirectionKind.out);
			parameternode_in = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode in", parameter_in);
			parameternode_out = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode out", parameter_out);
			e3 = ActivityFactory.createObjectFlow(decisionBehavior, vs3.result, parameternode_out);
			decision.decisionInput = decisionBehavior;
		}

		public Activity getDecisionBehavior() {
			return decisionBehavior;
		}

		public Parameter getParameter_in() {
			return parameter_in;
		}

		public Parameter getParameter_out() {
			return parameter_out;
		}

		public ActivityParameterNode getParameternode_in() {
			return parameternode_in;
		}

		public ActivityParameterNode getParameternode_out() {
			return parameternode_out;
		}

		public ObjectFlow getE3() {
			return e3;
		}
		
	}
	
	private class DecisionNode5TestActivityFactory extends DecisionNode4TestActivityFactory{
		protected Parameter parameter_in2;
		protected ActivityParameterNode parameternode_in2;
		
		DecisionNode5TestActivityFactory() {
			super();
			activity.name = "DecisionNode5TestActivity";
			
			decisionBehavior.name = "DecisionNode5Test DecisionBehavior";
			parameter_in2 = ActivityFactory.createParameter("parameter in 2", ParameterDirectionKind.in);
			parameternode_in2 = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode in", parameter_in2);
		}

		public Parameter getParameter_in2() {
			return parameter_in2;
		}

		public ActivityParameterNode getParameternode_in2() {
			return parameternode_in2;
		}
		
	}
}
