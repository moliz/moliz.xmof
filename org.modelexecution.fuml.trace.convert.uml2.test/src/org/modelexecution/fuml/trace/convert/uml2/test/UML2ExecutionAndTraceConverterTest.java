/*
* Copyright (c) 2014 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Tanja Mayerhofer - initial API and implementation
*/
package org.modelexecution.fuml.trace.convert.uml2.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.IConverter;
import org.modelexecution.fuml.trace.convert.uml2.UML2TraceConverter;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.KernelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.debugger.uml.UMLModelExecutor;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at) 
 *
 */
public class UML2ExecutionAndTraceConverterTest {

	private static final String MAIN_ACTIVITY = "Main";
	private static final String MODEL_PATH = "model/bidirassoctest.uml";
	private static final String TRACE_PATH = "output/bidirassoctest.xmi";

	@Test
	public void test() {
		UMLModelExecutor executor = new UMLModelExecutor(MODEL_PATH);
		Trace fumlTrace = executor.executeActivity(MAIN_ACTIVITY);
		IConversionResult modelConversionResult = executor.getModelLoader().getConversionResult();
		IConverter traceConverter = new UML2TraceConverter();
		org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace = traceConverter.convert(fumlTrace, modelConversionResult).getTrace();
		persistUML2Trace(umlTrace, TRACE_PATH);
	}
	
	private void persistUML2Trace(org.modelexecution.fuml.trace.uml2.tracemodel.Trace trace, String outputPath) {
		ResourceSet resourceSet = initializeResourceSet();
		URI outputUri = URI.createFileURI(new File(outputPath).getAbsolutePath());
		Resource traceResource = resourceSet.createResource(outputUri);
		traceResource.getContents().add(trace);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		try {
			traceResource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}
	
	private static ResourceSet initializeResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
				UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		resourceSet.getPackageRegistry().put(TracemodelPackage.eNS_URI,
				TracemodelPackage.eINSTANCE);
		
		resourceSet.getPackageRegistry().put(org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage.eNS_URI,
				org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage.eINSTANCE);

		resourceSet.getPackageRegistry().put(KernelPackage.eNS_URI,
				KernelPackage.eINSTANCE);
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(".xmi", new XMIResourceFactoryImpl());
		
		return resourceSet;
	}

}
