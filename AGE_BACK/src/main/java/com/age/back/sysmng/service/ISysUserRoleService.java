package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.model.SysUserRole;

public interface ISysUserRoleService {

	public void saveOrUpdate(SysUserRole sysUserRole);

	public SysUserRole get(String id);

	public void delete(String id);

	public void saveSysUserRoles(String sysUserId, List<String> sysRoleIds);

	public List<SysRole> findSysRolesBySysUserId(String sysUserId);
}
