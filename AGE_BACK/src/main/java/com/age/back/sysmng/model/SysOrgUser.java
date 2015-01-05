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
 * 机构用户关联
 */	
@Entity
@Table(name = "sys_org_user")
public class SysOrgUser implements java.io.Serializable{
	
	private static final long serialVersionUID = 2887331285136608L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 

    /**
     * 人员id
     */ 	  
	private SysUser sysUser;
    /**
     * 机构id
     */ 	  
	private SysOrg sysOrg;

	public SysOrgUser(){
	}

	public SysOrgUser(java.lang.String id){
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
	@JoinColumns({@JoinColumn(name = "ORG_ID") })
	public SysOrg getSysOrg() {
		return sysOrg;
	}
	
	public void setSysOrg(SysOrg sysOrg){
		this.sysOrg = sysOrg;
	}
	
}
