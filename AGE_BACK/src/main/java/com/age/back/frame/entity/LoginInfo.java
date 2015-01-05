package com.age.back.frame.entity;

/***
 * 
* @Description: 用于接受ca参数 
* @author 郑永标
* @date 2014年5月5日 上午10:35:32
 */
public class LoginInfo {
	private String ip;
	private int port;
	private String cert;
	private String pr;
	private String data;
	private String passWord;
	private String userName;
	private String rd;
	private String loginType;
	private String ca;
	private String caInfo;

	public String getCaInfo() {
		return caInfo;
	}

	public void setCaInfo(String caInfo) {
		this.caInfo = caInfo;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
