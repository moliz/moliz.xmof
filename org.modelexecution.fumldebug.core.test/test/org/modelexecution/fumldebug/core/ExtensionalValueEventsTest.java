/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */

package org.modelexecution.fumldebug.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.BreakpointEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;
import org.modelexecution.fumldebug.core.event.FeatureValueEvent;
import org.modelexecution.fumldebug.core.event.SuspendEvent;
import org.modelexecution.fumldebug.core.impl.BreakpointImpl;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import UMLPrimitiveTypes.UnlimitedNatural;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.UnlimitedNaturalValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.CompleteActions.ReclassifyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ClearAssociationAction;
import fUML.Syntax.Actions.IntermediateActions.ClearStructuralFeatureAction;
import fUML.Syntax.Actions.IntermediateActions.CreateLinkAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyLinkAction;
import fUML.Syntax.Actions.IntermediateActions.DestroyObjectAction;
import fUML.Syntax.Actions.IntermediateActions.RemoveStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Classes.Kernel.AggregationKind;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.ClassifierList;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.PropertyList;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

/**
 * @author Tanja Mayerhofer
 *
 */
public class ExtensionalValueEventsTest extends MolizTest implements ExecutionEventListener{

	private List<Event> eventlist = new ArrayList<Event>();
	private List<ExtensionalValueList> extensionalValueLists = new ArrayList<ExtensionalValueList>();
	
	public ExtensionalValueEventsTest() {
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventlist = new ArrayList<Event>();
		extensionalValueLists = new ArrayList<ExtensionalValueList>();
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
     * Tests the ExtensionalValueEvents for CreateObjectAction
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 */
	@Test
	public void testCreateObjectAction() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Activity activity = ActivityFactory.createActivity("testCreateObjectAction");
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);
		
