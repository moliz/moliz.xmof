/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityParameterNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.DecisionNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.InputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.OutputValue;
import org.modelexecution.fumldebug.core.trace.tracemodel.StructuredActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.PrimitiveValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @generated
 */
public class TracemodelPackageImpl extends EPackageImpl implements TracemodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callActionExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userParameterInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectTokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlTokenInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueSnapshotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuredActivityNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityParameterNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlNodeExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType behaviorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityParameterNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType inputPinEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType outputPinEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activityEdgeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType valueEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType primitiveValueEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType object_EDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TracemodelPackageImpl() {
		super(eNS_URI, TracemodelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TracemodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TracemodelPackage init() {
		if (isInited) return (TracemodelPackage)EPackage.Registry.INSTANCE.getEPackage(TracemodelPackage.eNS_URI);

		// Obtain or create and register package
		TracemodelPackageImpl theTracemodelPackage = (TracemodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracemodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracemodelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTracemodelPackage.createPackageContents();

		// Initialize created meta-data
		theTracemodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTracemodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TracemodelPackage.eNS_URI, theTracemodelPackage);
		return theTracemodelPackage;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_ActivityExecutions() {
		return (EReference)traceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_ValueInstances() {
		return (EReference)traceEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_InitialLocusValueInstances() {
		return (EReference)traceEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityExecution() {
		return activityExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityExecution_UserParameterInputs() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityExecution_NodeExecutions() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityExecution_Caller() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityExecution_Activity() {
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityExecution_ActivityExecutionID() {
		return (EAttribute)activityExecutionEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityExecution_Trace() {
		return (EReference)activityExecutionEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityNodeExecution() {
		return activityNodeExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeExecution_LogicalSuccessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeExecution_LogicalPredecessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeExecution_ChronologicalSuccessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeExecution_ChronologicalPredecessor() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityNodeExecution_Node() {
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityNodeExecution_ActivityExecution() {
		return (EReference)activityNodeExecutionEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityNodeExecution_Executed() {
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(6);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivityNodeExecution_UnderExecution() {
		return (EAttribute)activityNodeExecutionEClass.getEStructuralFeatures().get(7);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallActionExecution() {
		return callActionExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallActionExecution_Callee() {
		return (EReference)callActionExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserParameterInput() {
		return userParameterInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserParameterInput_InputParameterNode() {
		return (EAttribute)userParameterInputEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserParameterInput_ProvidedValues() {
		return (EReference)userParameterInputEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInput() {
		return inputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInput_InputPin() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInput_InputValues() {
		return (EReference)inputEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutput() {
		return outputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutput_OutputPin() {
		return (EAttribute)outputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutput_OutputValues() {
		return (EReference)outputEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTokenInstance() {
		return tokenInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTokenInstance_TraversedEdges() {
		return (EAttribute)tokenInstanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectTokenInstance() {
		return objectTokenInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObjectTokenInstance_TransportedValue() {
		return (EReference)objectTokenInstanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControlTokenInstance() {
		return controlTokenInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueInstance() {
		return valueInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueInstance_RuntimeValue() {
		return (EAttribute)valueInstanceEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueInstance_Snapshots() {
		return (EReference)valueInstanceEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueInstance_Original() {
		return (EReference)valueInstanceEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueInstance_Destroyed() {
		return (EAttribute)valueInstanceEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueSnapshot() {
		return valueSnapshotEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueSnapshot_Value() {
		return (EAttribute)valueSnapshotEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecisionNodeExecution() {
		return decisionNodeExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecisionNodeExecution_DecisionInputValue() {
		return (EReference)decisionNodeExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuredActivityNodeExecution() {
		return structuredActivityNodeExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuredActivityNodeExecution_NestedNodeExecutions() {
		return (EReference)structuredActivityNodeExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityParameterNodeExecution() {
		return activityParameterNodeExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityParameterNodeExecution_Input() {
		return (EReference)activityParameterNodeExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityParameterNodeExecution_ProvidedValues() {
		return (EReference)activityParameterNodeExecutionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputValue() {
		return inputValueEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInputValue_InputObjectToken() {
		return (EReference)inputValueEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInputValue_InputValueSnapshot() {
		return (EReference)inputValueEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutputValue() {
		return outputValueEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutputValue_OutputObjectToken() {
		return (EReference)outputValueEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutputValue_OutputValueSnapshot() {
		return (EReference)outputValueEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionExecution() {
		return actionExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionExecution_Inputs() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionExecution_Outputs() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionExecution_IncomingControl() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionExecution_OutgoingControl() {
		return (EReference)actionExecutionEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControlNodeExecution() {
		return controlNodeExecutionEClass;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControlNodeExecution_RoutedTokens() {
		return (EReference)controlNodeExecutionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActivity() {
		return activityEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActivityNode() {
		return activityNodeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBehavior() {
		return behaviorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActivityParameterNode() {
		return activityParameterNodeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInputPin() {
		return inputPinEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOutputPin() {
		return outputPinEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActivityEdge() {
		return activityEdgeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getValue() {
		return valueEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPrimitiveValue() {
		return primitiveValueEDataType;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getObject_() {
		return object_EDataType;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracemodelFactory getTracemodelFactory() {
		return (TracemodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		traceEClass = createEClass(TRACE);
		createEReference(traceEClass, TRACE__ACTIVITY_EXECUTIONS);
		createEReference(traceEClass, TRACE__VALUE_INSTANCES);
		createEReference(traceEClass, TRACE__INITIAL_LOCUS_VALUE_INSTANCES);

		activityExecutionEClass = createEClass(ACTIVITY_EXECUTION);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__NODE_EXECUTIONS);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__CALLER);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY);
		createEAttribute(activityExecutionEClass, ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID);
		createEReference(activityExecutionEClass, ACTIVITY_EXECUTION__TRACE);

		activityNodeExecutionEClass = createEClass(ACTIVITY_NODE_EXECUTION);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__LOGICAL_PREDECESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_SUCCESSOR);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__CHRONOLOGICAL_PREDECESSOR);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__NODE);
		createEReference(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__ACTIVITY_EXECUTION);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__EXECUTED);
		createEAttribute(activityNodeExecutionEClass, ACTIVITY_NODE_EXECUTION__UNDER_EXECUTION);

		callActionExecutionEClass = createEClass(CALL_ACTION_EXECUTION);
		createEReference(callActionExecutionEClass, CALL_ACTION_EXECUTION__CALLEE);

		userParameterInputEClass = createEClass(USER_PARAMETER_INPUT);
		createEAttribute(userParameterInputEClass, USER_PARAMETER_INPUT__INPUT_PARAMETER_NODE);
		createEReference(userParameterInputEClass, USER_PARAMETER_INPUT__PROVIDED_VALUES);

		inputEClass = createEClass(INPUT);
		createEAttribute(inputEClass, INPUT__INPUT_PIN);
		createEReference(inputEClass, INPUT__INPUT_VALUES);

		outputEClass = createEClass(OUTPUT);
		createEAttribute(outputEClass, OUTPUT__OUTPUT_PIN);
		createEReference(outputEClass, OUTPUT__OUTPUT_VALUES);

		tokenInstanceEClass = createEClass(TOKEN_INSTANCE);
		createEAttribute(tokenInstanceEClass, TOKEN_INSTANCE__TRAVERSED_EDGES);

		objectTokenInstanceEClass = createEClass(OBJECT_TOKEN_INSTANCE);
		createEReference(objectTokenInstanceEClass, OBJECT_TOKEN_INSTANCE__TRANSPORTED_VALUE);

		controlTokenInstanceEClass = createEClass(CONTROL_TOKEN_INSTANCE);

		actionExecutionEClass = createEClass(ACTION_EXECUTION);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__INPUTS);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__OUTPUTS);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__INCOMING_CONTROL);
		createEReference(actionExecutionEClass, ACTION_EXECUTION__OUTGOING_CONTROL);

		controlNodeExecutionEClass = createEClass(CONTROL_NODE_EXECUTION);
		createEReference(controlNodeExecutionEClass, CONTROL_NODE_EXECUTION__ROUTED_TOKENS);

		valueInstanceEClass = createEClass(VALUE_INSTANCE);
		createEAttribute(valueInstanceEClass, VALUE_INSTANCE__RUNTIME_VALUE);
		createEReference(valueInstanceEClass, VALUE_INSTANCE__SNAPSHOTS);
		createEReference(valueInstanceEClass, VALUE_INSTANCE__ORIGINAL);
		createEAttribute(valueInstanceEClass, VALUE_INSTANCE__DESTROYED);

		valueSnapshotEClass = createEClass(VALUE_SNAPSHOT);
		createEAttribute(valueSnapshotEClass, VALUE_SNAPSHOT__VALUE);

		decisionNodeExecutionEClass = createEClass(DECISION_NODE_EXECUTION);
		createEReference(decisionNodeExecutionEClass, DECISION_NODE_EXECUTION__DECISION_INPUT_VALUE);

		structuredActivityNodeExecutionEClass = createEClass(STRUCTURED_ACTIVITY_NODE_EXECUTION);
		createEReference(structuredActivityNodeExecutionEClass, STRUCTURED_ACTIVITY_NODE_EXECUTION__NESTED_NODE_EXECUTIONS);

		activityParameterNodeExecutionEClass = createEClass(ACTIVITY_PARAMETER_NODE_EXECUTION);
		createEReference(activityParameterNodeExecutionEClass, ACTIVITY_PARAMETER_NODE_EXECUTION__INPUT);
		createEReference(activityParameterNodeExecutionEClass, ACTIVITY_PARAMETER_NODE_EXECUTION__PROVIDED_VALUES);

		inputValueEClass = createEClass(INPUT_VALUE);
		createEReference(inputValueEClass, INPUT_VALUE__INPUT_OBJECT_TOKEN);
		createEReference(inputValueEClass, INPUT_VALUE__INPUT_VALUE_SNAPSHOT);

		outputValueEClass = createEClass(OUTPUT_VALUE);
		createEReference(outputValueEClass, OUTPUT_VALUE__OUTPUT_OBJECT_TOKEN);
		createEReference(outputValueEClass, OUTPUT_VALUE__OUTPUT_VALUE_SNAPSHOT);

		// Create data types
		activityEDataType = createEDataType(ACTIVITY);
		activityNodeEDataType = createEDataType(ACTIVITY_NODE);
		behaviorEDataType = createEDataType(BEHAVIOR);
		activityParameterNodeEDataType = createEDataType(ACTIVITY_PARAMETER_NODE);
		inputPinEDataType = createEDataType(INPUT_PIN);
		outputPinEDataType = createEDataType(OUTPUT_PIN);
		activityEdgeEDataType = createEDataType(ACTIVITY_EDGE);
		valueEDataType = createEDataType(VALUE);
		primitiveValueEDataType = createEDataType(PRIMITIVE_VALUE);
		object_EDataType = createEDataType(OBJECT_);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		callActionExecutionEClass.getESuperTypes().add(this.getActionExecution());
		objectTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());
		controlTokenInstanceEClass.getESuperTypes().add(this.getTokenInstance());
		actionExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());
		controlNodeExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());
		decisionNodeExecutionEClass.getESuperTypes().add(this.getControlNodeExecution());
		decisionNodeExecutionEClass.getESuperTypes().add(this.getCallActionExecution());
		structuredActivityNodeExecutionEClass.getESuperTypes().add(this.getActionExecution());
		activityParameterNodeExecutionEClass.getESuperTypes().add(this.getActivityNodeExecution());

		// Initialize classes and features; add operations and parameters
		initEClass(traceEClass, Trace.class, "Trace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrace_ActivityExecutions(), this.getActivityExecution(), this.getActivityExecution_Trace(), "activityExecutions", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_ValueInstances(), this.getValueInstance(), null, "valueInstances", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_InitialLocusValueInstances(), this.getValueInstance(), null, "initialLocusValueInstances", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityExecutionEClass, ActivityExecution.class, "ActivityExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityExecution_UserParameterInputs(), this.getUserParameterInput(), null, "userParameterInputs", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_NodeExecutions(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ActivityExecution(), "nodeExecutions", null, 0, -1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_Caller(), this.getCallActionExecution(), this.getCallActionExecution_Callee(), "caller", null, 0, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_Activity(), this.getActivity(), "activity", null, 1, 1, ActivityExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityExecution_ActivityExecutionID(), ecorePackage.getEInt(), "activityExecutionID", null, 1, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityExecution_Trace(), this.getTrace(), this.getTrace_ActivityExecutions(), "trace", null, 1, 1, ActivityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityNodeExecutionEClass, ActivityNodeExecution.class, "ActivityNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityNodeExecution_LogicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalPredecessor(), "logicalSuccessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_LogicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_LogicalSuccessor(), "logicalPredecessor", null, 0, -1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalSuccessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalPredecessor(), "chronologicalSuccessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ChronologicalPredecessor(), this.getActivityNodeExecution(), this.getActivityNodeExecution_ChronologicalSuccessor(), "chronologicalPredecessor", null, 0, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityNodeExecution_Node(), this.getActivityNode(), "node", null, 1, 1, ActivityNodeExecution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityNodeExecution_ActivityExecution(), this.getActivityExecution(), this.getActivityExecution_NodeExecutions(), "activityExecution", null, 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getActivityNodeExecution_Executed(), ecorePackage.getEBoolean(), "executed", "false", 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivityNodeExecution_UnderExecution(), ecorePackage.getEBoolean(), "underExecution", null, 1, 1, ActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callActionExecutionEClass, CallActionExecution.class, "CallActionExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallActionExecution_Callee(), this.getActivityExecution(), this.getActivityExecution_Caller(), "callee", null, 0, 1, CallActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userParameterInputEClass, UserParameterInput.class, "UserParameterInput", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserParameterInput_InputParameterNode(), this.getActivityParameterNode(), "inputParameterNode", null, 1, 1, UserParameterInput.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserParameterInput_ProvidedValues(), this.getOutputValue(), null, "providedValues", null, 0, -1, UserParameterInput.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInput_InputPin(), this.getInputPin(), "inputPin", null, 0, 1, Input.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInput_InputValues(), this.getInputValue(), null, "inputValues", null, 0, -1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutput_OutputPin(), this.getOutputPin(), "outputPin", null, 0, 1, Output.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutput_OutputValues(), this.getOutputValue(), null, "outputValues", null, 0, -1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tokenInstanceEClass, TokenInstance.class, "TokenInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTokenInstance_TraversedEdges(), this.getActivityEdge(), "traversedEdges", null, 0, -1, TokenInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(objectTokenInstanceEClass, ObjectTokenInstance.class, "ObjectTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObjectTokenInstance_TransportedValue(), this.getValueInstance(), null, "transportedValue", null, 1, 1, ObjectTokenInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlTokenInstanceEClass, ControlTokenInstance.class, "ControlTokenInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionExecutionEClass, ActionExecution.class, "ActionExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionExecution_Inputs(), this.getInput(), null, "inputs", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionExecution_Outputs(), this.getOutput(), null, "outputs", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionExecution_IncomingControl(), this.getControlTokenInstance(), null, "incomingControl", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionExecution_OutgoingControl(), this.getControlTokenInstance(), null, "outgoingControl", null, 0, -1, ActionExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlNodeExecutionEClass, ControlNodeExecution.class, "ControlNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControlNodeExecution_RoutedTokens(), this.getTokenInstance(), null, "routedTokens", null, 1, -1, ControlNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueInstanceEClass, ValueInstance.class, "ValueInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueInstance_RuntimeValue(), this.getValue(), "runtimeValue", null, 1, 1, ValueInstance.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueInstance_Snapshots(), this.getValueSnapshot(), null, "snapshots", null, 1, -1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueInstance_Original(), this.getValueSnapshot(), null, "original", null, 1, 1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValueInstance_Destroyed(), ecorePackage.getEBoolean(), "destroyed", "false", 1, 1, ValueInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueSnapshotEClass, ValueSnapshot.class, "ValueSnapshot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueSnapshot_Value(), this.getValue(), "value", null, 1, 1, ValueSnapshot.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decisionNodeExecutionEClass, DecisionNodeExecution.class, "DecisionNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDecisionNodeExecution_DecisionInputValue(), this.getInputValue(), null, "decisionInputValue", null, 0, 1, DecisionNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structuredActivityNodeExecutionEClass, StructuredActivityNodeExecution.class, "StructuredActivityNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructuredActivityNodeExecution_NestedNodeExecutions(), this.getActivityNodeExecution(), null, "nestedNodeExecutions", null, 0, -1, StructuredActivityNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityParameterNodeExecutionEClass, ActivityParameterNodeExecution.class, "ActivityParameterNodeExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityParameterNodeExecution_Input(), this.getInput(), null, "input", null, 0, 1, ActivityParameterNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityParameterNodeExecution_ProvidedValues(), this.getOutputValue(), null, "providedValues", null, 0, -1, ActivityParameterNodeExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputValueEClass, InputValue.class, "InputValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInputValue_InputObjectToken(), this.getObjectTokenInstance(), null, "inputObjectToken", null, 1, 1, InputValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInputValue_InputValueSnapshot(), this.getValueSnapshot(), null, "inputValueSnapshot", null, 1, 1, InputValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputValueEClass, OutputValue.class, "OutputValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutputValue_OutputObjectToken(), this.getObjectTokenInstance(), null, "outputObjectToken", null, 1, 1, OutputValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutputValue_OutputValueSnapshot(), this.getValueSnapshot(), null, "outputValueSnapshot", null, 1, 1, OutputValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(activityEDataType, Activity.class, "Activity", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityNodeEDataType, ActivityNode.class, "ActivityNode", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(behaviorEDataType, Behavior.class, "Behavior", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityParameterNodeEDataType, ActivityParameterNode.class, "ActivityParameterNode", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(inputPinEDataType, InputPin.class, "InputPin", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(outputPinEDataType, OutputPin.class, "OutputPin", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(activityEdgeEDataType, ActivityEdge.class, "ActivityEdge", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(valueEDataType, Value.class, "Value", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(primitiveValueEDataType, PrimitiveValue.class, "PrimitiveValue", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(object_EDataType, Object_.class, "Object_", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TracemodelPackageImpl
