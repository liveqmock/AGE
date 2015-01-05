package com.age.core.domain;

import java.util.Map;

public class CursorVO {
    //游标类
	private int id;
	private String values;
	private String remarks;
	private Map retMap;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Map getRetMap() {
		return retMap;
	}
	public void setRetMap(Map retMap) {
		this.retMap = retMap;
	}
	
}
