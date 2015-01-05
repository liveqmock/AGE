package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysTempStep;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysTempStepService {

	public void saveOrUpdate(SysTempStep sysTempStep);

	public SysTempStep get(String id);

	public void delete(String id);

	public Page<SysTempStep> findSysTempSteps(PageRequest pageRequest, SysTempStep sysTempStep);

	public void deleteSysSteps(List<String> list);

	public Page<SysTempStep> findSysTempStepsToFlow(PageRequest pageRequest,
			SysTempStep sysTempStep,String flowID);

	
}
