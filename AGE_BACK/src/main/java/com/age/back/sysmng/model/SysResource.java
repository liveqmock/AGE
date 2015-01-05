package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.age.back.sysmng.common.SysMngContstant;

/**
 * 资源表
 */
@Entity
@Table(name = "sys_resource")
public class SysResource implements java.io.Serializable {

	private static final long serialVersionUID = 2249799865087909L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 资源代码
	 */
	private java.lang.String resNo;
	/**
	 * 资源名称
	 */
	private java.lang.String resName;
	/**
	 * 显示名称
	 */
	private java.lang.String displayName;
	/**
	 * 资源类型(0：模块；1：页面；2：操作)
	 */
	private java.lang.String type;
	/**
	 * 访问地址
	 */
	private java.lang.String url;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;
	/**
	 * 描述
	 */
	private java.lang.String resDesc;
	/**
	 * 是否叶子
	 */
	private java.lang.Boolean isLeaf;
	/**
	 * 删除状态(0：未删除；1：已删除)
	 */
	private java.lang.Boolean isDelete;
	/**
	 * CA权限(0：不需要；1：必须验证CA)
	 */
	private java.lang.Boolean caFlag;

	/**
	 * 父资源id
	 */
	private SysResource parentSysResource;

	public SysResource() {
	}

	public SysResource(java.lang.String id) {
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

	@Column(name = "RES_NO")
	public java.lang.String getResNo() {
		return this.resNo;
	}

	public void setResNo(java.lang.String value) {
		this.resNo = value;
	}

	@Column(name = "RES_NAME")
	public java.lang.String getResName() {
		return this.resName;
	}

	public void setResName(java.lang.String value) {
		this.resName = value;
	}

	@Column(name = "DISPLAY_NAME")
	public java.lang.String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(java.lang.String value) {
		this.displayName = value;
	}

	@Column(name = "TYPE")
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String value) {
		this.type = value;
	}

	@Column(name = "URL")
	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}

	@Column(name = "ORDER_NO")
	public java.lang.Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(java.lang.Integer value) {
		this.orderNo = value;
	}

	@Column(name = "RES_DESC")
	public java.lang.String getResDesc() {
		return this.resDesc;
	}

	public void setResDesc(java.lang.String value) {
		this.resDesc = value;
	}

	@Column(name = "IS_LEAF")
	public java.lang.Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(java.lang.Boolean value) {
		this.isLeaf = value;
	}

	@Column(name = "IS_DELETE")
	public java.lang.Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(java.lang.Boolean value) {
		this.isDelete = value;
	}

	@Column(name = "CA_FLAG")
	public java.lang.Boolean getCaFlag() {
		return caFlag;
	}

	public void setCaFlag(java.lang.Boolean caFlag) {
		this.caFlag = caFlag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "PARENT_RES_ID") })
	public SysResource getParentSysResource() {
		return parentSysResource;
	}

	public void setParentSysResource(SysResource parentSysResource) {
		this.parentSysResource = parentSysResource;
	}

	private java.lang.String typeCN;

	@Transient
	public java.lang.String getTypeCN() {
		return SysMngContstant.getSysMngResourceTypeCN(type);
	}

	public void setTypeCN(java.lang.String typeCN) {
		this.typeCN = typeCN;
	}

}
