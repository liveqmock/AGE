package com.age.back.sysmng.service;

import java.util.*;  

import com.age.back.sysmng.model.*;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
public interface ISysLogService{

	public void saveOrUpdate(SysLog sysLog);
	
	public SysLog get(String id);

	public void delete(String id);
	
	public Page<SysLog> findSysLogs(PageRequest pageRequest,SysLog sysLog);
}
