package com.age.back.sysmng.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
import java.util.*;

import com.age.back.sysmng.dao.*;
import com.age.back.sysmng.model.*;
import com.age.back.sysmng.service.*;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
@Service 
public class SysLogService implements ISysLogService{ 
	@Autowired
	private SysLogDao sysLogDao;
	
	public void saveOrUpdate(SysLog sysLog) {
		sysLogDao.saveOrUpdate(sysLog);
	} 
	
	public SysLog get(String id) {
		return sysLogDao.get(id);
	}

	public void delete(String id) {
		sysLogDao.delete(id);
	} 
	
	public Page<SysLog> findSysLogs(PageRequest pageRequest,SysLog sysLog) {
		return sysLogDao.findSysLogs(pageRequest,sysLog);
	}
}
