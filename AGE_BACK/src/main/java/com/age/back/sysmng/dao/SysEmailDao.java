package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysEmail;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;
import com.age.core.utils.DateUtils;

@Repository
public class SysEmailDao extends HibernateDao<SysEmail, java.lang.String> {

	public int updateSendFlag(String sendFlag, String id) {
		String hql = "update SysEmail set sendFlag=? ,sendTime=? where id=?";
		return batchExecute(hql, sendFlag, DateUtils.currentDatetime(), id);
	}

	public Page<SysEmail> findSysEmails(PageRequest pageRequest, SysEmail sysEmail) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysEmail where 1=1");
		if (sysEmail != null) {
			if (StringUtils.isNotBlank(sysEmail.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysEmail.getId());
			}
			if (StringUtils.isNotBlank(sysEmail.getSenderAddress())) {
				sb.append(" and senderAddress=:senderAddress");
				values.put("senderAddress", sysEmail.getSenderAddress());
			}
			if (StringUtils.isNotBlank(sysEmail.getSenderName())) {
				sb.append(" and senderName=:senderName");
				values.put("senderName", sysEmail.getSenderName());
			}
			if (StringUtils.isNotBlank(sysEmail.getReceiverAddress())) {
				sb.append(" and receiverAddress=:receiverAddress");
				values.put("receiverAddress", sysEmail.getReceiverAddress());
			}
			if (StringUtils.isNotBlank(sysEmail.getReceiverName())) {
				sb.append(" and receiverName=:receiverName");
				values.put("receiverName", sysEmail.getReceiverName());
			}
			if (StringUtils.isNotBlank(sysEmail.getSubject())) {
				sb.append(" and subject=:subject");
				values.put("subject", sysEmail.getSubject());
			}
			if (StringUtils.isNotBlank(sysEmail.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysEmail.getContent());
			}
			if (StringUtils.isNotBlank(sysEmail.getCreateTime())) {
				sb.append(" and createTime=:createTime");
				values.put("createTime", sysEmail.getCreateTime());
			}
			if (StringUtils.isNotBlank(sysEmail.getSendTime())) {
				sb.append(" and sendTime=:sendTime");
				values.put("sendTime", sysEmail.getSendTime());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysEmail> findSysEmails(SysEmail sysEmail) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysEmail where 1=1");
		if (sysEmail != null) {
			if (StringUtils.isNotBlank(sysEmail.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysEmail.getId());
			}
			if (StringUtils.isNotBlank(sysEmail.getSenderAddress())) {
				sb.append(" and senderAddress=:senderAddress");
				values.put("senderAddress", sysEmail.getSenderAddress());
			}
			if (StringUtils.isNotBlank(sysEmail.getSenderName())) {
				sb.append(" and senderName=:senderName");
				values.put("senderName", sysEmail.getSenderName());
			}
			if (StringUtils.isNotBlank(sysEmail.getReceiverAddress())) {
				sb.append(" and receiverAddress=:receiverAddress");
				values.put("receiverAddress", sysEmail.getReceiverAddress());
			}
			if (StringUtils.isNotBlank(sysEmail.getReceiverName())) {
				sb.append(" and receiverName=:receiverName");
				values.put("receiverName", sysEmail.getReceiverName());
			}
			if (StringUtils.isNotBlank(sysEmail.getSubject())) {
				sb.append(" and subject=:subject");
				values.put("subject", sysEmail.getSubject());
			}
			if (StringUtils.isNotBlank(sysEmail.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysEmail.getContent());
			}
			if (StringUtils.isNotBlank(sysEmail.getCreateTime())) {
				sb.append(" and createTime=:createTime");
				values.put("createTime", sysEmail.getCreateTime());
			}
			if (StringUtils.isNotBlank(sysEmail.getSendTime())) {
				sb.append(" and sendTime=:sendTime");
				values.put("sendTime", sysEmail.getSendTime());
			}
		}
		return find(sb.toString(), values);
	}
}
