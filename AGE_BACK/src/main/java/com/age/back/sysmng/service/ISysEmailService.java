package com.age.back.sysmng.service;

import com.age.back.sysmng.model.SysEmail;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysEmailService {

	public void saveOrUpdate(SysEmail sysEmail);

	public SysEmail get(String id);

	public void delete(String id);

	public Page<SysEmail> findSysEmails(PageRequest pageRequest, SysEmail sysEmail);

	public int updateSendFlag(String sendFlag, String id);

}
