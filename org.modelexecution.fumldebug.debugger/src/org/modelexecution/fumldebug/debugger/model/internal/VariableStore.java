/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.model.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VariableStore<VariableType, ValueType, ObjectType> {

	private List<VariableType> variables = new ArrayList<VariableType>();
	private BidirectionalMap<VariableType, ObjectType> objects = new BidirectionalMap<VariableType, ObjectType>();
	private BidirectionalMap<VariableType, ValueType> values = new BidirectionalMap<VariableType, ValueType>();

	public void store(VariableType variable, ValueType value, ObjectType object) {
		objects.put(variable, object);
		values.put(variable, value);
		if (!variables.contains(variable))
			variables.add(variable);
	}

	public int size() {
		return variables.size();
	}

	public boolean containsValue(ValueType value) {
		return values.containsValue(value);
	}

	public boolean containsObject(ObjectType object) {
		return objects.containsValue(object);
	}

	public boolean containsVariableType(VariableType variable) {
		return values.containsKey(variable);
	}

	public VariableType getVariableByObject(ObjectType object) {
		return objects.getByValue(object);
	}

	public ValueType getValueByVariable(VariableType variable) {
		return values.getByKey(variable);
	}

	public Collection<VariableType> getVariables() {
		return new ArrayList<VariableType>(variables);
	}

	public ObjectType getObjectByVariable(VariableType variable) {
		return objects.getByKey(variable);
	}

	public void remove(VariableType variable) {
		objects.removeByKey(variable);
		values.removeByKey(variable);
		variables.remove(variable);
	}
}
