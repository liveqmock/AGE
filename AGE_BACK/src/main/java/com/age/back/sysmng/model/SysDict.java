package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 系统字典
 */	
@Entity
@Table(name = "sys_dict")
public class SysDict implements java.io.Serializable{
	
	private static final long serialVersionUID = 2231185973444008L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 
    /**
     * 名称
     */ 	 
	private java.lang.String dictName; 
    /**
     * 代码
     */ 	 
	private java.lang.String dictCode; 
    /**
     * 删除状态(0：未删除；1：已删除)
     */ 	 
	private java.lang.Boolean isDelete; 


	public SysDict(){
	}

	public SysDict(java.lang.String id){
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
	
	@Column(name = "DICT_CODE")
	public java.lang.String getDictCode() {
		return this.dictCode;
	}
	
	public void setDictCode(java.lang.String value) {
		this.dictCode = value;
	}
	
	@Column(name = "IS_DELETE")
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}
	
}
