package org.modelexecution.fumldebug.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fUML.Semantics.Classes.Kernel.DispatchStrategy;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

public class InheritanceBasedDispatchStrategy extends DispatchStrategy {

	@Override
	public Behavior getMethod(Object_ object, Operation operation) {
		Behavior method = null;

		if (doesOperationBelongToObject(object, operation)) {
			method = getMostSpecificMethod(object, operation);
		}

		return method;
	}

	private boolean doesOperationBelongToObject(Object_ object,
			Operation operation) {
		Class_ classOwningOperation = operation.class_;

		Set<Class_> objectTypes = getAllSuperTypes(object);

		if (objectTypes.contains(classOwningOperation))
			return true;
		else
			return false;
	}

	private Set<Class_> getAllSuperTypes(Object_ object) {
		HashSet<Class_> objectTypes = new HashSet<Class_>();
		objectTypes.addAll(object.types);
		objectTypes.addAll(getAllSuperTypes(object.types));
		
//		for (Class_ type : object.types)
//			objectTypes.addAll(getAllSuperTypes(type));
		return objectTypes;
	}
	
	private Set<Class_> getAllSuperTypes(Collection<Class_> classes) {
		Set<Class_> allSuperTypes = new HashSet<Class_>();
		
		Set<Class_> superTypes = getSuperTypes(classes);
		while(superTypes.size() > 0) {
			allSuperTypes.addAll(superTypes);
			superTypes = getSuperTypes(superTypes);
		}
		
		return allSuperTypes;
	}

//	private Set<Class_> getAllSuperTypes(Class_ class_) {
//		Set<Class_> superTypes = new HashSet<Class_>();
//		superTypes.addAll(class_.superClass);
//		for (Class_ superClass : class_.superClass)
//			superTypes.addAll(getAllSuperTypes(superClass));
//		return superTypes;
//	}

	private Set<Class_> getSuperTypes(Collection<Class_> classes) {
		Set<Class_> superTypes = new HashSet<Class_>();
		Iterator<Class_> iterator = classes.iterator();
		while (iterator.hasNext()) {
			Class_ class_ = iterator.next();
			superTypes.addAll(class_.superClass);
		}
		return superTypes;
	}
	
	private Behavior getMostSpecificMethod(Object_ object, Operation operation) {
		HashMap<Class_, Set<Behavior>> class2method = buildClass2MethodMap(operation);

		Set<Class_> classes = new HashSet<Class_>(object.types);
		while (classes.size() > 0) {
			Behavior method = getMethod(classes, class2method);
			if (method != null)
				return method;
			classes = getSuperTypes(classes);
		}

		return null;
	}

	private HashMap<Class_, Set<Behavior>> buildClass2MethodMap(
			Operation operation) {
		HashMap<Class_, Set<Behavior>> class2method = new HashMap<Class_, Set<Behavior>>();
		for (Behavior operationMethod : operation.method) {
			if (operationMethod.owner instanceof Class_) {
				Class_ operationOwner = (Class_) operationMethod.owner;
				if (!class2method.containsKey(operationOwner)) {
					class2method.put(operationOwner, new HashSet<Behavior>());
				}
				class2method.get(operationOwner).add(operationMethod);
			}
		}
		return class2method;
	}

	private Behavior getMethod(Set<Class_> classes,
			HashMap<Class_, Set<Behavior>> class2method) {
		Iterator<Class_> iterator = classes.iterator();
		while (iterator.hasNext()) {
			Class_ class_ = iterator.next();
			if (class2method.containsKey(class_))
				return class2method.get(class_).iterator().next();
		}
		return null;
	}

}
