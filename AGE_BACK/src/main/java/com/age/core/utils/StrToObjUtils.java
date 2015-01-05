package com.age.core.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StrToObjUtils {
	public static List<String> toList(String str, String separatorChars) {
		return Arrays.asList(StringUtils.split(str, separatorChars));
	}
}
