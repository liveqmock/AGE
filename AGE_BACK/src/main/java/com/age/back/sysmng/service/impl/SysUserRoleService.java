package com.age.back.sysmng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysUserRoleDao;
import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.model.SysUser;
import com.age.back.sysmng.model.SysUserRole;
import com.age.back.sysmng.service.ISysUserRoleService;
import com.age.core.utils.IdUtils;

@Service
public class SysUserRoleService implements ISysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	public void saveOrUpdate(SysUserRole sysUserRole) {
		sysUserRoleDao.saveOrUpdate(sysUserRole);
	}

	public SysUserRole get(String id) {
		return sysUserRoleDao.get(id);
	}

	public void delete(String id) {
		sysUserRoleDao.delete(id);
	}

	public void saveSysUserRoles(String sysUserId, List<String> sysRoleIds) {
		sysUserRoleDao.deleteBySysUserId(sysUserId);

		for (String roleId : sysRoleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setId(IdUtils.uuid32());
			sysUserRole.setSysRole(new SysRole(roleId));
			sysUserRole.setSysUser(new SysUser(sysUserId));
			sysUserRoleDao.saveOrUpdate(sysUserRole);
		}
	}

	public List<SysRole> findSysRolesBySysUserId(String sysUserId) {
		return sysUserRoleDao.findSysRolesBySysUserId(sysUserId);
	}
}
