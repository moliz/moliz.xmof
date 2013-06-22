/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.debugger.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.modelexecution.fumldebug.debugger.model.internal.VariableStore;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Loci.LociL1.Locus;

public class LocusValue extends Value {

	private static final String LOCUS_VALUE_REFERENCED_TYPE_NAME = "Locus";
	private static final String OBJECT_VARIABLE_NAME_PREFIX = "obj";
	private static final String LINK_VARIABLE_NAME_PREFIX = "link";
	private Locus value = null;

	private VariableStore<ObjectVariable, ObjectValue, Object_> objectVariables = new VariableStore<ObjectVariable, ObjectValue, Object_>();
	private VariableStore<LinkVariable, LinkValue, Link> linkVariables = new VariableStore<LinkVariable, LinkValue, Link>();

	public LocusValue(ActivityDebugTarget target, Locus value) {
		super(target);
		this.value = value;
		updateVariables();
	}

	private void updateVariables() {
		for (ExtensionalValue extensionalValue : value.extensionalValues) {
			if (extensionalValue.getClass() == Object_.class) {
				Object_ object_ = (Object_) extensionalValue;
				if (!objectVariables.containsObject(object_)) {
					ObjectVariable objectVariable = new ObjectVariable(
							getActivityDebugTarget(),
							OBJECT_VARIABLE_NAME_PREFIX
									+ objectVariables.size(), this);
					ObjectValue objectValue = new ObjectValue(
							getActivityDebugTarget(), object_);
					objectVariables.store(objectVariable, objectValue, object_);
				}
			} else if (extensionalValue instanceof Link) {
				Link link = (Link) extensionalValue;
				if (!linkVariables.containsObject(link)) {
					LinkVariable linkVariable = new LinkVariable(
							getActivityDebugTarget(), LINK_VARIABLE_NAME_PREFIX
									+ linkVariables.size(), this);
					LinkValue linkValue = new LinkValue(
							getActivityDebugTarget(), link);
					linkVariables.store(linkVariable, linkValue, link);
				}
			}
		}
		for (ObjectVariable variable : objectVariables.getVariables()) {
			if (!value.extensionalValues.contains(objectVariables
					.getObjectByVariable(variable)))
				objectVariables.remove(variable);
		}
		for (LinkVariable variable : linkVariables.getVariables()) {
			if (!value.extensionalValues.contains(linkVariables
					.getObjectByVariable(variable)))
				linkVariables.remove(variable);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	@Override
	public String getReferenceTypeName() throws DebugException {
		return LOCUS_VALUE_REFERENCED_TYPE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	@Override
	public String getValueString() throws DebugException {
		return "Locus (id=" + value.hashCode() + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	@Override
	public IVariable[] getVariables() throws DebugException {
		updateVariables();
		Collection<IVariable> variables = new ArrayList<IVariable>();
		variables.addAll(objectVariables.getVariables());
		variables.addAll(linkVariables.getVariables());
		return variables.toArray(new IVariable[variables.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	@Override
	public boolean hasVariables() throws DebugException {
		updateVariables();
		return objectVariables.size() > 0 || linkVariables.size() > 0;
	}

	public ObjectValue getObjectValue(ObjectVariable variable) {
		return objectVariables.getValueByVariable(variable);
	}

	public ObjectVariable getObjectVariable(Object_ object) {
		return objectVariables.getVariableByObject(object);
	}

	public LinkValue getLinkValue(LinkVariable variable) {
		return linkVariables.getValueByVariable(variable);
	}
}
