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
 * SysTempFlowStep
 */
@Entity
@Table(name = "SYS_TEMP_FLOW_STEP")
public class SysTempFlowStep implements java.io.Serializable {

	private static final long serialVersionUID = 2251391836844423L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 顺序号
	 */
	private java.math.BigDecimal orderNo;

	/**
	 * 所属流程环节模版id
	 */
	private SysTempStep sysTempStep;
	/**
	 * 所属流程模版id
	 */
	private SysTempFlow sysTempFlow;

	public SysTempFlowStep() {
	}

	public SysTempFlowStep(java.lang.String id) {
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

	@Column(name = "ORDER_NO")
	public java.math.BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(java.math.BigDecimal value) {
		this.orderNo = value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "TEMP_FLOW_STEP_ID") })
	public SysTempStep getSysTempStep() {
		return sysTempStep;
	}

	public void setSysTempStep(SysTempStep sysTempStep) {
		this.sysTempStep = sysTempStep;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "TEMP_FLOW_ID") })
	public SysTempFlow getSysTempFlow() {
		return sysTempFlow;
	}

	public void setSysTempFlow(SysTempFlow sysTempFlow) {
		this.sysTempFlow = sysTempFlow;
	}

}
