package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.model.SysRoleResource;

public interface ISysRoleResourceService {

	public void saveOrUpdate(SysRoleResource sysRoleResource);

	public SysRoleResource get(String id);

	public void delete(String id);

	public void saveSysRoleResources(String sysRoleId, List<String> sysResourceIds);

	public List<SysResource> findSysResourcesBySysRoleId(String sysRoleId);
}
