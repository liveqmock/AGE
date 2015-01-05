package com.age.back.sysmng.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysOrg;
import com.age.back.sysmng.model.SysOrgUser;
import com.age.back.sysmng.model.SysUser;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysOrgUserDao extends HibernateDao<SysOrgUser, java.lang.String> {
	public List<SysOrgUser> findBySysUser(SysUser sysUser) {
		String hql = "from SysOrgUser where sysUser=? ";
		return this.find(hql, sysUser);
	}

	/**
	 * 用户所属机构id
	 * @param sysUser
	 */
	public List<String> findSysOrgIds(SysUser sysUser) {
		List<SysOrgUser> sysOrgUser = findBySysUser(sysUser);
		List<String> list = new ArrayList<String>(sysOrgUser.size());
		for (SysOrgUser obj : sysOrgUser) {
			String id = obj.getSysOrg().getId();
			list.add(id);
		}
		return list;
	}

	public List<SysOrgUser> findBySysOrg(SysOrg SysOrg) {
		String hql = "from SysOrgUser where sysOrg.id=? ";
		return this.find(hql, SysOrg.getId());
	}
}
