package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysOrgDao;
import com.age.back.sysmng.dao.SysOrgUserDao;
import com.age.back.sysmng.model.SysOrg;
import com.age.back.sysmng.model.SysOrgUser;
import com.age.back.sysmng.service.ISysOrgService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysOrgService implements ISysOrgService {
	@Autowired
	private SysOrgDao sysOrgDao;
	@Autowired
	private SysOrgUserDao sysOrgUserDao;

	public void saveOrUpdate(SysOrg sysOrgVO, String parentId) {
		SysOrg sysOrg = null;
		if (StringUtils.isBlank(sysOrgVO.getId())) {//保存
			sysOrg = new SysOrg();
			sysOrg.setId(IdUtils.uuid32());
			sysOrg.setIsDelete(false);
			if (StringUtils.isEmpty(parentId)) {//没有父机构
				sysOrg.setIsLeaf(true);//没有父机构的叶子机构
			} else {//有父机构
				SysOrg parentSysOrg = sysOrgDao.get(parentId);
				parentSysOrg.setIsLeaf(false);
				sysOrgDao.saveOrUpdate(parentSysOrg);
				sysOrg.setParentSysOrg(parentSysOrg);
				sysOrg.setIsLeaf(true);
			}
			int orderNo = sysOrgDao.findMaxOrderNo();
			sysOrgVO.setOrderNo(orderNo);
		} else {//修改
			sysOrg = sysOrgDao.get(sysOrgVO.getId());
		}
		sysOrg.setOrgName(sysOrgVO.getOrgName());
		sysOrg.setPhone(sysOrgVO.getPhone());
		sysOrg.setAddress(sysOrgVO.getAddress());
		sysOrg.setOrderNo(sysOrgVO.getOrderNo());
		sysOrgDao.saveOrUpdate(sysOrg);
	}

	public void saveOrUpdate(SysOrg sysOrg) {
		sysOrgDao.saveOrUpdate(sysOrg);
	}

	public SysOrg get(String id) {
		return sysOrgDao.get(id);
	}

	public void delete(String id) {
		SysOrg sysOrg = sysOrgDao.get(id);
		//机构在SysOrgUser表中有数据时候不能删除
		List<SysOrgUser> sysOrgUsers = sysOrgUserDao.findBySysOrg(sysOrg);
		if (sysOrgUsers != null && sysOrgUsers.size() > 0) {
			throw new ServiceException("机构下面还有用户，不能删除!");
		}
		SysOrg parentSysOrg = sysOrg.getParentSysOrg();

		sysOrgDao.delete(id);

		if (parentSysOrg != null) {
			List<SysOrg> sysOrgs = sysOrgDao.findSysOrgsByParentId(parentSysOrg.getId());
			if (sysOrgs.isEmpty()) {
				parentSysOrg.setIsLeaf(true);
				sysOrgDao.saveOrUpdate(parentSysOrg);
			}
		}
	}

	public Page<SysOrg> findSysOrgs(PageRequest pageRequest, SysOrg sysOrg) {
		return sysOrgDao.findSysOrgs(pageRequest, sysOrg);
	}

	public List<SysOrg> findSysOrgsByParentId(String parentId) {
		return sysOrgDao.findSysOrgsByParentId(parentId);
	}

	public void deleteSysOrgs(List<String> ids) {
		sysOrgDao.deleteSysOrgs(ids);
	}
}
