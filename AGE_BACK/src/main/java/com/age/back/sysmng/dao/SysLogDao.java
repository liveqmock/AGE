package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysLog;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysLogDao extends HibernateDao<SysLog, java.lang.String> {

	public Page<SysLog> findSysLogs(PageRequest pageRequest, SysLog sysLog) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysLog where 1=1");
		if (sysLog != null) {
			if (StringUtils.isNotBlank(sysLog.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysLog.getId());
			}
			if (StringUtils.isNotBlank(sysLog.getUserId())) {
				sb.append(" and userId=:userId");
				values.put("userId", sysLog.getUserId());
			}
			if (StringUtils.isNotBlank(sysLog.getUserName())) {
				sb.append(" and userName=:userName");
				values.put("userName", sysLog.getUserName());
			}
			if (StringUtils.isNotBlank(sysLog.getIp())) {
				sb.append(" and ip=:ip");
				values.put("ip", sysLog.getIp());
			}
			if (StringUtils.isNotBlank(sysLog.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysLog.getContent());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

}
