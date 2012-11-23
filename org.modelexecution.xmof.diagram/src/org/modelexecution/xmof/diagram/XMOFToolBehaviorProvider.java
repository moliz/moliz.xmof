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
import org.modelexecution.xmof.diagram.features.CreateActionFeature;
import org.modelexecution.xmof.diagram.features.CreateControlNodeFeature;

public class XMOFToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public XMOFToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public boolean equalsBusinessObjects(Object o1, Object o2) {
		if (o1 instanceof Pin && o2 instanceof Pin) {
			return o1 == o2;
		}
		return super.equalsBusinessObjects(o1, o2);
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();
		
		// add connections compartment
		for(IPaletteCompartmentEntry entry : super.getPalette()) {
			if(!entry.getLabel().equals("Objects")) {
				ret.add(entry);
			}
		}
		
		// add action compartment
		PaletteCompartmentEntry actionsCompartmentEntry = new PaletteCompartmentEntry("Actions", null);
		ret.add(actionsCompartmentEntry);
		
		PaletteCompartmentEntry controlNodeCompartmentEntry = new PaletteCompartmentEntry("Control Nodes", null);
		ret.add(controlNodeCompartmentEntry);
			
		// add control node compartment
		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();
		for(ICreateFeature cf: createFeatures) {
			ObjectCreationToolEntry creationEntry = new ObjectCreationToolEntry(cf.getName(), cf.getDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId(), cf);
			if(cf instanceof CreateActionFeature) {				
				actionsCompartmentEntry.addToolEntry(creationEntry);
			} else if(cf instanceof CreateControlNodeFeature) {
				controlNodeCompartmentEntry.addToolEntry(creationEntry);
			}
		}
		
		return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
	}

	
}
