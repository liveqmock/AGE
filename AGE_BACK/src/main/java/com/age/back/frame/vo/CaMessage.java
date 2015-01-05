package com.age.back.frame.vo;

public class CaMessage {
	/**
	 * ca是否通过
	 */
	private boolean isPass = false;
	/**
	 * 证书序列号
	 */
	private String serialNo;
	/**
	 * 证书状态
	 */
	private String status;
	/**
	 * 附带信息
	 */
	private String message;

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
