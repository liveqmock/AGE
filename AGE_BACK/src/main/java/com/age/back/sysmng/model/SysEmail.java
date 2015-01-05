package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysEmail
 */
@Entity
@Table(name = "SYS_EMAIL")
public class SysEmail implements java.io.Serializable {

	private static final long serialVersionUID = 2489986314413022L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 邮件发送人地址
	 */
	private java.lang.String senderAddress;
	/**
	 * 邮件发送人名称
	 */
	private java.lang.String senderName;
	/**
	 * 邮件接收人地址
	 */
	private java.lang.String receiverAddress;
	/**
	 * 邮件接收人名称
	 */
	private java.lang.String receiverName;
	/**
	 * 邮件主题
	 */
	private java.lang.String subject;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 * 邮件创建时间
	 */
	private java.lang.String createTime;
	/**
	 * 邮件发送时间
	 */
	private java.lang.String sendTime;
	/**
	 * 是否成功发送：0=初始；1=成功；2=失败
	 */
	private java.lang.String sendFlag;

	public SysEmail() {
	}

	public SysEmail(java.lang.String id) {
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

	@Column(name = "SENDER_ADDRESS")
	public java.lang.String getSenderAddress() {
		return this.senderAddress;
	}

	public void setSenderAddress(java.lang.String value) {
		this.senderAddress = value;
	}

	@Column(name = "SENDER_NAME")
	public java.lang.String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(java.lang.String value) {
		this.senderName = value;
	}

	@Column(name = "RECEIVER_ADDRESS")
	public java.lang.String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(java.lang.String value) {
		this.receiverAddress = value;
	}

	@Column(name = "RECEIVER_NAME")
	public java.lang.String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(java.lang.String value) {
		this.receiverName = value;
	}

	@Column(name = "SUBJECT")
	public java.lang.String getSubject() {
		return this.subject;
	}

	public void setSubject(java.lang.String value) {
		this.subject = value;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String value) {
		this.content = value;
	}

	@Column(name = "CREATE_TIME")
	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}

	@Column(name = "SEND_TIME")
	public java.lang.String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(java.lang.String value) {
		this.sendTime = value;
	}

	@Column(name = "SEND_FLAG")
	public java.lang.String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(java.lang.String sendFlag) {
		this.sendFlag = sendFlag;
	}

}
