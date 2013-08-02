/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm;

import static junit.framework.Assert.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;

public class XMOFVirtualMachineTest {

	@Test
	public void runSimpleModel() {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();
		XMOFBasedModel simpleModel = new XMOFBasedModel(
				modelResource.getContents());
		XMOFVirtualMachine vm = new XMOFVirtualMachine(simpleModel);
		assertTrue(vm.mayRun());
		vm.run();
	}

	@Test
	public void runPetriNetModel() {
		PetriNetFactory factory = new PetriNetFactory();
		factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();
		XMOFBasedModel petrinet = new XMOFBasedModel(
				modelResource.getContents());
		XMOFVirtualMachine vm = new XMOFVirtualMachine(petrinet);
		assertTrue(vm.mayRun());
		
		// before the execution the input place has one token
		EObject inputplace = factory.getInputplace();
		assertEquals(1, (int)(inputplace.eGet(factory.getInitialTokensAttribute())));
		assertEquals(0, (int)(inputplace.eGet(factory.getTokensAttribute())));		
		EObject outputplace = factory.getOutputplace();
		assertEquals(0, (int)(outputplace.eGet(factory.getInitialTokensAttribute())));
		assertEquals(0, (int)(outputplace.eGet(factory.getTokensAttribute())));
		
		vm.run();
		
		// after the execution the output place has one token
		Object_ inputplace_ = vm.getInstanceMap().getObject(inputplace);
		FeatureValue tokensFeatureValue =  getFeatureValueByName(inputplace_, factory.getTokensAttribute().getName()); 
		assertNotNull(tokensFeatureValue);
		assertEquals(1, tokensFeatureValue.values.size());
		assertEquals(0, ((IntegerValue)tokensFeatureValue.values.get(0)).value);
		
		Object_ outputplace_ = vm.getInstanceMap().getObject(outputplace);
		tokensFeatureValue =  getFeatureValueByName(outputplace_, factory.getTokensAttribute().getName()); 
		assertNotNull(tokensFeatureValue);
		assertEquals(1, tokensFeatureValue.values.size());
		assertEquals(1, ((IntegerValue)tokensFeatureValue.values.get(0)).value);
	}

	private FeatureValue getFeatureValueByName(Object_ object, String featureName) {
		for(FeatureValue featureValue : object.featureValues) {
			if(featureValue.feature.name.equals(featureName)) {
				return featureValue;
			}
		}
		return null;
	}
	
	
}
