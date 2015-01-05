package com.age.back.sysmng.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.model.SysUser;
import com.age.back.sysmng.model.SysUserRole;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysUserRoleDao extends HibernateDao<SysUserRole, java.lang.String> {

	public List<SysRole> findSysRolesBySysUserId(String sysUserId) {
		return find("select sur.sysRole from SysUserRole sur where sur.sysRole.isDelete=0 and sur.sysUser.id=?",
				sysUserId);
	}

	public void deleteBySysUserId(String sysUserId) {
		batchExecute("delete from SysUserRole where sysUser.id=?", sysUserId);
	}
	public List<SysUserRole> findSysUserRole(SysUser sysUser){
		String hql = " from SysUserRole where sysUser=? ";
		return this.find(hql, sysUser);
	}
	/**
	 * 满足当前角色id
	 * @param sysUser
	 */
	public List<String> findUserRoleIds(SysUser sysUser){
		List<SysUserRole> sysOrgUser = findSysUserRole(sysUser);
		List<String> list = new ArrayList<String>(sysOrgUser.size());
		for (SysUserRole obj : sysOrgUser) {
			String id = obj.getSysRole().getId();
			list.add(id);
		}
		return list;
	}
}
