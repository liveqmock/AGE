package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysOrg;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysOrgService {

	public void saveOrUpdate(SysOrg sysOrgVO, String parentId);

	public void saveOrUpdate(SysOrg sysOrg);

	public SysOrg get(String id);

	public void delete(String id);

	public Page<SysOrg> findSysOrgs(PageRequest pageRequest, SysOrg sysOrg);

	public List<SysOrg> findSysOrgsByParentId(String parentId);

	public void deleteSysOrgs(List<String> ids);
}
