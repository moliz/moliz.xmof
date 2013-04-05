package org.modelexecution.fumldebug.core;

import java.util.Arrays;

import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityFinalNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlFlow;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Activities.IntermediateActivities.InitialNode;
import fUML.Syntax.Activities.IntermediateActivities.JoinNode;
import fUML.Syntax.Activities.IntermediateActivities.MergeNode;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;


public class TestActivityFactory {
	
	protected class DecisionNodeTestActivity1 {
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
	
	protected class DecisionNodeTestActivity2 extends DecisionNodeTestActivity1{
		protected ValueSpecificationAction vs2;
		protected ObjectFlow decisionInputFlow;
		protected ControlFlow c1;
		
		protected DecisionNodeTestActivity2() {
			super();
			activity.name = "DecisionNode2TestActivity";
			vs2 = ActivityFactory.createValueSpecificationAction(activity, "specify 2", 2);
			
			LiteralInteger guardliteral = new LiteralInteger();
			guardliteral.value = 2;
			e2.guard = guardliteral;
			
			decisionInputFlow = ActivityFactory.createObjectFlow(activity, vs2.result, decision);
			decision.decisionInputFlow = decisionInputFlow;
			c1 = ActivityFactory.createControlFlow(activity, vs1, vs2);
		}
		
	}
	
	protected class DecisionNodeTestActivity3 extends DecisionNodeTestActivity2{
		
		protected DecisionNodeTestActivity3() {
			super();
			activity.name = "DecisionNode3TestActivity";
			
			LiteralBoolean guardliteral = new LiteralBoolean();
			guardliteral.value = true;
			e2.guard = guardliteral;
			
			decision.decisionInput = ExecutionContext.getInstance().getOpaqueBehavior("less");
		}
		
	}
	
	protected class DecisionNodeTestActivity4 extends DecisionNodeTestActivity3{
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
	
	protected class DecisionNodeTestActivity5 extends DecisionNodeTestActivity3{
		protected ValueSpecificationAction vs1_2;
		protected ValueSpecificationAction vs1_3;
		protected InitialNode init;
		protected ForkNode fork;
		protected JoinNode join;
		protected ObjectFlow e3, e4, e5;
		protected ControlFlow c2, c3, c4;
		
		protected DecisionNodeTestActivity5() {
			super();
			activity.name = "DecisionNode5TestActivity";
			
			init = ActivityFactory.createInitialNode(activity, "initial node");
			vs1_2 = ActivityFactory.createValueSpecificationAction(activity, "specify1 2", 1);
			vs1_3 = ActivityFactory.createValueSpecificationAction(activity, "specify1 3", 1);
			fork = ActivityFactory.createForkNode(activity, "fork");
			join = ActivityFactory.createJoinNode(activity, "join");
			
			TestActivityFactory.modifyEdge(e1, vs1.result, join);
			TestActivityFactory.modifyEdge(e2, vs1_2.result, join);
			e2.guard = null;
			e3 = ActivityFactory.createObjectFlow(activity, vs1_3.result, join);
			e4 = ActivityFactory.createObjectFlow(activity, join, decision);
			e5 = ActivityFactory.createObjectFlow(activity, decision, parameternode);
			LiteralBoolean guardLiteral = new LiteralBoolean();
			guardLiteral.value = true;
			e5.guard = guardLiteral;
			TestActivityFactory.modifyEdge(c1, init, fork);
			c2 = ActivityFactory.createControlFlow(activity, fork, vs1);
			c3 = ActivityFactory.createControlFlow(activity, fork, vs1_2);
			c4 = ActivityFactory.createControlFlow(activity, fork, vs1_3);
		}
		
	}
	
	protected class DecisionNodeTestActivity6 extends DecisionNodeTestActivity3{
		protected ValueSpecificationAction vs0;
		protected MergeNode merge;
		protected ObjectFlow e3, e4;
		
		protected DecisionNodeTestActivity6() {
			super();
			activity.name = "DecisionNode6TestActivity";
			vs0 = ActivityFactory.createValueSpecificationAction(activity, "specify 0", 0);
			merge = ActivityFactory.createMergeNode(activity, "merge");
			
			TestActivityFactory.modifyEdge(e1, vs0.result, merge);
			TestActivityFactory.modifyEdge(e2, vs1.result, merge);
			e2.guard = null;
			e3 = ActivityFactory.createObjectFlow(activity, merge, decision);
			e4 = ActivityFactory.createObjectFlow(activity, decision, parameternode, true);
			TestActivityFactory.modifyEdge(c1, vs0, vs1);
		}

	}
	
	protected class DecisionNodeTestActivity7 extends DecisionNodeTestActivity6{
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
	
	protected static void modifyEdge(ActivityEdge edge, ActivityNode source, ActivityNode target) {
		if(edge.source != null) {
			edge.source.outgoing.remove(edge);
		}
		if(edge.target != null) {
			edge.target.incoming.remove(edge);
		}
		
		edge.source = source;
		source.outgoing.add(edge);
		edge.target = target;
		target.incoming.add(edge);
		
		if(edge instanceof ControlFlow) {
			edge.name = "ControlFlow " + source.name + " --> " + target.name;
		} else {
			edge.name = "ObjectFlow " + source.name + " --> " + target.name;
		}
	}
	
	protected abstract class StructuredActivityNodeTestActivity {
		
		protected void addInputPinsToStructuredActivityNode(StructuredActivityNode node, InputPin... inputpins) {
			for(InputPin inputpin : inputpins) {
				node.addStructuredNodeInput(inputpin);
			}
		}
		
		protected void addOutputPinsToStructuredActivityNode(StructuredActivityNode node, OutputPin... outputpins) {
			for(OutputPin outputpin : outputpins) {
				node.addStructuredNodeOutput(outputpin);
			}
		}
		
