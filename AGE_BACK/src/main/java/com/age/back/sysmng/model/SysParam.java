package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统参数
 */
@Entity
@Table(name = "sys_param")
public class SysParam implements java.io.Serializable {

	private static final long serialVersionUID = 2527523504697993L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String paramName;
	/**
	 * 代码
	 */
	private java.lang.String paramCode;
	/**
	 * 参数值
	 */
	private java.lang.String paramValue;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;
	/**
	 * 删除状态(0：未删除；1：已删除)
	 */
	private java.lang.Boolean isDelete;

	public SysParam() {
	}

	public SysParam(java.lang.String id) {
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

	@Column(name = "PARAM_NAME")
	public java.lang.String getParamName() {
		return this.paramName;
	}

	public void setParamName(java.lang.String value) {
		this.paramName = value;
	}

	@Column(name = "PARAM_CODE")
	public java.lang.String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(java.lang.String value) {
		this.paramCode = value;
	}

	@Column(name = "PARAM_VALUE")
	public java.lang.String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(java.lang.String value) {
		this.paramValue = value;
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
