package com.age.back.sysmng.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysTempFlowDao;
import com.age.back.sysmng.dao.SysTempFlowStepDao;
import com.age.back.sysmng.model.SysTempFlow;
import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.back.sysmng.service.ISysTempFlowService;
import com.age.back.utils.UserUtils;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysTempFlowService implements ISysTempFlowService {
	@Autowired
	private SysTempFlowDao sysTempFlowDao;
	@Autowired
	private SysTempFlowStepDao sysTempFlowStepDao;

	public void saveOrUpdate(SysTempFlow sysTempFlowOV) {
		SysTempFlow sysTempFlow = null;

		if (StringUtils.isBlank(sysTempFlowOV.getId())) {
			sysTempFlow = new SysTempFlow();
			sysTempFlow.setId(IdUtils.uuid32());
			int orderNo = sysTempFlowDao.findMaxOrderNo();
			sysTempFlowOV.setOrderNo(new BigDecimal(orderNo));
		} else {
			sysTempFlow = get(sysTempFlowOV.getId());
		}

		sysTempFlow.setFlowNo(sysTempFlowOV.getFlowNo());
		sysTempFlow.setName(sysTempFlowOV.getName());
		sysTempFlow.setDisplayName(sysTempFlowOV.getDisplayName());
		sysTempFlow.setRemark(sysTempFlowOV.getRemark());
		sysTempFlow.setOwnerId(UserUtils.getCurrentUser().getId());
		sysTempFlow.setOrderNo(sysTempFlowOV.getOrderNo());

		sysTempFlow.setState(sysTempFlowOV.getState());//sysTempFlow.setState("1");
		sysTempFlowDao.saveOrUpdate(sysTempFlow);
	}

	public SysTempFlow get(String id) {
		return sysTempFlowDao.get(id);
	}

	public void delete(String id) {
		sysTempFlowDao.delete(id);
	}

	public Page<SysTempFlow> findSysTempFlows(PageRequest pageRequest, SysTempFlow sysTempFlow) {
		return sysTempFlowDao.findSysTempFlows(pageRequest, sysTempFlow);
	}

	@Override
	public void deleteSysTemps(List<String> list) {
		List<SysTempFlowStep> SysTempFlowSteps = sysTempFlowStepDao.findSysTempFlowStepByflowId(list);
		if (SysTempFlowSteps != null && SysTempFlowSteps.size() > 0) {
			throw new ServiceException("该流程模版已设置环节，不能删除！");
		}
		sysTempFlowDao.deleteSysTemps(list);

	}
}