		protected void addNodesToStructuredActivityNode(StructuredActivityNode node, ActivityNode... nodes) {
			for(ActivityNode n : nodes) {
				node.addNode(n);
			}
		}
		
		protected void addEdgesToStructuredActivityNode(StructuredActivityNode node, ActivityEdge... edges) {
			for(ActivityEdge edge : edges) {
				node.addEdge(edge);
			}
		}
	}
	
	protected class StructuredActivityNodeTestActivity1 extends StructuredActivityNodeTestActivity{
		protected Activity activity;
		protected Parameter objectparameter, valueparameter, outparameter;
		protected StructuredActivityNode structurednode;
		protected AddStructuralFeatureValueAction addaction;
		protected Class_ class_;
		protected Property name; 
		protected ObjectFlow e1, e2, e3, e4, e5, e6;
		protected Object_ o1, o2;
		protected StringValue string1, string2;
		protected ParameterValueList parametervaluelist;
		
		protected StructuredActivityNodeTestActivity1() {
			createClass();
			createActivity();
			createParameterValues();
		}

		private void createActivity() {
			activity = ActivityFactory.createActivity("StructuredActivityNodeTestActivity1");
			objectparameter = ActivityFactory.createParameter(activity, "objectparameter", ParameterDirectionKind.in);
			valueparameter = ActivityFactory.createParameter(activity, "valueparameter", ParameterDirectionKind.in);
			outparameter = ActivityFactory.createParameter(activity, "outparameter", ParameterDirectionKind.out);
			addaction = ActivityFactory.createAddStructuralFeatureValueAction("set name", name, true);
			ActivityParameterNode objectparameternode = ActivityFactory.createActivityParameterNode(activity, "objectparameternode", objectparameter);
			ActivityParameterNode valueparameternode = ActivityFactory.createActivityParameterNode(activity, "valueparameternode", valueparameter);
			ActivityParameterNode outparameternode = ActivityFactory.createActivityParameterNode(activity, "outparameter", outparameter);
			InputPin objectinputpin = ActivityFactory.createInputPin("objectinputpin", 1, -1);
			InputPin valueinputpin = ActivityFactory.createInputPin("valueinputpin", 1, -1);
			OutputPin outputpin = ActivityFactory.createOutputPin("outputpin", 0, -1);			
			e1 = ActivityFactory.createObjectFlow(activity, objectparameternode, objectinputpin);
			e2 = ActivityFactory.createObjectFlow(activity, valueparameternode, valueinputpin);
			e3 = ActivityFactory.createObjectFlow(objectinputpin, addaction.object);
			e4 = ActivityFactory.createObjectFlow(valueinputpin, addaction.value);
			e5 = ActivityFactory.createObjectFlow(addaction.result, outputpin);
			e6 = ActivityFactory.createObjectFlow(activity, outputpin, outparameternode);
			structurednode = ActivityFactory.createStructuredActivityNode(activity, "structuredactivitynode");
			addInputPinsToStructuredActivityNode(structurednode, objectinputpin, valueinputpin);
			addOutputPinsToStructuredActivityNode(structurednode, outputpin);
			addNodesToStructuredActivityNode(structurednode, addaction);
			addEdgesToStructuredActivityNode(structurednode, e3, e4, e5);
		}

		private void createClass() {
			class_ = ActivityFactory.createClass("Person");
			name = ActivityFactory.createProperty("name", 0, -1, null, class_);
		}

		private void createParameterValues() {
			o1 = createObject(class_);
			o2 = createObject(class_);
			string1 = createStringValue("tanja");
			string2 = createStringValue("philip");
			
			ParameterValue objectparametervalue = createParameterValue(objectparameter, o1, o2);
			ParameterValue valueparametervalue = createParameterValue(valueparameter, string1, string2);
			parametervaluelist = createParameterVaueList(objectparametervalue, valueparametervalue);
		}
		
	}
	
	protected class StructuredActivityNodeTestActivity2 extends StructuredActivityNodeTestActivity {
		protected Activity activity;
		protected ControlFlow c1, c2;
		protected InitialNode initial;
		protected ActivityFinalNode final_;
		protected StructuredActivityNode structurednode;
		
		protected StructuredActivityNodeTestActivity2() {
			activity = ActivityFactory.createActivity("StructuredActivityNodeTest2");
			initial = ActivityFactory.createInitialNode(activity, "initial");
			final_ = ActivityFactory.createActivityFinalNode(activity, "final");
			structurednode = ActivityFactory.createStructuredActivityNode(activity, "structurednode");
			c1 = ActivityFactory.createControlFlow(activity, initial, structurednode);
			c2 = ActivityFactory.createControlFlow(activity, structurednode, final_);
		}
	}
	
	protected static Object_ createObject(Class_ type) {
		Object_ o = new Object_();
		o.types.add(type);
		o.createFeatureValues();
		return o;
	}
	
	protected static StringValue createStringValue(String string) {
		StringValue stringValue = new StringValue();
		stringValue.value = string;
		return stringValue;
	}
	
	protected static ParameterValue createParameterValue(Parameter parameter, Value... values) {
		ValueList valuelist = new ValueList();
		valuelist.addAll(Arrays.asList(values));
		
		ParameterValue parametervalue = new ParameterValue();
		parametervalue.parameter = parameter;
		parametervalue.values = valuelist;
		
		return parametervalue;
	}
			
	protected static ParameterValueList createParameterVaueList(ParameterValue... parametervalues) {
		ParameterValueList parametervaluelist = new ParameterValueList();
		parametervaluelist.addAll(Arrays.asList(parametervalues));
		return parametervaluelist;
	}
	
}
