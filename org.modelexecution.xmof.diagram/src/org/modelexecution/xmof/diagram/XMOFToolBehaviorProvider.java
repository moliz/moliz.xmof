/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.modelexecution.xmof.Syntax.Actions.BasicActions.Pin;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionNode;
import org.modelexecution.xmof.diagram.features.CreateActionFeature;
import org.modelexecution.xmof.diagram.features.CreateActivityFeature;
import org.modelexecution.xmof.diagram.features.CreateControlNodeFeature;
import org.modelexecution.xmof.diagram.features.CreateExpansionNodeFeature;
import org.modelexecution.xmof.diagram.features.CreateExpansionRegionInputPinFeature;

public class XMOFToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public XMOFToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public boolean equalsBusinessObjects(Object o1, Object o2) {
		if (o1 instanceof Pin && o2 instanceof Pin) {
			return o1 == o2;
		} else if(o1 instanceof ExpansionNode && o2 instanceof ExpansionNode) {
			return o1 == o2;
		}
		return super.equalsBusinessObjects(o1, o2);
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();		
		
		// add compartment for activity
		PaletteCompartmentEntry activityCompartmentEntry = new PaletteCompartmentEntry("Activity", null);
		//ret.add(activityCompartmentEntry);
		
		// add compartment for connections 
				for(IPaletteCompartmentEntry entry : super.getPalette()) {
					if(!entry.getLabel().equals("Objects")) {
						ret.add(entry);
					}
				}
		
		// add compartment for actions
		PaletteCompartmentEntry actionsCompartmentEntry = new PaletteCompartmentEntry("Actions", null);
		ret.add(actionsCompartmentEntry);
		
		// add compartment for control nodes 
		PaletteCompartmentEntry controlNodeCompartmentEntry = new PaletteCompartmentEntry("Control Nodes", null);
		ret.add(controlNodeCompartmentEntry);

		// add compartment for object nodes
		PaletteCompartmentEntry objectNodeCompartmentEntry = new PaletteCompartmentEntry("Object Nodes", null);
		ret.add(objectNodeCompartmentEntry);

		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();
		for(ICreateFeature cf: createFeatures) {
			ObjectCreationToolEntry creationEntry = new ObjectCreationToolEntry(cf.getName(), cf.getDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId(), cf);
			if(cf instanceof CreateActionFeature) {				
				actionsCompartmentEntry.addToolEntry(creationEntry);
			} else if(cf instanceof CreateControlNodeFeature) {
				controlNodeCompartmentEntry.addToolEntry(creationEntry);
			} else if(cf instanceof CreateExpansionNodeFeature || cf instanceof CreateExpansionRegionInputPinFeature) {
				objectNodeCompartmentEntry.addToolEntry(creationEntry);
			} else if(cf instanceof CreateActivityFeature) {
				activityCompartmentEntry.addToolEntry(creationEntry);
			}
		}
		
		return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
	}
	
}
