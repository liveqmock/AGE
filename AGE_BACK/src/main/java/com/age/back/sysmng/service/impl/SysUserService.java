package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.common.SysMngContstant;
import com.age.back.sysmng.dao.SysOrgUserDao;
import com.age.back.sysmng.dao.SysUserDao;
import com.age.back.sysmng.model.SysOrg;
import com.age.back.sysmng.model.SysOrgUser;
import com.age.back.sysmng.model.SysUser;
import com.age.back.sysmng.service.ISysUserService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysUserService implements ISysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysOrgUserDao sysOrgUserDao;

	public void create(SysUser sysUser, String orgId) {
		if (isExistUserAccount(sysUser, false))
			throw new ServiceException("该人员账号已存在");
		sysUserDao.saveOrUpdate(sysUser);

		SysOrgUser sysOrgUser = new SysOrgUser();
		sysOrgUser.setId(IdUtils.uuid32());
		sysOrgUser.setSysOrg(new SysOrg(orgId));
		sysOrgUser.setSysUser(sysUser);
		sysOrgUserDao.saveOrUpdate(sysOrgUser);
	}

	public void saveOrUpdate(SysUser sysUser) {
		if (isExistUserAccount(sysUser, true)) {
			throw new ServiceException("该人员账号已存在");
		}
		sysUserDao.saveOrUpdate(sysUser);
	}

	public void deleteSysUsers(List<String> ids) {
		sysUserDao.deleteSysUsers(ids);
	}

	public SysUser get(String id) {
		return sysUserDao.get(id);
	}

	public void delete(String id) {
		sysUserDao.delete(id);
	}

	public Page<SysUser> findSysUsersByOrgId(PageRequest pageRequest, String orgId) {
		return sysUserDao.findSysUsersByOrgId(pageRequest, orgId);
	}

	public Page<SysUser> findSysUsers(PageRequest pageRequest, SysUser sysUser) {
		//没有被删除  可用状态为启用
		sysUser.setIsDelete(false);//删除状态(0：未删除；1：已删除)
		sysUser.setState(SysMngContstant.SYS_MNG_STATE_ENABLED);//账号状态(0：禁用；1：可用)
		return sysUserDao.findSysUsers(pageRequest, sysUser);
	}

	public boolean isExistUserAccount(SysUser sysUser, boolean updateFlag) {
		return sysUserDao.isExistUserAccount(sysUser, updateFlag);
	}

	public SysUser findSysUserByUserAccount(String userAccount) {
		return sysUserDao.findSysUserByUserAccount(userAccount);
	}

	/**
	 * 保存用户
	 */
	@Override
	public void save(SysUser sysUserVO, String orgId) {
		SysUser sysUser = null;
		if (StringUtils.isBlank(sysUserVO.getId())) {
			sysUser = new SysUser();
			sysUser.setId(IdUtils.uuid32());
			sysUser.setUserAccount(sysUserVO.getUserAccount());
			sysUser.setUserName(sysUserVO.getUserName());
			sysUser.setPassword(DigestUtils.md5Hex(sysUserVO.getPassword()));
			sysUser.setMobile(sysUserVO.getMobile());
			sysUser.setPhone(sysUserVO.getPhone());
			sysUser.setCa(sysUserVO.getCa());
			int orderNo = sysUserDao.findMaxOrderNo();
			sysUser.setOrderNo(orderNo);

			sysUser.setIsDelete(false);
			sysUser.setState(sysUserVO.getState());
			create(sysUser, orgId);
		} else {
			sysUser = get(sysUserVO.getId());
			sysUser.setUserAccount(sysUserVO.getUserAccount());
			sysUser.setUserName(sysUserVO.getUserName());
			sysUser.setPassword(DigestUtils.md5Hex(sysUserVO.getPassword()));
			sysUser.setMobile(sysUserVO.getMobile());
			sysUser.setPhone(sysUserVO.getPhone());
			sysUser.setOrderNo(sysUserVO.getOrderNo());
			sysUser.setState(sysUserVO.getState());
			sysUser.setCa(sysUserVO.getCa());
			if (isExistUserAccount(sysUser, true)) {
				throw new ServiceException("该人员账号已存在");
			}
			sysUserDao.saveOrUpdate(sysUser);
		}
	}

	@Override
	public SysUser getByCa(String ca) {
		return sysUserDao.findUniqueBy("ca", ca);
	}

}
