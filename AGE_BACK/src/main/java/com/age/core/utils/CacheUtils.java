package com.age.core.utils;

import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CacheUtils {

	public static String mapKey(Map<String, String> obj) {
		return joinMapValue(new TreeMap<String, String>(obj));
	}

	public static String objectKey(Object obj) {
		return joinMapValue(obj2TreeMap(obj));
	}

	private static Map<String, String> obj2TreeMap(Object obj) {
		Map<String, String> map = new java.util.TreeMap<String, String>();
		BeanWrapper wrapper = new BeanWrapperImpl(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			try {
				if (!name.equals("class")) {
					if (descriptors[i].getReadMethod() != null) {
						map.put(name, String.valueOf(wrapper.getPropertyValue(name)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	private static String joinMapValue(Map<String, String> map) {
		StringBuffer b = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				b.append(entry.getValue());
			}
			b.append("&");
		}
		return b.toString();
	}

	private String getCacheKey(String targetName, String methodName, Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}
}
