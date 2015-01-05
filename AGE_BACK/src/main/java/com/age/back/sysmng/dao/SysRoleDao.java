package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysRole;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysRoleDao extends HibernateDao<SysRole, java.lang.String> {

	public void deleteSysRoles(List<String> ids) {
		String hql = "update SysRole set isDelete=1 where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	public boolean isExistRoleNo(SysRole sysRole, boolean updateFlag) {
		String hql = "";
		if (updateFlag) {//更新数据时判断
			hql = "from SysRole where isDelete=0 and roleNo is not null and roleNo=:roleNo and id <>'" + sysRole.getId() + "'";
		} else {//新增数据时判断
			hql = "from SysRole where isDelete=0 and roleNo is not null and roleNo=:roleNo";
		}
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("roleNo", sysRole.getRoleNo());
		return !find(hql, values).isEmpty();
	}

	public Page<SysRole> findSysRoles(PageRequest pageRequest, SysRole sysRole) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysRole where isDelete=0");
		if (sysRole != null) {
			if (StringUtils.isNotBlank(sysRole.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysRole.getId());
			}
			if (StringUtils.isNotBlank(sysRole.getRoleNo())) {
				sb.append(" and roleNo=:roleNo");
				values.put("roleNo", sysRole.getRoleNo());
			}
			if (StringUtils.isNotBlank(sysRole.getRoleName())) {
				sb.append(" and roleName=:roleName");
				values.put("roleName", sysRole.getRoleName());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysRole> findSysRoles() {
		return find("from SysRole where isDelete=0");
	}

	public List findSysRolesByUsrAccount(String account) {
		String hql = "select distinct r.roleName from SysRole r,SysUser u where u.userAccount='" + account + "'";

		return find(hql);

	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysRole";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
