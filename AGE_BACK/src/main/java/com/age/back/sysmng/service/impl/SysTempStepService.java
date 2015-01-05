package com.age.back.sysmng.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysTempFlowStepDao;
import com.age.back.sysmng.dao.SysTempStepDao;
import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.back.sysmng.model.SysTempStep;
import com.age.back.sysmng.service.ISysTempStepService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysTempStepService implements ISysTempStepService {
	@Autowired
	private SysTempStepDao sysTempStepDao;
	@Autowired
	private SysTempFlowStepDao sysTempFlowStepDao;

	public void saveOrUpdate(SysTempStep sysTempStepOV) {
		SysTempStep sysTempStep = null;

		if (StringUtils.isBlank(sysTempStepOV.getId())) {
			sysTempStep = new SysTempStep();
			sysTempStep.setId(IdUtils.uuid32());
			int orderNo = sysTempStepDao.findMaxOrderNo();
			sysTempStepOV.setOrderNo(new BigDecimal(orderNo));
		} else {
			sysTempStep = get(sysTempStepOV.getId());
		}
		sysTempStep.setStepName(sysTempStepOV.getStepName());
		sysTempStep.setDisplayName(sysTempStepOV.getDisplayName());
		sysTempStep.setRemark(sysTempStepOV.getRemark());
		sysTempStep.setOperateUrl(sysTempStepOV.getOperateUrl());
		sysTempStep.setOwnerName(sysTempStepOV.getOwnerName());
		sysTempStep.setOwnerType(sysTempStepOV.getOwnerType());
		sysTempStep.setOrderNo(sysTempStepOV.getOrderNo());

		sysTempStep.setOwnerId(sysTempStepOV.getOwnerId());
		sysTempStep.setStepNo(sysTempStepOV.getStepNo());

		sysTempStep.setState(sysTempStepOV.getState());
		sysTempStepDao.saveOrUpdate(sysTempStep);
	}

	public SysTempStep get(String id) {
		return sysTempStepDao.get(id);
	}

	public void delete(String id) {
		sysTempStepDao.delete(id);
	}

	public Page<SysTempStep> findSysTempSteps(PageRequest pageRequest, SysTempStep sysTempStep) {
		return sysTempStepDao.findSysTempSteps(pageRequest, sysTempStep);
	}

	@Override
	public void deleteSysSteps(List<String> list) {
		List<SysTempFlowStep> SysTempFlowSteps = sysTempFlowStepDao.findSysTempFlowStepByStepId(list);
		if (SysTempFlowSteps != null && SysTempFlowSteps.size() > 0) {
			throw new ServiceException("该环节被流程模版使用，不能删除！");
		}
		sysTempStepDao.deleteSysSteps(list);

	}

	@Override
	public Page<SysTempStep> findSysTempStepsToFlow(PageRequest pageRequest, SysTempStep sysTempStep, String flowID) {
		return sysTempStepDao.findSysTempStepsToFlow(pageRequest, sysTempStep, flowID);

	}

}
