/*
* Copyright (c) 2014 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Tanja Mayerhofer - initial API and implementation
*/
package org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.provider;

import org.eclipse.uml2.uml.Class;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.BooleanValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.EnumerationValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.IntegerValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Link;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.StringValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.UnlimitedNaturalValue;
import org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Value;

/**
 * @author Tanja Mayerhofer (mayerhofer@big.tuwien.ac.at) 
 *
 */
public class ValueTextUtil {
	
	public static String getValueString(Value value) {
		String runtimeValue_Value = "";
		if (value instanceof StringValue) {
			runtimeValue_Value = getStringValueString((StringValue) value);
		} else if (value instanceof IntegerValue) {
			runtimeValue_Value = getIntegerValueString((IntegerValue) value);
		} else if (value instanceof BooleanValue) {
			runtimeValue_Value = getBooleanValueString((BooleanValue) value);
		} else if (value instanceof UnlimitedNaturalValue) {
			runtimeValue_Value = getUnlimitedNaturalValueString((UnlimitedNaturalValue) value);
		} else if (value instanceof Link) {
			runtimeValue_Value = getLinkString((Link) value);
		} else if (value instanceof org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object) {
			runtimeValue_Value = getObjectString((org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object) value);
		} else if (value instanceof EnumerationValue) {
			runtimeValue_Value = getEnumerationValueString((EnumerationValue) value);
		}
		return runtimeValue_Value;
	}

	private static String getEnumerationValueString(EnumerationValue enumerationValue) {
		return enumerationValue.getLiteral().getName() + " : " + enumerationValue.getType().getName();
	}

	private static String getObjectString(
			org.modelexecution.fuml.trace.uml2.fuml.Semantics.Classes.Kernel.Object object) {
		String objectString = "";
		for(Class type : object.getTypes()) {
			if(!objectString.equals(""))
				objectString += ", ";
			objectString += type.getName();
		}
		return " : " + objectString;
	}

	private static String getLinkString(Link link) {
		String linkType = "";
		if(link.getType() != null)
			linkType = link.getType().getName();
		return " : " + linkType;
	}

	private static String getUnlimitedNaturalValueString(UnlimitedNaturalValue unlimitedNaturalValue) {
		return unlimitedNaturalValue.getValue() + " : UnlimitedNatural";
	}

	private static String getBooleanValueString(BooleanValue booleanValue) {
		return booleanValue.isValue() + " : Boolean";
	}

	private static String getIntegerValueString(IntegerValue integerValue) {
		return integerValue.getValue() + " : Integer";
	}

	private static String getStringValueString(StringValue stringValue) {
		return stringValue.getValue() + " : String";
	}

}
