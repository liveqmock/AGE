package com.age.core.sqlbuilder.datamodifier.modifier;

import com.age.core.sqlbuilder.datamodifier.DataModifier;
import com.age.core.sqlbuilder.datamodifier.DefaultUtils;

/**
 * @author badqiu
 */
public class DefaultDataModifier implements DataModifier {
	private static final String DEFAULT_STRING = "";
	public Object modify(Object value, String modifierArgument) {
		if(value == null)
			return DefaultUtils.defaultString(modifierArgument, DEFAULT_STRING);
		return value;
	}
}
