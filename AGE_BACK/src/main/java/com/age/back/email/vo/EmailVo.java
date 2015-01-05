package com.age.back.email.vo;

import java.io.Serializable;
import java.util.Enumeration;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

public class EmailVo implements ObjectMessage, Serializable {
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

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(java.lang.String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public java.lang.String getSenderName() {
		return senderName;
	}

	public void setSenderName(java.lang.String senderName) {
		this.senderName = senderName;
	}

	public java.lang.String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(java.lang.String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public java.lang.String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(java.lang.String receiverName) {
		this.receiverName = receiverName;
	}

	public java.lang.String getSubject() {
		return subject;
	}

	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.lang.String sendTime) {
		this.sendTime = sendTime;
	}

	public java.lang.String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(java.lang.String sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Override
	public Serializable getObject() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(Serializable arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void acknowledge() throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBody() throws JMSException {
		// TODO Auto-generated method stub
	}

	@Override
	public void clearProperties() throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getBooleanProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByteProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDoubleProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloatProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSCorrelationID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getJMSDeliveryMode() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Destination getJMSDestination() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getJMSExpiration() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSMessageID() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getJMSPriority() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getJMSRedelivered() throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Destination getJMSReplyTo() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getJMSTimestamp() throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getJMSType() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLongProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getObjectProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getPropertyNames() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getShortProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStringProperty(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean propertyExists(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBooleanProperty(String arg0, boolean arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setByteProperty(String arg0, byte arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDoubleProperty(String arg0, double arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFloatProperty(String arg0, float arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIntProperty(String arg0, int arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSCorrelationID(String arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSDeliveryMode(int arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSDestination(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSExpiration(long arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSMessageID(String arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSPriority(int arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSRedelivered(boolean arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSReplyTo(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSTimestamp(long arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setJMSType(String arg0) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLongProperty(String arg0, long arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObjectProperty(String arg0, Object arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShortProperty(String arg0, short arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStringProperty(String arg0, String arg1) throws JMSException {
		// TODO Auto-generated method stub

	}

}
