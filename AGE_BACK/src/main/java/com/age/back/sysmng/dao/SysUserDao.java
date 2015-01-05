package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysUser;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysUserDao extends HibernateDao<SysUser, java.lang.String> {
	public Page<SysUser> findSysUsers(PageRequest pageRequest, SysUser sysUser) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysUser where 1=1");
		if (sysUser != null) {
			if (StringUtils.isNotBlank(sysUser.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysUser.getId());
			}
			if (StringUtils.isNotBlank(sysUser.getUserAccount())) {
				sb.append(" and userAccount like :userAccount");
				values.put("userAccount", "%" + sysUser.getUserAccount() + "%");
			}
			if (StringUtils.isNotBlank(sysUser.getUserName())) {
				sb.append(" and userName like :userName");
				values.put("userName", "%" + sysUser.getUserName() + "%");
			}
			if (StringUtils.isNotBlank(sysUser.getPassword())) {
				sb.append(" and password=:password");
				values.put("password", sysUser.getPassword());
			}
			if (StringUtils.isNotBlank(sysUser.getMobile())) {
				sb.append(" and mobile=:mobile");
				values.put("mobile", sysUser.getMobile());
			}
			if (StringUtils.isNotBlank(sysUser.getPhone())) {
				sb.append(" and phone=:phone");
				values.put("phone", sysUser.getPhone());
			}
			if (sysUser.getOrderNo() != null) {
				sb.append(" and orderNo=:orderNo");
				values.put("orderNo", sysUser.getOrderNo());
			}
			if (sysUser.getIsDelete() != null) {
				sb.append(" and isDelete=:isDelete");
				values.put("isDelete", sysUser.getIsDelete());
			}
			if (sysUser.getState() != null) {
				sb.append(" and state=:state");
				values.put("state", sysUser.getState());
			}
			if (StringUtils.isNotBlank(sysUser.getAccountType())) {
				sb.append(" and accountType=:accountType");
				values.put("accountType", sysUser.getAccountType());
			}
			if (StringUtils.isNotBlank(sysUser.getUserDetailId())) {
				sb.append(" and userDetailId=:userDetailId");
				values.put("userDetailId", sysUser.getUserDetailId());
			}
			if (StringUtils.isNotBlank(sysUser.getCa())) {
				sb.append(" and ca=:ca");
				values.put("ca", sysUser.getCa());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public boolean isExistUserAccount(SysUser sysUser, boolean updateFlag) {
		String hql = "";
		if (updateFlag) {//更新数据时判断
			hql = "from SysUser where  userAccount is not null and userAccount=:userAccount and id <>'" + sysUser.getId() + "'";
		} else {//新增数据时判断
			hql = "from SysUser where  userAccount is not null and userAccount=:userAccount";
		}
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userAccount", sysUser.getUserAccount());
		return !find(hql, values).isEmpty();
	}

	public void deleteSysUsers(List<String> ids) {
		String hql = "update SysUser set isDelete=1 where id in(:ids)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ids", ids);
		batchExecute(hql, values);
		String sysOrgUserhql = "delete SysOrgUser  where sysUser.id in(:ids) ";
		Map<String, Object> sysOrgUservalues = new HashMap<String, Object>();
		sysOrgUservalues.put("ids", ids);
		batchExecute(sysOrgUserhql, sysOrgUservalues);
	}

	public SysUser findSysUserByUserAccount(String userAccount) {
		String hql = "from SysUser where isDelete=0 and userAccount =? and state=1";
		return findUnique(hql, userAccount);
	}

	public Page<SysUser> findSysUsersByOrgId(PageRequest pageRequest, String orgId) {
		String hql = "select su from SysUser su , SysOrgUser sou where su.isDelete=0 and sou.sysUser.id=su.id and sou.sysOrg.id=? order by su.orderNo";
		return findPage(pageRequest, hql, orgId);
	}

	/**
	 * 取得用户信息表中中最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysUser";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}

	/**
	 * 更改用户信息表中CA字段
	 * @return
	 */
	public void updateSysUsrCA(String ca, String account) {
		String hql = "update SysUser set ca= ? where userAccount = ?";
		batchExecute(hql, ca, account);
	}

}
