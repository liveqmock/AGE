package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysRoleDao;
import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.service.ISysRoleService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysRoleService implements ISysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	public void saveOrUpdate(SysRole sysRoleVO) {
		SysRole sysRole = null;
		boolean updateFlag = false;
		if (StringUtils.isBlank(sysRoleVO.getId())) {
			sysRole = new SysRole();
			sysRole.setId(IdUtils.uuid32());
			sysRole.setIsDelete(false);
			int orderNo = sysRoleDao.findMaxOrderNo();
			sysRoleVO.setOrderNo(orderNo);
		} else {
			sysRole = get(sysRoleVO.getId());
			updateFlag = true;
		}

		sysRole.setRoleNo(sysRoleVO.getRoleNo());
		sysRole.setRoleName(sysRoleVO.getRoleName());
		sysRole.setRoleDesc(sysRoleVO.getRoleDesc());
		sysRole.setOrderNo(sysRoleVO.getOrderNo());
		if (isExistRoleNo(sysRoleVO, updateFlag)) {
			throw new ServiceException("该角色代码已经存在！");
		} else {
			sysRoleDao.saveOrUpdate(sysRole);
		}
	}

	public SysRole get(String id) {
		return sysRoleDao.get(id);
	}

	public void delete(String id) {
		sysRoleDao.delete(id);
	}

	public Page<SysRole> findSysRoles(PageRequest pageRequest, SysRole sysRole) {
		return sysRoleDao.findSysRoles(pageRequest, sysRole);
	}

	public void deleteSysRoles(List<String> ids) {
		sysRoleDao.deleteSysRoles(ids);
	}

	public boolean isExistRoleNo(SysRole sysRole, boolean updateFlag) {
		return sysRoleDao.isExistRoleNo(sysRole, updateFlag);
	}

	public List<SysRole> findSysRoles() {
		return sysRoleDao.findSysRoles();
	}

	@Override
	public List findSysRolesByUsrAccount(String account) {
		// TODO Auto-generated method stub
		return sysRoleDao.findSysRolesByUsrAccount(account);
	}
}
