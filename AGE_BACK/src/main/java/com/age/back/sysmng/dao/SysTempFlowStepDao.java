package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysTempFlowStepDao extends HibernateDao<SysTempFlowStep, java.lang.String> {

	public Page<SysTempFlowStep> findSysTempFlowSteps(PageRequest pageRequest, SysTempFlowStep sysTempFlowStep) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempFlowStep where 1=1");
		if (sysTempFlowStep != null) {
			if (StringUtils.isNotBlank(sysTempFlowStep.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempFlowStep.getId());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysTempFlowStep> findSysTempFlowSteps(SysTempFlowStep sysTempFlowStep) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempFlowStep where 1=1");
		if (sysTempFlowStep != null) {
			if (StringUtils.isNotBlank(sysTempFlowStep.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempFlowStep.getId());
			}
		}
		return find(sb.toString(), values);
	}

	/**
	 * 顺序号递增排序
	 */
	public List<SysTempFlowStep> findSysTempFlowSteps(SysTempFlowStep sysTempFlowStep, boolean isOrder) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysTempFlowStep where 1=1");
		if (sysTempFlowStep != null) {
			if (StringUtils.isNotBlank(sysTempFlowStep.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysTempFlowStep.getId());
			}
			if (sysTempFlowStep.getSysTempFlow() != null) {
				sb.append(" and sysTempFlow=:sysTempFlow ");
				values.put("sysTempFlow", sysTempFlowStep.getSysTempFlow());
			}
			if (sysTempFlowStep.getSysTempStep() != null) {
				sb.append(" and sysTempStep=:sysTempStep ");
				values.put("sysTempStep", sysTempFlowStep.getSysTempStep());
			}
			if (isOrder) {
				sb.append(" order by orderNo asc");
			}
		}
		return find(sb.toString(), values);
	}

	public void remove(List<String> idsList, String sysTempFlowID) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "delete SysTempFlowStep where id in (:ids) and sysTempFlow.id=:sysTempFlowID ";
		values.put("ids", idsList);
		values.put("sysTempFlowID", sysTempFlowID);
		batchExecute(hql, values);
	}

	/**
	 * 根据环节id查找环节流程表
	 * @param idsList
	 * @return
	 */
	public List<SysTempFlowStep> findSysTempFlowStepByStepId(List<String> idsList) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = " from SysTempFlowStep where sysTempStep.id in (:ids) ";
		values.put("ids", idsList);
		return find(hql, values);
	}

	/**
	 * 根据流程id查找环节流程表
	 * @param idsList
	 * @return
	 */
	public List<SysTempFlowStep> findSysTempFlowStepByflowId(List<String> idsList) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = " from SysTempFlowStep where sysTempFlow.id in (:ids) ";
		values.put("ids", idsList);
		return find(hql, values);
	}
}
