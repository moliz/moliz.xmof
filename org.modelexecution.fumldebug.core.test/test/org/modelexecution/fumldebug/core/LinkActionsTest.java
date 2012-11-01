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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.IntermediateActions.CreateLinkAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ReadLinkAction;
import fUML.Syntax.Actions.IntermediateActions.ReadStructuralFeatureAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.Activities.IntermediateActivities.ForkNode;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.PropertyList;

/**
 * @author Tanja Mayerhofer
 *
 */
public class LinkActionsTest extends MolizTest implements ExecutionEventListener {

	private List<Event> eventlist = new ArrayList<Event>();
	private ExecutionContext executionContext = ExecutionContext.getInstance();
	
	private Class_ cl_student;
	private Class_ cl_university;
	private Property prop_student;
	private Property prop_university;			
	private Association as_student2university;
	private Parameter param_linkstudent;
	private Parameter param_linkuniversity;		
	private Parameter param_propstudent;
	private Parameter param_propuniversity;		
	private ForkNode fork_student;
	private ForkNode fork_university ;
	private CreateLinkAction action_link;

	public LinkActionsTest() {
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
		ExecutionContext.getInstance().reset();
		ExecutionContext.getInstance().getExecutionEventProvider().addEventListener(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}	
		
	@Test
	public void testAssociationOwningBothEnds() {				
		Activity activity = createActivityForCreatingAndReadingLink();
		
		// Start execution
		executionContext.execute(activity, null, null);
				
		// Get output
		ParameterValue output_student = getOutputValue(param_linkstudent);
		ParameterValue output_university = getOutputValue(param_linkuniversity);
		assertNotNull(output_student);
		assertNotNull(output_university);
		
		// Check output
		assertTrue(output_student.values.get(0) instanceof Reference);
		Object_ obj_student = ((Reference)output_student.values.get(0)).referent;
		assertTrue(obj_student.types.get(0).equals(cl_student));
		assertEquals(0, obj_student.featureValues.size());
		
		assertTrue(output_university.values.get(0) instanceof Reference);
		Object_ obj_university = ((Reference)output_university.values.get(0)).referent;
		assertTrue(obj_university.types.get(0).equals(cl_university));
		assertEquals(0, obj_university.featureValues.size());
		
		// Get link from locus
		ExtensionalValueList values = executionContext.getLocus().getExtent(as_student2university);
		assertEquals(1, values.size());
		assertTrue(values.get(0) instanceof Link);
		Link link = (Link)values.get(0);
		
		// Check link from locus
		assertTrue(link.type.equals(as_student2university));
		assertEquals(2, link.featureValues.size());
		FeatureValue value_student = null;
		FeatureValue value_university = null;
		for(int i=0;i<link.featureValues.size();++i) {
			if(link.featureValues.get(i).feature.equals(prop_student)) {
				value_student = link.featureValues.get(i);
			} else if(link.featureValues.get(i).feature.equals(prop_university)){
				value_university = link.featureValues.get(i);
			}
		}
		assertNotNull(value_student);
		assertNotNull(value_university);
		
		assertEquals(((Reference)value_student.values.get(0)).referent, obj_student);
		assertEquals(((Reference)value_university.values.get(0)).referent, obj_university);
	}
	
	@Test
	public void testAssociationOwningNoEnds() {				
		Activity activity = createActivityForCreatingAndReadingLinkAndFeature();
		
		/*
		 * Start execution
		 */
		executionContext.execute(activity, null, null);
				
		/*
		 * Get output
		 */
		ParameterValue output_linkstudent = getOutputValue(param_linkstudent);
		ParameterValue output_linkuniversity = getOutputValue(param_linkuniversity);
		ParameterValue output_propstudent = getOutputValue(param_propstudent);
		ParameterValue output_propuniversity = getOutputValue(param_propuniversity);
		assertNotNull(output_linkstudent);
		assertNotNull(output_linkuniversity);
		assertNotNull(output_propstudent);
		assertNotNull(output_propuniversity);
		
		/*
		 * Check output
		 */
		// Read link action provided student object 
		assertTrue(output_linkstudent.values.get(0) instanceof Reference);
		Object_ obj_student = ((Reference)output_linkstudent.values.get(0)).referent;
		assertTrue(obj_student.types.get(0).equals(cl_student));
		// Student object has no university set
		assertEquals(1, obj_student.featureValues.size());
		assertEquals(prop_university, obj_student.featureValues.get(0).feature);
		assertEquals(0, obj_student.featureValues.get(0).values.size());
		
		// Read link action provided university object
		assertTrue(output_linkuniversity.values.get(0) instanceof Reference);
		Object_ obj_university = ((Reference)output_linkuniversity.values.get(0)).referent;
		assertTrue(obj_university.types.get(0).equals(cl_university));
		// University object has no student set
		assertEquals(1, obj_university.featureValues.size());
		assertEquals(prop_student, obj_university.featureValues.get(0).feature);
		assertEquals(0, obj_university.featureValues.get(0).values.size());
		
		// Read structural feature action on university feature of student object provided linked university object
		assertEquals(1, output_propuniversity.values.size());
		assertEquals(obj_university, ((Reference)output_propuniversity.values.get(0)).referent);
		
		// Read structural feature action on student feature of university object provided linked student object 
		assertEquals(1, output_propstudent.values.size());
		assertEquals(obj_student, ((Reference)output_propstudent.values.get(0)).referent);		
		
		/*
		 * Get link from locus
		 */
		ExtensionalValueList values = executionContext.getLocus().getExtent(as_student2university);
		assertEquals(1, values.size());
		assertTrue(values.get(0) instanceof Link);
		Link link = (Link)values.get(0);
		
		/*
		 * Check link from locus
		 */
		assertTrue(link.type.equals(as_student2university));
		assertEquals(2, link.featureValues.size());
		FeatureValue value_linkstudent = null;
		FeatureValue value_linkuniversity = null;
		for(int i=0;i<link.featureValues.size();++i) {
			if(link.featureValues.get(i).feature.equals(prop_student)) {
				value_linkstudent = link.featureValues.get(i);
			} else if(link.featureValues.get(i).feature.equals(prop_university)){
				value_linkuniversity = link.featureValues.get(i);
			}
		}
		assertNotNull(value_linkstudent);
		assertNotNull(value_linkuniversity);
		
		assertEquals(((Reference)value_linkstudent.values.get(0)).referent, obj_student);
		assertEquals(((Reference)value_linkuniversity.values.get(0)).referent, obj_university);
	}

	private Class_ createStudentClass() {
		Class_ class_ = ActivityFactory.createClass("Student");
		return class_;
	}
	
	private Class_ createUniversityClass() {
		Class_ class_ = ActivityFactory.createClass("University");
		return class_;
	}
	
	private Property createStudentAssociationProperty() {
		Property prop_student = ActivityFactory.createProperty("student", 0, -1, cl_student);
		return prop_student;
	}
	
	private Property createUniversityAssociationProperty() {
		Property prop_university = ActivityFactory.createProperty("university", 0, -1, cl_university);
		return prop_university;
	}
	
	private Association createStudentUniversityAssociationOwningBothEnds() {				
		Association association = new Association();
		association.name = "student2university";		
		association.memberEnd.add(prop_student);
		association.memberEnd.add(prop_university);
		prop_student.association = association;
		prop_university.association = association;
		association.ownedEnd.add(prop_student);
		association.ownedEnd.add(prop_student);		
		prop_student.owningAssociation = association;
		prop_university.owningAssociation = association;
		
		return association;
	}
	
	private Association createStudentUniversityAssociationOwningNoEnds() {				
		Association association = new Association();
		association.name = "student2university";		
		association.memberEnd.add(prop_student);
		association.memberEnd.add(prop_university);
		prop_student.association = association;
		prop_university.association = association;
		
		cl_student.addOwnedAttribute(prop_university);
		cl_university.addOwnedAttribute(prop_student);
		prop_student.class_ = cl_university;
		prop_university.class_ = cl_student;
		
		return association;
	}
	
	private Activity createActivityForCreatingAndReadingLink() {			
		cl_student = createStudentClass();
		cl_university = createUniversityClass();
		prop_student = createStudentAssociationProperty();
		prop_university = createUniversityAssociationProperty();			
		as_student2university = createStudentUniversityAssociationOwningBothEnds();		
		param_linkstudent = ActivityFactory.createParameter("student", ParameterDirectionKind.out, cl_student);
		param_linkuniversity = ActivityFactory.createParameter("university", ParameterDirectionKind.out, cl_university);
		
		Activity activity = ActivityFactory.createActivity("activity for creating and reading link");
		CreateObjectAction action_student = ActivityFactory.createCreateObjectAction(activity, "create student", cl_student);
		CreateObjectAction action_university = ActivityFactory.createCreateObjectAction(activity, "create university", cl_university);
		
		fork_student = ActivityFactory.createForkNode(activity, "fork student");
		fork_university = ActivityFactory.createForkNode(activity, "fork university");

		PropertyList props_association = new PropertyList();
		props_association.add(prop_student);
		props_association.add(prop_university);
		action_link = ActivityFactory.createCreateLinkAction(activity, "create student2university", props_association);
		
		PropertyList props_student = new PropertyList();
		props_student.add(prop_student);
		ReadLinkAction action_readlinkstudent = ActivityFactory.createReadLinkAction(activity, "read student2university through student", props_student, prop_university);
		
		PropertyList props_university = new PropertyList();
		props_university.add(prop_university);
		ReadLinkAction action_readlinkuniversity = ActivityFactory.createReadLinkAction(activity, "read student2university through university", props_university, prop_student);
				
		activity.ownedParameter.add(param_linkstudent);
		activity.ownedParameter.add(param_linkuniversity);
		ActivityParameterNode paramnode_student = ActivityFactory.createActivityParameterNode(activity, "student link", param_linkstudent);
		ActivityParameterNode paramnode_university = ActivityFactory.createActivityParameterNode(activity, "university link", param_linkuniversity);				
		
		ActivityFactory.createObjectFlow(activity, action_student.result, fork_student);
		ActivityFactory.createObjectFlow(activity, action_university.result, fork_university);
		ActivityFactory.createObjectFlow(activity, fork_student, action_link.input.get(0));
		ActivityFactory.createObjectFlow(activity, fork_university, action_link.input.get(1));
		ActivityFactory.createObjectFlow(activity, fork_student, action_readlinkstudent.input.get(0));
		ActivityFactory.createObjectFlow(activity, action_readlinkstudent.result, paramnode_university);
		ActivityFactory.createControlFlow(activity, action_link, action_readlinkstudent);		
		ActivityFactory.createObjectFlow(activity, fork_university, action_readlinkuniversity.input.get(0));
		ActivityFactory.createObjectFlow(activity, action_readlinkuniversity.result, paramnode_student);
		ActivityFactory.createControlFlow(activity, action_link, action_readlinkuniversity);
		
		return activity;
	}
	
	private Activity createActivityForCreatingAndReadingLinkAndFeature() { 				
		param_propstudent = ActivityFactory.createParameter("student", ParameterDirectionKind.out, cl_student);
		param_propuniversity = ActivityFactory.createParameter("university", ParameterDirectionKind.out, cl_university);		

		Activity activity = createActivityForCreatingAndReadingLink();
		as_student2university = createStudentUniversityAssociationOwningNoEnds();
		
		ReadStructuralFeatureAction action_readStudent = ActivityFactory.createReadStructuralFeatureAction(activity, "read student", prop_student);
		ReadStructuralFeatureAction action_readUniversity = ActivityFactory.createReadStructuralFeatureAction(activity, "read university", prop_university);
		
		activity.ownedParameter.add(param_linkstudent);
		activity.ownedParameter.add(param_linkuniversity);
		activity.ownedParameter.add(param_propstudent);
		activity.ownedParameter.add(param_propuniversity);
		ActivityParameterNode paramnode_propstudent = ActivityFactory.createActivityParameterNode(activity, "student property", param_propstudent);
		ActivityParameterNode paramnode_propuniversity = ActivityFactory.createActivityParameterNode(activity, "university property", param_propuniversity);		
		
		
		ActivityFactory.createObjectFlow(activity, fork_student, action_readUniversity.input.get(0));
		ActivityFactory.createObjectFlow(activity, fork_university, action_readStudent.input.get(0));

		ActivityFactory.createObjectFlow(activity, action_readUniversity.result, paramnode_propuniversity);
		ActivityFactory.createObjectFlow(activity, action_readStudent.result, paramnode_propstudent);	
		
		ActivityFactory.createControlFlow(activity, action_link, action_readUniversity);
		ActivityFactory.createControlFlow(activity, action_link, action_readStudent);
		
		return activity;
	}
	
	private ParameterValue getOutputValue(Parameter parameter) {
		ActivityEntryEvent activityentry = ((ActivityEntryEvent)eventlist.get(0));
		int executionID = activityentry.getActivityExecutionID();
		ParameterValueList output = executionContext.getActivityOutput(executionID);

		ParameterValue outputvalue = null;
		for(int i=0;i<output.size();++i) {
			if(output.get(i).parameter.equals(parameter)) {
				outputvalue = output.get(i);
			}
		}
		return outputvalue;
	}	
	
	@Override
	public void notify(Event event) {
		eventlist.add(event);
		if(event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent exitEvent = (ActivityNodeExitEvent)event;
			System.out.println("node executed: " + exitEvent.getNode().getClass().getName() + " " + exitEvent.getNode().name);
		}
	}
		
}
