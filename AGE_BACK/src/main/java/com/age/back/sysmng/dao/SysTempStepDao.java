package com.age.back.sysmng.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysTempStep;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysTempStepDao extends HibernateDao<SysTempStep, java.lang.String> {

	public Page<SysTempStep> findSysTempSteps(PageRequest pageRequest, SysTempStep sysTempStep) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempStep where 1=1");
		if (sysTempStep != null) {
			if (StringUtils.isNotBlank(sysTempStep.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempStep.getId());
			}
			if (StringUtils.isNotBlank(sysTempStep.getStepName())) {
				sb.append(" and stepName like :stepName");
				values.put("stepName", "%" + sysTempStep.getStepName() + "%");
			}
			if (StringUtils.isNotBlank(sysTempStep.getDisplayName())) {
				sb.append(" and displayName=:displayName");
				values.put("displayName", sysTempStep.getDisplayName());
			}
			if (StringUtils.isNotBlank(sysTempStep.getRemark())) {
				sb.append(" and remark=:remark");
				values.put("remark", sysTempStep.getRemark());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOperateUrl())) {
				sb.append(" and operateUrl=:operateUrl");
				values.put("operateUrl", sysTempStep.getOperateUrl());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerName())) {
				sb.append(" and ownerName=:ownerName");
				values.put("ownerName", sysTempStep.getOwnerName());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerType())) {
				sb.append(" and ownerType=:ownerType");
				values.put("ownerType", sysTempStep.getOwnerType());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerId())) {
				sb.append(" and ownerId=:ownerId");
				values.put("ownerId", sysTempStep.getOwnerId());
			}
			if (StringUtils.isNotBlank(sysTempStep.getState())) {
				sb.append(" and state = :state");
				values.put("state", sysTempStep.getState());
			}
		}
		sb.append(" order by orderNo");
		return findPage(pageRequest, sb.toString(), values);
	}

	public Page<SysTempStep> findSysTempStepsToFlow(PageRequest pageRequest, SysTempStep sysTempStep, String flowID) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("select stfs.id as id, stfs.sysTempStep.stepName as stepName, stfs.sysTempStep.stepNo as stepNo, stfs.sysTempStep.displayName as displayName, stfs.sysTempStep.remark as remark, stfs.sysTempStep.operateUrl as operateUrl, stfs.sysTempStep.ownerName as ownerName, stfs.sysTempStep.ownerType as ownerType, stfs.sysTempStep.ownerId as ownerId, stfs.orderNo as orderNo, stfs.sysTempStep.state as state from SysTempFlowStep stfs  where 1=1  ");
		if (StringUtils.isNotBlank(flowID)) {
			sb.append(" and stfs.sysTempFlow.id=:id");
			values.put("id", flowID);
		}
		sb.append(" order by stfs.orderNo asc ");
		return findPage(pageRequest, sb.toString(), values, SysTempStep.class);
	}

	public List<SysTempStep> findSysTempSteps(SysTempStep sysTempStep) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempStep where 1=1");
		if (sysTempStep != null) {
			if (StringUtils.isNotBlank(sysTempStep.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempStep.getId());
			}
			if (StringUtils.isNotBlank(sysTempStep.getStepName())) {
				sb.append(" and stepName=:stepName");
				values.put("stepName", sysTempStep.getStepName());
			}
			if (StringUtils.isNotBlank(sysTempStep.getDisplayName())) {
				sb.append(" and displayName=:displayName");
				values.put("displayName", sysTempStep.getDisplayName());
			}
			if (StringUtils.isNotBlank(sysTempStep.getRemark())) {
				sb.append(" and remark=:remark");
				values.put("remark", sysTempStep.getRemark());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOperateUrl())) {
				sb.append(" and operateUrl=:operateUrl");
				values.put("operateUrl", sysTempStep.getOperateUrl());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerName())) {
				sb.append(" and ownerName=:ownerName");
				values.put("ownerName", sysTempStep.getOwnerName());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerType())) {
				sb.append(" and ownerType=:ownerType");
				values.put("ownerType", sysTempStep.getOwnerType());
			}
			if (StringUtils.isNotBlank(sysTempStep.getOwnerId())) {
				sb.append(" and ownerId=:ownerId");
				values.put("ownerId", sysTempStep.getOwnerId());
			}
		}
		return find(sb.toString(), values);
	}

	public void deleteSysSteps(List<String> ids) {
		String hql = "delete from SysTempStep where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysTempStep";
		BigDecimal temp = findUnique(hql, new HashMap<String, String>());
		if (temp == null) {
			return 5;
		} else {
			Integer maxOrderNo = temp.intValue();
			return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
		}
	}
}
