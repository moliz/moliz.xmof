/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	private static final String NET = "Net";
	private static final String NAME = "name";
	private static final String PLACES = "places";
	private static final String TRANSITIONS = "transitions";
	private static final String PLACE = "Place";
	private static final String TRANSITION = "Transition";
	private static final String INITIAL_TOKENS = "initialTokens";
	private static final String INCOMING = "incoming";
	private static final String OUTGOING = "outgoing";

	private EPackage metamodelPackage;

	private EObject netObject;
	private EObject transitionObject;
	private EObject place2Object;
	private EObject place1Object;

	@Before
	public void createModels() {
		createMetamodel();
		createModel();
	}

	private void createMetamodel() {
		metamodelPackage = EcoreFactory.eINSTANCE.createEPackage();
		createPlaceClass();
		createTransitionClass();
		createNetClass();
	}

	private void createPlaceClass() {
		EClass eClass = createClass(PLACE);
		addNameAttribute(eClass);
		addTokensAttribute(eClass);
	}

	private EClass createClass(String name) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		metamodelPackage.getEClassifiers().add(eClass);
		return eClass;
	}

	private void addNameAttribute(EClass eClass) {
		EAttribute nameAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		nameAttribute.setName(NAME);
		nameAttribute.setEType(EcorePackage.eINSTANCE.getEString());
		eClass.getEStructuralFeatures().add(nameAttribute);
	}

	private void addTokensAttribute(EClass eClass) {
		EAttribute nameAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		nameAttribute.setName(INITIAL_TOKENS);
		nameAttribute.setEType(EcorePackage.eINSTANCE.getEInt());
		eClass.getEStructuralFeatures().add(nameAttribute);
	}

	private void createNetClass() {
		EClass eClass = createClass(NET);
		addReference(eClass, PLACES, getPlaceClass(), true);
		addReference(eClass, TRANSITIONS, getTransitionClass(), true);
	}

	private EClass getPlaceClass() {
		return getClass(PLACE);
	}

	private EClass getTransitionClass() {
		return getClass(TRANSITION);
	}

	private void addReference(EClass eClass, String name, EClassifier eType,
			boolean containment) {
		EReference placesReference = EcoreFactory.eINSTANCE.createEReference();
		placesReference.setName(name);
		placesReference.setLowerBound(0);
		placesReference.setUpperBound(-1);
		placesReference.setEType(eType);
		placesReference.setContainment(containment);
		eClass.getEStructuralFeatures().add(placesReference);
	}

	private void createTransitionClass() {
		EClass eClass = createClass(TRANSITION);
		addIncomingReference(eClass);
		addOutgoingReference(eClass);
	}

	private void addIncomingReference(EClass eClass) {
		EReference incomingReference = EcoreFactory.eINSTANCE
				.createEReference();
		incomingReference.setName(INCOMING);
		incomingReference.setLowerBound(0);
		incomingReference.setUpperBound(-1);
		incomingReference.setEType(getPlaceClass());
		incomingReference.setContainment(false);
		eClass.getEStructuralFeatures().add(incomingReference);
	}

	private void addOutgoingReference(EClass eClass) {
		EReference outgoingReference = EcoreFactory.eINSTANCE
				.createEReference();
		outgoingReference.setName(OUTGOING);
		outgoingReference.setLowerBound(0);
		outgoingReference.setUpperBound(-1);
		outgoingReference.setEType(getPlaceClass());
		outgoingReference.setContainment(false);
		eClass.getEStructuralFeatures().add(outgoingReference);
	}

	private EClass getNetClass() {
		return getClass(NET);
	}

	private EClass getClass(String name) {
		return (EClass) metamodelPackage.getEClassifier(name);
	}

	private void createModel() {
		EFactory factory = metamodelPackage.getEFactoryInstance();
		netObject = factory.create(getNetClass());

		place1Object = factory.create(getPlaceClass());
		setName(place1Object, "place1");
		setTokens(place1Object, 1);

		place2Object = factory.create(getPlaceClass());
		setName(place2Object, "place2");

		transitionObject = factory.create(getTransitionClass());
		setIncoming(transitionObject, place1Object);
		setOutgoing(transitionObject, place2Object);

		EList<EObject> placesValue = new BasicEList<EObject>();
		placesValue.add(place1Object);
		placesValue.add(place2Object);
		EReference placesReference = getPlacesReference(netObject.eClass());
		netObject.eSet(placesReference, placesValue);

		EList<EObject> transitionsValue = new BasicEList<EObject>();
		transitionsValue.add(transitionObject);
		EReference transitionsReference = getTransitionsReference(netObject
				.eClass());
		netObject.eSet(transitionsReference, transitionsValue);
	}

	private void setName(EObject placeObject, String name) {
		EAttribute nameAttribute = getNameAttribute(placeObject.eClass());
		placeObject.eSet(nameAttribute, name);
	}

	private EAttribute getNameAttribute(EClass eClass) {
		return (EAttribute) eClass.getEStructuralFeature(NAME);
	}

	private void setTokens(EObject placeObject, int tokens) {
		EAttribute tokenAttribute = getTokenAttribute(placeObject.eClass());
		placeObject.eSet(tokenAttribute, tokens);
	}

	private EAttribute getTokenAttribute(EClass eClass) {
		return (EAttribute) eClass.getEStructuralFeature(INITIAL_TOKENS);
	}

	private void setIncoming(EObject transitionObject, EObject placeObject) {
		EReference incomingReference = getIncomingReference(transitionObject
				.eClass());
		EList<EObject> values = new BasicEList<EObject>();
		values.add(placeObject);
		transitionObject.eSet(incomingReference, values);
	}

	private EReference getIncomingReference(EClass eClass) {
		return (EReference) eClass.getEStructuralFeature(INCOMING);
	}

	private void setOutgoing(EObject transitionObject, EObject placeObject) {
		EReference outgoingReference = getOutgoingReference(transitionObject
				.eClass());
		EList<EObject> values = new BasicEList<EObject>();
		values.add(placeObject);
		transitionObject.eSet(outgoingReference, values);
	}

	private EReference getOutgoingReference(EClass eClass) {
		return (EReference) eClass.getEStructuralFeature(OUTGOING);
	}

	private EReference getPlacesReference(EClass eClass) {
		return (EReference) eClass.getEStructuralFeature(PLACES);
	}

	private EReference getTransitionsReference(EClass eClass) {
		return (EReference) eClass.getEStructuralFeature(TRANSITIONS);
	}

	@Test
	public void testSimpleObject() {
		Collection<EPackage> inputPackages = new ArrayList<EPackage>();
		inputPackages.add(metamodelPackage);
		Collection<EClass> mainClasses = new ArrayList<EClass>();
		mainClasses.add(getNetClass());
		ConfigurationGenerator generator = new ConfigurationGenerator(
				inputPackages, mainClasses);
		Collection<EPackage> configurationPackages = generator
				.generateConfigurationPackages();
		assertEquals(1, configurationPackages.size());

		Collection<EObject> originalObjects = new ArrayList<EObject>();
		originalObjects.add(netObject);
		ConfigurationObjectMap map = new ConfigurationObjectMap(
				originalObjects, configurationPackages);
		EObject confNetObject = map.getConfigurationObject(netObject);
		assertNotNull(confNetObject);
		assertEquals(confNetObject, map.getConfigurationObject(map
				.getOriginalObject(confNetObject)));

		testReferenceValues(map, confNetObject);
		testAttributeValues(map, confNetObject);
	}

	private void testReferenceValues(ConfigurationObjectMap map,
			EObject confNetObject) {
		EList<?> placesValue = (EList<?>) confNetObject
				.eGet(getPlacesReference(getNetClass()));
		assertEquals(2, placesValue.size());

		EList<?> transitionsValue = (EList<?>) confNetObject
				.eGet(getTransitionsReference(getNetClass()));
		assertEquals(1, transitionsValue.size());

		EObject confTransition = (EObject) transitionsValue.get(0);

		assertEquals(confTransition,
				map.getConfigurationObject(transitionObject));
		assertEquals(transitionObject, map.getOriginalObject(map
				.getConfigurationObject(transitionObject)));

		EList<?> incomingValue = (EList<?>) confTransition
				.eGet(getIncomingReference(getTransitionClass()));
		EList<?> outgoingValue = (EList<?>) confTransition
				.eGet(getOutgoingReference(getTransitionClass()));
		assertEquals(1, incomingValue.size());
		assertEquals(1, outgoingValue.size());
		assertEquals(place1Object,
				map.getOriginalObject((EObject) incomingValue.get(0)));
		assertEquals(place2Object,
				map.getOriginalObject((EObject) outgoingValue.get(0)));
	}

	private void testAttributeValues(ConfigurationObjectMap map,
			EObject confNetObject) {
		EList<?> placesValue = (EList<?>) confNetObject
				.eGet(getPlacesReference(getNetClass()));
		EObject confPlace1Object = (EObject) placesValue.get(0);
		EObject confPlace2Object = (EObject) placesValue.get(1);
		
		EAttribute nameAttribute = getNameAttribute(getPlaceClass());
		String confPlace1ObjectName = (String) confPlace1Object.eGet(nameAttribute);
		String place1ObjectName = (String) place1Object.eGet(nameAttribute);
		String confPlace2ObjectName = (String) confPlace2Object.eGet(nameAttribute);
		String place2ObjectName = (String) place2Object.eGet(nameAttribute);
		
		assertEquals(place1ObjectName, confPlace1ObjectName);
		assertEquals(place2ObjectName, confPlace2ObjectName);
		
		EAttribute tokenAttribute = getTokenAttribute(getPlaceClass());
		int confPlace1ObjectToken = (Integer) confPlace1Object.eGet(tokenAttribute);
		int place1ObjectToken = (Integer) place1Object.eGet(tokenAttribute);
		int confPlace2ObjectToken = (Integer) confPlace2Object.eGet(tokenAttribute);
		int place2ObjectToken = (Integer) place2Object.eGet(tokenAttribute);
		
		assertEquals(place1ObjectToken, confPlace1ObjectToken);
		assertEquals(place2ObjectToken, confPlace2ObjectToken);
	}

}
