package org.modelexecution.fuml.values.convert.uml2;

import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.modelexecution.fuml.values.convert.IConversionResult;
import org.modelexecution.fuml.values.convert.IConversionStatus;
import org.modelexecution.fuml.values.convert.IConverter;
import org.modelexecution.fuml.values.convert.ValueConverterPlugin;
import org.modelexecution.fuml.values.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.values.convert.impl.ConversionStatusImpl;
import org.modelexecution.fuml.values.convert.uml2.internal.FUMLValueInput;
import org.modelexecution.fuml.values.convert.uml2.internal.UML2ValueFactory;
import org.modelexecution.fuml.values.convert.uml2.internal.UML2ValuePopulatorSuite;

public class UML2ValueConverter implements IConverter{

	private FUMLValueInput fumlValueInput;
	private ConversionResultImpl result;
	private ConversionStatusImpl status;

	@Override
	public boolean canConvert(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		if (input == null)
			return false;
		initializeFumlValueInput(input, modelConversionResult);
		return fumlValueInput.hasInput()
				&& fumlValueInput.hasModelConversionResult();
	}

	private void initializeFumlValueInput(
			Object input,
			org.modelexecution.fuml.convert.IConversionResult modelConversionResult) {
		if (needToInitializeFumlValueInput(input)) {
			fumlValueInput = new FUMLValueInput(input, modelConversionResult);
		}
	}

	private boolean needToInitializeFumlValueInput(Object input) {
		return fumlValueInput == null
				|| !fumlValueInput.getOriginalInput().equals(input);
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
				UML2ValueConverterPlugin.ID,
				IConversionStatus.CANNOT_CONVERT_INPUT_ERROR, "Cannot convert "
						+ input.toString(), new IllegalArgumentException(
						"Cannot convert " + input.toString()));
		result.setStatus(status);
		return result;
	}

	protected IConversionResult startConversion() {
		initializeResult();
		instantiateModel();
		populateModelValues();
//		completeValues();
		return result;
	}

	//TODO
	/**
	 * According to the metamodel of fUML, feature values _contain_ values. In
	 * particular, the feature values of an object contain primitive values and
	 * the feature values of a link contain references. Because it is a
	 * _containment_ reference, the same value may be only contained by one
	 * feature value. However, the fUML virtual machine does not account for
	 * that. In particular, it may happen that the values
	 * contained by feature values get lost in the conversion
	 * process. Thus, after the conversion, we go through those feature values
	 * and create new copies of the lost values.
	 */
//	private void completeValues() {
//		Collection<Object> elementsToConvert = fumlValueInput.getElementsToConvert();
//		for (Object element : elementsToConvert) {
//			if(element instanceof Value) {
//				Value value = (Value) element;
//				Collection<FeatureValue> fumlFeatureValuesOfRuntimeValue = getFeatureValues(value);
//				completeFeatureValues(fumlFeatureValuesOfRuntimeValue);
//			}
//		}
//	}

//	private Collection<FeatureValue> getFeatureValues(Value fumlValue) {
//		Collection<FeatureValue> fumlFeatureValues = new ArrayList<FeatureValue>();
//		if (fumlValue instanceof CompoundValue) {
//			CompoundValue fumlValueAsCompoundValue = (CompoundValue) fumlValue;
//			fumlFeatureValues.addAll(fumlValueAsCompoundValue
//					.getFeatureValues());
//		}
//		return fumlFeatureValues;
//	}
//
//	private void completeFeatureValues(
//			Collection<FeatureValue> fumlFeatureValues) {
//		for (FeatureValue fumlFeatureValue : fumlFeatureValues) {
//			completeFeatureValue(fumlFeatureValue);
//		}
//	}

//	private void completeFeatureValue(FeatureValue fumlFeatureValue) {
//		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue umlFeatureValue = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue) result
//				.getOutputFeatureValue(fumlFeatureValue);
//		for (int i = 0; i < fumlFeatureValue.values.size(); ++i) {
//			Value fumlValue = fumlFeatureValue.values.get(i);
//			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValue = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value) result
//					.getOutputValue(fumlValue);
//			if (isUmlFeatureValueIncomplete(umlFeatureValue, umlValue, i)) {
//				org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValueCopy = EcoreUtil
//						.copy(umlValue);
//				try {
//					umlFeatureValue.getValues().add(i, umlValueCopy);
//				} catch (IndexOutOfBoundsException e) {
//					addErrorToResult(IConversionStatus.ERROR_WHILE_CONVERSION,
//							"Exception while completing runtime values.", e);
//				}
//			}
//		}
//	}

//	private boolean isUmlFeatureValueIncomplete(
//			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.FeatureValue umlFeatureValue,
//			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValue,
//			int position) {
//		return umlFeatureValue.getValues().size() < (position + 1)
//				|| umlFeatureValue.getValues().get(position) != umlValue;
//	}

	private void initializeResult() {
		result = new ConversionResultImpl(fumlValueInput.getOriginalInput(),
				fumlValueInput.getModelConversionResult());
		status = new ConversionStatusImpl(UML2ValueConverterPlugin.ID,
				IConversionStatus.OK, "Initializing OK", null);
		result.setStatus(status);
	}

	private void instantiateModel() {
		UML2ValueFactory traceFactory = new UML2ValueFactory();
		for (Object inputElement : fumlValueInput.getElementsToConvert()) {
			instantiateElement(traceFactory, inputElement);
		}
	}

	private void instantiateElement(UML2ValueFactory factory,
			Object inputElement) {
		EObject element = factory.create(inputElement);
		if (element != null) {
			result.addInOutMapping(inputElement, element);
		} else {
			addWarningToResult("Could not convert " + inputElement.toString());
		}
	}

	private void populateModelValues() {
		UML2ValuePopulatorSuite populator = new UML2ValuePopulatorSuite(
				result, fumlValueInput.getModelConversionResult());
		for (Iterator<Entry<Object, Object>> iterator = result.getMappings()
				.iterator(); iterator.hasNext();) {
			Entry<Object, Object> mapping = iterator.next();
			applyPopulator(populator, mapping.getValue(), mapping.getKey());
		}
	}

	private void applyPopulator(UML2ValuePopulatorSuite populator,
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
		status.add(new Status(IStatus.ERROR, UML2ValueConverterPlugin.ID, code,
				message, throwable));
		ValueConverterPlugin.instance.getLog().log(IStatus.ERROR, message,
				throwable);
	}

	protected void addInfoToResult(String message) {
		status.add(new Status(IStatus.INFO, UML2ValueConverterPlugin.ID,
				message));
	}

	protected void addWarningToResult(String message) {
		status.add(new Status(IStatus.WARNING, UML2ValueConverterPlugin.ID,
				message));
		ValueConverterPlugin.instance.getLog().log(IStatus.WARNING, message);
	}
}
