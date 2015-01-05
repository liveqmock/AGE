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
 * SysBulletinFile
 */
@Entity
@Table(name = "SYS_BULLETIN_FILE")
public class SysBulletinFile implements java.io.Serializable {

	private static final long serialVersionUID = 2398815825052794L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 材料名称
	 */
	private java.lang.String fileName;
	/**
	 * 文件地址
	 */
	private java.lang.String fileUrl;
	/**
	 * 记录创建时间
	 */
	private java.lang.String recCreateTime;
	/**
	 * 顺序号
	 */
	private java.lang.Integer orderNo;

	/**
	 * 公告ID
	 */
	private SysBulletin sysBulletin;

	public SysBulletinFile() {
	}

	public SysBulletinFile(java.lang.String id) {
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

	@Column(name = "FILE_NAME")
	public java.lang.String getFileName() {
		return this.fileName;
	}

	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}

	@Column(name = "FILE_URL")
	public java.lang.String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(java.lang.String value) {
		this.fileUrl = value;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( { @JoinColumn(name = "BULLETIN_ID") })
	public SysBulletin getSysBulletin() {
		return sysBulletin;
	}

	public void setSysBulletin(SysBulletin sysBulletin) {
		this.sysBulletin = sysBulletin;
	}

}
