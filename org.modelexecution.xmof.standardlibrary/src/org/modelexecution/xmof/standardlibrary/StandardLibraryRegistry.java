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

	protected final static String XMOF_STANDARD_LIBRARY_INTEGER_FUNCTIONS = "xMOF Integer Function Library";
	protected final static String XMOF_STANDARD_LIBRARY_LIST_FUNCTIONS = "xMOF List Function Library";
	
	
	/* (non-Javadoc)
	 * @see org.modelexecution.xmof.vm.libraryregistry.IOpaqueBehaviorExecutionRegistry#registerOpaqueBehaviorExecutions(fUML.Semantics.Loci.LociL1.Locus)
	 */
	@Override
	public void registerOpaqueBehaviorExecutions(ExecutionContext executionContext) {
		loadOpaqueBehaviors(XMOF_STANDARD_LIBRARY_INTEGER_FUNCTIONS);
		loadOpaqueBehaviors(XMOF_STANDARD_LIBRARY_LIST_FUNCTIONS);

		registerOpaqueBehaviorExecution(new IntegerPlusFunctionBehaviorExecution(), "IntegerFunctions::IntegerPlus", executionContext);
		registerOpaqueBehaviorExecution(new IntegerMinusFunctionBehaviorExecution(), "IntegerFunctions::IntegerMinus", executionContext);
		registerOpaqueBehaviorExecution(new IntegerTimesFunctionBehaviorExecution(), "IntegerFunctions::IntegerTimes", executionContext);
		registerOpaqueBehaviorExecution(new IntegerDivideFunctionBehaviorExecution(), "IntegerFunctions::IntegerDivide", executionContext);
		registerOpaqueBehaviorExecution(new IntegerLessThanFunctionBehaviorExecution(), "IntegerFunctions::IntegerLess", executionContext);
		registerOpaqueBehaviorExecution(new IntegerGreaterFunctionBehaviorExecution(), "IntegerFunctions::IntegerGreater", executionContext);				
		registerOpaqueBehaviorExecution(new IntegerLessThanOrEqualsFunctionBehaviorExecution(), "IntegerFunctions::IntegerLessOrEquals", executionContext);
		registerOpaqueBehaviorExecution(new IntegerGreaterThanOrEqualsFunctionBehaviorExecution(), "IntegerFunctions::IntegerGreaterOrEquals", executionContext);
		
		registerOpaqueBehaviorExecution(new ListGetFunctionBehaviorExecution(), "ListFunctions::ListGet", executionContext);
		registerOpaqueBehaviorExecution(new ListSizeFunctionBehaviorExecution(), "ListFunctions::ListSize", executionContext);
		registerOpaqueBehaviorExecution(new ListIndexOfFunctionBehaviorExecution(), "ListFunctions::ListIndexOf", executionContext);		
	}

}
