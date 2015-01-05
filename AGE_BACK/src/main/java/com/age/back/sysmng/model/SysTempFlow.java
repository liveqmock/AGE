package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysTempFlow
 */
@Entity
@Table(name = "SYS_TEMP_FLOW")
public class SysTempFlow implements java.io.Serializable {

	private static final long serialVersionUID = 2202980954998833L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 编号
	 */
	private java.lang.String flowNo;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 显示名称
	 */
	private java.lang.String displayName;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 拥有者
	 */
	private java.lang.String ownerId;
	/**
	 * 顺序号
	 */
	private java.math.BigDecimal orderNo;
	/**
	 * 状态(0：禁用；1：可用)
	 */
	private java.lang.String state;

	public SysTempFlow() {
	}

	public SysTempFlow(java.lang.String id) {
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

	@Column(name = "FLOW_NO")
	public java.lang.String getFlowNo() {
		return this.flowNo;
	}

	public void setFlowNo(java.lang.String value) {
		this.flowNo = value;
	}

	@Column(name = "NAME")
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "DISPLAY_NAME")
	public java.lang.String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(java.lang.String value) {
		this.displayName = value;
	}

	@Column(name = "REMARK")
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	@Column(name = "OWNER_ID")
	public java.lang.String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(java.lang.String value) {
		this.ownerId = value;
	}

	@Column(name = "ORDER_NO")
	public java.math.BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(java.math.BigDecimal value) {
		this.orderNo = value;
	}

	@Column(name = "STATE")
	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}
}
