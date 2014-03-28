package org.modelexecution.xmof.states.builder.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

public class StatesBuilderUtil {

	public static StatesBuilder createStatesBuilder(XMOFVirtualMachine vm,
			Resource configurationModelResource) {
		StatesBuilder statesBuilder = new StatesBuilder(
				configurationModelResource);
		statesBuilder.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);
		return statesBuilder;
	}
}
