package com.age.back.sysmng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysRoleResourceDao;
import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.model.SysRoleResource;
import com.age.back.sysmng.service.ISysRoleResourceService;
import com.age.core.utils.IdUtils;

@Service
public class SysRoleResourceService implements ISysRoleResourceService {
	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;

	public void saveOrUpdate(SysRoleResource sysRoleResource) {
		sysRoleResourceDao.saveOrUpdate(sysRoleResource);
	}

	public SysRoleResource get(String id) {
		return sysRoleResourceDao.get(id);
	}

	public void delete(String id) {
		sysRoleResourceDao.delete(id);
	}

	public List<SysResource> findSysResourcesBySysRoleId(String sysRoleId) {
		return sysRoleResourceDao.findSysResourcesBySysRoleId(sysRoleId);
	}

	public void saveSysRoleResources(String sysRoleId, List<String> sysResourceIds) {
		sysRoleResourceDao.deleteBySysRoleId(sysRoleId);

		for (String resId : sysResourceIds) {
			SysRoleResource sysRoleResource = new SysRoleResource();
			sysRoleResource.setId(IdUtils.uuid32());
			sysRoleResource.setSysRole(new SysRole(sysRoleId));
			sysRoleResource.setSysResource(new SysResource(resId));
			sysRoleResourceDao.saveOrUpdate(sysRoleResource);
		}
	}

}
