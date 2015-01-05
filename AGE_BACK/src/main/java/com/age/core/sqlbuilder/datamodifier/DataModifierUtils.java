package com.age.core.sqlbuilder.datamodifier;
import java.util.Map;

import com.age.core.sqlbuilder.datamodifier.modifier.BigDecimalDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.BigIntegerDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.BooleanDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.ByteDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.DateDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.DefaultDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.DoubleDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.FloatDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.IntegerDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.LongDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.ShortDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.SqlDateDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.SqlTimeDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.StringDataModifier;
import com.age.core.sqlbuilder.datamodifier.modifier.TimestampDataModifier;

/**
 * 工具类,将DataModifierBean的相关方法static化,并注册默认的修饰符
 * <pre>
		default = new DefaultDataModifier()
		boolean = new BooleanDataModifier()
		string = new StringDataModifier()
		byte = new ByteDataModifier()
		short = new ShortDataModifier()
		int = new IntegerDataModifier()
		long = new LongDataModifier()
		float = new FloatDataModifier()
		double = new DoubleDataModifier()
		BigInteger = new BigIntegerDataModifier()
		BigDecimal = new BigDecimalDataModifier()
		date = new DateDataModifier()
		sqldate = new SqlDateDataModifier()
		sqltime = new SqlTimeDataModifier()
		timestamp = new TimestampDataModifier()
 * </pre>
 * @author badqiu
 *
 */
public class DataModifierUtils {
	
	private static DataModifierBean delegate = new DataModifierBean();
	
	static{
		registerDefaultDataModifiers(delegate);
	}
	
	private DataModifierUtils(){
	}
	
	public static void registerDefaultDataModifiers(DataModifierBean bean) {
		bean.registerDataModifier("default",new DefaultDataModifier());
		bean.registerDataModifier("boolean",new BooleanDataModifier());
		bean.registerDataModifier("string",new StringDataModifier());
		bean.registerDataModifier("byte",new ByteDataModifier());
		bean.registerDataModifier("short",new ShortDataModifier());
		bean.registerDataModifier("int",new IntegerDataModifier());
		bean.registerDataModifier("long",new LongDataModifier());
		bean.registerDataModifier("float",new FloatDataModifier());
		bean.registerDataModifier("double",new DoubleDataModifier());
		bean.registerDataModifier("BigInteger",new BigIntegerDataModifier());
		bean.registerDataModifier("BigDecimal",new BigDecimalDataModifier());
		bean.registerDataModifier("date",new DateDataModifier());
		bean.registerDataModifier("sqldate",new SqlDateDataModifier());
		bean.registerDataModifier("sqltime",new SqlTimeDataModifier());
		bean.registerDataModifier("timestamp",new TimestampDataModifier());
	}
	
	public static void deregisterDataModifier(String modifierName) {
		delegate.deregisterDataModifier(modifierName);
	}

	public static void registerDataModifier(String modifierName, DataModifier modifier) {
		delegate.registerDataModifier(modifierName, modifier);
	}
	
	public Map getAvailableDataModifiers() {
		return delegate.getAvailableDataModifiers();
	}

	public static Object directModify(String dataModifierExpression, Object dataModifyValue) {
		return delegate.directModify(dataModifierExpression, dataModifyValue);
	}

	public static Object modify(String completeExpression, Object dataModifyValue) {
		return delegate.modify(completeExpression, dataModifyValue);
	}

	public static String getModifyVariable(String completeExpression) {
		return DataModifierBean.getModifyVariable(completeExpression);
	}
	
}
