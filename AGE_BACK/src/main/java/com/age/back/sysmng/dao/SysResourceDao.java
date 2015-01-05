package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysResource;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysResourceDao extends HibernateDao<SysResource, java.lang.String> {

	public List<SysResource> findSysResourcesByParentId(String parentId) {
		if (StringUtils.isEmpty(parentId)) {
			return find("from SysResource where isDelete=0 and parentSysResource.id is null order by orderNo");
		} else {
			return find("from SysResource where isDelete=0 and parentSysResource.id=? order by orderNo", parentId);
		}
	}

	public void deleteSysResources(List<String> ids) {
		String hql = "update SysResource set isDelete=1 where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	public List<SysResource> findSysResources() {
		return find("from SysResource where isDelete=0 ");
	}

	public List<String> findResuorceNameByUsrAccount(String account) {
		String sql = "select r.RES_NO  from sys_resource r left join sys_role_resource rr on  r.id=rr.resource_id  left join sys_role ro on ro.id=rr.role_id "
				+ "left join sys_user_role ur on ro.id=ur.role_id left join sys_user u on u.id=ur.user_id where u.user_account='" + account + "'";
		String hql = "select distinct r from  SysResource r,SysRoleResource rr,SysRole ro,SysUserRole ur,SysUser u where u.userAccount='" + account + "'";
		Session session = getSession();
		Query q = session.createSQLQuery(sql);
		List l = q.list();
		return l;

	}

	public List<SysResource> findSysResourcesByParams(Map<String, Object> values) {
		StringBuilder sb = new StringBuilder();
		sb.append("select srr.sysResource from SysRoleResource srr,SysUserRole sur where srr.sysRole=sur.sysRole ");
		for (String key : values.keySet()) {
			if ("sysUser".equals(key)) {
				sb.append(" and sur.sysUser=:sysUser");
			}
			if ("rootModule".equals(key)) {
				sb.append(" and srr.sysResource.parentSysResource.id is null ");
			}
			if ("parentId".equals(key)) {
				sb.append(" and srr.sysResource.parentSysResource.id =:parentId ");
			}
		}
		sb.append(" order by srr.sysResource.orderNo asc ");
		return find(sb.toString(), values);
	}

	public List<SysResource> findCASysResources() {
		String hql = "from SysResource where isDelete=0 and caFlag=1";
		return find(hql);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysResource";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
