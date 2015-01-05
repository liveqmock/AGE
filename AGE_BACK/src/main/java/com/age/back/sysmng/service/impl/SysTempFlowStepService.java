package com.age.back.sysmng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.StringUtils;

import com.age.back.sysmng.dao.SysTempFlowStepDao;
import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.back.sysmng.service.ISysTempFlowStepService;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Service
public class SysTempFlowStepService implements ISysTempFlowStepService {
	@Autowired
	private SysTempFlowStepDao sysTempFlowStepDao;

	public void saveOrUpdate(SysTempFlowStep sysTempFlowStep) {
		sysTempFlowStepDao.saveOrUpdate(sysTempFlowStep);
	}

	public SysTempFlowStep get(String id) {
		return sysTempFlowStepDao.get(id);
	}

	public void delete(String id) {
		sysTempFlowStepDao.delete(id);
	}

	public Page<SysTempFlowStep> findSysTempFlowSteps(PageRequest pageRequest, SysTempFlowStep sysTempFlowStep) {
		return sysTempFlowStepDao.findSysTempFlowSteps(pageRequest, sysTempFlowStep);
	}

	/**
	 * 删除
	 */
	@Override
	public void remove(String ids, String sysTempFlowID) {
		List<String> idsList = StrToObjUtils.toList(ids, ",");
		sysTempFlowStepDao.remove(idsList, sysTempFlowID);
	}
}
