package com.age.back.sysmng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * SysBulletin
 */
@Entity
@Table(name = "SYS_BULLETIN")
public class SysBulletin implements java.io.Serializable {

	private static final long serialVersionUID = 2514008243748956L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 公告标题
	 */
	private java.lang.String title;
	/**
	 * 公告内容
	 */
	private String content;
	/**
	 * 公告发布时间
	 */
	private java.lang.String publishTime;
	/**
	 * 公告发布人
	 */
	private java.lang.String publisher;
	/**
	 * 公告状态：0=初始；1=发布；2=撤销；
	 */
	private java.lang.String status;
	/**
	 * 记录创建时间
	 */
	private java.lang.String recCreateTime;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;

	public SysBulletin() {
	}

	public SysBulletin(java.lang.String id) {
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

	@Column(name = "TITLE")
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String value) {
		this.title = value;
	}

	@Lob
    @Type(type = "org.springframework.orm.hibernate3.support.ClobStringType")
	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String value) {
		this.content = value;
	}

	@Column(name = "PUBLISH_TIME")
	public java.lang.String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(java.lang.String value) {
		this.publishTime = value;
	}

	@Column(name = "PUBLISHER")
	public java.lang.String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(java.lang.String value) {
		this.publisher = value;
	}

	@Column(name = "STATUS")
	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	@Column(name = "REC_CREATE_TIME")
	public java.lang.String getRecCreateTime() {
		return this.recCreateTime;
	}

	public void setRecCreateTime(java.lang.String value) {
		this.recCreateTime = value;
	}

	@Column(name = "ORDER_NO")
	public java.lang.Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(java.lang.Integer value) {
		this.orderNo = value;
	}

}
