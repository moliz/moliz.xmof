/**
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.trace.uml2.tracemodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.modelexecution.fuml.trace.uml2.tracemodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TracemodelPackage
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
			public Adapter caseParameterSetting(ParameterSetting object) {
				return createParameterSettingAdapter();
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
			public Adapter caseActionExecution(ActionExecution object) {
				return createActionExecutionAdapter();
			}
			@Override
			public Adapter caseControlNodeExecution(ControlNodeExecution object) {
				return createControlNodeExecutionAdapter();
			}
			@Override
			public Adapter caseValueInstance(ValueInstance object) {
				return createValueInstanceAdapter();
			}
			@Override
			public Adapter caseValueSnapshot(ValueSnapshot object) {
				return createValueSnapshotAdapter();
			}
			@Override
			public Adapter caseDecisionNodeExecution(DecisionNodeExecution object) {
				return createDecisionNodeExecutionAdapter();
			}
			@Override
			public Adapter caseStructuredActivityNodeExecution(StructuredActivityNodeExecution object) {
				return createStructuredActivityNodeExecutionAdapter();
			}
			@Override
			public Adapter caseInputValue(InputValue object) {
				return createInputValueAdapter();
			}
			@Override
			public Adapter caseOutputValue(OutputValue object) {
				return createOutputValueAdapter();
			}
			@Override
			public Adapter caseInputParameterValue(InputParameterValue object) {
				return createInputParameterValueAdapter();
			}
			@Override
			public Adapter caseInputParameterSetting(InputParameterSetting object) {
				return createInputParameterSettingAdapter();
			}
			@Override
			public Adapter caseOutputParameterSetting(OutputParameterSetting object) {
				return createOutputParameterSettingAdapter();
			}
			@Override
			public Adapter caseOutputParameterValue(OutputParameterValue object) {
				return createOutputParameterValueAdapter();
			}
			@Override
			public Adapter caseInitialNodeExecution(InitialNodeExecution object) {
				return createInitialNodeExecutionAdapter();
			}
			@Override
			public Adapter caseParameterValue(ParameterValue object) {
				return createParameterValueAdapter();
			}
			@Override
			public Adapter caseExpansionInput(ExpansionInput object) {
				return createExpansionInputAdapter();
			}
			@Override
			public Adapter caseExpansionRegionExecution(ExpansionRegionExecution object) {
				return createExpansionRegionExecutionAdapter();
			}
			@Override
			public Adapter caseInputOutputValue(InputOutputValue object) {
				return createInputOutputValueAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.Trace
	 * @generated
	 */
	public Adapter createTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ActivityExecution
	 * @generated
	 */
	public Adapter createActivityExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution <em>Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ActivityNodeExecution
	 * @generated
	 */
	public Adapter createActivityNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.CallActionExecution <em>Call Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.CallActionExecution
	 * @generated
	 */
	public Adapter createCallActionExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ParameterSetting <em>Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ParameterSetting
	 * @generated
	 */
	public Adapter createParameterSettingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.Input
	 * @generated
	 */
	public Adapter createInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.Output
	 * @generated
	 */
	public Adapter createOutputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.TokenInstance <em>Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.TokenInstance
	 * @generated
	 */
	public Adapter createTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance <em>Object Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ObjectTokenInstance
	 * @generated
	 */
	public Adapter createObjectTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance <em>Control Token Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ControlTokenInstance
	 * @generated
	 */
	public Adapter createControlTokenInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ActionExecution <em>Action Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ActionExecution
	 * @generated
	 */
	public Adapter createActionExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ControlNodeExecution <em>Control Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ControlNodeExecution
	 * @generated
	 */
	public Adapter createControlNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance <em>Value Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance
	 * @generated
	 */
	public Adapter createValueInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot <em>Value Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ValueSnapshot
	 * @generated
	 */
	public Adapter createValueSnapshotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.DecisionNodeExecution <em>Decision Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.DecisionNodeExecution
	 * @generated
	 */
	public Adapter createDecisionNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.StructuredActivityNodeExecution <em>Structured Activity Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.StructuredActivityNodeExecution
	 * @generated
	 */
	public Adapter createStructuredActivityNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputValue <em>Input Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.InputValue
	 * @generated
	 */
	public Adapter createInputValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.OutputValue <em>Output Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.OutputValue
	 * @generated
	 */
	public Adapter createOutputValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterValue <em>Input Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterValue
	 * @generated
	 */
	public Adapter createInputParameterValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterSetting <em>Input Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.InputParameterSetting
	 * @generated
	 */
	public Adapter createInputParameterSettingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.OutputParameterSetting <em>Output Parameter Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.OutputParameterSetting
	 * @generated
	 */
	public Adapter createOutputParameterSettingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.OutputParameterValue <em>Output Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.OutputParameterValue
	 * @generated
	 */
	public Adapter createOutputParameterValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution <em>Initial Node Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.InitialNodeExecution
	 * @generated
	 */
	public Adapter createInitialNodeExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ParameterValue <em>Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ParameterValue
	 * @generated
	 */
	public Adapter createParameterValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput <em>Expansion Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionInput
	 * @generated
	 */
	public Adapter createExpansionInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionRegionExecution <em>Expansion Region Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.ExpansionRegionExecution
	 * @generated
	 */
	public Adapter createExpansionRegionExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.modelexecution.fuml.trace.uml2.tracemodel.InputOutputValue <em>Input Output Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.modelexecution.fuml.trace.uml2.tracemodel.InputOutputValue
	 * @generated
	 */
	public Adapter createInputOutputValueAdapter() {
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
