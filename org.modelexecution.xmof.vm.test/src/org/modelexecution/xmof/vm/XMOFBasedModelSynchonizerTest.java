package org.modelexecution.xmof.vm;

import static org.junit.Assert.*;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory.MainEClassClassifierBehaviorKind;

public class XMOFBasedModelSynchonizerTest {
	
	private EClass mainEClass, studentClass;
	private EReference studentsReference;
	
	@Test
	public void testCreateObject() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.CREATE);
		assertEquals(1, modelResource.getContents().size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(2, modelResource.getContents().size());
		assertTrue(modelResource.getContents().get(0).eClass().equals(mainEClass));
		assertTrue(modelResource.getContents().get(1).eClass().equals(studentClass));
	}
	
	@Test
	public void testDestroyRootObject() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.DESTROY_ROOT);
		assertEquals(1, modelResource.getContents().size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(0, modelResource.getContents().size());
	}
	
	@Test
	public void testDestroyChildObject() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.DESTROY_CHILD);
		assertEquals(1, modelResource.getContents().size());
		assertTrue(modelResource.getContents().get(0).eClass().equals(mainEClass));
		assertEquals(2, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(1, modelResource.getContents().size());
		assertTrue(modelResource.getContents().get(0).eClass().equals(mainEClass));
		assertEquals(0, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
	}

	private Resource initializeStudentSystemResource(MainEClassClassifierBehaviorKind mainEClassClassifierBehavior) {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		factory.createMetamodelResource(mainEClassClassifierBehavior);
		mainEClass = factory.getMainEClass();
		studentClass = factory.getStudentClass();
		studentsReference = factory.getStudentsReference();
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
