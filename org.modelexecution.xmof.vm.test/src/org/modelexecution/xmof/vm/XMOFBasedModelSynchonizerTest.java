package org.modelexecution.xmof.vm;

import static org.junit.Assert.*;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;

public class XMOFBasedModelSynchonizerTest {

	@Test
	public void runPetriNetModel() {
		Resource modelResource = initializePetrinetResource();
		XMOFBasedModel petrinet = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(petrinet);
		initializeSynchronizer(vm, editingDomain);
		vm.run();

	}

	private Resource initializePetrinetResource() {
		PetriNetFactory factory = new PetriNetFactory();
		factory.createMetamodelResource();
		Resource modelResource = factory.createModelResource();
		return modelResource;
	}

	private EditingDomain createEditingDomain(Resource modelResource) {
		return TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain(modelResource.getResourceSet());
	}

	private void initializeSynchronizer(XMOFVirtualMachine vm,
			EditingDomain editingDomain) {
		XMOFInstanceMap instanceMap = vm.getInstanceMap();
		XMOFBasedModelSynchronizer synchronizer = new XMOFBasedModelSynchronizer(
				instanceMap, editingDomain);
		vm.addRawExecutionEventListener(synchronizer);
	}

}
