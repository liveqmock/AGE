package com.age.back.jms.message;

import java.io.Serializable;

/**
 * 表id
 */
public interface Id<T> extends Serializable{
	public T getId();
}
