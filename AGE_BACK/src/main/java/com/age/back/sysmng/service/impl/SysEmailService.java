package com.age.back.sysmng.service.impl;

import org.apache.ibatis.executor.BatchExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysEmailDao;
import com.age.back.sysmng.model.SysEmail;
import com.age.back.sysmng.service.ISysEmailService;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

@Service
public class SysEmailService implements ISysEmailService {
	@Autowired
	private SysEmailDao sysEmailDao;

	public void saveOrUpdate(SysEmail sysEmail) {
		sysEmailDao.saveOrUpdate(sysEmail);
	}

	public SysEmail get(String id) {
		return sysEmailDao.get(id);
	}

	public void delete(String id) {
		sysEmailDao.delete(id);
	}

	public Page<SysEmail> findSysEmails(PageRequest pageRequest, SysEmail sysEmail) {
		return sysEmailDao.findSysEmails(pageRequest, sysEmail);
	}

	@Override
	public int updateSendFlag(String sendFlag, String id) {
		return sysEmailDao.updateSendFlag(sendFlag, id);
	}

}
