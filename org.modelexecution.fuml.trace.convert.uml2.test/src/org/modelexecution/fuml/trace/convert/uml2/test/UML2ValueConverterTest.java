package org.modelexecution.fuml.trace.convert.uml2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.values.convert.IConversionResult;
import org.modelexecution.fuml.values.convert.IConverter;
import org.modelexecution.fuml.values.convert.uml2.UML2ValueConverter;

import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;

public class UML2ValueConverterTest {

	@Test
	public void test() {
		StringValue fumlStringValue = createStringValue("test");
		IConversionResult conversionResult = convert(fumlStringValue);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value umlValue = conversionResult
				.getOutputValue(fumlStringValue);
		assertNotNull(umlValue);
		assertTrue(umlValue instanceof org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue);
		org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue umlStringValue = (org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue) umlValue;
		assertEquals("test", umlStringValue.getValue());
	}

	private StringValue createStringValue(String value) {
		StringValue stringValue = new StringValue();
		stringValue.value = value;
		return stringValue;
	}

	private IConversionResult convert(
			Value... values) {
		ValueList valuesToConvert = new ValueList();
		valuesToConvert.addAll(Arrays
				.<fUML.Semantics.Classes.Kernel.Value> asList(values));

		IConverter valueConverter = new UML2ValueConverter();
		return valueConverter.convert(valuesToConvert, new ConversionResultImpl());
	}

}
