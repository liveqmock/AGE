package com.age.back.sysmng.service;

import com.age.back.sysmng.model.SysOrgUser;

public interface ISysOrgUserService {

	public void saveOrUpdate(SysOrgUser sysOrgUser);

	public SysOrgUser get(String id);

	public void delete(String id);
}
