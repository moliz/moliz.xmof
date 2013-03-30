package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;


public class TestActivityFactory {
	
	public class DecisionNodeTestActivity1 {
		protected ValueSpecificationAction vs1;
		protected DecisionNode decision;
		protected ActivityParameterNode parameternode;
		protected Parameter parameter;
		protected ObjectFlow e1;
		protected ObjectFlow e2;
		protected Activity activity;
		
		protected DecisionNodeTestActivity1() {
			activity = ActivityFactory.createActivity("DecisionNode1TestActivity");
			vs1 = ActivityFactory.createValueSpecificationAction(activity, "specify 1", 1);
			decision = ActivityFactory.createDecisionNode(activity, "decision");
			parameter = ActivityFactory.createParameter(activity, "parameter", ParameterDirectionKind.out);
			parameternode = ActivityFactory.createActivityParameterNode(activity, "parameter node", parameter);
			e1 = ActivityFactory.createObjectFlow(activity, vs1.result, decision);
			e2 = ActivityFactory.createObjectFlow(activity, decision, parameternode, 1);
		}
		
	}
	
	public class DecisionNodeTestActivity2 extends DecisionNodeTestActivity1{
		protected ValueSpecificationAction vs2;
		protected ObjectFlow decisionInputFlow;
		
		protected DecisionNodeTestActivity2() {
			super();
			activity.name = "DecisionNode2TestActivity";
			vs2 = ActivityFactory.createValueSpecificationAction(activity, "specify 2", 2);
			
			LiteralInteger guardliteral = new LiteralInteger();
			guardliteral.value = 2;
			e2.guard = guardliteral;
			
			decisionInputFlow = ActivityFactory.createObjectFlow(activity, vs2.result, decision);
			decision.decisionInputFlow = decisionInputFlow;
		}
		
	}
	
	public class DecisionNodeTestActivity3 extends DecisionNodeTestActivity2{
		
		protected DecisionNodeTestActivity3() {
			super();
			activity.name = "DecisionNode3TestActivity";
			
			LiteralBoolean guardliteral = new LiteralBoolean();
			guardliteral.value = true;
			e2.guard = guardliteral;
			
			decision.decisionInput = ExecutionContext.getInstance().getOpaqueBehavior("less");
		}
		
	}
	
	public class DecisionNodeTestActivity4 extends DecisionNodeTestActivity3{
		protected Activity decisionBehavior;
		protected Parameter parameter_in;
		protected Parameter parameter_in2;
		protected Parameter parameter_out;
		protected ActivityParameterNode parameternode_in;
		protected ActivityParameterNode parameternode_in2;
		protected ActivityParameterNode parameternode_out;
		protected ObjectFlow e3;
		protected ValueSpecificationAction vs3;
		
		protected DecisionNodeTestActivity4() {
			super();
			activity.name = "DecisionNode4TestActivity";
			
			LiteralInteger guardliteral = new LiteralInteger();
			guardliteral.value = 3;
			e2.guard = guardliteral;
			
			decisionBehavior = ActivityFactory.createActivity("DecisionNode4Test DecisionBehavior");
			vs3 = ActivityFactory.createValueSpecificationAction(decisionBehavior, "specify 3", 3);			
			parameter_in = ActivityFactory.createParameter("parameter in", ParameterDirectionKind.in);
			parameter_in2 = ActivityFactory.createParameter("parameter in 2", ParameterDirectionKind.in);			
			parameter_out = ActivityFactory.createParameter("parameter in", ParameterDirectionKind.out);
			parameternode_in = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode in", parameter_in);
			parameternode_in2 = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode in", parameter_in2);
			parameternode_out = ActivityFactory.createActivityParameterNode(decisionBehavior, "paramenternode out", parameter_out);
			e3 = ActivityFactory.createObjectFlow(decisionBehavior, vs3.result, parameternode_out);
			decision.decisionInput = decisionBehavior;
		}
		
	}
	
	public class DecisionNodeTestActivity5 extends DecisionNodeTestActivity3{
		protected ValueSpecificationAction vs1_2;
		protected ValueSpecificationAction vs1_3;
		protected InitialNode init;
		protected ForkNode fork;
		protected JoinNode join;
		protected ObjectFlow e3, e4, e5;
		protected ControlFlow c1, c2, c3, c4;
		
		protected DecisionNodeTestActivity5() {
			super();
			activity.name = "DecisionNode5TestActivity";
			
			init = ActivityFactory.createInitialNode(activity, "initial node");
			vs1_2 = ActivityFactory.createValueSpecificationAction(activity, "specify1 2", 1);
			vs1_3 = ActivityFactory.createValueSpecificationAction(activity, "specify1 3", 1);
			fork = ActivityFactory.createForkNode(activity, "fork");
			join = ActivityFactory.createJoinNode(activity, "join");
			
			e1.target = join;
			e2.source = vs1_2.result;
			e2.target = join;
			e2.guard = null;
			e3 = ActivityFactory.createObjectFlow(activity, vs1_3.result, join);
			e4 = ActivityFactory.createObjectFlow(activity, join, decision);
			e5 = ActivityFactory.createObjectFlow(activity, decision, parameternode);
			LiteralBoolean guardLiteral = new LiteralBoolean();
			guardLiteral.value = true;
			e5.guard = guardLiteral;
			c1 = ActivityFactory.createControlFlow(activity, init, fork);
			c2 = ActivityFactory.createControlFlow(activity, fork, vs1);
			c3 = ActivityFactory.createControlFlow(activity, fork, vs1_2);
			c4 = ActivityFactory.createControlFlow(activity, fork, vs1_3);
		}
		
	}
	
	public class DecisionNodeTestActivity6 extends DecisionNodeTestActivity3{
		protected ValueSpecificationAction vs0;
		protected MergeNode merge;
		protected ControlFlow c1;
		protected ObjectFlow e3, e4;
		
		protected DecisionNodeTestActivity6() {
			super();
			activity.name = "DecisionNode6TestActivity";
			vs0 = ActivityFactory.createValueSpecificationAction(activity, "specify 0", 0);
			merge = ActivityFactory.createMergeNode(activity, "merge");
			
			e1.source = vs0.result;
			e1.target = merge;
			e2.source = vs1.result;
			e2.target = merge;
			e2.guard = null;
			e3 = ActivityFactory.createObjectFlow(activity, merge, decision);
			e4 = ActivityFactory.createObjectFlow(activity, decision, parameternode, true);
			c1 = ActivityFactory.createControlFlow(activity, vs0, vs1);			
		}

	}
	
	public class DecisionNodeTestActivity7 extends DecisionNodeTestActivity6{
		protected ControlFlow c2, c3, c4;
		protected MergeNode merge2;
		
		protected DecisionNodeTestActivity7() {
			super();
			activity.name = "DecisionNode7TestActivity";
			merge2 = ActivityFactory.createMergeNode(activity, "merge2");
			c2 = ActivityFactory.createControlFlow(activity, vs0, merge2);	
			c3 = ActivityFactory.createControlFlow(activity, vs1, merge2);	
			c4 = ActivityFactory.createControlFlow(activity, merge2, vs2);
		}

	}
		
}
