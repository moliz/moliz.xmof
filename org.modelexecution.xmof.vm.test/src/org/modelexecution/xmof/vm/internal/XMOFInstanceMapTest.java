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

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.xmof.XMOFConverter;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory;

import fUML.Semantics.Loci.LociL1.Locus;

public class XMOFInstanceMapTest {

	@Test
	public void testSimpleStudentSystem() {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		Resource metamodelResource = factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();

		XMOFConverter converter = new XMOFConverter();
		IConversionResult conversionResult = converter
				.convert(metamodelResource);

		Locus locus = new Locus();
		
		XMOFInstanceMap instanceMap = new XMOFInstanceMap(conversionResult,
				modelResource.getContents(), locus);

		// TODO tests

	}

}
