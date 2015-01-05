package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysParamDao;
import com.age.back.sysmng.model.SysParam;
import com.age.back.sysmng.service.ISysParamService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysParamService implements ISysParamService {
	@Autowired
	private SysParamDao sysParamDao;

	public void saveOrUpdate(SysParam sysParamVO) {
		SysParam sysParam = null;
		boolean updateFlag = false;
		if (StringUtils.isBlank(sysParamVO.getId())) {
			sysParam = new SysParam();
			sysParam.setId(IdUtils.uuid32());
			sysParam.setIsDelete(false);

			int orderNo = sysParamDao.findMaxOrderNo();
			sysParamVO.setOrderNo(orderNo);
		} else {
			sysParam = get(sysParamVO.getId());
			updateFlag = true;
		}

		sysParam.setParamName(sysParamVO.getParamName());
		sysParam.setParamCode(sysParamVO.getParamCode());
		sysParam.setParamValue(sysParamVO.getParamValue());
		sysParam.setOrderNo(sysParamVO.getOrderNo());

		if (isExistParamCode(sysParamVO, updateFlag)) {
			throw new ServiceException("该商户号已经存在！");
		} else {
			sysParamDao.saveOrUpdate(sysParam);
		}
	}

	public SysParam get(String id) {
		return sysParamDao.get(id);
	}

	public void delete(String id) {
		sysParamDao.delete(id);
	}

	public Page<SysParam> findSysParams(PageRequest pageRequest, SysParam sysParam) {
		return sysParamDao.findSysParams(pageRequest, sysParam);
	}

	public boolean isExistParamCode(SysParam sysParam, boolean updateFlag) {
		return sysParamDao.isExistParamCode(sysParam, updateFlag);
	}

	public void deleteSysParams(List<String> ids) {
		sysParamDao.deleteSysParams(ids);
	}
}
