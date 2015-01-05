package com.age.core.sqlbuilder.datamodifier;
/**
 * 
 * @author badqiu
 *
 */
public interface DataModifier {
	
	public Object modify(Object value,String modifierArgument) throws Exception;
	
}