package com.age.back.sysmng.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysTempFlow;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysTempFlowDao extends HibernateDao<SysTempFlow, java.lang.String> {

	public Page<SysTempFlow> findSysTempFlows(PageRequest pageRequest, SysTempFlow sysTempFlow) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempFlow where 1=1");
		if (sysTempFlow != null) {
			if (StringUtils.isNotBlank(sysTempFlow.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempFlow.getId());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getFlowNo())) {
				sb.append(" and flowNo=:flowNo");
				values.put("flowNo", sysTempFlow.getFlowNo());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getName())) {
				sb.append(" and name like :name");
				values.put("name", "%" + sysTempFlow.getName() + "%");
			}
			if (StringUtils.isNotBlank(sysTempFlow.getDisplayName())) {
				sb.append(" and displayName=:displayName");
				values.put("displayName", sysTempFlow.getDisplayName());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getRemark())) {
				sb.append(" and remark=:remark");
				values.put("remark", sysTempFlow.getRemark());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getOwnerId())) {
				sb.append(" and ownerId=:ownerId");
				values.put("ownerId", sysTempFlow.getOwnerId());
			}
		}
		sb.append(" order by orderNo ");
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysTempFlow> findSysTempFlows(SysTempFlow sysTempFlow) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempFlow where 1=1");
		if (sysTempFlow != null) {
			if (StringUtils.isNotBlank(sysTempFlow.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempFlow.getId());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getFlowNo())) {
				sb.append(" and flowNo=:flowNo");
				values.put("flowNo", sysTempFlow.getFlowNo());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getName())) {
				sb.append(" and name=:name");
				values.put("name", sysTempFlow.getName());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getDisplayName())) {
				sb.append(" and displayName=:displayName");
				values.put("displayName", sysTempFlow.getDisplayName());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getRemark())) {
				sb.append(" and remark=:remark");
				values.put("remark", sysTempFlow.getRemark());
			}
			if (StringUtils.isNotBlank(sysTempFlow.getOwnerId())) {
				sb.append(" and ownerId=:ownerId");
				values.put("ownerId", sysTempFlow.getOwnerId());
			}
		}
		return find(sb.toString(), values);
	}

	public void deleteSysTemps(List<String> ids) {
		String hql = "delete from SysTempFlow where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysTempFlow";
		BigDecimal temp = findUnique(hql, new HashMap<String, String>());
		if (temp == null) {
			return 5;
		} else {
			Integer maxOrderNo = temp.intValue();
			return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
		}
	}
}
