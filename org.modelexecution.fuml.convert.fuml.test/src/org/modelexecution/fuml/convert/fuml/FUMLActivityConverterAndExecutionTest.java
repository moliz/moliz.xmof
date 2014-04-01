/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 * Tanja Mayerhofer - implementation
 */
package org.modelexecution.fuml.convert.fuml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.Syntax.Activities.IntermediateActivities.IntermediateActivitiesPackage;
import org.modelexecution.fuml.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IValueConversionResult;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListGetFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListSizeFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Library.IntegerFunctionImplementation.IntegerGreaterFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerMinusFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerPlusFunctionBehaviorExecution;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Parameter;

public class FUMLActivityConverterAndExecutionTest implements
		ExecutionEventListener {

	private ResourceSet resourceSet;
	private ExecutionContext executionContext;
	private IConversionResult conversionResult;

	@Before
	public void registerExecutionListener() {
		getExecutionContext().addEventListener(this);
	}

	@After
	public void unregisterExecutionListener() {
		getExecutionContext().removeEventListener(this);
	}

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(
				IntermediateActivitiesPackage.eNS_URI,
				IntermediateActivitiesPackage.eINSTANCE);
	}

	private Resource getResource(String path) {
		return resourceSet.getResource(
				URI.createFileURI(new File(path).getAbsolutePath()), true);
	}

	@Test
	public void testPetriNetExample() {
		// convert syntax part (i.e., classes and activities)
		Resource classModelResource = getResource("models/petrinet.xmi");
		FUMLConverter converter = new FUMLConverter();
		conversionResult = converter.convert(classModelResource);

		// convert semantics part (i.e., input values for activity execution)
		Resource valueResource = getResource("models/petrinet_input.xmi");
		FUMLValueConverter valueConverter = new FUMLValueConverter(
				conversionResult);
		IValueConversionResult valueConversionResult = valueConverter
				.convert(valueResource);
		Assert.assertEquals(9, valueConversionResult.getExtensionalValues()
				.size());
		Assert.assertTrue(checkPlacesBeforeExecution(valueConversionResult));

		// provide input values do execution context
		addExtensionalValuesToContext(valueConversionResult
				.getExtensionalValues());

		// obtain activity that shall be executed
		fUML.Syntax.Activities.IntermediateActivities.Activity activity = conversionResult
				.getActivity("main");

		// create parameters for activity execution
		Object_ netObject = getObject(valueConversionResult, "Net").get(0);
		Parameter netParameter = getParameter(activity, "net");
		ParameterValue netParameterValue = createParameterValue(netParameter,
				netObject);
		ParameterValueList activityParameterValues = createParameterValueList(netParameterValue);

		// register opaqueBehvaiors
		registerOpaqueBehaviors(classModelResource);

		// execute activity
		execute(activity, activityParameterValues);

		Assert.assertTrue(checkPlacesAfterExecution(valueConversionResult));
	}

	private void registerOpaqueBehaviors(Resource resource) {
		Set<OpaqueBehavior> opaqueBehaviors = getOpaqueBehaviors(resource);
		for (Iterator<OpaqueBehavior> iterator = opaqueBehaviors.iterator(); iterator
				.hasNext();) {
			OpaqueBehavior opaqueBehavior = iterator.next();
			OpaqueBehaviorExecution execution = getOpaqueBehaviorExecution(opaqueBehavior);
			if (execution != null)
				executionContext.addOpaqueBehavior(execution);
		}
	}

	private OpaqueBehaviorExecution getOpaqueBehaviorExecution(
			OpaqueBehavior opaqueBehavior) {
		fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior fUMLOpaqueBehavior = (fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior) conversionResult
				.getFUMLElement(opaqueBehavior);
		OpaqueBehaviorExecution execution = null;
		if (opaqueBehavior.getName().equals("add")) {
			execution = new IntegerPlusFunctionBehaviorExecution();
		} else if (opaqueBehavior.getName().equals("subtract")) {
			execution = new IntegerMinusFunctionBehaviorExecution();
		} else if (opaqueBehavior.getName().equals("greater")) {
			execution = new IntegerGreaterFunctionBehaviorExecution();
		} else if (opaqueBehavior.getName().equals("listget")) {
			execution = new ListGetFunctionBehaviorExecution();
		} else if (opaqueBehavior.getName().equals("listsize")) {
			execution = new ListSizeFunctionBehaviorExecution();
		}
		if (execution != null && fUMLOpaqueBehavior != null) {
			execution.types.add(fUMLOpaqueBehavior);
			return execution;
		}
		return null;
	}

	private Set<OpaqueBehavior> getOpaqueBehaviors(Resource resource) {
		Set<OpaqueBehavior> opaqueBehaviors = new HashSet<OpaqueBehavior>();
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator
				.hasNext();) {
			EObject eObject = iterator.next();
			if (eObject instanceof OpaqueBehavior)
				opaqueBehaviors.add((OpaqueBehavior) eObject);
		}
		return opaqueBehaviors;
	}

	private boolean checkPlacesBeforeExecution(
			IValueConversionResult valueConversionResult) {
		Object_ place1 = getObject(valueConversionResult, "Place", "name",
				createStringValue("p1")).get(0);
		Object_ place2 = getObject(valueConversionResult, "Place", "name",
				createStringValue("p2")).get(0);

		// tokens value is not set for the place instances
		return getFeatureValue(place1, "tokens").values.size() == 0
				&& getFeatureValue(place2, "tokens").values.size() == 0;
	}

	private boolean checkPlacesAfterExecution(
			IValueConversionResult valueConversionResult) {
		Object_ place1 = getObject(valueConversionResult, "Place", "name",
				createStringValue("p1")).get(0);
		Object_ place2 = getObject(valueConversionResult, "Place", "name",
				createStringValue("p2")).get(0);

		int place1_tokens = ((IntegerValue) getFeatureValue(place1, "tokens").values
				.get(0)).value;
		int place2_tokens = ((IntegerValue) getFeatureValue(place2, "tokens").values
				.get(0)).value;

		return (place1_tokens == 0 && place2_tokens == 1);
	}

	private StringValue createStringValue(String str) {
		StringValue value = new StringValue();
		value.value = str;
		return value;
	}

	private ParameterValueList createParameterValueList(
			ParameterValue... parameterValues) {
		ParameterValueList parameterValueList = new ParameterValueList();
		for (ParameterValue parameterValue : parameterValues) {
			parameterValueList.add(parameterValue);
		}
		return parameterValueList;
	}

	private ParameterValue createParameterValue(Parameter parameter,
			Value... values) {
		ParameterValue parameterValue = new ParameterValue();
		parameterValue.parameter = parameter;
		for (Value value : values) {
			if (value instanceof Object_) {
				Reference reference = new Reference();
				reference.referent = (Object_) value;
				parameterValue.values.add(reference);
			}
		}
		return parameterValue;
	}

	private Parameter getParameter(
			fUML.Syntax.Activities.IntermediateActivities.Activity activity,
			String parameterName) {
		for (Parameter parameter : activity.ownedParameter) {
			if (parameter.name.equals(parameterName))
				return parameter;
		}
		return null;
	}

	private void execute(
			fUML.Syntax.Activities.IntermediateActivities.Activity activity,
			ParameterValueList parameter) {
		getExecutionContext().execute(activity, null, parameter);
	}

	private void addExtensionalValuesToContext(
			Collection<ExtensionalValue> extensionalValues) {
		for (ExtensionalValue value : extensionalValues) {
			getExecutionContext().getLocus().add(value);
		}
	}

	private ExecutionContext getExecutionContext() {
		if (executionContext == null) {
			executionContext = ExecutionContext.getInstance();
		}
		return executionContext;
	}

	private List<Object_> getObject(
			IValueConversionResult valueConversionResult, String classname) {
		List<Object_> objects = new ArrayList<Object_>();
		for (ExtensionalValue value : valueConversionResult
				.getExtensionalValues()) {
			if (value instanceof Object_)
				for (Classifier classifier : value.getTypes()) {
					if (classifier.name.equals(classname))
						objects.add((Object_) value);
				}
		}
		return objects;
	}

	private List<Object_> getObject(
			IValueConversionResult valueConversionResult, String classname,
			String propertyname, Value value) {
		List<Object_> result = new ArrayList<Object_>();
		List<Object_> objects = getObject(valueConversionResult, classname);
		for (Object_ o : objects) {
			FeatureValue featureValue = getFeatureValue(o, propertyname);
			for (Value v : featureValue.values) {
				if (v.equals(value)) {
					result.add(o);
					break;
				}
			}
		}
		return result;
	}

	private FeatureValue getFeatureValue(Object_ object, String propertyname) {
		for (FeatureValue featureValue : object.featureValues) {
			if (featureValue.feature.name.equals(propertyname)) {
				return featureValue;
			}
		}
		return null;
	}

	@Override
	public void notify(Event event) {
		System.out.println(event);
	}
}
