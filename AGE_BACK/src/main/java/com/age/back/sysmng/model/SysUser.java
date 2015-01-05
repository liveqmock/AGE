package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.age.back.sysmng.common.SysMngContstant;

/**
 * 用户信息
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 2161823047897369L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 用户账号
	 */
	private java.lang.String userAccount;
	/**
	 * 用户名称
	 */
	private java.lang.String userName;
	/**
	 * 登陆密码
	 */
	private java.lang.String password;
	/**
	 * 手机
	 */
	private java.lang.String mobile;
	/**
	 * 固定电话
	 */
	private java.lang.String phone;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;
	/**
	 * 删除状态(0：未删除；1：已删除)
	 */
	private java.lang.Boolean isDelete;
	/**
	 * 账号状态(0：禁用；1：可用)
	 */
	private java.lang.Integer state;
	/**
	* 账号类型(0：贷款用户；1：信贷人员)
	*/
	private java.lang.String accountType;
	/**
	 * 用户详细信息ID
	 */
	private java.lang.String userDetailId;
	/**
	 * ca码
	 */
	private java.lang.String ca;

	public SysUser() {
	}

	public SysUser(java.lang.String id) {
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

	@Column(name = "USER_ACCOUNT")
	public java.lang.String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(java.lang.String value) {
		this.userAccount = value;
	}

	@Column(name = "USER_NAME")
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	@Column(name = "PASSWORD")
	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	@Column(name = "MOBILE")
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

	@Column(name = "PHONE")
	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String value) {
		this.phone = value;
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

	@Column(name = "STATE")
	public java.lang.Integer getState() {
		return this.state;
	}

	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	private String stateCN;

	@Transient
	public String getStateCN() {
		return SysMngContstant.getSysMngStateCN(state);
	}

	public void setStateCN(String stateCN) {
		this.stateCN = stateCN;
	}

	@Column(name = "ACCOUNT_TYPE")
	public java.lang.String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(java.lang.String value) {
		this.accountType = value;
	}

	@Column(name = "USER_DETAIL_ID")
	public java.lang.String getUserDetailId() {
		return this.userDetailId;
	}

	public void setUserDetailId(java.lang.String value) {
		this.userDetailId = value;
	}

	@Column(name = "CA")
	public java.lang.String getCa() {
		return this.ca;
	}

	public void setCa(java.lang.String value) {
		this.ca = value;
	}

}
