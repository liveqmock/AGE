package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysTempStep
 */
@Entity
@Table(name = "SYS_TEMP_STEP")
public class SysTempStep implements java.io.Serializable {

	private static final long serialVersionUID = 2534211470180247L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String stepName;
	/**
	 * 编号
	 */
	private java.lang.String stepNo;
	/**
	 * 显示名称
	 */
	private java.lang.String displayName;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 操作界面
	 */
	private java.lang.String operateUrl;
	/**
	 * 拥有者名称
	 */
	private java.lang.String ownerName;
	/**
	 * 拥有者类型 0=角色；1=部门；2:=人员
	 */
	private java.lang.String ownerType;
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

	public SysTempStep() {
	}

	public SysTempStep(java.lang.String id) {
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

	@Column(name = "STEP_NAME")
	public java.lang.String getStepName() {
		return this.stepName;
	}

	public void setStepName(java.lang.String value) {
		this.stepName = value;
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

	@Column(name = "OPERATE_URL")
	public java.lang.String getOperateUrl() {
		return this.operateUrl;
	}

	public void setOperateUrl(java.lang.String value) {
		this.operateUrl = value;
	}

	@Column(name = "OWNER_NAME")
	public java.lang.String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(java.lang.String value) {
		this.ownerName = value;
	}

	@Column(name = "OWNER_TYPE")
	public java.lang.String getOwnerType() {
		return this.ownerType;
	}

	public void setOwnerType(java.lang.String value) {
		this.ownerType = value;
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

	@Column(name = "STEP_NO")
	public java.lang.String getStepNo() {
		return stepNo;
	}

	public void setStepNo(java.lang.String stepNo) {
		this.stepNo = stepNo;
	}

	@Column(name = "STATE")
	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

}
