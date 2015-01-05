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
 * SysRoleResource
 */	
@Entity
@Table(name = "sys_role_resource")
public class SysRoleResource implements java.io.Serializable{
	
	private static final long serialVersionUID = 2824556994423754L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 

    /**
     * resourceId
     */ 	  
	private SysResource sysResource;
    /**
     * roleId
     */ 	  
	private SysRole sysRole;

	public SysRoleResource(){
	}

	public SysRoleResource(java.lang.String id){
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "RESOURCE_ID") })
	public SysResource getSysResource() {
		return sysResource;
	}
	
	public void setSysResource(SysResource sysResource){
		this.sysResource = sysResource;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "ROLE_ID") })
	public SysRole getSysRole() {
		return sysRole;
	}
	
	public void setSysRole(SysRole sysRole){
		this.sysRole = sysRole;
	}
	
}
