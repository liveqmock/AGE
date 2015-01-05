package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 系统字典值
 */	
@Entity
@Table(name = "sys_dict_value")
public class SysDictValue implements java.io.Serializable{
	
	private static final long serialVersionUID = 2430126946733899L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 
    /**
     * 名称
     */ 	 
	private java.lang.String dictName; 
    /**
     * 值
     */ 	 
	private java.lang.String dictValue; 

    /**
     * 字典id
     */ 	  
	private SysDict sysDict;

	public SysDictValue(){
	}

	public SysDictValue(java.lang.String id){
		this.id = id;
	}

	@Id 
	@Column(name = "ID", unique = true, nullable = false)
	public java.lang.String getId() {
		return this.id;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	} 
	
	@Column(name = "DICT_NAME")
	public java.lang.String getDictName() {
		return this.dictName;
	}
	
	public void setDictName(java.lang.String value) {
		this.dictName = value;
	}
	
	@Column(name = "DICT_VALUE")
	public java.lang.String getDictValue() {
		return this.dictValue;
	}
	
	public void setDictValue(java.lang.String value) {
		this.dictValue = value;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "DICT_ID") })
	public SysDict getSysDict() {
		return sysDict;
	}
	
	public void setSysDict(SysDict sysDict){
		this.sysDict = sysDict;
	}
	
}
