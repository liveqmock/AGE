package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysRole;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysRoleService {

	public void saveOrUpdate(SysRole sysRole);

	public SysRole get(String id);

	public void delete(String id);

	public Page<SysRole> findSysRoles(PageRequest pageRequest, SysRole sysRole);

	public void deleteSysRoles(List<String> ids);

	public boolean isExistRoleNo(SysRole sysRole, boolean updateFlag);

	public List<SysRole> findSysRoles();

	public List findSysRolesByUsrAccount(String account);
}
