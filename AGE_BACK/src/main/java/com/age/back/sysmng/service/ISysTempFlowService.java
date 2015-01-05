package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysTempFlow;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysTempFlowService {

	public void saveOrUpdate(SysTempFlow sysTempFlow);

	public SysTempFlow get(String id);

	public void delete(String id);

	public Page<SysTempFlow> findSysTempFlows(PageRequest pageRequest, SysTempFlow sysTempFlow);

	public void deleteSysTemps(List<String> list);
}
