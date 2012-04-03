/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.convert.uml2;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConversionStatus;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.impl.ConversionStatusImpl;
import org.modelexecution.fuml.convert.uml2.internal.ElementFactory;
import org.modelexecution.fuml.convert.uml2.internal.UML2Input;

/**
 * Converter for converting {@link Activity UML2 activities} or resources
 * containing {@link Activity UML2 activities} into
 * {@link fUML.Syntax.Activities.IntermediateActivities.Activity fUML
 * activities}.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class UML2Converter implements IConverter {

	private ConversionResultImpl result;
	private ConversionStatusImpl status;

	@Override
	public boolean canConvert(Object input) {
		// TODO use UML2Input to decide whether we can convert that
		return isActivity(input) || isResourceContainingActivity(input)
				|| isEObjectContainingActivity(input);
	}

	private boolean isActivity(Object input) {
		return input instanceof Activity;
	}

	private boolean isResourceContainingActivity(Object input) {
		if (input instanceof Resource) {
			Resource resource = (Resource) input;
			return containsActivity(resource.getAllContents());
		}
		return false;
	}

	private boolean isEObjectContainingActivity(Object input) {
		if (input instanceof EObject) {
			EObject eObject = (EObject) input;
			return containsActivity(eObject.eAllContents());
		}
		return false;
	}

	private boolean containsActivity(TreeIterator<EObject> iterator) {
		while (iterator.hasNext()) {
			if (isActivity(iterator.next())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IConversionResult convert(Object input) {
		if (!canConvert(input))
			return createCannotConvertResult(input);
		return startConversion(input);
	}

	private IConversionResult createCannotConvertResult(Object input) {
		ConversionResultImpl result = new ConversionResultImpl(input);
		ConversionStatusImpl status = new ConversionStatusImpl(
				UML2ConverterPlugin.ID,
				IConversionStatus.CANNOT_CONVERT_INPUT_ERROR, "Cannot convert "
						+ input.toString(), new IllegalArgumentException(
						"Cannot convert " + input.toString()));
		result.setStatus(status);
		return result;
	}

	protected ConversionResultImpl startConversion(Object input) {
		initializeResult(input);
		UML2Input umlInput = new UML2Input(input);
		instantiateModel(umlInput);
		populateModelValues();
		setMainActivitiesToResult(input);
		return result;
	}

	private void initializeResult(Object input) {
		result = new ConversionResultImpl(input);
		status = new ConversionStatusImpl(UML2ConverterPlugin.ID,
				IConversionStatus.ERROR, "Initializing OK", null);
		result.setStatus(status);
	}

	private void instantiateModel(UML2Input umlInput) {
		ElementFactory factory = new ElementFactory();
		for (Element inputElement : umlInput.getElementsToConvert()) {
			instantiateElement(factory, inputElement);
			for (TreeIterator<EObject> treeIterator = inputElement
					.eAllContents(); treeIterator.hasNext();) {
				EObject inputElementChild = treeIterator.next();
				if (inputElementChild instanceof Element) {
					instantiateElement(factory, (Element) inputElement);
				}
			}
		}
	}

	private void instantiateElement(ElementFactory factory, Element inputElement) {
		fUML.Syntax.Classes.Kernel.Element element = factory
				.create(inputElement);
		if (element != null) {
			result.addInOutMapping(inputElement, element);
		} else {
			addWarningToResult("Could not convert " + inputElement.toString());
		}
	}

	private void populateModelValues() {
		// TODO implement

	}

	private void setMainActivitiesToResult(Object input) {
		// TODO let UML2Input decide what is/are the main activities
		if (input instanceof Activity) {
			fUML.Syntax.Classes.Kernel.Element element = result
					.getFUMLElement(input);
			if (element != null
					&& element instanceof fUML.Syntax.Activities.IntermediateActivities.Activity)
				result.addActivity((fUML.Syntax.Activities.IntermediateActivities.Activity) element);
		}
	}

	protected void addErrorToResult(int code, String message,
			Throwable throwable) {
		status.add(new Status(IStatus.ERROR, UML2ConverterPlugin.ID, code,
				message, throwable));
	}

	protected void addInfoToResult(String message) {
		status.add(new Status(IStatus.INFO, UML2ConverterPlugin.ID, message));
	}

	protected void addWarningToResult(String message) {
		status.add(new Status(IStatus.WARNING, UML2ConverterPlugin.ID, message));
	}

}
