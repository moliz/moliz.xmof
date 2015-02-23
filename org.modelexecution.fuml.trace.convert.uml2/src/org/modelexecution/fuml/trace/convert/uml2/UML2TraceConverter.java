/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API
 * Tanja Mayerhofer - implementation
 */
package org.modelexecution.fuml.trace.convert.uml2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelexecution.fuml.trace.convert.IConversionResult;
import org.modelexecution.fuml.trace.convert.IConversionStatus;
import org.modelexecution.fuml.trace.convert.IConverter;
import org.modelexecution.fuml.trace.convert.TraceConverterPlugin;
import org.modelexecution.fuml.trace.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.trace.convert.impl.ConversionStatusImpl;
import org.modelexecution.fuml.trace.convert.uml2.internal.FUMLTraceInput;
import org.modelexecution.fuml.trace.convert.uml2.internal.UML2TraceElementFactory;
import org.modelexecution.fuml.trace.convert.uml2.internal.UML2TraceElementPopulatorSuite;
import org.modelexecution.fuml.values.convert.uml2.UML2ValueConverter;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;

/**
 * Converter for converting {@link Trace instances (fUML)} into
 * {@link org.modelexecution.fuml.trace.uml2.tracemodel.Trace instances (UML)}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2TraceConverter implements IConverter {

	private FUMLTraceInput fumlTraceInput;
	private ConversionResultImpl result;
	private ConversionStatusImpl status;

	@Override
	public boolean canConvert(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		if (input == null)
			return false;
		initializeFumlTraceInput(input, modelConversionResult);
		return fumlTraceInput.containsTrace()
				&& fumlTraceInput.containsModelConversionResult();
	}

	private void initializeFumlTraceInput(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		if (needToInitializeUml2Input(input)) {
			fumlTraceInput = new FUMLTraceInput(input, modelConversionResult);
		}
	}

	private boolean needToInitializeUml2Input(Object input) {
		return fumlTraceInput == null
				|| !fumlTraceInput.getOriginalInput().equals(input);
	}

	@Override
	public IConversionResult convert(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		if (!canConvert(input, modelConversionResult))
			return createCannotConvertResult(input, modelConversionResult);
		return startConversion();
	}

	private IConversionResult createCannotConvertResult(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		ConversionResultImpl result = new ConversionResultImpl(input,
				modelConversionResult);
		ConversionStatusImpl status = new ConversionStatusImpl(
				UML2TraceConverterPlugin.ID,
				IConversionStatus.CANNOT_CONVERT_INPUT_ERROR, "Cannot convert "
						+ input.toString(), new IllegalArgumentException(
						"Cannot convert " + input.toString()));
		result.setStatus(status);
		return result;
	}

	protected IConversionResult startConversion() {
		initializeResult();
		convertValues();
		instantiateModel();
		populateModelValues();
		addRuntimeValuesToTrace();
		completeRuntimeValues();
		return result;
	}

	private void convertValues() {
		ValueList valuesToConvert = new ValueList();
		valuesToConvert.addAll(fumlTraceInput.getValuesToConvert());

		UML2ValueConverter valueConverter = new UML2ValueConverter();
		org.modelexecution.fuml.values.convert.IConversionResult valueConversionResult = valueConverter
				.convert(valuesToConvert,
						fumlTraceInput.getModelConversionResult());
		result.setValueConversionResult(valueConversionResult);

		if (valueConversionResult.getStatus().getSeverity() == IStatus.ERROR) {
			addErrorToResult(
					org.modelexecution.fuml.values.convert.IConversionStatus.ERROR_WHILE_CONVERSION,
					"Exception while converting values.", valueConversionResult
							.getStatus().getException());
		}
	}

	private void addRuntimeValuesToTrace() {
		org.modelexecution.fuml.trace.uml2.tracemodel.Trace umlTrace = result
				.getTrace();
		for (org.modelexecution.fuml.trace.uml2.tracemodel.ValueInstance umlValueInstance : umlTrace
				.getValueInstances()) {
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlRuntimeValue = umlValueInstance
					.getRuntimeValue();
			if (umlRuntimeValue != null)
				umlTrace.getRuntimeValues().add(umlRuntimeValue);
		}
	}

	/**
	 * According to the metamodel of fUML, feature values _contain values_. In
	 * particular, the feature values of an object contain primitive values and
	 * the feature values of a link contain references. Because it is a
	 * _containment_ reference, the same value maybe only contained by one
	 * feature value. However, the fUML virtual machine does not account for
	 * that. In the trace, the runtime values associated with value instances
	 * are affected by this. In particular, it may happen that the values
	 * contained by feature values of runtime values get lost in the conversion
	 * process. Thus, after the conversion, we go through those feature values
	 * and create new copies of the lost values.
	 */
	private void completeRuntimeValues() {
		Collection<Value> fumlRuntimeValues = fumlTraceInput.getRuntimeValues();
		for (Value fumlRuntimeValue : fumlRuntimeValues) {
			Collection<FeatureValue> fumlFeatureValuesOfRuntimeValue = getFeatureValues(fumlRuntimeValue);
			completeFeatureValues(fumlFeatureValuesOfRuntimeValue);
		}
	}

	private Collection<FeatureValue> getFeatureValues(Value fumlValue) {
		Collection<FeatureValue> fumlFeatureValues = new ArrayList<FeatureValue>();
		if (fumlValue instanceof CompoundValue) {
			CompoundValue fumlValueAsCompoundValue = (CompoundValue) fumlValue;
			fumlFeatureValues.addAll(fumlValueAsCompoundValue
					.getFeatureValues());
		}
		return fumlFeatureValues;
	}

	private void completeFeatureValues(
			Collection<FeatureValue> fumlFeatureValues) {
		for (FeatureValue fumlFeatureValue : fumlFeatureValues) {
			completeFeatureValue(fumlFeatureValue);
		}
	}

	private void completeFeatureValue(FeatureValue fumlFeatureValue) {
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue umlFeatureValue = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue) result
				.getOutputUMLTraceElement(fumlFeatureValue);
		for (int i = 0; i < fumlFeatureValue.values.size(); ++i) {
			Value fumlValue = fumlFeatureValue.values.get(i);
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValue = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value) result
					.getOutputUMLTraceElement(fumlValue);
			if (isUmlFeatureValueIncomplete(umlFeatureValue, umlValue, i)) {
				org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValueCopy = EcoreUtil
						.copy(umlValue);
				try {
					umlFeatureValue.getValues().add(i, umlValueCopy);
				} catch (IndexOutOfBoundsException e) {
					addErrorToResult(IConversionStatus.ERROR_WHILE_CONVERSION,
							"Exception while completing runtime values.", e);
				}
			}
		}
	}

	private boolean isUmlFeatureValueIncomplete(
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue umlFeatureValue,
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValue,
			int position) {
		return umlFeatureValue.getValues().size() < (position + 1)
				|| umlFeatureValue.getValues().get(position) != umlValue;
	}

	private void initializeResult() {
		result = new ConversionResultImpl(fumlTraceInput.getOriginalInput(),
				fumlTraceInput.getModelConversionResult());
		status = new ConversionStatusImpl(UML2TraceConverterPlugin.ID,
				IConversionStatus.OK, "Initializing OK", null);
		result.setStatus(status);
	}

	private void instantiateModel() {
		UML2TraceElementFactory traceFactory = new UML2TraceElementFactory();
		for (EObject inputElement : fumlTraceInput.getTraceElementsToConvert()) {
			instantiateElement(traceFactory, inputElement);
		}
	}

	private void instantiateElement(UML2TraceElementFactory factory,
			EObject inputElement) {
		EObject element = factory.create(inputElement);
		if (element != null) {
			result.addInOutMapping(inputElement, element);
		} else {
			addWarningToResult("Could not convert " + inputElement.toString());
		}
	}

	private void populateModelValues() {
		UML2TraceElementPopulatorSuite populator = new UML2TraceElementPopulatorSuite(
				result, fumlTraceInput.getModelConversionResult());
		for (Iterator<Entry<Object, Object>> iterator = result.getMappings()
				.iterator(); iterator.hasNext();) {
			Entry<Object, Object> mapping = iterator.next();
			applyPopulator(populator, mapping.getValue(), mapping.getKey());
		}
	}

	private void applyPopulator(UML2TraceElementPopulatorSuite populator,
			Object fUMLElement, Object uml2Element) {
		try {
			populator.populate(fUMLElement, uml2Element);
		} catch (Exception e) {
			addErrorToResult(IConversionStatus.ERROR_WHILE_CONVERSION,
					"Exception while applying feature values.", e);
		}
	}

	protected void addErrorToResult(int code, String message,
			Throwable throwable) {
		status.add(new Status(IStatus.ERROR, UML2TraceConverterPlugin.ID, code,
				message, throwable));
		TraceConverterPlugin.instance.getLog().log(IStatus.ERROR, message,
				throwable);
	}

	protected void addInfoToResult(String message) {
		status.add(new Status(IStatus.INFO, UML2TraceConverterPlugin.ID,
				message));
	}

	protected void addWarningToResult(String message) {
		status.add(new Status(IStatus.WARNING, UML2TraceConverterPlugin.ID,
				message));
		TraceConverterPlugin.instance.getLog().log(IStatus.WARNING, message);
	}

}
