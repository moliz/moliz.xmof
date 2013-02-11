/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.convert.trace.uml2.tracemodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.modelexecution.fuml.convert.trace.uml2.tracemodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TracemodelPackage
 * @generated
 */
public class TracemodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracemodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracemodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TracemodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TracemodelSwitch<Adapter> modelSwitch =
		new TracemodelSwitch<Adapter>() {
			@Override
			public Adapter caseTrace(Trace object) {
				return createTraceAdapter();
			}
			@Override
			public Adapter caseActivityExecution(ActivityExecution object) {
				return createActivityExecutionAdapter();
			}
			@Override
			public Adapter caseActivityNodeExecution(ActivityNodeExecution object) {
				return createActivityNodeExecutionAdapter();
			}
			@Override
			public Adapter caseCallActionExecution(CallActionExecution object) {
				return createCallActionExecutionAdapter();
			}
			@Override
			public Adapter caseUserParameterInput(UserParameterInput object) {
				return createUserParameterInputAdapter();
			}
			@Override
			public Adapter caseInput(Input object) {
				return createInputAdapter();
			}
			@Override
			public Adapter caseOutput(Output object) {
				return createOutputAdapter();
			}
			@Override
			public Adapter caseTokenInstance(TokenInstance object) {
				return createTokenInstanceAdapter();
			}
			@Override
			public Adapter caseObjectTokenInstance(ObjectTokenInstance object) {
				return createObjectTokenInstanceAdapter();
			}
			@Override
			public Adapter caseControlTokenInstance(ControlTokenInstance object) {
				return createControlTokenInstanceAdapter();
			}
			@Override
			public Adapter caseValueInstance(ValueInstance object) {
				return createValueInstanceAdapter();
			}
			@Override
			public Adapter caseActionExecution(ActionExecution object) {
				return createActionExecutionAdapter();
			}
			@Override
			public Adapter caseControlNodeExecution(ControlNodeExecution object) {
				return createControlNodeExecutionAdapter();
			}
			@Override
			public Adapter caseValueInstanceSnapshot(ValueInstanceSnapshot object) {
				return createValueInstanceSnapshotAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.Trace
	 * @generated
	 */
	public Adapter createTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityExecution
	 * @generated
	 */
	public Adapter createActivityExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution <em>Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActivityNodeExecution
	 * @generated
	 */
	public Adapter createActivityNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution <em>Call Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.CallActionExecution
	 * @generated
	 */
	public Adapter createCallActionExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput <em>User Parameter Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.UserParameterInput
	 * @generated
	 */
	public Adapter createUserParameterInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.Input
	 * @generated
	 */
	public Adapter createInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.Output
	 * @generated
	 */
	public Adapter createOutputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance <em>Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.TokenInstance
	 * @generated
	 */
	public Adapter createTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ObjectTokenInstance <em>Object Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ObjectTokenInstance
	 * @generated
	 */
	public Adapter createObjectTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ControlTokenInstance <em>Control Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ControlTokenInstance
	 * @generated
	 */
	public Adapter createControlTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstance
	 * @generated
	 */
	public Adapter createValueInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActionExecution <em>Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ActionExecution
	 * @generated
	 */
	public Adapter createActionExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ControlNodeExecution <em>Control Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ControlNodeExecution
	 * @generated
	 */
	public Adapter createControlNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstanceSnapshot <em>Value Instance Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.convert.trace.uml2.tracemodel.ValueInstanceSnapshot
	 * @generated
	 */
	public Adapter createValueInstanceSnapshotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TracemodelAdapterFactory
