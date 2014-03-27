package org.modelexecution.fumldebug.core;

import java.util.ArrayList;
import java.util.List;

import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.TestIdentityAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.Clause;
import fUML.Syntax.Activities.CompleteStructuredActivities.ConditionalNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.LoopNode;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind;
import fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
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
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;


public class TestActivityFactory {
	
	protected class LinkCreatorDestroyerActivity {
		protected Class_ university, student;		
		protected Property universityname, studentname;
		protected Association university_student;
		protected Property students, visiteduniversity;
		
		protected Activity activity;
		protected Parameter parameter_u, parameter_s2, parameter_s3;
		protected ParameterValueList parametervaluelist;
		
		protected Object_ u, s1, s2, s3;
		protected Link l1, l2, l3;
		
		protected RemoveStructuralFeatureValueAction removestudent;
		protected DestroyObjectAction destroystudent;
		protected CreateObjectAction createstudent;
		protected AddStructuralFeatureValueAction addstudent;
		
		protected LinkCreatorDestroyerActivity() {
			createClass();
			createActivity();
			createParameterValues();
		}

		private void createClass() {
			university = ActivityFactory.createClass("University");
			universityname = ActivityFactory.createProperty("universityname", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), university);
			student = ActivityFactory.createClass("Student");
			studentname = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), university);
			
			students = ActivityFactory.createProperty("students", 0, -1, student, university);			
			visiteduniversity = ActivityFactory.createProperty("visiteduniversity", 0, 1, university, student);
			university_student = new Association();
			university_student.name = "university_student";
			university_student.memberEnd.add(students);
			university_student.memberEnd.add(visiteduniversity);
			students.association = university_student;
			visiteduniversity.association = university_student;
		}		

		private void createActivity() {
			activity = ActivityFactory.createActivity("LinkCreatorDestroyerActivity");
			parameter_u = ActivityFactory.createParameter(activity, "u", ParameterDirectionKind.in);			
			parameter_s2 = ActivityFactory.createParameter(activity, "s2", ParameterDirectionKind.in);
			parameter_s3 = ActivityFactory.createParameter(activity, "s3", ParameterDirectionKind.in);
			
			ActivityParameterNode parameternode_u = ActivityFactory.createActivityParameterNode(activity, "u", parameter_u);
			ActivityParameterNode parameternode_s2 = ActivityFactory.createActivityParameterNode(activity, "s2", parameter_s2);
			ActivityParameterNode parameternode_s3 = ActivityFactory.createActivityParameterNode(activity, "s3", parameter_s3);
			ForkNode fork = ActivityFactory.createForkNode(activity, "fork");
			removestudent = ActivityFactory.createRemoveStructuralFeatureValueAction(activity, "remove students", students, false);
			destroystudent = ActivityFactory.createDestroyObjectAction(activity, "destroy student", true, false);
			createstudent = ActivityFactory.createCreateObjectAction(activity, "create student", student);
			addstudent = ActivityFactory.createAddStructuralFeatureValueAction(activity, "add student", students);
			
			ActivityFactory.createObjectFlow(activity, parameternode_u, fork);			
			ActivityFactory.createObjectFlow(activity, fork, removestudent.object);
			ActivityFactory.createObjectFlow(activity, fork, addstudent.object);
			ActivityFactory.createObjectFlow(activity, parameternode_s2, removestudent.value);
			ActivityFactory.createObjectFlow(activity, parameternode_s3, destroystudent.target);
			ActivityFactory.createObjectFlow(activity, createstudent.result, addstudent.value);
		}

		private void createParameterValues() {
			u = ActivityFactory.createObject(university);
			ActivityFactory.setObjectProperty(u, universityname, ActivityFactory.createStringValue("TU Wien"));
			s1 = ActivityFactory.createObject(student);
			ActivityFactory.setObjectProperty(s1, studentname, ActivityFactory.createStringValue("Student1"));
			s2 = ActivityFactory.createObject(student);
			ActivityFactory.setObjectProperty(s2, studentname, ActivityFactory.createStringValue("Student2"));
			s3 = ActivityFactory.createObject(student);
			ActivityFactory.setObjectProperty(s3, studentname, ActivityFactory.createStringValue("Student3"));
			
			l1 = ActivityFactory.createLink(university_student, visiteduniversity, u, students, s1);
			l2 = ActivityFactory.createLink(university_student, visiteduniversity, u, students, s2);
			l3 = ActivityFactory.createLink(university_student, visiteduniversity, u, students, s3);
			
			parametervaluelist = new ParameterValueList();
			parametervaluelist.add(ActivityFactory.createParameterValue(parameter_u, u));
			parametervaluelist.add(ActivityFactory.createParameterValue(parameter_s2, s2));
			parametervaluelist.add(ActivityFactory.createParameterValue(parameter_s3, s3));			
		}
	}
	
	protected class ExpansionRegionTestActivity1 {
		protected Activity activity;
		protected Parameter objectparameter, valueparameter, outparameter;
		protected ExpansionRegion expansionregion;
		protected AddStructuralFeatureValueAction setname;
		protected ObjectFlow o1, o2, o3, o4, o5, o6;
		
		protected Class_ class_;
		protected Property name;
		
		protected Object_ obj1, obj2, obj3;
		protected StringValue string1;
		protected ParameterValueList parametervaluelist;
		
		protected ExpansionRegionTestActivity1() {
			createClass();			
			createActivity();
			createParameterValues();
		}

		private void createActivity() {
			activity = ActivityFactory.createActivity("ExpansionRegionTestActivity1");
			objectparameter = ActivityFactory.createParameter(activity, "object parameter", ParameterDirectionKind.in);
			valueparameter = ActivityFactory.createParameter(activity, "value parameter", ParameterDirectionKind.in);
			outparameter = ActivityFactory.createParameter(activity, "out parameter", ParameterDirectionKind.out);
			ActivityParameterNode objectparameternode = ActivityFactory.createActivityParameterNode(activity, "object parameter", objectparameter);
			ActivityParameterNode valueparameternode = ActivityFactory.createActivityParameterNode(activity, "value parameter", valueparameter);
			ActivityParameterNode outparameternode = ActivityFactory.createActivityParameterNode(activity, "out parameter", outparameter);
			setname = ActivityFactory.createAddStructuralFeatureValueAction(activity, "set name", name);
			List<ActivityNode> expansionnodes = new ArrayList<ActivityNode>();
			expansionnodes.add(setname);
			expansionregion = ActivityFactory.createExpansionRegion(activity, "set names", ExpansionKind.iterative, expansionnodes, 1, 1, 1);
			o1 = ActivityFactory.createObjectFlow(activity, objectparameternode, expansionregion.inputElement.get(0));
			o2 = ActivityFactory.createObjectFlow(activity, valueparameternode, expansionregion.input.get(0));
			o3 = ActivityFactory.createObjectFlow(expansionregion, expansionregion.inputElement.get(0), setname.object);
			o4 = ActivityFactory.createObjectFlow(expansionregion, expansionregion.input.get(0), setname.value);
			o5 = ActivityFactory.createObjectFlow(expansionregion, setname.result, expansionregion.outputElement.get(0));
			o6 = ActivityFactory.createObjectFlow(activity, expansionregion.outputElement.get(0), outparameternode);
		}

		private void createClass() {
			class_ = ActivityFactory.createClass("Person");
			name = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		}
		
		private void createParameterValues() {
			obj1 = ActivityFactory.createObject(class_);
			obj2 = ActivityFactory.createObject(class_);
			obj3 = ActivityFactory.createObject(class_);
			string1 = ActivityFactory.createStringValue("tanja");
			
			ParameterValue objectparametervalue = ActivityFactory.createParameterValue(objectparameter, obj1, obj2, obj3);
			ParameterValue valueparametervalue = ActivityFactory.createParameterValue(valueparameter, string1);
			parametervaluelist = ActivityFactory.createParameterValueList(objectparametervalue, valueparametervalue);
		}
		
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 3) {
				return false;
			}			
			for(Value value : outvalues.get(0).values) {
				if(!(value instanceof Object_)) {
					return false;
				}
				Object_ obj = (Object_)value;
				if( !( checkType(obj) && checkName(obj) ) ) {
					return false;
				}
			}
			return true;
		}
		
		private boolean checkType(Object_ o) {
			if(o.types.size() != 1) {
				return false;
			}
			return o.types.get(0).equals(class_);
		}
		
		private boolean checkName(Object_ o) {
			FeatureValue namevalue = o.getFeatureValue(name);
			if(namevalue.values.size() != 1) {
				return false;
			}			
			if(!(namevalue.values.get(0) instanceof StringValue)) {
				return false;
			}			
			StringValue namestring = (StringValue)namevalue.values.get(0);			
			return namestring.value.equals("tanja");
		}
	}
	
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
		
		updateEdgeName(edge);
	}
	
	protected static void modifyEdgeSource(ActivityEdge edge, ActivityNode source) {
		if(edge.source != null) {
			edge.source.outgoing.remove(edge);
		}
		edge.source = source;
		source.outgoing.add(edge);
		
		updateEdgeName(edge);
	}
	
	protected static void modifyEdgeTarget(ActivityEdge edge, ActivityNode target) {
		if(edge.target != null) {
			edge.target.incoming.remove(edge);
		}
		
		edge.target = target;
		target.incoming.add(edge);
		
		updateEdgeName(edge);		
	}
	
	private static void updateEdgeName(ActivityEdge edge) {
		if(edge instanceof ControlFlow) {
			edge.name = "ControlFlow " + edge.source.name + " --> " + edge.target.name;
		} else {
			edge.name = "ObjectFlow " + edge.source.name + " --> " + edge.target.name;
		}
	}
	
	protected class StructuredActivityNodeTestActivity1{
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
			ActivityFactory.addInputPinsToStructuredActivityNode(structurednode, objectinputpin, valueinputpin);
			ActivityFactory.addOutputPinsToStructuredActivityNode(structurednode, outputpin);
			ActivityFactory.addNodesToStructuredActivityNode(structurednode, addaction);
			ActivityFactory.addEdgesToStructuredActivityNode(structurednode, e3, e4, e5);
		}

		private void createClass() {
			class_ = ActivityFactory.createClass("Person");
			name = ActivityFactory.createProperty("name", 0, -1, null, class_);
		}

		private void createParameterValues() {
			o1 = ActivityFactory.createObject(class_);
			o2 = ActivityFactory.createObject(class_);
			string1 = ActivityFactory.createStringValue("tanja");
			string2 = ActivityFactory.createStringValue("philip");
			
			ParameterValue objectparametervalue = ActivityFactory.createParameterValue(objectparameter, o1, o2);
			ParameterValue valueparametervalue = ActivityFactory.createParameterValue(valueparameter, string1, string2);
			parametervaluelist = ActivityFactory.createParameterValueList(objectparametervalue, valueparametervalue);
		}
		
	}
	
	protected class StructuredActivityNodeTestActivity2 {
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
	
	protected class StructuredActivityNodeTestActivity3 {
		protected Activity activity;
		protected Parameter parameterin, parameterout;
		protected CreateObjectAction create1, create2;
		protected StructuredActivityNode structurednode1, structurednode2;
		protected InitialNode initial;
		protected AddStructuralFeatureValueAction setname;
		protected ControlFlow c1, c2, c3;
		protected ObjectFlow o1, o2, o3, o4, o5, o6, o7, o8, o9;
		
		protected ParameterValueList parametervaluelist;
		
		protected Class_ class_;
		protected Property name;
		
		protected StringValue string1, string2;
		
		protected StructuredActivityNodeTestActivity3() {
			createClass();			
			createActivity();
			createParameterValues();
		}

		protected void createParameterValues() {
			string1 = ActivityFactory.createStringValue("tanja");
			string2 = ActivityFactory.createStringValue("philip");
			
			ParameterValue valueparametervalue = ActivityFactory.createParameterValue(parameterin, string1, string2);
			parametervaluelist = ActivityFactory.createParameterValueList(valueparametervalue);			
		}

		protected void createActivity() {
			activity = ActivityFactory.createActivity("StructuredActivityNodeTestActivity3");
			parameterin = ActivityFactory.createParameter("in", ParameterDirectionKind.in);
			parameterout = ActivityFactory.createParameter("out", ParameterDirectionKind.out);
			ActivityParameterNode paramnodein = ActivityFactory.createActivityParameterNode(activity, "in", parameterin);
			ActivityParameterNode paramnodeout = ActivityFactory.createActivityParameterNode(activity, "out", parameterout);
			create1 = ActivityFactory.createCreateObjectAction(activity, "create1", class_);
			create2 = ActivityFactory.createCreateObjectAction(activity, "create2", class_);
			initial = ActivityFactory.createInitialNode(activity, "initial");
			setname = ActivityFactory.createAddStructuralFeatureValueAction(activity, "set name", name);
			
			structurednode1 = ActivityFactory.createStructuredActivityNode(activity, "structurednode1");
			InputPin inpin_value_structurednode1 = ActivityFactory.createInputPin("value", 1, -1);
			OutputPin outpin_object_structurednode1 = ActivityFactory.createOutputPin("output", 0, -1);
			ActivityFactory.addInputPinsToStructuredActivityNode(structurednode1, inpin_value_structurednode1);
			ActivityFactory.addOutputPinsToStructuredActivityNode(structurednode1, outpin_object_structurednode1);
						
			structurednode2 = ActivityFactory.createStructuredActivityNode("structurednode2");
			InputPin inpin_value_structurednode2 = ActivityFactory.createInputPin("value", 1, -1);
			InputPin inpin_object_structurednode2 = ActivityFactory.createInputPin("object", 1, -1);
			OutputPin outpin_object_structurednode2 = ActivityFactory.createOutputPin("output", 0, -1);
			ActivityFactory.addInputPinsToStructuredActivityNode(structurednode2, inpin_value_structurednode2, inpin_object_structurednode2);
			ActivityFactory.addOutputPinsToStructuredActivityNode(structurednode2, outpin_object_structurednode2);
						
			ActivityFactory.addNodesToStructuredActivityNode(structurednode1, initial, create1, create2, structurednode2);			
			ActivityFactory.addNodesToStructuredActivityNode(structurednode2, setname);
			
			o1 = ActivityFactory.createObjectFlow(activity, paramnodein, inpin_value_structurednode1);
			o9 = ActivityFactory.createObjectFlow(activity, outpin_object_structurednode1, paramnodeout);
			
			c1 = ActivityFactory.createControlFlow(initial, create1);
			c2 = ActivityFactory.createControlFlow(create1, create2);
			c3 = ActivityFactory.createControlFlow(create2, structurednode2);
			o2 = ActivityFactory.createObjectFlow(inpin_value_structurednode1, inpin_value_structurednode2);
			o3 = ActivityFactory.createObjectFlow(create1.result, inpin_object_structurednode2);
			o4 = ActivityFactory.createObjectFlow(create2.result, inpin_object_structurednode2);
			o8 = ActivityFactory.createObjectFlow(outpin_object_structurednode2, outpin_object_structurednode1);
			ActivityFactory.addEdgesToStructuredActivityNode(structurednode1, c1, c2, c3, o2, o3, o4, o8);
			
			o5 = ActivityFactory.createObjectFlow(inpin_object_structurednode2, setname.object);
			o6 = ActivityFactory.createObjectFlow(inpin_value_structurednode2, setname.value);
			o7 = ActivityFactory.createObjectFlow(setname.result, outpin_object_structurednode2);
			ActivityFactory.addEdgesToStructuredActivityNode(structurednode2, o5, o6, o7);
		}

		protected void createClass() {
			class_ = ActivityFactory.createClass("Person");
			name = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		}
		
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 2) {
				return false;
			}
			if(!(outvalues.get(0).values.get(0) instanceof Reference)) {
				return false;
			}
			if(!(outvalues.get(0).values.get(1) instanceof Reference)) {
				return false;
			}
			Object_ o1_out = ((Reference)outvalues.get(0).values.get(0)).referent;
			Object_ o2_out = ((Reference)outvalues.get(0).values.get(1)).referent;
			if(!((StringValue)o1_out.featureValues.get(0).values.get(0)).value.equals(string1.value)) {
				return false;
			}
			if(!((StringValue)o2_out.featureValues.get(0).values.get(0)).value.equals(string2.value)) {
				return false;
			}
			return true;
		}
	}
	
	protected class StructuredActivityNodeTestActivity4 extends StructuredActivityNodeTestActivity3 {
		protected CallBehaviorAction callA2;
		
		protected Activity activity2;
		protected Parameter parameterinobjectA2, parameterinvalueA2, parameteroutA2;
		protected InitialNode initialA2;

		protected ControlFlow c1A2;
		protected ObjectFlow o1A2, o2A2, o3A2;
		
		protected StructuredActivityNodeTestActivity4() {
			super();
		}

		@Override
		protected void createParameterValues() {
			super.createParameterValues();
			parametervaluelist.get(0).values.remove(1); 
		}

		@Override
		protected void createActivity() {
			super.createActivity();
			
			activity2 = ActivityFactory.createActivity("StructuredActivityNodeTestActivity4 - activity2");
			parameterinobjectA2 = ActivityFactory.createParameter(activity2, "in object", ParameterDirectionKind.in);
			parameterinvalueA2 = ActivityFactory.createParameter(activity2, "in value", ParameterDirectionKind.in);
			parameteroutA2 = ActivityFactory.createParameter(activity2, "out", ParameterDirectionKind.out);
			
			ActivityParameterNode paramnodeinobjectA2 = ActivityFactory.createActivityParameterNode(activity2, "in object", parameterinobjectA2);
			ActivityParameterNode paramnodeinvalueA2 = ActivityFactory.createActivityParameterNode(activity2, "in value", parameterinvalueA2);
			ActivityParameterNode paramnodeoutA2 = ActivityFactory.createActivityParameterNode(activity2, "out", parameteroutA2);
			initialA2 = ActivityFactory.createInitialNode(activity2, "initial");
			setname.activity = activity2;
			activity2.node.add(setname);
			c1A2 = ActivityFactory.createControlFlow(activity2, initialA2, setname);
			o1A2 = ActivityFactory.createObjectFlow(activity2, paramnodeinobjectA2, setname.object);
			o2A2 = ActivityFactory.createObjectFlow(activity2, paramnodeinvalueA2, setname.value);
			o3A2 = ActivityFactory.createObjectFlow(activity2, setname.result, paramnodeoutA2);

			activity.name = "StructuredActivityNodeTestActivity4";
			callA2 = ActivityFactory.createCallBehaviorAction("call A2", activity2, 1, 2);
			ActivityFactory.addNodesToStructuredActivityNode(structurednode2, callA2);		
			ActivityFactory.removeNodesFromStructuredActivityNode(structurednode2, setname);	
			TestActivityFactory.modifyEdgeTarget(o5, callA2.input.get(0));
			TestActivityFactory.modifyEdgeTarget(o6, callA2.input.get(1));
			TestActivityFactory.modifyEdgeSource(o7, callA2.output.get(0));
		}

		@Override
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 1) {
				return false;
			}
			if(!(outvalues.get(0).values.get(0) instanceof Reference)) {
				return false;
			}
			Object_ o1_out = ((Reference)outvalues.get(0).values.get(0)).referent;
			if(!((StringValue)o1_out.featureValues.get(0).values.get(0)).value.equals(string1.value)) {
				return false;
			}
			return true;
		}		
	}
	
	protected class StructuredActivityNodeTestActivity5 extends StructuredActivityNodeTestActivity4 {
		protected InitialNode initial2;
		
		protected StructuredActivityNodeTestActivity5() {
			super();
		}

		@Override
		protected void createActivity() {
			super.createActivity();
			
			activity.name = "StructuredActivityNodeTestActivity5";
			
			initial2 = ActivityFactory.createInitialNode("initial2");	
			ActivityFactory.addNodesToStructuredActivityNode(structurednode2, initial2);
		}
	}
	
	protected class ConditionalNodeTestActivity1 {
		protected Class_ class_;
		protected Property name;
		
		protected Activity activity;
		protected ReadStructuralFeatureAction readname; 
		protected ConditionalNode conditionalnode;	
		protected Clause clause1, clause2;
		protected ValueSpecificationAction specifytanja, specifyphilip, specify1, specify2;
		protected TestIdentityAction testidtanja, testidphilip;
		protected ForkNode fork;
		
		protected ObjectFlow o1, o2, o3, o4, o5, o6, o7;
		protected ControlFlow c1;
		
		protected Object_ obj1;
		protected StringValue string1;
		
		protected Parameter inparameter, outparameter;
		protected ParameterValueList parametervaluelist;
		
		protected ConditionalNodeTestActivity1() {
			createClass();			
			createActivity();
			createParameterValues();
		}

		protected void createParameterValues() {
			obj1 = ActivityFactory.createObject(class_);	
			string1 = ActivityFactory.createStringValue("tanja");
			ActivityFactory.setObjectProperty(obj1, name, string1);			
			
			ParameterValue objectparametervalue = ActivityFactory.createParameterValue(inparameter, obj1);
			parametervaluelist = ActivityFactory.createParameterValueList(objectparametervalue);
		}

		private void createActivity() {
			activity = ActivityFactory.createActivity("ConditionalNodeTestActivity1");
			inparameter = ActivityFactory.createParameter(activity, "in", ParameterDirectionKind.in);
			outparameter = ActivityFactory.createParameter(activity, "out", ParameterDirectionKind.out);
			ActivityParameterNode inparamnode = ActivityFactory.createActivityParameterNode(activity, "in", inparameter);
			ActivityParameterNode outparamnode = ActivityFactory.createActivityParameterNode(activity, "out", outparameter);
			
			readname = ActivityFactory.createReadStructuralFeatureAction(activity, "read name", name);
			fork = ActivityFactory.createForkNode(activity, "fork");
			o1 = ActivityFactory.createObjectFlow(activity, inparamnode, readname.object);
			o2 = ActivityFactory.createObjectFlow(activity, readname.result, fork);			
			
			specifytanja = ActivityFactory.createValueSpecificationAction("specify tanja", "tanja");
			testidtanja = ActivityFactory.createTestIdentityAction("test identity tanja");
			specify1 = ActivityFactory.createValueSpecificationAction("specify 1", 1);
			clause1 = ActivityFactory.createClause(testidtanja.result, specify1.result);			
			ActivityFactory.addTestNodesToClause(clause1, specifytanja, testidtanja);
			ActivityFactory.addBodyNodesToClause(clause1, specify1);
			
			specifyphilip = ActivityFactory.createValueSpecificationAction("specify philip", "philip");
			testidphilip = ActivityFactory.createTestIdentityAction("test identity philip");
			specify2 = ActivityFactory.createValueSpecificationAction("specify 2", 2);
			clause2 = ActivityFactory.createClause(testidphilip.result, specify2.result);
			ActivityFactory.addTestNodesToClause(clause2, specifyphilip, testidphilip);
			ActivityFactory.addBodyNodesToClause(clause2, specify2);
			
			o3 = ActivityFactory.createObjectFlow(activity, fork, testidtanja.second);
			o4 = ActivityFactory.createObjectFlow(activity, fork, testidphilip.second);
			
			o5 = ActivityFactory.createObjectFlow(specifytanja.result, testidtanja.first);
			o6 = ActivityFactory.createObjectFlow(specifyphilip.result, testidphilip.first);
			
			conditionalnode = ActivityFactory.createConditionalNode(activity, "conditional node", 1, clause1, clause2); 
			
			ActivityFactory.addEdgesToStructuredActivityNode(conditionalnode, o5, o6);
			
			c1 = ActivityFactory.createControlFlow(activity, readname, conditionalnode);
			o7 = ActivityFactory.createObjectFlow(activity, conditionalnode.result.get(0), outparamnode);
		}

		private void createClass() {
			class_ = ActivityFactory.createClass("Person");
			name = ActivityFactory.createProperty("name", 0, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_);
		}
		
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 1) {
				return false;
			}
			if(!(outvalues.get(0).values.get(0) instanceof IntegerValue)) {
				return false;
			}
			if(((IntegerValue)outvalues.get(0).values.get(0)).value != 1) {
				return false;
			}
			return true;
		}	
	}
	
	protected class ConditionalNodeTestActivity2 extends ConditionalNodeTestActivity1 {
		
		protected ConditionalNodeTestActivity2() {
			super();
			activity.name = "ConditionalNodeTestActivity2";
			clause2.addPredecessorClause(clause1);
		}
		
	}
	
	protected class ConditionalNodeTestActivity3 extends ConditionalNodeTestActivity2 {
		
		protected ConditionalNodeTestActivity3() {
			super();
			activity.name = "ConditionalNodeTestActivity3";
		}

		@Override
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 1) {
				return false;
			}
			if(!(outvalues.get(0).values.get(0) instanceof IntegerValue)) {
				return false;
			}
			if(((IntegerValue)outvalues.get(0).values.get(0)).value != 2) {
				return false;
			}
			return true;
		}

		@Override
		protected void createParameterValues() {
			obj1 = ActivityFactory.createObject(class_);	
			string1 = ActivityFactory.createStringValue("philip");
			ActivityFactory.setObjectProperty(obj1, name, string1);			
			
			ParameterValue objectparametervalue = ActivityFactory.createParameterValue(inparameter, obj1);
			parametervaluelist = ActivityFactory.createParameterValueList(objectparametervalue);
		}
				
	}
	
	protected class LoopNodeTestActivity1 {
		protected Class_ class_;
		
		protected Activity activity;
		protected ValueSpecificationAction specify0, specify1, specify2;
		protected CreateObjectAction createobject;
		protected CallBehaviorAction callsubtract;
		protected ForkNode fork;
		protected TestIdentityAction testid;
		protected LoopNode loopnode;
		
		protected ObjectFlow o1, o2, o3, o4, o5, o6, o7;
		protected ControlFlow c1;
		
		protected Parameter parameterobjects;
		
		protected LoopNodeTestActivity1() {
			class_ = ActivityFactory.createClass("Class");
			
			activity = ActivityFactory.createActivity("LoopNodeTestActivity1");
			parameterobjects = ActivityFactory.createParameter(activity, "parameter objects", ParameterDirectionKind.out);
				
			ActivityParameterNode parameternodeobjects = ActivityFactory.createActivityParameterNode(activity, "parameter objects", parameterobjects);						
			specify2 = ActivityFactory.createValueSpecificationAction(activity, "specify 2", 2);
			
			specify1 = ActivityFactory.createValueSpecificationAction("specify 1", 1);
			specify0 = ActivityFactory.createValueSpecificationAction("specify 0", 0);
			createobject = ActivityFactory.createCreateObjectAction("create object", class_);
			testid = ActivityFactory.createTestIdentityAction("test identity");
			callsubtract = ActivityFactory.createCallBehaviorAction("call subtract", ExecutionContext.getInstance().getOpaqueBehavior("subtract"), 1, 2);
			fork = ActivityFactory.createForkNode("fork");						
			
			o1 = ActivityFactory.createObjectFlow(activity, specify2.result, callsubtract.input.get(0));
			o2 = ActivityFactory.createObjectFlow(specify1.result, callsubtract.input.get(1));
			o3 = ActivityFactory.createObjectFlow(callsubtract.result.get(0), fork);
			o4 = ActivityFactory.createObjectFlow(fork, testid.second);
			o5 = ActivityFactory.createObjectFlow(fork, callsubtract.input.get(0));
			o6 = ActivityFactory.createObjectFlow(specify0.result, testid.first);
			o7 = ActivityFactory.createObjectFlow(activity, createobject.result, parameternodeobjects);
			c1 = ActivityFactory.createControlFlow(callsubtract, specify0);
			
			loopnode = ActivityFactory.createLoopNode(activity, "loop node", 1, false);
			ActivityFactory.setLoopNodeDecider(loopnode, testid.result);

			ActivityFactory.addNodesToStructuredActivityNode(loopnode, fork);
			ActivityFactory.addTestNodesToLoopNode(loopnode, specify1, callsubtract, specify0, testid);
			ActivityFactory.addBodyNodesToLoopNode(loopnode, createobject);
			ActivityFactory.addEdgesToStructuredActivityNode(loopnode, c1, o2, o3, o4, o5, o6);
		}
		
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 2) {
				return false;
			}
			if(!(outvalues.get(0).values.get(0) instanceof Reference)) {
				return false;
			}
			if(!(outvalues.get(0).values.get(1) instanceof Reference)) {
				return false;
			}
			return true;
		}
	}
	
	protected class LoopNodeTestActivity2 {
		protected Class_ class_;
		
		protected Activity activity;
		protected ValueSpecificationAction specify0, specify1, specify3;
		protected CreateObjectAction createobject;
		protected CallBehaviorAction calladd, callsmaller;
		protected ForkNode fork;
		protected LoopNode loopnode;
		
		protected ObjectFlow o1, o2, o3, o4, o5, o6, o7, o8;
		
		protected Parameter parameterobjects;
		
		protected LoopNodeTestActivity2() {
			class_ = ActivityFactory.createClass("Class");
			
			activity = ActivityFactory.createActivity("LoopNodeTestActivity2");
			parameterobjects = ActivityFactory.createParameter(activity, "parameter objects", ParameterDirectionKind.out);
				
			ActivityParameterNode parameternodeobjects = ActivityFactory.createActivityParameterNode(activity, "parameter objects", parameterobjects);						
			specify0 = ActivityFactory.createValueSpecificationAction(activity, "specify 0", 0);
			specify1 = ActivityFactory.createValueSpecificationAction("specify 1", 1);
			specify3 = ActivityFactory.createValueSpecificationAction("specify 3", 3);
			createobject = ActivityFactory.createCreateObjectAction("create object", class_);
			calladd = ActivityFactory.createCallBehaviorAction("call add", ExecutionContext.getInstance().getOpaqueBehavior("add"), 1, 2);
			callsmaller = ActivityFactory.createCallBehaviorAction("call smaller", ExecutionContext.getInstance().getOpaqueBehavior("less"), 1, 2);
			fork = ActivityFactory.createForkNode("fork");
			
			loopnode = ActivityFactory.createLoopNode(activity, "loop node", 1, true);
			OutputPin loopVariable = ActivityFactory.createOutputPin("loopVariable", 0, -1);
			OutputPin structuredNodeOutput = ActivityFactory.createOutputPin("structuredNodeOutput", 0, -1);
			InputPin loopVariableInput = ActivityFactory.createInputPin("loopVariableInput", 0, -1);

			loopnode.addLoopVariable(loopVariable);
			loopnode.addStructuredNodeOutput(structuredNodeOutput);
			loopnode.addLoopVariableInput(loopVariableInput);
			loopnode.setDecider(callsmaller.result.get(0));
			loopnode.addBodyOutput(calladd.result.get(0));

			o1 = ActivityFactory.createObjectFlow(activity, specify0.result, loopVariableInput);
			o2 = ActivityFactory.createObjectFlow(loopVariable, fork);
			o3 = ActivityFactory.createObjectFlow(fork, callsmaller.argument.get(0));
			o4 = ActivityFactory.createObjectFlow(fork, calladd.argument.get(0));
			o5 = ActivityFactory.createObjectFlow(specify3.result, callsmaller.argument.get(1));
			o6 = ActivityFactory.createObjectFlow(createobject.result, structuredNodeOutput);
			o7 = ActivityFactory.createObjectFlow(specify1.result, calladd.argument.get(1));
			o8 = ActivityFactory.createObjectFlow(activity, structuredNodeOutput, parameternodeobjects);
			
			ActivityFactory.addNodesToStructuredActivityNode(loopnode, fork);
			ActivityFactory.addTestNodesToLoopNode(loopnode, specify3, callsmaller);
			ActivityFactory.addBodyNodesToLoopNode(loopnode, createobject, specify1, calladd);
			ActivityFactory.addEdgesToStructuredActivityNode(loopnode, o2, o3, o4, o5, o6, o7);
		}
		
		protected boolean checkOutput(ParameterValueList outvalues) {
			if(outvalues.size() != 1) {
				return false;
			}
			if(outvalues.get(0).values.size() != 3) {
				return false;
			}
			for(Value value : outvalues.get(0).values) {
				if(!(value instanceof Reference))
					return false;
			}
			return true;
		}
	}
	
}
