package com.age.back.sysmng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysOrgUserDao;
import com.age.back.sysmng.model.SysOrgUser;
import com.age.back.sysmng.service.ISysOrgUserService;

@Service
public class SysOrgUserService implements ISysOrgUserService {
	@Autowired
	private SysOrgUserDao sysOrgUserDao;

	public void saveOrUpdate(SysOrgUser sysOrgUser) {
		sysOrgUserDao.saveOrUpdate(sysOrgUser);
	}

	public SysOrgUser get(String id) {
		return sysOrgUserDao.get(id);
	}

	public void delete(String id) {
		sysOrgUserDao.delete(id);
	}
}
