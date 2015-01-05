package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysUser;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysUserService {
	public Page<SysUser> findSysUsers(PageRequest pageRequest, SysUser sysUser);

	public void create(SysUser sysUser, String orgId);

	public void saveOrUpdate(SysUser sysUser);

	public SysUser get(String id);

	public void delete(String id);

	public Page<SysUser> findSysUsersByOrgId(PageRequest pageRequest, String orgId);

	public void deleteSysUsers(List<String> ids);

	public SysUser findSysUserByUserAccount(String userAccount);

	public void save(SysUser sysUserVO, String orgId);

	public SysUser getByCa(String ca);

}
