package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysOrg;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysOrgDao extends HibernateDao<SysOrg, java.lang.String> {

	public Page<SysOrg> findSysOrgs(PageRequest pageRequest, SysOrg sysOrg) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysOrg where isDelete=0");
		if (sysOrg != null) {
			if (StringUtils.isNotBlank(sysOrg.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysOrg.getId());
			}
			if (StringUtils.isNotBlank(sysOrg.getOrgName())) {
				sb.append(" and orgName=:orgName");
				values.put("orgName", sysOrg.getOrgName());
			}
			if (StringUtils.isNotBlank(sysOrg.getPhone())) {
				sb.append(" and phone=:phone");
				values.put("phone", sysOrg.getPhone());
			}
			if (StringUtils.isNotBlank(sysOrg.getAddress())) {
				sb.append(" and address=:address");
				values.put("address", sysOrg.getAddress());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysOrg> findSysOrgsByParentId(String parentId) {
		if (StringUtils.isEmpty(parentId)) {
			return find("from SysOrg where isDelete=0 and parentSysOrg.id is null order by orderNo");
		} else {
			return find("from SysOrg where isDelete=0 and parentSysOrg.id=? order by orderNo", parentId);
		}
	}

	public void deleteSysOrgs(List<String> ids) {
		String hql = "update SysOrg set isDelete=1 where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	//根据id 和父机构名称获得 子机构 名称
	public SysOrg findSysOrgsByid(String id, String pOrgName) {
		return findUnique(" from SysOrg where isDelete=0 and id=? and parentSysOrg.orgName=? ", id, pOrgName);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysOrg";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
