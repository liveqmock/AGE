package com.age.core.sqlbuilder.datamodifier.modifier;

import com.age.core.sqlbuilder.datamodifier.DataModifier;

/**
 * @author badqiu
 */
public class IntegerDataModifier implements DataModifier{
	public Object modify(Object value, String modifierArgument) {
		if(value == null) return null;
		if(value instanceof Integer) return value;
		return new Integer(value.toString());
	}
}
