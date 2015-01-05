package com.age.back.sysmng.service;

import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysTempFlowStepService {

	public void saveOrUpdate(SysTempFlowStep sysTempFlowStep);

	public SysTempFlowStep get(String id);

	public void delete(String id);

	public Page<SysTempFlowStep> findSysTempFlowSteps(PageRequest pageRequest, SysTempFlowStep sysTempFlowStep);
	/**
	 * 删除
	 * @param ids
	 * @param sysTempFlowID
	 */
	public void remove(String ids, String sysTempFlowID);
}
