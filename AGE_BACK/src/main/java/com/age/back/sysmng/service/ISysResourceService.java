package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysResource;

public interface ISysResourceService {

	public void saveOrUpdate(SysResource sysResourceVO, String parentId);

	public void saveOrUpdate(SysResource sysResource);

	public SysResource get(String id);

	public void delete(String id);

	public List<SysResource> findSysResourcesByParentId(String parentId);

	public void deleteSysResources(List<String> ids);

	public List<SysResource> findSysResources();

	public List<String> findSysResourcesByUsrAccount(String account);

	public List<Object> findCurrentUserMenus();

	public List<SysResource> findSysResourcesByISCA();

	/**
	 * 当前用户菜单 使用JSONArray方式
	 * @return
	 */
	public List<?> findCurrentUserMenus2();
}
