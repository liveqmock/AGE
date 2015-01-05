package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysParam;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysParamService {

	public void saveOrUpdate(SysParam sysParam);

	public SysParam get(String id);

	public void delete(String id);

	public Page<SysParam> findSysParams(PageRequest pageRequest, SysParam sysParam);

	public boolean isExistParamCode(SysParam sysParam, boolean updateFlag);

	public void deleteSysParams(List<String> ids);
}
