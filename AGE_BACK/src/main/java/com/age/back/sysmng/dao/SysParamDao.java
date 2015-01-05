package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysParam;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysParamDao extends HibernateDao<SysParam, java.lang.String> {

	public boolean isExistParamCode(SysParam sysParam, boolean updateFlag) {
		String hql = "";
		if (updateFlag) {//更新数据时判断
			hql = "from SysParam where isDelete=0 and paramCode is not null and paramCode=:paramCode and id <>'" + sysParam.getId() + "'";
		} else {//新增数据时判断
			hql = "from SysParam where isDelete=0 and paramCode is not null and paramCode=:paramCode";
		}
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("paramCode", sysParam.getParamCode());
		return !find(hql, values).isEmpty();
	}

	public void deleteSysParams(List<String> ids) {
		String hql = "update SysParam set isDelete=1 where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	public Page<SysParam> findSysParams(PageRequest pageRequest, SysParam sysParam) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysParam where isDelete=0");
		if (sysParam != null) {
			if (StringUtils.isNotBlank(sysParam.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysParam.getId());
			}
			if (StringUtils.isNotBlank(sysParam.getParamName())) {
				sb.append(" and paramName=:paramName");
				values.put("paramName", sysParam.getParamName());
			}
			if (StringUtils.isNotBlank(sysParam.getParamCode())) {
				sb.append(" and paramCode=:paramCode");
				values.put("paramCode", sysParam.getParamCode());
			}
			if (StringUtils.isNotBlank(sysParam.getParamValue())) {
				sb.append(" and paramValue=:paramValue");
				values.put("paramValue", sysParam.getParamValue());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysParam";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
