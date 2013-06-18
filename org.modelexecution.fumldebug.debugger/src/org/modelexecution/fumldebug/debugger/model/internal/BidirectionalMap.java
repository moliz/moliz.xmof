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
import java.util.HashMap;
import java.util.List;

public class BidirectionalMap<KeyType, ValueType> {

	private List<KeyType> keys = new ArrayList<KeyType>();
	private List<ValueType> values = new ArrayList<ValueType>();
	private HashMap<KeyType, ValueType> valueMap = new HashMap<KeyType, ValueType>();
	private HashMap<ValueType, KeyType> keyMap = new HashMap<ValueType, KeyType>();

	public void put(KeyType key, ValueType value) {
		valueMap.put(key, value);
		keyMap.put(value, key);
		if (!keys.contains(key)) {
			keys.add(key);
		}
		if (!values.contains(value)) {
			values.add(value);
		}
	}

	public ValueType getByKey(KeyType key) {
		return valueMap.get(key);
	}

	public KeyType getByValue(ValueType value) {
		return keyMap.get(value);
	}

	public ValueType removeByKey(KeyType key) {
		ValueType value = valueMap.remove(key);
		keyMap.remove(value);
		return value;
	}

	public KeyType removeByValue(ValueType value) {
		KeyType key = keyMap.remove(value);
		valueMap.remove(key);
		return key;
	}

	public boolean containsValue(ValueType value) {
		return keyMap.containsKey(value);
	}

	public boolean containsKey(KeyType key) {
		return valueMap.containsKey(key);
	}

	public int size() {
		return valueMap.size();
	}

	public Collection<ValueType> getValues() {
		return new ArrayList<ValueType>(values);
	}

	public Collection<KeyType> getKeys() {
		return new ArrayList<KeyType>(keys);
	}

}
