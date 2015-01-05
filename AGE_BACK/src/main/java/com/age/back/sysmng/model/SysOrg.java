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
 * 机构信息
 */
@Entity
@Table(name = "sys_org")
public class SysOrg implements java.io.Serializable {

	private static final long serialVersionUID = 2359772553806485L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 部门名称
	 */
	private java.lang.String orgName;
	/**
	 * 部门联系电话
	 */
	private java.lang.String phone;
	/**
	 * 部门地址
	 */
	private java.lang.String address;
	/**
	 * 删除状态(0：未删除；1：已删除)
	 */
	private java.lang.Boolean isDelete;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;
	/**
	 * 是否叶子
	 */
	private java.lang.Boolean isLeaf;

	/**
	 * 上级部门id
	 */
	private SysOrg parentSysOrg;

	public SysOrg() {
	}

	public SysOrg(java.lang.String id) {
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

	@Column(name = "ORG_NAME")
	public java.lang.String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}

	@Column(name = "PHONE")
	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String value) {
		this.phone = value;
	}

	@Column(name = "ADDRESS")
	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	@Column(name = "IS_DELETE")
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}

	@Column(name = "ORDER_NO")
	public java.lang.Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(java.lang.Integer value) {
		this.orderNo = value;
	}

	@Column(name = "IS_LEAF")
	public java.lang.Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(java.lang.Boolean value) {
		this.isLeaf = value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "PARENT_ORG_ID") })
	public SysOrg getParentSysOrg() {
		return parentSysOrg;
	}

	public void setParentSysOrg(SysOrg parentSysOrg) {
		this.parentSysOrg = parentSysOrg;
	}

}
