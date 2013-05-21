package org.modelexecution.xmof.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;
import org.modelexecution.xmof.vm.SimpleStudentSystemFactory.MainEClassClassifierBehaviorKind;

public class XMOFBasedModelSynchonizerTest {
	
	private EPackage rootPackage;
	private EClass mainEClass, studentClass;
	private EReference studentsReference;
	private EAttribute studentName, studentNickname, studentNicknameNotUnique;
	private EEnum studentStatusEnum;
	private EAttribute studentStatusAttribute;
	
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
	
	@Test
	public void testAddMultipleValues() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_MULTIPLE_VALUES);
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(3, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		assertEquals("tanjania", ((EList<?>)studentTanja.eGet(studentNickname)).get(0));
		assertEquals("tanjihhhii", ((EList<?>)studentTanja.eGet(studentNickname)).get(1));
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNickname)).get(2));
	}
	
	@Test
	public void testAddMultipleValuesDuplicate() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_MULTIPLE_VALUES_DUPLICATE);
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(2, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNickname)).get(0));
		assertEquals("tanjihhhii", ((EList<?>)studentTanja.eGet(studentNickname)).get(1));
	}
	
	@Test
	public void testAddMultipleValuesReplace() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_MULTIPLE_VALUES_REPLACE);
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(1, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		assertEquals("tanjania", ((EList<?>)studentTanja.eGet(studentNickname)).get(0));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddSingleValue() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_SINGLE_VALUE);
		
		// add new student to model
		EFactory factory = rootPackage.getEFactoryInstance();		
		EObject studentSystem = modelResource.getContents().get(0);
		EObject newStudent = factory.create(studentClass);
		EList<EObject> studentList = new BasicEList<EObject>();
		studentList.addAll((Collection<EObject>)studentSystem.eGet(studentsReference));
		studentList.add(newStudent);
		studentSystem.eSet(studentsReference, studentList);
		
		assertEquals(newStudent, ((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(2));
		assertEquals(null, newStudent.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals("tanj", newStudent.eGet(studentName));
	}
	
	@Test
	public void testAddSingleValueAlreadySet() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_SINGLE_VALUE_ALREADY_SET);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals("tanj", studentTanja.eGet(studentName));
	}
	
	@Test
	public void testAddSingleValueDuplicate() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_SINGLE_VALUE_DUPLICATE);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals("Tanja", studentTanja.eGet(studentName));
	}
	
	@Test
	public void testAddSingleValueReplace() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_SINGLE_VALUE_REPLACE);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals("tanj", studentTanja.eGet(studentName));
	}
	
	@Test
	public void testClearMultipleValues() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.CLEAR_MULTIPLE_VALUES);
		
		// set nicknames
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		EList<String> nicknames = new BasicEList<String>();
		nicknames.add("tanj");
		nicknames.add("tanjania");
		studentTanja.eSet(studentNickname, nicknames);
		assertEquals(2, ((EList<?>)studentTanja.eGet(studentNickname)).size());
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNickname)).size());
	}
	
	@Test
	public void testClearSingleValue() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.CLEAR_SINGLE_VALUE);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(null, studentTanja.eGet(studentName));
	}
	
	@Test
	public void testRemoveMultipleUnique() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_MULTIPLE_UNIQUE);
		
		// set nicknames
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		setStructuralFeature(studentTanja, studentNicknameNotUnique, "tanj", "tanjania", "tanj");
		assertEquals(3, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(2, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(0));
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(1));
	}
	
	@Test
	public void testRemoveMultipleNotUnique() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_MULTIPLE_NOT_UNIQUE);
		
		// set nicknames
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		setStructuralFeature(studentTanja, studentNicknameNotUnique, "tanj", "tanjania", "tanj");
		assertEquals(3, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(2, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		assertEquals("tanjania", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(0));
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(1));
	}
	
	@Test
	public void testRemoveMultipleDuplicates() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_MULTIPLE_DUPLICATES);
		
		// set nicknames
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		setStructuralFeature(studentTanja, studentNicknameNotUnique, "tanj", "tanjania", "tanj");
		assertEquals(3, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(1, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		assertEquals("tanjania", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(0));
	}

	@Test
	public void testRemoveSingleValue() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_SINGLE_VALUE);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(null, studentTanja.eGet(studentName));
	}
	
	@Test
	public void testRemoveMultipleNotUniqueAt() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_MULTIPLE_NOT_UNIQUE_AT);
		
		// set nicknames
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(0, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		setStructuralFeature(studentTanja, studentNicknameNotUnique, "tanj", "tanjania", "tanj");
		assertEquals(3, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(2, ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).size());
		assertEquals("tanj", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(0));
		assertEquals("tanjania", ((EList<?>)studentTanja.eGet(studentNicknameNotUnique)).get(1));
	}
	
	@Test
	public void testSetEnumeration() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.SET_ENUMERATION);
		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		assertEquals(studentStatusEnum.getEEnumLiteralByLiteral("ACTIVE"), studentTanja.eGet(studentStatusAttribute));
		
		XMOFBasedModel model = new XMOFBasedModel(
				modelResource.getContents());
		EditingDomain editingDomain = createEditingDomain(modelResource);

		XMOFVirtualMachine vm = new XMOFVirtualMachine(model);
		initializeSynchronizer(vm, editingDomain);
		vm.run();
		assertEquals(studentStatusEnum.getEEnumLiteralByLiteral("PASSIVE"), studentTanja.eGet(studentStatusAttribute));
	}
		
	@Test
	public void testAddChild() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_CHILD);
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
		assertEquals(3, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		EObject student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals(null, student.eGet(studentName));
		student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(1);
		assertEquals("Tanja", student.eGet(studentName));
		student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(2);
		assertEquals("Philip", student.eGet(studentName));
	}

	@Test
	public void testRemoveChild() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_CHILD);
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
		assertEquals(1, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		EObject studentPhilip = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Philip", studentPhilip.eGet(studentName));
	}	
	
	@Test
	public void testRemoveChild2() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_CHILD2);
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
		assertEquals(1, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
	}

	@Test
	public void testAddChildAt() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.ADD_CHILD_AT);
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
		assertEquals(3, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		EObject student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", student.eGet(studentName));
		student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(1);
		assertEquals(null, student.eGet(studentName));
		student = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(2);
		assertEquals("Philip", student.eGet(studentName));
	}
	
	@Test
	public void testRemoveChildAt() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_CHILD_AT);
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
		assertEquals(1, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
	}
	
	@Test
	public void testRemoveAndAddChild() {
		Resource modelResource = initializeStudentSystemResource(MainEClassClassifierBehaviorKind.REMOVE_AND_ADD_CHILD);
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
		assertEquals(2, ((Collection<?>)modelResource.getContents().get(0).eGet(studentsReference)).size());		
		EObject studentTanja = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(0);
		assertEquals("Tanja", studentTanja.eGet(studentName));
		EObject studentPhilip = (EObject)((EList<?>)modelResource.getContents().get(0).eGet(studentsReference)).get(1);
		assertEquals("Philip", studentPhilip.eGet(studentName));
	}	

	
	private void setStructuralFeature(EObject eObject, EStructuralFeature eStructuralFeature, Object... values) {
		EList<Object> valuelist = new BasicEList<Object>();
		for(Object v : values) {
			valuelist.add(v);
		}		
		eObject.eSet(eStructuralFeature, valuelist);
	}

	private Resource initializeStudentSystemResource(MainEClassClassifierBehaviorKind mainEClassClassifierBehavior) {
		SimpleStudentSystemFactory factory = new SimpleStudentSystemFactory();
		factory.createMetamodelResource(mainEClassClassifierBehavior);
		mainEClass = factory.getMainEClass();
		studentClass = factory.getStudentClass();
		studentsReference = factory.getStudentsReference();
		studentName = factory.getStudentNameAttribute();
		studentNickname = factory.getStudentNicknameAttribute();
		studentNicknameNotUnique = factory.getStudentNicknameNotUniqueAttribute();
		studentStatusEnum = factory.getStudentStatusEnum();
		studentStatusAttribute = factory.getStudentStatusAttribute();
		rootPackage = factory.getRootPackage();
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
