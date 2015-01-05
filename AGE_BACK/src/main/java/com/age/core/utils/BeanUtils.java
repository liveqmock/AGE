package com.age.core.utils;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

public class BeanUtils {

	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 取出bean中set/get方法对应的字段，切忌不要同时使用空字段的set/get方法
	 * @param beanClass
	 * @return
	 */
	public static Vector getProperties(Class beanClass) {
		Vector properties = new Vector();
		//Class beanClass = bean.getClass();
		Method[] methods = beanClass.getMethods();

		//遍历方法集，不适用set/get方法重载情况！
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String name = method.getName();
			Class type = method.getReturnType();
			if (name.startsWith("get") || name.startsWith("is")) {
				String property = null;
				if (name.startsWith("is")) {
					property = name.substring(2);
				} else {
					property = name.substring(3);
				}
				//检查
				try {

					@SuppressWarnings("unused")
					Method setMethod = beanClass.getMethod("set" + property, new Class[] { type });
					properties.add(firstCharToLowerCase(property));
				} catch (SecurityException ex) {
				} catch (NoSuchMethodException ex) {
					logger.debug(ex.toString());
				}
			}
		}
		return properties;
	}

	private static String firstCharToLowerCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	private static String firstCharToUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 取出bean中set/get方法对应的字段，切忌不要同时使用空字段的set/get方法
	 * @param beanClass
	 * @return
	 */
	public static String[] getProperties(Object obj) {

		Vector properties = new Vector();
		Class beanClass = obj.getClass();
		Method[] methods = beanClass.getMethods();

		//遍历方法集，不适用set/get方法重载情况！
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String name = method.getName();
			Class type = method.getReturnType();
			if (name.startsWith("get") || name.startsWith("is")) {
				String property = null;
				if (name.startsWith("is")) {
					property = name.substring(2);
				} else {
					property = name.substring(3);
				}
				//检查
				try {

					@SuppressWarnings("unused")
					Method setMethod = beanClass.getMethod("set" + property, new Class[] { type });
					properties.add(firstCharToLowerCase(property));
				} catch (SecurityException ex) {
				} catch (NoSuchMethodException ex) {
					logger.debug(ex.toString());
				}
			}
		}
		Object[] str = properties.toArray();
		String rets[] = new String[str.length];
		for (int i = 0; i < rets.length; i++) {
			rets[i] = str[i].toString();
		}
		return rets;
	}

	@SuppressWarnings("unused")
	public static String[] getProperties(Map map) {
		String retStr[] = new String[map.size()];
		Iterator ite = map.entrySet().iterator();
		int i = 0;
		while (ite.hasNext()) {
			Map.Entry obj = (Map.Entry) ite.next();
			Object key = obj.getKey();
			Object value = obj.getValue();
			retStr[i] = key.toString();
			i++;
		}
		return retStr;

	}

	/**
	 * 获取Bean属性的数据类型
	 * @param beanClass Bean类
	 * @param name 属性名称
	 * @return 数据类型的Class
	 * @throws SystemException
	 */
	public static Class getPropertyType(Class beanClass, String name) throws Exception {
		@SuppressWarnings("unused")
		Vector properties = new Vector();
		String getName = "get" + firstCharToUpperCase(name);
		String isName = "is" + firstCharToUpperCase(name);
		Method method = null;
		try {
			try {
				method = beanClass.getMethod(getName, new Class[] {});
			} catch (NoSuchMethodException e) {
				method = beanClass.getMethod(isName, new Class[] {});
			}
			return method.getReturnType();
		} catch (NoSuchMethodException e) {
			throw new Exception("无效的属性。", e);
		}
	}

	/**
	 * 获得Bean对象实例的属性值
	 * @param bean Bean对象实例
	 * @param name 属性名称
	 * @return 属性值对象
	 * @throws SystemException
	 */
	public static Object getPropertyValue(Object bean, String name) throws Exception {
		Class beanClass = bean.getClass();
		String getName = "get" + firstCharToUpperCase(name);
		String isName = "is" + firstCharToUpperCase(name);
		Method method = null;
		try {
			try {
				method = beanClass.getMethod(getName, new Class[] {});
			} catch (NoSuchMethodException e) {
				method = beanClass.getMethod(isName, new Class[] {});
			}
			return method.invoke(bean, new Object[] {});
		} catch (NoSuchMethodException e) {
			throw new Exception("无效的属性。", e);
		} catch (java.lang.IllegalAccessException iae) {
			throw new Exception(iae);
		} catch (java.lang.reflect.InvocationTargetException ite) {
			throw new Exception(ite);
		}
	}

	/**
	 * 设置Bean对象的属性值实例
	 * @param bean Bean对象实例
	 * @param name 属性名称
	 * @param value 属性值
	 * @throws Exception
	 */
	public static void setPropertyValue(Object bean, String name, Object value) throws Exception {
		Class beanClass = bean.getClass();
		String getMethodName = "get" + firstCharToUpperCase(name);
		String setMethodName = "set" + firstCharToUpperCase(name);
		try {
			Method getMethod = beanClass.getMethod(getMethodName, new Class[] {});
			Method setMethod = beanClass.getMethod(setMethodName, new Class[] { getMethod.getReturnType() });
			setMethod.invoke(bean, new Object[] { value });
		} catch (NoSuchMethodException e) {
			throw new Exception("无效的属性。", e);
		} catch (java.lang.IllegalAccessException iae) {
			throw new Exception(iae);
		} catch (java.lang.reflect.InvocationTargetException ite) {
			throw new Exception(ite);
		}
	}

	/**
	 * 设置Bean对象的属性值实例
	 * @param bean Bean对象实例
	 * @param name 属性名称
	 * @param value 属性值
	 * @param propertyClass 属性值的实际类型（必须value.getClass()的子类）
	 * @throws Exception
	 */
	public static void setPropertyValue(Object bean, String name, Object value, Class propertyClass) throws Exception {
		if (value != null && !propertyClass.isAssignableFrom(value.getClass())) {
			throw new Exception("value与PropertyClass类型不符。");
		}
		setPropertyValue(bean, name, value);
	}

	/**
	 * 设置属性值
	 * @param bean Bean对象实例
	 * @param valueMap 属性值Map
	 * @throws Exception
	 */
	public static void setPropertyValues(Object bean, Map valueMap) throws Exception {
		Set keySet = valueMap.keySet();
		Iterator iterator = keySet.iterator();
		if (iterator.hasNext()) {
			Object key = iterator.next();
			Object value = valueMap.get(key);
			setPropertyValue(bean, key.toString(), value);
		}
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	public static Field getDeclaredField(Object object, String fieldName) throws NoSuchFieldException {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(Class clazz, String fieldName) throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
	}

	public static Iterator CopyIterator(Class classType, Iterator src) {
		return CopyList(classType, src).iterator();
	}

	public static List CopyList(Class classType, Iterator src) {
		List tag = new ArrayList();
		while (src.hasNext()) {
			Object obj = null, ormObj = src.next();
			try {
				obj = classType.newInstance();
				BeanWrapper wrapper = new BeanWrapperImpl(obj);
				BeanWrapper wrapper1 = new BeanWrapperImpl(ormObj);
				PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
				for (int i = 0; i < descriptors.length; i++) {
					String name = descriptors[i].getName();
					wrapper.setPropertyValue(name, wrapper1.getPropertyValue(name));
				}

			} catch (Exception e) {
			}
			if (obj != null)
				tag.add(obj);
		}
		return tag;
	}

	public static void Map2Obj(Map map, Object obj) {
		BeanWrapper wrapper = new BeanWrapperImpl(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			Object v = map.get(name);
			if (v != null && descriptors[i].getWriteMethod() != null) {
				wrapper.setPropertyValue(name, v);
			}
		}
	}

	public static void Obj2Map(Object obj, Map map) {
		if (map == null)
			map = new java.util.HashMap();
		BeanWrapper wrapper = new BeanWrapperImpl(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			try {
				if (descriptors[i].getReadMethod() != null) {
					map.put(name, wrapper.getPropertyValue(name));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Map Obj2Map(Object obj) {
		Map map = new java.util.HashMap();
		BeanWrapper wrapper = new BeanWrapperImpl(obj);
		PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			String name = descriptors[i].getName();
			try {
				if (descriptors[i].getReadMethod() != null) {
					map.put(name, wrapper.getPropertyValue(name));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@SuppressWarnings("unused")
	public static String[] getMapKeyValue(Map map) {
		Iterator ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry obj = (Map.Entry) ite.next();
			Object key = obj.getKey();
			Object value = obj.getValue();
		}
		return null;
	}

	//===================================================================
	/**
	 * Converts a serializable object to a byte array.
	 */
	public static byte[] objectToBytes(Object object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(baos);
		os.writeObject(object);
		return baos.toByteArray();
	}

	/**
	 * Converts a byte array to a serializable object.
	 */
	public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream is = new ObjectInputStream(bais);
		return is.readObject();
	}

	//=========================================================================

	/**
	 * Instantaite an Object from a given class name
	 * 
	 * @param className
	 *            full qualified name of the class
	 * @return the instantaited Object
	 * @exception java.lang.Exception
	 *                if instantiation failed
	 */
	public static Object createObject(String className) throws Exception {
		return createObject(Class.forName(className));
	}

	/**
	 * Instantaite an Object instance
	 * 
	 * @param classObject
	 *            Class object representing the object type to be instantiated
	 * @return the instantaied Object
	 * @exception java.lang.Exception
	 *                if instantiation failed
	 */
	public static Object createObject(Class classObject) throws Exception {
		return classObject.newInstance();
	}

	/**
	 * Instantaite an Object instance, requires a constructor with parameters
	 * 
	 * @param className
	 *            full qualified name of the class
	 * @param params
	 *            an array including the required parameters to instantaite the
	 *            object
	 * @return the instantaited Object
	 * @exception java.lang.Exception
	 *                if instantiation failed
	 */
	public static Object createObject(String className, Object[] params) throws Exception {
		return createObject(Class.forName(className), params);
	}

	/**
	 * Instantaite an Object instance, requires a constractor with parameters
	 * 
	 * @param classObject
	 *            , Class object representing the object type to be instantiated
	 * @param params
	 *            an array including the required parameters to instantaite the
	 *            object
	 * @return the instantaied Object
	 * @exception java.lang.Exception
	 *                if instantiation failed
	 */
	public static Object createObject(Class classObject, Object[] params) throws Exception {
		Constructor[] constructors = classObject.getConstructors();
		Object object = null;
		for (int counter = 0; counter < constructors.length; counter++) {
			try {
				object = constructors[counter].newInstance(params);
			} catch (Exception e) {
				if (e instanceof InvocationTargetException)
					((InvocationTargetException) e).getTargetException().printStackTrace();
				// do nothing, try the next constructor
			}
		}
		if (object == null)
			throw new InstantiationException();
		return object;
	}

	public static Class createClass(String className) {
		Class classService = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			classService = classLoader.loadClass(className);
		} catch (Exception ex) {
			System.err.print("[JdonFramework] createClass error:" + ex);
		}
		return classService;
	}

	//==========================================================================================

	public static Class[] getParentAllInterfaces(Class pojoClass) {
		Class[] interfaces = null;
		try {
			List interfacesL = new ArrayList();
			while (pojoClass != null) {
				for (int i = 0; i < pojoClass.getInterfaces().length; i++) {
					Class ifc = pojoClass.getInterfaces()[i];
					// not add jdk interface
					if (!ifc.getName().startsWith("java."))
						interfacesL.add(ifc);
				}
				pojoClass = pojoClass.getSuperclass();
			}
			if (interfacesL.size() == 0) {
				throw new Exception();
			}
			interfaces = (Class[]) interfacesL.toArray(new Class[interfacesL.size()]);
		} catch (Exception e) {
		}
		return interfaces;
	}

	public static Class[] getAllInterfaces(Class clazz) {
		if (clazz == null) {
			return new Class[0];
		}
		List<Class> classList = new ArrayList<Class>();
		while (clazz != null) {
			Class[] interfaces = clazz.getInterfaces();
			for (Class interf : interfaces) {
				if (!classList.contains(interf)) {
					classList.add(interf);
				}
				Class[] superInterfaces = getAllInterfaces(interf);
				for (Class superIntf : superInterfaces) {
					if (!classList.contains(superIntf)) {
						classList.add(superIntf);
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		return classList.toArray(new Class[classList.size()]);
	}

	public static Field[] getAllDecaredFields(Class clazz) {
		List<Field> fields = new ArrayList<Field>();
		// fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

		Class[] superClasses = getAllSuperclasses(clazz);
		for (Class superClass : superClasses) {
			fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
		}
		return fields.toArray(new Field[fields.size()]);
	}

	public static Class[] getAllSuperclasses(Class cls) {
		if (cls == null) {
			return new Class[0];
		}
		List<Class> classList = new ArrayList<Class>();
		Class superClass = cls;
		while (superClass != null && !Object.class.equals(superClass) && !Class.class.equals(superClass)) {
			classList.add(superClass);
			superClass = superClass.getSuperclass();
		}
		return classList.toArray(new Class[classList.size()]);
	}

	public static Field getDecaredField(Class clazz, String name) throws NoSuchFieldException {
		Field field = null;
		Class[] superClasses = getAllSuperclasses(clazz);
		for (Class superClass : superClasses) {
			try {
				field = superClass.getDeclaredField(name);
				break;
			} catch (NoSuchFieldException e) {
				// ignore
			}
		}
		if (field == null) {
			throw new NoSuchFieldException("No such declared field " + name + " in " + clazz);
		}
		return field;
	}

}
