package com.age.back.sysmng.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.model.SysRoleResource;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysRoleResourceDao extends HibernateDao<SysRoleResource, java.lang.String> {

	public void deleteBySysRoleId(String sysRoleId) {
		batchExecute("delete from SysRoleResource where sysRole.id=?", sysRoleId);
	}

	public List<SysResource> findSysResourcesBySysRoleId(String sysRoleId) {
		return find("select srr.sysResource from SysRoleResource srr where srr.sysResource.isDelete=0 and srr.sysRole.id=?", sysRoleId);
	}
}
