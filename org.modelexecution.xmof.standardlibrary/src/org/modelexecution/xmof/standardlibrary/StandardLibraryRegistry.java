/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.xmof.standardlibrary;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.behaviorlibrary.IntegerGreaterThanOrEqualsFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.IntegerLessThanFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.IntegerLessThanOrEqualsFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListGetFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListIndexOfFunctionBehaviorExecution;
import org.modelexecution.fumldebug.core.behaviorlibrary.ListSizeFunctionBehaviorExecution;
import org.modelexecution.xmof.vm.libraryregistry.AbstractXMOFOpaqueBehaviorExecutionRegistry;

import fUML.Library.IntegerFunctionImplementation.IntegerDivideFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerGreaterFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerMinusFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerPlusFunctionBehaviorExecution;
import fUML.Library.IntegerFunctionImplementation.IntegerTimesFunctionBehaviorExecution;
 

public class StandardLibraryRegistry extends
		AbstractXMOFOpaqueBehaviorExecutionRegistry {

	protected final static String XMOF_STANDARD_LIBRARY_NAME = "xMOF Primitive Behaviors Library";
	
	
	/* (non-Javadoc)
	 * @see org.modelexecution.xmof.vm.libraryregistry.IOpaqueBehaviorExecutionRegistry#registerOpaqueBehaviorExecutions(fUML.Semantics.Loci.LociL1.Locus)
	 */
	@Override
	public void registerOpaqueBehaviorExecutions(ExecutionContext executionContext) {
		loadOpaqueBehaviors(XMOF_STANDARD_LIBRARY_NAME);

		registerOpaqueBehaviorExecution(new IntegerPlusFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerPlus", executionContext);
		registerOpaqueBehaviorExecution(new IntegerMinusFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerMinus", executionContext);
		registerOpaqueBehaviorExecution(new IntegerTimesFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerTimes", executionContext);
		registerOpaqueBehaviorExecution(new IntegerDivideFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerDivide", executionContext);
		registerOpaqueBehaviorExecution(new IntegerLessThanFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerLess", executionContext);
		registerOpaqueBehaviorExecution(new IntegerGreaterFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerGreater", executionContext);				
		registerOpaqueBehaviorExecution(new IntegerLessThanOrEqualsFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerLessOrEquals", executionContext);
		registerOpaqueBehaviorExecution(new IntegerGreaterThanOrEqualsFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::IntegerFunctions::IntegerGreaterOrEquals", executionContext);
		
		registerOpaqueBehaviorExecution(new ListGetFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::ListFunctions::ListGet", executionContext);
		registerOpaqueBehaviorExecution(new ListSizeFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::ListFunctions::ListSize", executionContext);
		registerOpaqueBehaviorExecution(new ListIndexOfFunctionBehaviorExecution(), "XMOF::PrimitiveBehaviors::ListFunctions::ListIndexOf", executionContext);		
	}

}
