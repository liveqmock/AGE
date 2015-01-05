package com.age.core.utils.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class SpringPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, String> properties = new HashMap<String, String>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
		for (Entry<Object, Object> entry : props.entrySet()) {
			String stringKey = String.valueOf(entry.getKey());
			String stringValue = String.valueOf(entry.getValue());
			stringValue = helper.replacePlaceholders(stringValue, props);
			properties.put(stringKey, stringValue);
		}
		super.processProperties(beanFactoryToProcess, props);
	}

	public static Map<String, String> getProperties() {
		return properties;
	}

	public static String getProperty(String key) {
		String property = properties.get(key);
		if (StringUtils.isEmpty(property))
			throw new IllegalArgumentException("key=" + key + "的属性未找到");
		return property;
	}
}
