/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm.internal;

import org.eclipse.emf.ecore.EReference;

import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Property;

public class LinkCreationData {

	protected class LinkEndCreationData {

		private LinkCreationData linkData;

		private Property end;

		protected LinkEndCreationData(LinkCreationData linkData) {
			this.linkData = linkData;
		}

		protected Property getEnd() {
			return end;
		}

		protected void setEnd(Property end) {
			this.end = end;
		}

		protected LinkCreationData getLinkData() {
			return linkData;
		}

		protected boolean representsOpposite(EReference oppositeReference,
				Object_ sourceObject, Object_ targetObject) {
			return targetObject.equals(linkData.getSourceObject())
					&& sourceObject.equals(linkData.getTargetObject())
					&& oppositeReference.getName().equals(end.name);
		}
	}

	private Object_ sourceObject;
	private Object_ targetObject;
	private Association association;
	private LinkEndCreationData sourceEndData;
	private LinkEndCreationData targetEndData;
	private int sourcePosition = 1;
	private int targetPosition = 1;

	public LinkCreationData() {
	}

	public LinkCreationData(Object_ sourceObject, Object_ targetObject,
			Association association) {
		this.sourceObject = sourceObject;
		this.targetObject = targetObject;
		this.association = association;
	}

	public LinkCreationData.LinkEndCreationData createLinkEndCreationData() {
		return new LinkCreationData.LinkEndCreationData(this);
	}

	public Object_ getSourceObject() {
		return sourceObject;
	}

	public void setSourceObject(Object_ sourceObject) {
		this.sourceObject = sourceObject;
	}

	public Object_ getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object_ targetObject) {
		this.targetObject = targetObject;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public LinkEndCreationData getSourceEndData() {
		return sourceEndData;
	}

	public void setSourceEndData(LinkEndCreationData sourceEndData) {
		this.sourceEndData = sourceEndData;
	}

	public LinkEndCreationData getTargetEndData() {
		return targetEndData;
	}

	public void setTargetEndData(LinkEndCreationData targetEndData) {
		this.targetEndData = targetEndData;
	}

	public int getSourcePosition() {
		return sourcePosition;
	}

	public void setSourcePosition(int sourcePosition) {
		this.sourcePosition = sourcePosition;
	}

	public int getTargetPosition() {
		return targetPosition;
	}

	public void setTargetPosition(int targetPosition) {
		this.targetPosition = targetPosition;
	}

	public Link createNewLink() {
		Link newLink = new Link();
		newLink.type = getAssociation();
		newLink.setFeatureValue(getSourceEndData().getEnd(),
				asValueList(getSourceObject()), getSourcePosition());
		newLink.setFeatureValue(getTargetEndData().getEnd(),
				asValueList(getTargetObject()), getTargetPosition());
		return newLink;
	}

	private ValueList asValueList(Object_ object) {
		Reference reference = new Reference();
		reference.referent = object;
		ValueList valueList = new ValueList();
		valueList.add(reference);
		return valueList;
	}
}