package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 系统日志
 */	
@Entity
@Table(name = "sys_log")
public class SysLog implements java.io.Serializable{
	
	private static final long serialVersionUID = 2624281633386724L;  

    /**
     * id
     */ 	 
	private java.lang.String id; 
    /**
     * 登陆人员id
     */ 	 
	private java.lang.String userId; 
    /**
     * 登陆人员姓名
     */ 	 
	private java.lang.String userName; 
    /**
     * 登录IP
     */ 	 
	private java.lang.String ip; 
    /**
     * 记录时间
     */ 	 
	private java.util.Date logTime; 
    /**
     * 记录类型(0：登录日志，1：操作日志)
     */ 	 
	private java.lang.Integer logType; 
    /**
     * 记录详细
     */ 	 
	private java.lang.String content; 


	public SysLog(){
	}

	public SysLog(java.lang.String id){
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
	
	@Column(name = "USER_ID")
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	@Column(name = "USER_NAME")
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "IP")
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	@Column(name = "LOG_TIME")
	public java.util.Date getLogTime() {
		return this.logTime;
	}
	
	public void setLogTime(java.util.Date value) {
		this.logTime = value;
	}
	
	@Column(name = "LOG_TYPE")
	public java.lang.Integer getLogType() {
		return this.logType;
	}
	
	public void setLogType(java.lang.Integer value) {
		this.logType = value;
	}
	
	@Column(name = "CONTENT")
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
}
