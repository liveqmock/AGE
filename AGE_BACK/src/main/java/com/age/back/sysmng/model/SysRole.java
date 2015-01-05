package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * SysRole
 */	
@Entity
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable{
	
	private static final long serialVersionUID = 2053567989198482L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 
    /**
     * 角色代码
     */ 	 
	private java.lang.String roleNo; 
    /**
     * 角色名称
     */ 	 
	private java.lang.String roleName; 
    /**
     * 描述
     */ 	 
	private java.lang.String roleDesc; 
    /**
     * 顺序号
     */ 	 
	private java.lang.Integer orderNo; 
    /**
     * 删除状态(0：未删除；1：已删除)
     */ 	 
	private java.lang.Boolean isDelete; 


	public SysRole(){
	}

	public SysRole(java.lang.String id){
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
	
	@Column(name = "ROLE_NO")
	public java.lang.String getRoleNo() {
		return this.roleNo;
	}
	
	public void setRoleNo(java.lang.String value) {
		this.roleNo = value;
	}
	
	@Column(name = "ROLE_NAME")
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	@Column(name = "ROLE_DESC")
	public java.lang.String getRoleDesc() {
		return this.roleDesc;
	}
	
	public void setRoleDesc(java.lang.String value) {
		this.roleDesc = value;
	}
	
	@Column(name = "ORDER_NO")
	public java.lang.Integer getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(java.lang.Integer value) {
		this.orderNo = value;
	}
	
	@Column(name = "IS_DELETE")
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}
	
}