		ActivityFactory.createControlFlow(activity, create1, create2);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertTrue(value1 instanceof Object_);
		assertEquals(1, value1.getTypes().size());
		assertEquals(class1, value1.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		ExtensionalValue value2 =  ((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertTrue(value2 instanceof Object_);
		assertEquals(1, value2.getTypes().size());
		assertEquals(class2, value2.getTypes().get(0));
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(8)).getParent());
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
     * Tests the ExtensionalValueEvents for CreateLinkAction
	 * 
	 * Activity:
	 * CreateObjectAction Student (class = Student)
	 * CreateObjectAction Vorlesung (class = Vorlesung)
	 * (Association: properties: vorlesungen:Vorlesung[*], studenten:Student[*])
	 * CreateLinkAction 
	 * 
	 * Activity DataFlow:
	 * CreateObjectAction Student target --> CreateLinkAction input
	 * CreateObjectAction Vorlesung target --> CreateLinkAction input
	 */
	@Test
	public void testCreateLinkAction() {
		Class_ student = ActivityFactory.createClass("Student");		
		Class_ vorlesung = ActivityFactory.createClass("Vorlesung");
		
		Property vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, vorlesung, student);
		Property studenten = ActivityFactory.createProperty("studenten", 0, -1, student, vorlesung);
		PropertyList members = new PropertyList();
		members.add(vorlesungen);
		members.add(studenten);
		Association student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Activity activity = ActivityFactory.createActivity("testCreateLinkAction");
				
		CreateObjectAction createstudent = ActivityFactory.createCreateObjectAction(activity, "CreateObject Student", student);
		CreateObjectAction createvorlesung = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung", vorlesung);
		CreateLinkAction createlinkaction = ActivityFactory.createCreateLinkAction(activity, "CreateLink student2vorlesung", members);
		
		ActivityFactory.createObjectFlow(activity, createvorlesung.result, createlinkaction.input.get(0));
		ActivityFactory.createObjectFlow(activity, createstudent.result, createlinkaction.input.get(1));
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(createstudent, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ stud_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(student, stud_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(createstudent, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createvorlesung, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		Object_ vo_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertEquals(vorlesung, vo_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(createvorlesung, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(createlinkaction, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(9)).getType());
		Link link =  (Link)(((ExtensionalValueEvent)eventlist.get(9)).getExtensionalValue());
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(vorlesungen);
		values_expected.add(studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(vo_obj);
		objects_expected.add(stud_obj);
		checkLink(student2vorlesung, link, values_expected, objects_expected);
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(createlinkaction, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(11)).getParent());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for CreateLinkAction 
	 * if link has to be removed first
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * RemoveStructuralFeatureValueAction (feature = association)
	 * 
	 * Activity input:
	 * object student
	 * (link exists between this object and a vorlesung object)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> CreateLinkAction.object
	 */
	@Test
	public void testCreateLinkAction2() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student, true);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung, true);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Object_ obj_vo = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);		
		Object_ obj_stud = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		valuelist.add(obj_vo);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		valuelist.add(obj_stud);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		

		Activity activity = ActivityFactory.createActivity("testCreateLinkAction2");	
		CreateLinkAction action_createlink = ActivityFactory.createCreateLinkAction(activity, "CreateLink student2vorlesung", members);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "object parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		Parameter param_voobj = ActivityFactory.createParameter(activity, "value parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_voobj = ActivityFactory.createActivityParameterNode(activity, "", param_voobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_createlink.input.get(1));
		ActivityFactory.createObjectFlow(activity, paramnode_voobj, action_createlink.input.get(0));
		
		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(obj_stud);
		paramvalue_studobj.values = values_stud;
		
		ParameterValue paramvalue_voobj = new ParameterValue();
		paramvalue_voobj.parameter = param_voobj;
		ValueList values_vo = new ValueList();
		values_vo.add(obj_vo);
		paramvalue_voobj.values = values_vo;
						
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		inputs.add(paramvalue_voobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(3));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(4)).getLocation());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createlink, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue());	
		
		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		Link link =  (Link)(((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue());
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(property_vorlesungen);
		values_expected.add(property_studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(obj_vo);
		objects_expected.add(obj_stud);
		checkLink(association_student2vorlesung, link, values_expected, objects_expected);

		assertTrue(!link.equals(((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue()));
		assertTrue(!link.equals(newLink));
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(action_createlink, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());

		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(9)).getParent());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
     * Tests the ExtensionalValueEvents for DestroyObjectAction
	 * 
	 * Activity:
	 * CreateObjectAction1 (class = Class1)
	 * CreateObjectAction2 (class = Class2)
	 * DestroyObjectAction (object from CreateObjectAction1) 
	 * 
	 * Activity DataFlow:
	 * CreateObjectAction1 result --> CreateObjectAction2 target
	 * 
	 * ActivityControlFlow:
	 * CreateObjectAction1 --> CreateObjectAction2
	 * CreateObjectAction2 --> DestroyObjectAction
	 */
	@Test
	public void testDestroyObjectAction() {
		Class_ class1 = ActivityFactory.createClass("Class1");		
		Class_ class2 = ActivityFactory.createClass("Class2");
				
		Activity activity = ActivityFactory.createActivity("testDestroyObjectAction");
				
		CreateObjectAction create1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
		CreateObjectAction create2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class2", class2);		
		DestroyObjectAction destroyobjectaction = ActivityFactory.createDestroyObjectAction(activity, "DestroyObject Class1", false, false);
		
		ActivityFactory.createObjectFlow(activity, create1.result, destroyobjectaction.target);
		ActivityFactory.createControlFlow(activity, create1, create2);
		ActivityFactory.createControlFlow(activity, create2, destroyobjectaction);
		
		// Set Breakpoint
		BreakpointImpl breakdestroyaction = new BreakpointImpl(destroyobjectaction);
		ExecutionContext.getInstance().addBreakpoint(breakdestroyaction);

		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create1, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ obj1 =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(class1, obj1.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create1, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(create2, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		Object_ obj2 =  (Object_)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertEquals(class2, obj2.getTypes().get(0));
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(create2, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().size());
		assertEquals(breakdestroyaction, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().get(0));
		SuspendEvent step = ((SuspendEvent)eventlist.get(8));
		assertEquals(create2, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(destroyobjectaction, step.getNewEnabledNodes().get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(13, eventlist.size());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeEntryEvent);
		assertEquals(destroyobjectaction, ((ActivityNodeEntryEvent)eventlist.get(9)).getNode());	
		
		assertTrue(eventlist.get(10) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(10)).getType());
		assertEquals(obj1, ((ExtensionalValueEvent)eventlist.get(10)).getExtensionalValue());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(destroyobjectaction, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		
		assertTrue(eventlist.get(12) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(12)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(12)).getParent());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for DestroyObjectAction 
	 * if links have to be removed
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * DestroyObjectAction (feature = association)
	 * 
	 * Activity input:
	 * object student
	 * (link exists between this object and a vorlesung object)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> DestroyObject.object
	 */
	@Test
	public void testDestroyObjectAction2() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student, true);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung, true);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Object_ obj_vo = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);	
		Reference reference_voobj = new Reference();
		reference_voobj.referent = obj_vo;
		
		Object_ obj_stud = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Reference reference_studobj = new Reference();
		reference_studobj.referent = obj_stud;
		
		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		//valuelist.add(obj_vo);
		valuelist.add(reference_voobj);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		//valuelist.add(obj_stud);
		valuelist.add(reference_studobj);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		

		Activity activity = ActivityFactory.createActivity("testDestroyObjectAction2");	
		DestroyObjectAction action_destroyobject = ActivityFactory.createDestroyObjectAction(activity, "DestroyObject student", true, true);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "object parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_destroyobject.target);
		
		// Start Debugging		
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		//values_stud.add(obj_stud);
		values_stud.add(reference_studobj);
		paramvalue_studobj.values = values_stud;
		
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(3));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(4)).getLocation());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_destroyobject, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue());	
		
		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		assertEquals(obj_stud, ((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue());

		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(action_destroyobject, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());

		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(9)).getParent());

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());	
		assertEquals(1, ((Object_)extensionalValueLists.get(extensionalValueLists.size()-1).get(0)).types.size());
		assertEquals(class_vorlesung, ((Object_)extensionalValueLists.get(extensionalValueLists.size()-1).get(0)).types.get(0));
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());		
		assertEquals(obj_vo, ExecutionContext.getInstance().getExtensionalValues().get(0));
	}
	
	/**
	 * Tests the ExtensionalValueEvents for DestroyObjectAction 
	 * if owned objects have to be removed
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * DestroyObjectAction (feature = association)
	 * 
	 * Activity input:
	 * object student (contains vorlesung object)
	 * (link exists between this object and a vorlesung object)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> DestroyObject.object
	 */
	@Test
	public void testDestroyObjectAction3() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student, true, AggregationKind.composite);		
		
		Object_ obj_vo = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);	
		Reference reference_voobj = new Reference();
		reference_voobj.referent = obj_vo;
		
		Object_ obj_stud = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Reference reference_studobj = new Reference();
		reference_studobj.referent = obj_stud;
		ValueList values_vorlesungen = new ValueList();
		values_vorlesungen.add(reference_voobj);
		obj_stud.setFeatureValue(property_vorlesungen, values_vorlesungen, 1);		
						
		Activity activity = ActivityFactory.createActivity("testDestroyObjectAction2");	
		DestroyObjectAction action_destroyobject = ActivityFactory.createDestroyObjectAction(activity, "DestroyObject student", true, true);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "object parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_destroyobject.target);
		
		// Start Debugging		
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(reference_studobj);
		paramvalue_studobj.values = values_stud;
								
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		/*
		 * TODO strange behavior
		 * If test case is executed on its own, 2 ExtensionalValueEvents are delivered
		 * (2 for creating objects),
		 * if all test cases are executed, 3 ExtensionalValueEvents are delivered
		 * (one additional event for setting the value of the vorlesungen property)
		 */
		assertTrue((eventlist.size() == 4 || eventlist.size() == 5));
		eventlist.remove(0);
		eventlist.remove(0);
		if(eventlist.get(0) instanceof FeatureValueEvent) {
			eventlist.remove(0);
		}
		
		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());

		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(7, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_destroyobject, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		assertEquals(obj_vo, ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue());	
		
		assertTrue(eventlist.get(4) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(4)).getType());
		assertEquals(obj_stud, ((ExtensionalValueEvent)eventlist.get(4)).getExtensionalValue());

		assertTrue(eventlist.get(5) instanceof ActivityNodeExitEvent);
		assertEquals(action_destroyobject, ((ActivityNodeExitEvent)eventlist.get(5)).getNode());

		assertTrue(eventlist.get(6) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(6)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(6)).getParent());

		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());	
		
		assertEquals(0, ExecutionContext.getInstance().getExtensionalValues().size());		
	}
	
	/**
     * Tests the ExtensionalValueEvents for DestroyLinkAction
	 * 
	 * Activity:
	 * CreateObjectAction Student (class = Student)
	 * CreateObjectAction Vorlesung (class = Vorlesung)
	 * (Association: properties: vorlesungen:Vorlesung[*], studenten:Student[*])
	 * ForkNode Student
	 * ForkNode Vorlesung
	 * CreateLinkAction 
	 * DestroyLinkAction
	 * 
	 * Activity DataFlow:
	 * CreateObjectAction Student target --> ForkNode Student
	 * CreateObjectAction Vorlesung target --> ForkNode Vorlesung
	 * 
	 * ForkNode Student --> CreateLinkAction input
	 * ForkNode Vorlesung --> CreateLinkAction input
	 * 
	 * ForkNode Student --> DestroyLinkAction input
	 * ForkNode Vorlesung --> DestroyLinkAction input
	 * 
	 * Activity ControlFlow:
	 * CreateLinkAction --> DestroyLinkAction
	 */
	@Test
	public void testDestroyLinkAction() {
		Class_ student = ActivityFactory.createClass("Student");		
		Class_ vorlesung = ActivityFactory.createClass("Vorlesung");
		
		Property vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, vorlesung, student);
		Property studenten = ActivityFactory.createProperty("studenten", 0, -1, student, vorlesung);
		PropertyList members = new PropertyList();
		members.add(vorlesungen);
		members.add(studenten);
		Association student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Activity activity = ActivityFactory.createActivity("testDestroyLinkAction");
				
		CreateObjectAction createstudent = ActivityFactory.createCreateObjectAction(activity, "CreateObject Student", student);
		CreateObjectAction createvorlesung = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung", vorlesung);
		
		ForkNode forkstudent = ActivityFactory.createForkNode(activity, "Fork Student");
		ForkNode forkvorlesung = ActivityFactory.createForkNode(activity, "Fork Vorlesung");
		
		CreateLinkAction createlinkaction = ActivityFactory.createCreateLinkAction(activity, "CreateLink student2vorlesung", members);
		DestroyLinkAction destroylinkaction = ActivityFactory.createDestroyLinkAction(activity, "DestroyLink student2vorlesung", members);
		
		ActivityFactory.createObjectFlow(activity, createvorlesung.result, forkvorlesung);
		ActivityFactory.createObjectFlow(activity, createstudent.result, forkstudent);
		
		ActivityFactory.createObjectFlow(activity, forkvorlesung, createlinkaction.input.get(0));
		ActivityFactory.createObjectFlow(activity, forkstudent, createlinkaction.input.get(1));
		
		ActivityFactory.createObjectFlow(activity, forkvorlesung, destroylinkaction.input.get(0));
		ActivityFactory.createObjectFlow(activity, forkstudent, destroylinkaction.input.get(1));
		
		ActivityFactory.createControlFlow(activity, createlinkaction, destroylinkaction);
		
		// Set Breakpoint for DestroyLinkAction
		Breakpoint breakpoint = new BreakpointImpl(destroylinkaction);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);		
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(16, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(createstudent, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ stud_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(student, stud_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(createstudent, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
	
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(createvorlesung, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		Object_ vo_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertEquals(vorlesung, vo_obj.getTypes().get(0));						
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(createvorlesung, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());	
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(forkstudent, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(forkstudent, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		assertEquals(forkvorlesung, ((ActivityNodeEntryEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(forkvorlesung, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());	
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeEntryEvent);
		assertEquals(createlinkaction, ((ActivityNodeEntryEvent)eventlist.get(12)).getNode());	
		
		assertTrue(eventlist.get(13) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(13)).getType());
		Link link =  (Link)((ExtensionalValueEvent)eventlist.get(13)).getExtensionalValue();
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(vorlesungen);
		values_expected.add(studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(vo_obj);
		objects_expected.add(stud_obj);
		checkLink(student2vorlesung, link, values_expected, objects_expected);

		assertTrue(eventlist.get(14) instanceof ActivityNodeExitEvent);
		assertEquals(createlinkaction, ((ActivityNodeExitEvent)eventlist.get(14)).getNode());
		
		assertTrue(eventlist.get(15) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(15)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(15)).getBreakpoints().get(0));
		SuspendEvent step = ((SuspendEvent)eventlist.get(15));
		assertEquals(createlinkaction, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(destroylinkaction, step.getNewEnabledNodes().get(0));
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(20, eventlist.size());
		
		assertTrue(eventlist.get(16) instanceof ActivityNodeEntryEvent);
		assertEquals(destroylinkaction, ((ActivityNodeEntryEvent)eventlist.get(16)).getNode());	
		
		assertTrue(eventlist.get(17) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(17)).getType());
		link =  (Link)((ExtensionalValueEvent)eventlist.get(17)).getExtensionalValue();		
		checkLink(null, link, values_expected, objects_expected);

		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(destroylinkaction, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		
		assertTrue(eventlist.get(19) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(19)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(19)).getParent());
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
     * Tests the ExtensionalValueEvents for ClearAssociationAction
	 * 
	 * Activity:
	 * CreateObjectAction Student (class = Student (properties: vorlesungen:Vorlesung[*]))
	 * CreateObjectAction Vorlesung 1 (class = Vorlesung)
	 * CreateObjectAction Vorlesung 2 (class = Vorlesung)
	 * (Association: properties: vorlesungen:Vorlesung[*], studenten:Student[*])
	 * ForkNode Student
	 * CreateLinkAction1 Student - Vorlesung 1
	 * CreateLinkAction2 Student - Vorlesung 2
	 * ClearAssociationAction (Association between Student and Vorlesung)
	 * 
	 * Activity DataFlow:
	 * CreateObjectAction Student result --> ForkNode Student
	 * ForkNode Student --> CreateLinkAction1 input
	 * ForkNode Student --> CreateLinkAction2 input
	 * ForkNode Student --> ClearAssociationAction) 
	 * CreateObjectAction Vorlesung 1 result --> CreateLinkAction1 input
	 * CreateObjectAction Vorlesung 2 result --> CreateLinkAction2 input
	 * 
	 * 
	 * Activity ControlFlow:
	 * CreateObjectAction Student --> CreateObjectACtion Vorlesung1
	 * CreateObjectACtion Vorlesung1 --> CreateObjectACtion Vorlesung2
	 * CreateLinkAction1 --> ClearAssociationAction
	 * CreateLinkAction2 --> ClearAssociationAction
	 */
	@Test
	public void testClearAssociationAction() {
		Class_ student = ActivityFactory.createClass("Student");		
		Class_ vorlesung = ActivityFactory.createClass("Vorlesung");
		
		Property vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, vorlesung, student);
		Property studenten = ActivityFactory.createProperty("studenten", 0, -1, student, vorlesung);
		PropertyList members = new PropertyList();
		members.add(vorlesungen);
		members.add(studenten);
		Association student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Activity activity = ActivityFactory.createActivity("testClearAssociationAction");
				
		CreateObjectAction createstudent = ActivityFactory.createCreateObjectAction(activity, "CreateObject Student", student);
		CreateObjectAction createvorlesung1 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung", vorlesung);
		CreateObjectAction createvorlesung2 = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung 2", vorlesung);
		
		ForkNode forkstudent = ActivityFactory.createForkNode(activity, "Fork Student");
		
		CreateLinkAction createlinkaction1 = ActivityFactory.createCreateLinkAction(activity, "CreateLink student2vorlesung", members);
		CreateLinkAction createlinkaction2 = ActivityFactory.createCreateLinkAction(activity, "CreateLink student2vorlesung", members);
		
		ClearAssociationAction clearassociation = ActivityFactory.createClearAssociationAction(activity, "Clear Association", student2vorlesung);
		
		ActivityFactory.createObjectFlow(activity, createstudent.result, forkstudent);
		ActivityFactory.createObjectFlow(activity, forkstudent, createlinkaction1.input.get(1));
		ActivityFactory.createObjectFlow(activity, forkstudent, createlinkaction2.input.get(1));
		ActivityFactory.createObjectFlow(activity, forkstudent, clearassociation.input.get(0));
		
		ActivityFactory.createObjectFlow(activity, createvorlesung1.result, createlinkaction1.input.get(0));
		ActivityFactory.createObjectFlow(activity, createvorlesung2.result, createlinkaction2.input.get(0));		
		
		ActivityFactory.createControlFlow(activity, createstudent, createvorlesung1);
		ActivityFactory.createControlFlow(activity, createstudent, createvorlesung2);
		ActivityFactory.createControlFlow(activity, createlinkaction1, clearassociation);
		ActivityFactory.createControlFlow(activity, createlinkaction2, clearassociation);
		
		// Set Breakpoint for ClearAssociationAction
		Breakpoint breakpoint = new BreakpointImpl(clearassociation);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);		
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(20, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(createstudent, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ stud_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(student, stud_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(createstudent, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
	
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(forkstudent, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(forkstudent, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ActivityNodeEntryEvent);
		assertEquals(createvorlesung1, ((ActivityNodeEntryEvent)eventlist.get(7)).getNode());	
		
		assertTrue(eventlist.get(8) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(8)).getType());
		Object_ vo1_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(8)).getExtensionalValue();
		assertEquals(vorlesung, vo1_obj.getTypes().get(0));						
		
		assertTrue(eventlist.get(9) instanceof ActivityNodeExitEvent);
		assertEquals(createvorlesung1, ((ActivityNodeExitEvent)eventlist.get(9)).getNode());	
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		assertEquals(createvorlesung2, ((ActivityNodeEntryEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(11)).getType());
		Object_ vo2_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(11)).getExtensionalValue();
		assertEquals(vorlesung, vo2_obj.getTypes().get(0));			
		
		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(createvorlesung2, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());
			
		assertTrue(eventlist.get(13) instanceof ActivityNodeEntryEvent);
		assertEquals(createlinkaction1, ((ActivityNodeEntryEvent)eventlist.get(13)).getNode());	
		
		assertTrue(eventlist.get(14) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(14)).getType());
		
		Link link =  (Link)((ExtensionalValueEvent)eventlist.get(14)).getExtensionalValue();
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(vorlesungen);
		values_expected.add(studenten);
		List<Object_> objects_expected_link1 = new ArrayList<Object_>();
		objects_expected_link1.add(vo1_obj);
		objects_expected_link1.add(stud_obj);
		checkLink(student2vorlesung, link, values_expected, objects_expected_link1);		
				
		assertTrue(eventlist.get(15) instanceof ActivityNodeExitEvent);
		assertEquals(createlinkaction1, ((ActivityNodeExitEvent)eventlist.get(15)).getNode());
		
		assertTrue(eventlist.get(16) instanceof ActivityNodeEntryEvent);
		assertEquals(createlinkaction2, ((ActivityNodeEntryEvent)eventlist.get(16)).getNode());	
		
		assertTrue(eventlist.get(17) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(17)).getType());
		link =  (Link) ((ExtensionalValueEvent)eventlist.get(17)).getExtensionalValue();
		List<Object_> objects_expected_link2 = new ArrayList<Object_>();
		objects_expected_link2.add(vo2_obj);
		objects_expected_link2.add(stud_obj);
		checkLink(student2vorlesung, link, values_expected, objects_expected_link2);				
				
		assertTrue(eventlist.get(18) instanceof ActivityNodeExitEvent);
		assertEquals(createlinkaction2, ((ActivityNodeExitEvent)eventlist.get(18)).getNode());
		
		assertTrue(eventlist.get(19) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(19)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(19)).getBreakpoints().get(0));
		SuspendEvent step = ((SuspendEvent)eventlist.get(19));
		assertEquals(createlinkaction2, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(clearassociation, step.getNewEnabledNodes().get(0));
		
		assertEquals(5, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(25, eventlist.size());
		
		assertTrue(eventlist.get(20) instanceof ActivityNodeEntryEvent);
		assertEquals(clearassociation, ((ActivityNodeEntryEvent)eventlist.get(20)).getNode());	
		
		assertTrue(eventlist.get(21) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(21)).getType());
		link = (Link)((ExtensionalValueEvent)eventlist.get(21)).getExtensionalValue();
		checkLink(null, link, values_expected, objects_expected_link1);	
		
		assertTrue(eventlist.get(22) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(22)).getType());
		link = (Link)((ExtensionalValueEvent)eventlist.get(22)).getExtensionalValue();
		checkLink(null, link, values_expected, objects_expected_link2);
		
		assertTrue(eventlist.get(23) instanceof ActivityNodeExitEvent);
		assertEquals(clearassociation, ((ActivityNodeExitEvent)eventlist.get(23)).getNode());
		
		assertTrue(eventlist.get(24) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(24)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(24)).getParent());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
		for(int i=0;i<3;++i) {
			assertTrue(extensionalValueLists.get(extensionalValueLists.size()-1).get(i).getClass().equals(Object_.class));
		}
	}
	
	private void checkLink(Association association, Link link, List<StructuralFeature> values_expected, List<Object_> objects_expected) {
		assertEquals(association, link.type);
		assertEquals(values_expected.size(), link.featureValues.size());		
		
		List<FeatureValue> values = new ArrayList<FeatureValue>();		
		for(int i=0;i<values_expected.size();++i){
			for(int j=0;j<link.featureValues.size();++j) {
				if(link.featureValues.get(j).feature.equals(values_expected.get(i))) {
					values.add(link.featureValues.get(j));
				}
			}
		}
		
		assertEquals(values_expected.size(), values.size());
		for(int i=0;i<values.size();++i) {
			assertEquals(1, values.get(i).values.size());
			Value v = values.get(i).values.get(0);
			Object_ o = null;
			if(v instanceof Reference) {
				o = ((Reference)v).referent;
			} else if (v instanceof Object_) {
				o = (Object_)v;
			}
			assertEquals(objects_expected.get(i), o);
		}		
	}
	
	/**
     * Tests the ExtensionalValueEvents for ReclassifyObjectAction 
     * if classifiers are removed as type
	 * 
	 * Activity:
	 * CreateObjectAction (class = Class1)
	 * ReclassifyObjectAction (remove: Class1)
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction1.result --> ReclassifyObjectAction.object
	 */
	@Test
	public void testReclassifyObjectAction() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		
		Activity activity = ActivityFactory.createActivity("testReclassifyObjectAction");
		
		ClassifierList oldTypes = new ClassifierList();
		oldTypes.add(class1);
		ClassifierList newTypes = new ClassifierList();
		
		ReclassifyObjectAction reclassify = ActivityFactory.createReclassifyObjectAction(activity, "ReclassifyObject", newTypes, oldTypes);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
				
		ActivityFactory.createObjectFlow(activity, create.result, reclassify.object);
		
		// Set Breakpoint
		Breakpoint breakpoint = new BreakpointImpl(reclassify);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertTrue(value1 instanceof Object_);
		assertEquals(1, value1.getTypes().size());
		assertEquals(class1, value1.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().get(0));
		SuspendEvent step = ((SuspendEvent)eventlist.get(5));
		assertEquals(create, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(reclassify, step.getNewEnabledNodes().get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ obj = (Object_) extensionalValueLists.get(extensionalValueLists.size()-1).get(0);
		assertEquals(1, obj.types.size());
		assertEquals(class1, obj.types.get(0));		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(10, eventlist.size());
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(reclassify, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_REMOVED, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		ExtensionalValue value2 =  ((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue();
		assertTrue(value2 instanceof Object_);
		assertEquals(value1, value2);
		assertEquals(0, value2.getTypes().size());
				
		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(reclassify, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());
		
		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(9)).getParent());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());		
		assertEquals(0, ((Object_)ExecutionContext.getInstance().getExtensionalValues().get(0)).getTypes().size());
	}
	
	/**
     * Tests the ExtensionalValueEvents for ReclassifyObjectAction
     * if classifiers are removed and added as type
	 * 
	 * Activity:
	 * CreateObjectAction (class = Class1)
	 * ReclassifyObjectAction (remove: Class1, add: Class2, Class3)
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction1.result --> ReclassifyObjectAction.object
	 */
	@Test
	public void testReclassifyObjectAction2() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Class_ class2 = ActivityFactory.createClass("Class2");
		Class_ class3 = ActivityFactory.createClass("Class3");
		
		Activity activity = ActivityFactory.createActivity("testReclassifyObjectAction");
		
		ClassifierList oldTypes = new ClassifierList();
		oldTypes.add(class1);
		ClassifierList newTypes = new ClassifierList();
		newTypes.add(class2);
		newTypes.add(class3);
		
		ReclassifyObjectAction reclassify = ActivityFactory.createReclassifyObjectAction(activity, "ReclassifyObject", newTypes, oldTypes);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
				
		ActivityFactory.createObjectFlow(activity, create.result, reclassify.object);
		
		// Set Breakpoint
		Breakpoint breakpoint = new BreakpointImpl(reclassify);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertTrue(value1 instanceof Object_);
		assertEquals(1, value1.getTypes().size());
		assertEquals(class1, value1.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().get(0));		
		SuspendEvent step = ((SuspendEvent)eventlist.get(5));
		assertEquals(create, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(reclassify, step.getNewEnabledNodes().get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ obj = (Object_) extensionalValueLists.get(extensionalValueLists.size()-1).get(0);
		assertEquals(1, obj.types.size());
		assertEquals(class1, obj.types.get(0));		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(12, eventlist.size());
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(reclassify, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_REMOVED, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		ExtensionalValue value2 =  ((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue();
		assertEquals(value1, value2);
		
		assertTrue(eventlist.get(8) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_ADDED, ((ExtensionalValueEvent)eventlist.get(8)).getType());
		ExtensionalValue value3 =  ((ExtensionalValueEvent)eventlist.get(8)).getExtensionalValue();
		assertEquals(value2, value3);
		
		assertTrue(eventlist.get(9) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_ADDED, ((ExtensionalValueEvent)eventlist.get(9)).getType());
		ExtensionalValue value4 =  ((ExtensionalValueEvent)eventlist.get(9)).getExtensionalValue();
		assertEquals(value3, value4);
		assertEquals(2, ((Object_)value4).types.size());
		assertTrue(((Object_)value4).types.contains(class2));
		assertTrue(((Object_)value4).types.contains(class3));
		assertFalse(((Object_)value4).types.contains(class1));
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(reclassify, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(11)).getParent());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());		
		assertEquals(((Object_)ExecutionContext.getInstance().getExtensionalValues().get(0)), value4);
	}
	
	/**
     * Tests the ExtensionalValueEvents for ReclassifyObjectAction 
     * if due to new/removed type classifier, feature values are 
     * added/removed
	 * 
	 * Activity:
	 * CreateObjectAction (class = Class1 (1 attribute of type Class1))
	 * ReclassifyObjectAction (remove: Class1, add Class2 (1attribute))
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction1.result --> ReclassifyObjectAction.object
	 */
	@Test
	public void testReclassifyObjectAction3() {
		Class_ class1 = ActivityFactory.createClass("Class1");
		Property property1 = ActivityFactory.createProperty("property1.1", 1, 1, class1, class1);
		Class_ class2 = ActivityFactory.createClass("Class2");
		Property property2 = ActivityFactory.createProperty("property2.1", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class2);
		
		Activity activity = ActivityFactory.createActivity("testReclassifyObjectAction");
		
		ClassifierList oldTypes = new ClassifierList();
		oldTypes.add(class1);
		ClassifierList newTypes = new ClassifierList();
		newTypes.add(class2);
		
		ReclassifyObjectAction reclassify = ActivityFactory.createReclassifyObjectAction(activity, "ReclassifyObject", newTypes, oldTypes);
		CreateObjectAction create = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class1);
				
		ActivityFactory.createObjectFlow(activity, create.result, reclassify.object);
		
		// Set Breakpoint
		Breakpoint breakpoint = new BreakpointImpl(reclassify);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(create, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertTrue(value1 instanceof Object_);
		assertEquals(1, value1.getTypes().size());
		assertEquals(class1, value1.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(create, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(5)).getBreakpoints().get(0));		
		SuspendEvent step = ((SuspendEvent)eventlist.get(5));
		assertEquals(create, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(reclassify, step.getNewEnabledNodes().get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ obj = (Object_) extensionalValueLists.get(extensionalValueLists.size()-1).get(0);
		assertEquals(1, obj.types.size());
		assertEquals(class1, obj.types.get(0));		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(13, eventlist.size());
				
		assertTrue(eventlist.get(6) instanceof ActivityNodeEntryEvent);
		assertEquals(reclassify, ((ActivityNodeEntryEvent)eventlist.get(6)).getNode());	
		
		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_REMOVED, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		ExtensionalValue value2 =  ((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue();
		assertEquals(value1, value2);
				
		assertTrue(eventlist.get(8) instanceof FeatureValueEvent);
		assertEquals(ExtensionalValueEventType.VALUE_DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(8)).getType());
		ExtensionalValue value3 =  ((ExtensionalValueEvent)eventlist.get(8)).getExtensionalValue();
		assertEquals(value2, value3);
		assertEquals(property1, ((FeatureValueEvent)eventlist.get(8)).getFeatureValue().feature);
		assertEquals(0, ((FeatureValueEvent)eventlist.get(8)).getFeatureValue().values.size());
		
		assertTrue(eventlist.get(9) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.TYPE_ADDED, ((ExtensionalValueEvent)eventlist.get(9)).getType());
		ExtensionalValue value4 =  ((ExtensionalValueEvent)eventlist.get(9)).getExtensionalValue();
		assertEquals(value3, value4);
		assertEquals(1, ((Object_)value4).types.size());
		assertTrue(((Object_)value4).types.contains(class2));
		assertFalse(((Object_)value4).types.contains(class1));
		
		assertTrue(eventlist.get(10) instanceof FeatureValueEvent);
		assertEquals(ExtensionalValueEventType.VALUE_CREATION, ((FeatureValueEvent)eventlist.get(10)).getType());
		ExtensionalValue value5 =  ((FeatureValueEvent)eventlist.get(10)).getExtensionalValue();
		assertEquals(value4, value5);
		assertEquals(property2, ((FeatureValueEvent)eventlist.get(10)).getFeatureValue().feature);
		assertEquals(0, ((FeatureValueEvent)eventlist.get(10)).getFeatureValue().values.size());
		assertEquals(1, value5.getFeatureValues().size());
		assertEquals(property2, value5.getFeatureValues().get(0).feature);
		assertEquals(0, value5.getFeatureValues().get(0).values.size());
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());
		Object_ objatlocus = (Object_)ExecutionContext.getInstance().getExtensionalValues().get(0);
		assertEquals(1, objatlocus.types.size());
		assertTrue(objatlocus.types.contains(class2));
		assertEquals(1, objatlocus.getFeatureValues().size());
		assertEquals(property2, objatlocus.getFeatureValues().get(0).feature);
		assertEquals(0, objatlocus.getFeatureValues().get(0).values.size());
		
		assertTrue(eventlist.get(11) instanceof ActivityNodeExitEvent);
		assertEquals(reclassify, ((ActivityNodeExitEvent)eventlist.get(11)).getNode());
		
		assertTrue(eventlist.get(12) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(12)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(12)).getParent());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
     * Tests the ExtensionalValueEvents for AddStructuralFeatureValueAction 
	 * 
	 * Activity:
	 * CreateObjectAction (class = Person (name:String[1..1]))
	 * ValueSpecificationAction
	 * AddStructuralFeatureValueAction (isReplaceAll = true)
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction.result --> AddStructuralFeatureValueAction.value
	 * ValueSpecificationAction.result --> AddStructuralFeatureValueAction.object
	 */
	@Test
	public void testAddStructuralFeatureValueAction() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("name", 1, 1, ExecutionContext.getInstance().getPrimitiveStringType(), class_person);
		
		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueAction");
		
		CreateObjectAction action_createobj = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class_person);
		ValueSpecificationAction action_valuespec = ActivityFactory.createValueSpecificationAction(activity, "CreateValue tanja", "tanja");
		AddStructuralFeatureValueAction action_addfeaturevalue = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddStructuralFeatureValue Person-name", property_name);
		
		ActivityFactory.createObjectFlow(activity, action_createobj.result, action_addfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, action_valuespec.result, action_addfeaturevalue.value);
		
		ActivityFactory.createControlFlow(activity, action_createobj, action_valuespec);
		
		for(int i=0;i<2;++i) {
			
			// Set Breakpoint
			Breakpoint breakpoint = new BreakpointImpl(action_addfeaturevalue);
			ExecutionContext.getInstance().addBreakpoint(breakpoint);
			
			// Start Debugging
			ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
					
			assertEquals(2, eventlist.size());
			
			assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
			ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
			assertEquals(activity, activityentry.getActivity());		
			assertNull(activityentry.getParent());
			
			assertTrue(eventlist.get(1) instanceof SuspendEvent);
			assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
			
			assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
			
			// Resume Execution
			ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
			
			assertEquals(8, eventlist.size());
			
			assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
			assertEquals(action_createobj, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
			
			assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
			assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
			ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
			assertTrue(value1 instanceof Object_);
			assertEquals(1, value1.getTypes().size());
			assertEquals(class_person, value1.getTypes().get(0));
					
			assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
			assertEquals(action_createobj, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
						
			assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
			assertEquals(action_valuespec, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
			
			assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
			assertEquals(action_valuespec, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
			
			assertTrue(eventlist.get(7) instanceof BreakpointEvent);
			assertEquals(1, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().size());
			assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().get(0));
			SuspendEvent step = ((SuspendEvent)eventlist.get(7));
			assertEquals(action_valuespec, step.getLocation());
			assertEquals(activityentry, step.getParent());
			assertEquals(1, step.getNewEnabledNodes().size());
			assertEquals(action_addfeaturevalue, step.getNewEnabledNodes().get(0));
			
			assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
			Object_ obj = (Object_) extensionalValueLists.get(extensionalValueLists.size()-1).get(0);
			assertEquals(1, obj.types.size());
			assertEquals(class_person, obj.types.get(0));		
			
			// Resume Execution
			ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
			
			assertEquals(12, eventlist.size());
					
			assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
			assertEquals(action_addfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	
			
			assertTrue(eventlist.get(9) instanceof FeatureValueEvent);
			assertEquals(ExtensionalValueEventType.VALUE_CHANGED, ((FeatureValueEvent)eventlist.get(9)).getType());
			ExtensionalValue value2 =  ((FeatureValueEvent)eventlist.get(9)).getExtensionalValue();
			assertTrue(value2 instanceof Object_);
			assertEquals(value1, value2);
			assertEquals(1, value2.getTypes().size());
			assertEquals(class_person, value2.getTypes().get(0));
			assertEquals(1, value2.featureValues.size());
			assertEquals(property_name, value2.featureValues.get(0).feature);
			assertEquals(1, value2.featureValues.get(0).values.size());
			assertTrue(value2.featureValues.get(0).values.get(0) instanceof StringValue);
			assertEquals("tanja", ((StringValue)value2.featureValues.get(0).values.get(0)).value);
					
			assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
			assertEquals(action_addfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
			
			assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
			assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
			assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(11)).getParent());
			
			assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
			
			assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());		
			assertEquals(value2, ((Object_)ExecutionContext.getInstance().getExtensionalValues().get(0)));
			
			if(i==0) {
				try {
					this.setUp();
				} catch (Exception e) {
					fail("Exception was thrown.");
				}
				
				action_addfeaturevalue.isReplaceAll = false;
			}
		}
	}
	
	/**
     * Tests the ExtensionalValueEvents for AddStructuralFeatureValueAction 
	 * For the name property of the created object, the values "tanja" and "mayerhofer"
	 * are set prior to execution AddStructuralFeatureValueAction
	 * 
	 * Activity:
	 * CreateObjectAction (class = Person (name:String[1..*] unique))
	 * ValueSpecificationAction
	 * AddStructuralFeatureValueAction (isReplaceAll = false)
	 * 
	 * Activity ObjectFlow:
	 * CreateObjectAction.result --> AddStructuralFeatureValueAction.value
	 * ValueSpecificationAction.result --> AddStructuralFeatureValueAction.object
	 */
	@Test
	public void testAddStructuralFeatureValueAction2() {
		Class_ class_person = ActivityFactory.createClass("Person");
		Property property_name = ActivityFactory.createProperty("name", 1, -1, ExecutionContext.getInstance().getPrimitiveStringType(), class_person);
		property_name.setIsUnique(true);
		
		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueAction");
		
		CreateObjectAction action_createobj = ActivityFactory.createCreateObjectAction(activity, "CreateObject Class1", class_person);
		ValueSpecificationAction action_valuespec = ActivityFactory.createValueSpecificationAction(activity, "CreateValue tanja", "tanja");
		AddStructuralFeatureValueAction action_addfeaturevalue = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddStructuralFeatureValue Person-name", property_name, false);
		
		ActivityFactory.createObjectFlow(activity, action_createobj.result, action_addfeaturevalue.object);
		ActivityFactory.createObjectFlow(activity, action_valuespec.result, action_addfeaturevalue.value);
		
		ActivityFactory.createControlFlow(activity, action_createobj, action_valuespec);
		
		// Set Breakpoint
		Breakpoint breakpoint = new BreakpointImpl(action_addfeaturevalue);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(8, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createobj, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		ExtensionalValue value1 =  ((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertTrue(value1 instanceof Object_);
		assertEquals(1, value1.getTypes().size());
		assertEquals(class_person, value1.getTypes().get(0));
		
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(action_createobj, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());

		ExtensionalValueList valuesAtLocus = ExecutionContext.getInstance().getExtensionalValues();
		assertEquals(2, valuesAtLocus.size());
		Object_ object = null;
		for(int i=0;i<valuesAtLocus.size();++i) {
			if(valuesAtLocus.get(i).getClass().equals(Object_.class)) {
				object = (Object_)valuesAtLocus.get(i);
				break;
			}			
		}
		assertNotNull(object);
		FeatureValue featureValue = object.getFeatureValue(property_name);
		assertNotNull(featureValue);
		StringValue str = new StringValue();
		str.value = "tanja";
		featureValue.values.add(str);
		str = new StringValue();
		str.value = "mayerhofer";
		featureValue.values.add(str);
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_valuespec, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());
		
		assertTrue(eventlist.get(6) instanceof ActivityNodeExitEvent);
		assertEquals(action_valuespec, ((ActivityNodeExitEvent)eventlist.get(6)).getNode());
		
		assertTrue(eventlist.get(7) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(7)).getBreakpoints().get(0));		
		SuspendEvent step = ((SuspendEvent)eventlist.get(7));
		assertEquals(action_valuespec, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(action_addfeaturevalue, step.getNewEnabledNodes().get(0));
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		Object_ obj = (Object_) extensionalValueLists.get(extensionalValueLists.size()-1).get(0);
		assertEquals(1, obj.types.size());
		assertEquals(class_person, obj.types.get(0));		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(12, eventlist.size());
				
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(action_addfeaturevalue, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof FeatureValueEvent);
		assertEquals(ExtensionalValueEventType.VALUE_CHANGED, ((FeatureValueEvent)eventlist.get(9)).getType());
		ExtensionalValue value2 =  ((FeatureValueEvent)eventlist.get(9)).getExtensionalValue();
		assertTrue(value2 instanceof Object_);
		assertEquals(value1, value2);
		assertEquals(1, value2.getTypes().size());
		assertEquals(class_person, value2.getTypes().get(0));
		assertEquals(1, value2.featureValues.size());
		assertEquals(property_name, value2.featureValues.get(0).feature);
		assertEquals(2, value2.featureValues.get(0).values.size());
		assertTrue(value2.featureValues.get(0).values.get(0) instanceof StringValue);
		assertEquals("tanja", ((StringValue)value2.featureValues.get(0).values.get(0)).value);
		assertTrue(value2.featureValues.get(0).values.get(1) instanceof StringValue);
		assertEquals("mayerhofer", ((StringValue)value2.featureValues.get(0).values.get(1)).value);
				
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(action_addfeaturevalue, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(11)).getParent());
		
		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		assertEquals(1, ExecutionContext.getInstance().getExtensionalValues().size());		
		assertEquals(value2, ((Object_)ExecutionContext.getInstance().getExtensionalValues().get(0)));
	}
	
   /**
    * Tests the ExtensionalValueEvents for AddStructuralFeatureValueAction when a link is created with this action
    *  
	* Activity:
	* CreateObjectAction Student (class = Student)
	* CreateObjectAction Vorlesung (class = Vorlesung)
	* (Association: properties: vorlesungen:Vorlesung[*], studenten:Student[*])
	* AddStructuralFeatureValueAction (structuralFeature = vorlesungen:Vorlesung[*] 
	* 
	* Activity DataFlow:
	* CreateObjectAction Student target --> AddStructuralFeatureValueAction.object
	* CreateObjectAction Vorlesung target --> AddStructuralFeatureValueAction.value
	*/
	@Test
	public void testAddStructuralFeatureValueActionForLinks() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueActionForLinks");
				
		CreateObjectAction action_createstudent = ActivityFactory.createCreateObjectAction(activity, "CreateObject Student", class_student);
		CreateObjectAction action_createvorlesung = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung", class_vorlesung);
		AddStructuralFeatureValueAction action_addfeaturelink = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddFeatureValue link between student and vorlesung", property_vorlesungen);
			
		ActivityFactory.createObjectFlow(activity, action_createvorlesung.result, action_addfeaturelink.value);
		ActivityFactory.createObjectFlow(activity, action_createstudent.result, action_addfeaturelink.object);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());
				
		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());
		
		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());
		
		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());
		
		assertEquals(12, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createstudent, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	
		
		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ stud_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(class_student, stud_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(action_createstudent, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());
					
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createvorlesung, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		Object_ vo_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertEquals(class_vorlesung, vo_obj.getTypes().get(0));
				
		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_createvorlesung, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());
		
		assertTrue(eventlist.get(8) instanceof ActivityNodeEntryEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeEntryEvent)eventlist.get(8)).getNode());	
		
		assertTrue(eventlist.get(9) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(9)).getType());
		Link link =  (Link)(((ExtensionalValueEvent)eventlist.get(9)).getExtensionalValue());
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(property_vorlesungen);
		values_expected.add(property_studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(vo_obj);
		objects_expected.add(stud_obj);
		checkLink(association_student2vorlesung, link, values_expected, objects_expected);
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeExitEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeExitEvent)eventlist.get(10)).getNode());
		
		assertTrue(eventlist.get(11) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(11)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(11)).getParent());
		
		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for AddStructuralFeatureValueAction when a link is created with this action
	 * in the case that a link already exists.
	 *  
	 * Activity:
	 * CreateObjectAction Student (class = Student)
	 * CreateObjectAction Vorlesung (class = Vorlesung)
	 * (Association: properties: vorlesungen:Vorlesung[*], studenten:Student[*])
	 * After the creation of the objects, a link is created between those objects
	 * AddStructuralFeatureValueAction (structuralFeature = vorlesungen:Vorlesung[*]  
	 * 
	 * Activity DataFlow:
	 * CreateObjectAction Student target --> AddStructuralFeatureValueAction.object
	 * CreateObjectAction Vorlesung target --> AddStructuralFeatureValueAction.value
	 * 
	 * ATTENTION
	 * This cannot be tested because the AddStructualFeatureValueAction is unable to detect
	 * already existing links because they do not take references to objects into account
	 * for determining if a link between the given objects already exists (CreateObjectAction, 
	 * ReadSelfAction, ReadExtentAction all provide references to objects instead of objects
	 * themselves) 
	 * 
	 * {@link #testAddStructuralFeatureValueActionForLinks3()}
	 */
	@Test
	public void testAddStructuralFeatureValueActionForLinks2() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");

		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);

		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueActionForLinks");

		CreateObjectAction action_createstudent = ActivityFactory.createCreateObjectAction(activity, "CreateObject Student", class_student);
		CreateObjectAction action_createvorlesung = ActivityFactory.createCreateObjectAction(activity, "CreateObject Vorlesung", class_vorlesung);
		AddStructuralFeatureValueAction action_addfeaturelink = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddFeatureValue link between student and vorlesung", property_vorlesungen);

		ActivityFactory.createObjectFlow(activity, action_createvorlesung.result, action_addfeaturelink.value);
		ActivityFactory.createObjectFlow(activity, action_createstudent.result, action_addfeaturelink.object);

		// Set Breakpoint for AddStructuralFeatureValueAction
		Breakpoint breakpoint = new BreakpointImpl(action_addfeaturelink);
		ExecutionContext.getInstance().addBreakpoint(breakpoint);
		
		// Start Debugging
		ExecutionContext.getInstance().executeStepwise(activity, null, new ParameterValueList());

		assertEquals(2, eventlist.size());

		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());

		assertEquals(0, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(9, eventlist.size());

		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createstudent, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());	

		assertTrue(eventlist.get(3) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(3)).getType());
		Object_ stud_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(3)).getExtensionalValue();
		assertEquals(class_student, stud_obj.getTypes().get(0));

		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(action_createstudent, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());

		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_createvorlesung, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	

		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		Object_ vo_obj =  (Object_)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue();
		assertEquals(class_vorlesung, vo_obj.getTypes().get(0));

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_createvorlesung, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());

		assertTrue(eventlist.get(8) instanceof BreakpointEvent);
		assertEquals(1, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().size());
		assertEquals(breakpoint, ((BreakpointEvent)eventlist.get(8)).getBreakpoints().get(0));		
		SuspendEvent step = ((SuspendEvent)eventlist.get(8));
		assertEquals(action_createvorlesung, step.getLocation());
		assertEquals(activityentry, step.getParent());
		assertEquals(1, step.getNewEnabledNodes().size());
		assertEquals(action_addfeaturelink, step.getNewEnabledNodes().get(0));
		
		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		valuelist.add(vo_obj);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		valuelist.add(stud_obj);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		
		
		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(14, eventlist.size());
		
		assertTrue(eventlist.get(10) instanceof ActivityNodeEntryEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeEntryEvent)eventlist.get(10)).getNode());	

		/*
		 * DESTRUCTION would have been expected
		 */
		/*
		assertTrue(eventlist.get(11) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(11)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(11)).getExtensionalValue());		
*/

		assertTrue(eventlist.get(11) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(11)).getType());
		Link link =  (Link)(((ExtensionalValueEvent)eventlist.get(11)).getExtensionalValue());
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(property_vorlesungen);
		values_expected.add(property_studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(vo_obj);
		objects_expected.add(stud_obj);
		checkLink(association_student2vorlesung, link, values_expected, objects_expected);

		assertTrue(eventlist.get(12) instanceof ActivityNodeExitEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeExitEvent)eventlist.get(12)).getNode());

		assertTrue(eventlist.get(13) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(13)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(13)).getParent());

		assertEquals(4, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for AddStructuralFeatureValueAction when a link is created with this action
	 * in the case that a link already exists.
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * AddStructuralFeatureValueAction
	 * 
	 * Activity input:
	 * object student
	 * object vorlesung
	 * (link exists between those objects)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> AddStructuralFeatureValueAction.object
	 * parameter object vorlesung --> AddStructuralFeatureValueAction.value
	 */
	@Test
	public void testAddStructuralFeatureValueActionForLinks3() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Object_ vo_obj = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);		
		Object_ stud_obj = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		valuelist.add(vo_obj);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		valuelist.add(stud_obj);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		

		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueActionForLinks");				
		AddStructuralFeatureValueAction action_addfeaturelink = ActivityFactory.createAddStructuralFeatureValueAction(activity, "AddFeatureValue link between student and vorlesung", property_vorlesungen);
		Parameter param_voobj = ActivityFactory.createParameter(activity, "VO Parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_voobj = ActivityFactory.createActivityParameterNode(activity, "", param_voobj);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "Student Parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_voobj, action_addfeaturelink.value);
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_addfeaturelink.object);

		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(stud_obj);
		paramvalue_studobj.values = values_stud;
		
		ParameterValue paramvalue_voobj = new ParameterValue();
		paramvalue_voobj.parameter = param_voobj;
		ValueList values_vo = new ValueList();
		values_vo.add(vo_obj);
		paramvalue_voobj.values = values_vo;
				
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		inputs.add(paramvalue_voobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(3));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(4)).getLocation());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(10, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue());		

		assertTrue(eventlist.get(7) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.CREATION, ((ExtensionalValueEvent)eventlist.get(7)).getType());
		Link link =  (Link)(((ExtensionalValueEvent)eventlist.get(7)).getExtensionalValue());
		List<StructuralFeature> values_expected = new ArrayList<StructuralFeature>();
		values_expected.add(property_vorlesungen);
		values_expected.add(property_studenten);
		List<Object_> objects_expected = new ArrayList<Object_>();
		objects_expected.add(vo_obj);
		objects_expected.add(stud_obj);
		checkLink(association_student2vorlesung, link, values_expected, objects_expected);

		assertTrue(eventlist.get(8) instanceof ActivityNodeExitEvent);
		assertEquals(action_addfeaturelink, ((ActivityNodeExitEvent)eventlist.get(8)).getNode());

		assertTrue(eventlist.get(9) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(9)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(9)).getParent());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for ClearStructuralFeatureAction for
	 * clearing a structural feature
	 * 
	 * class Student (properties: name:String[*])
	 *  
	 * Activity:
	 * ClearStructuralFeatureAction (feature=name)
	 * 
	 * Activity input:
	 * object student (name = {"tanja"})
	 * 
	 * Activity DataFlow:
	 * parameter object student --> ClearStructuralFeatureAction.object
	 */
	@Test
	public void testClearStructuralFeatureAction() {
		PrimitiveType type_string = ExecutionContext.getInstance().getPrimitiveStringType();
				
		Class_ class_student = ActivityFactory.createClass("Student");		
		Property property_name = ActivityFactory.createProperty("name", 0, -1, type_string, class_student);
				
		Object_ obj_stud = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		StringValue value_name = new StringValue();
		value_name.type = type_string;
		value_name.value = "tanja";
		ValueList values = new ValueList();
		values.add(value_name);		
		obj_stud.setFeatureValue(property_name, values, 1);		
		
		Activity activity = ActivityFactory.createActivity("testAddStructuralFeatureValueActionForLinks");				
		ClearStructuralFeatureAction action_clearstructfeature = ActivityFactory.createClearStructuralFeatureAction(activity, "ClearFeature name", property_name);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "Student Parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_clearstructfeature.object);

		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(obj_stud);
		paramvalue_studobj.values = values_stud;
						
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		/*
		 * TODO strange behavior
		 * If test case is executed on its own, 1 ExtensionalValueEvents are delivered
		 * (1 for creating object),
		 * if all test cases are executed, 2 ExtensionalValueEvents are delivered
		 * (one additional event for setting the value of the name property)
		 */
		assertTrue( 3 <= eventlist.size() && eventlist.size() <= 4);
		if(eventlist.get(0) instanceof ExtensionalValueEvent) {
			eventlist.remove(0);
		}
		if(eventlist.get(0) instanceof FeatureValueEvent) {
			eventlist.remove(0);
		}

		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_clearstructfeature, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());		

		assertTrue(eventlist.get(3) instanceof FeatureValueEvent);
		FeatureValueEvent event = (FeatureValueEvent)eventlist.get(3);
		assertEquals(ExtensionalValueEventType.VALUE_CHANGED, event.getType());
		assertTrue(event.getExtensionalValue() instanceof Object_);
		Object_ o = (Object_)event.getExtensionalValue();
		assertEquals(obj_stud, o);		
		FeatureValue value = event.getFeatureValue();
		assertEquals(property_name, value.feature);
		assertEquals(0, value.values.size());

		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(action_clearstructfeature, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());

		assertTrue(eventlist.get(5) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(5)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(5)).getParent());

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	 
	/**
	 * Tests the ExtensionalValueEvents for ClearStructuralFeatureAction for
	 * clearing a link
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * ClearStructuralFeatureAction (feature = association)
	 * 
	 * Activity input:
	 * object student
	 * (link exists between student object and a class object)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> ClearStructuralFeatureAction.object
	 */
	@Test
	public void testClearStructuralFeatureAction2() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Object_ vo_obj = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);		
		Object_ stud_obj = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		valuelist.add(vo_obj);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		valuelist.add(stud_obj);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		

		Activity activity = ActivityFactory.createActivity("testClearStructuralFeatureAction2");	
		ClearStructuralFeatureAction action_clearlink = ActivityFactory.createClearStructuralFeatureAction(activity, "ClearLink student2volresung", property_vorlesungen);

		Parameter param_studobj = ActivityFactory.createParameter(activity, "Student Parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_clearlink.object);

		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(stud_obj);
		paramvalue_studobj.values = values_stud;
						
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(3));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(4)).getLocation());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_clearlink, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue());		

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_clearlink, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());

		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(8)).getParent());

		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for RemoveStructuralFeatureValueAction for
	 * removing a value of a structural feature
	 * 
	 * class Student (properties: name:String[*])
	 *  
	 * Activity:
	 * RemoveStructuralFeatureValueAction (feature=name)
	 * 
	 * Activity input:
	 * object student (name = {"tanja", "mayerhofer"})
	 * value "mayerhofer"
	 * 
	 * Activity DataFlow:
	 * parameter object student --> RemoveStructuralFeatureValueAction.object
	 * parameter value mayerhofer --> RemoveStructuralFeatureValueAction.value
	 */
	@Test
	public void testRemoveStructuralFeatureValueAction() {
		PrimitiveType type_string = ExecutionContext.getInstance().getPrimitiveStringType();
				
		Class_ class_student = ActivityFactory.createClass("Student");		
		Property property_name = ActivityFactory.createProperty("name", 0, -1, type_string, class_student);
				
		Object_ obj_stud = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		StringValue value_name1 = new StringValue();
		value_name1.type = type_string;
		value_name1.value = "tanja";
		StringValue value_name2 = new StringValue();
		value_name2.type = type_string;
		value_name2.value = "mayerhofer";
		ValueList values = new ValueList();
		values.add(value_name1);
		values.add(value_name2);		
		obj_stud.setFeatureValue(property_name, values, 1);		
		
		Activity activity = ActivityFactory.createActivity("testRemoveStructuralFeatureValueAction");				
		RemoveStructuralFeatureValueAction action_removevalue = ActivityFactory.createRemoveStructuralFeatureValueAction(activity, "RemoveValue name", property_name, true);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "Object Parameter", ParameterDirectionKind.in);
		Parameter param_value = ActivityFactory.createParameter(activity, "Value Parameter", ParameterDirectionKind.in);
		Parameter param_removeAt = ActivityFactory.createParameter(activity, "RemoveAt Parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "object", param_studobj);
		ActivityParameterNode paramnode_value = ActivityFactory.createActivityParameterNode(activity, "value", param_value);
		ActivityParameterNode paramnode_removeAt = ActivityFactory.createActivityParameterNode(activity, "removeAt", param_removeAt);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_removevalue.object);
		ActivityFactory.createObjectFlow(activity, paramnode_value, action_removevalue.value);
		ActivityFactory.createObjectFlow(activity, paramnode_removeAt, action_removevalue.removeAt);

		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(obj_stud);
		paramvalue_studobj.values = values_stud;
		
		ParameterValue paramvalue_string = new ParameterValue();
		paramvalue_string.parameter = param_value;
		ValueList values_value = new ValueList();
		values_value.add(value_name2);
		paramvalue_string.values = values_value;
		
		ParameterValue paramvalue_removeAt = new ParameterValue();
		paramvalue_removeAt.parameter = param_removeAt;
		ValueList values_removeAt = new ValueList();
		UnlimitedNaturalValue value_removeAt = new UnlimitedNaturalValue();
		value_removeAt.type = ExecutionContext.getInstance().getPrimitiveUnlimitedNaturalType();
		UnlimitedNatural unlimnatural = new UnlimitedNatural();
		unlimnatural.naturalValue = 2;
		value_removeAt.value = unlimnatural;
		values_removeAt.add(value_removeAt);
		paramvalue_removeAt.values = values_removeAt;
						
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		inputs.add(paramvalue_string);
		inputs.add(paramvalue_removeAt);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		/*
		 * TODO strange behavior
		 * If test case is executed on its own, 1 ExtensionalValueEvents are delivered
		 * (1 for creating object),
		 * if all test cases are executed, 2 ExtensionalValueEvents are delivered
		 * (one additional event for setting the value of the name property)
		 */
		assertTrue( 3 <= eventlist.size() && eventlist.size() <= 4);
		if(eventlist.get(0) instanceof ExtensionalValueEvent) {
			eventlist.remove(0);
		}
		if(eventlist.get(0) instanceof FeatureValueEvent) {
			eventlist.remove(0);
		}

		assertEquals(2, eventlist.size());
		
		assertTrue(eventlist.get(0) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(1) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(1)).getLocation());

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(6, eventlist.size());
		
		assertTrue(eventlist.get(2) instanceof ActivityNodeEntryEvent);
		assertEquals(action_removevalue, ((ActivityNodeEntryEvent)eventlist.get(2)).getNode());		

		assertTrue(eventlist.get(3) instanceof FeatureValueEvent);
		FeatureValueEvent event = (FeatureValueEvent)eventlist.get(3);
		assertEquals(ExtensionalValueEventType.VALUE_CHANGED, event.getType());
		assertTrue(event.getExtensionalValue() instanceof Object_);
		Object_ o = (Object_)event.getExtensionalValue();
		assertEquals(obj_stud, o);		
		FeatureValue value = event.getFeatureValue();
		assertEquals(property_name, value.feature);
		assertEquals(1, value.values.size());
		assertEquals("tanja", ((StringValue)value.values.get(0)).value);

		assertTrue(eventlist.get(4) instanceof ActivityNodeExitEvent);
		assertEquals(action_removevalue, ((ActivityNodeExitEvent)eventlist.get(4)).getNode());

		assertTrue(eventlist.get(5) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(5)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(5)).getParent());

		assertEquals(1, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	/**
	 * Tests the ExtensionalValueEvents for RemoveStructuralFeatureValueAction for
	 * removing a link
	 * 
	 * class Student
	 * class Vorlesung
	 * association properties: vorlesungen:Vorlesung[*], studenten:Student[*]
	 *  
	 * Activity:
	 * RemoveStructuralFeatureValueAction (feature = association)
	 * 
	 * Activity input:
	 * object student
	 * (link exists between this object and a vorlesung object)
	 * 
	 * Activity DataFlow:
	 * parameter object student --> RemoveStructuralFeatureValueAction.object
	 */
	@Test
	public void testRemoveStructuralFeatureValueAction2() {
		Class_ class_student = ActivityFactory.createClass("Student");		
		Class_ class_vorlesung = ActivityFactory.createClass("Vorlesung");
		Property property_vorlesungen = ActivityFactory.createProperty("vorlesungen", 0, -1, class_vorlesung, class_student);
		Property property_studenten = ActivityFactory.createProperty("studenten", 0, -1, class_student, class_vorlesung);
		PropertyList members = new PropertyList();
		members.add(property_vorlesungen);
		members.add(property_studenten);
		Association association_student2vorlesung = ActivityFactory.createAssociation("student2vorlesung", members);
		
		Object_ vo_obj = ExecutionContext.getInstance().getLocus().instantiate(class_vorlesung);		
		Object_ stud_obj = ExecutionContext.getInstance().getLocus().instantiate(class_student);
		Link newLink = new Link();
		ValueList valuelist = new ValueList();
		valuelist.add(vo_obj);
		newLink.setFeatureValue(property_vorlesungen, valuelist, 1);
		valuelist = new ValueList();
		valuelist.add(stud_obj);
		newLink.setFeatureValue(property_studenten, valuelist, 1);
		newLink.type = association_student2vorlesung;
		newLink.addTo(ExecutionContext.getInstance().getLocus());		

		Activity activity = ActivityFactory.createActivity("testRemoveStructuralFeatureValueAction2");	
		RemoveStructuralFeatureValueAction action_removelink = ActivityFactory.createRemoveStructuralFeatureValueAction(activity, "RemoveLink student2vorlesung", property_vorlesungen, false);
		Parameter param_studobj = ActivityFactory.createParameter(activity, "object parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_studobj = ActivityFactory.createActivityParameterNode(activity, "", param_studobj);
		Parameter param_voobj = ActivityFactory.createParameter(activity, "value parameter", ParameterDirectionKind.in);
		ActivityParameterNode paramnode_voobj = ActivityFactory.createActivityParameterNode(activity, "", param_voobj);
		
		ActivityFactory.createObjectFlow(activity, paramnode_studobj, action_removelink.object);
		ActivityFactory.createObjectFlow(activity, paramnode_voobj, action_removelink.value);
		
		// Start Debugging
		ParameterValue paramvalue_studobj = new ParameterValue();
		paramvalue_studobj.parameter = param_studobj;
		ValueList values_stud = new ValueList();
		values_stud.add(stud_obj);
		paramvalue_studobj.values = values_stud;
		
		ParameterValue paramvalue_voobj = new ParameterValue();
		paramvalue_voobj.parameter = param_voobj;
		ValueList values_vo = new ValueList();
		values_vo.add(vo_obj);
		paramvalue_voobj.values = values_vo;
						
		ParameterValueList inputs = new ParameterValueList();
		inputs.add(paramvalue_studobj);
		inputs.add(paramvalue_voobj);
		
		ExecutionContext.getInstance().executeStepwise(activity, null, inputs);

		assertEquals(5, eventlist.size());

		assertTrue(eventlist.get(3) instanceof ActivityEntryEvent);
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(3));		
		assertEquals(activity, activityentry.getActivity());		
		assertNull(activityentry.getParent());

		assertTrue(eventlist.get(4) instanceof SuspendEvent);
		assertEquals(activity, ((SuspendEvent)eventlist.get(4)).getLocation());

		assertEquals(3, extensionalValueLists.get(extensionalValueLists.size()-1).size());

		// Resume Execution
		ExecutionContext.getInstance().resume(activityentry.getActivityExecutionID());

		assertEquals(9, eventlist.size());
		
		assertTrue(eventlist.get(5) instanceof ActivityNodeEntryEvent);
		assertEquals(action_removelink, ((ActivityNodeEntryEvent)eventlist.get(5)).getNode());	
		
		assertTrue(eventlist.get(6) instanceof ExtensionalValueEvent);
		assertEquals(ExtensionalValueEventType.DESTRUCTION, ((ExtensionalValueEvent)eventlist.get(6)).getType());
		assertEquals(newLink, (Link)((ExtensionalValueEvent)eventlist.get(6)).getExtensionalValue());		

		assertTrue(eventlist.get(7) instanceof ActivityNodeExitEvent);
		assertEquals(action_removelink, ((ActivityNodeExitEvent)eventlist.get(7)).getNode());

		assertTrue(eventlist.get(8) instanceof ActivityExitEvent);
		assertEquals(activity, ((ActivityExitEvent)eventlist.get(8)).getActivity());
		assertEquals(activityentry, ((ActivityExitEvent)eventlist.get(8)).getParent());

		assertEquals(2, extensionalValueLists.get(extensionalValueLists.size()-1).size());		
	}
	
	@Override
	public void notify(Event event) {
		eventlist.add(event);
		
		if(event instanceof SuspendEvent || event instanceof ActivityExitEvent) {
			ExtensionalValueList list = new ExtensionalValueList();
			for(int i=0;i<ExecutionContext.getInstance().getExtensionalValues().size();++i) {
				ExtensionalValue value = ExecutionContext.getInstance().getExtensionalValues().get(i);
				if(value.getClass() == Object_.class) {
					list.add(copyObject((Object_)ExecutionContext.getInstance().getExtensionalValues().get(i)));
				}
				if(value.getClass() == Link.class) {
					list.add(value);
				}
			}
			extensionalValueLists.add(list);
		}
	}			
}
