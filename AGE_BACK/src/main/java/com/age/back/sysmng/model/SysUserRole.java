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
 * SysUserRole
 */	
@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable{
	
	private static final long serialVersionUID = 2628673497194064L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 

    /**
     * 用户id
     */ 	  
	private SysUser sysUser;
    /**
     * 角色id
     */ 	  
	private SysRole sysRole;

	public SysUserRole(){
	}

	public SysUserRole(java.lang.String id){
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
	@JoinColumns({@JoinColumn(name = "USER_ID") })
	public SysUser getSysUser() {
		return sysUser;
	}
	
	public void setSysUser(SysUser sysUser){
		this.sysUser = sysUser;
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
