package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysDictDao;
import com.age.back.sysmng.dao.SysDictValueDao;
import com.age.back.sysmng.model.SysDict;
import com.age.back.sysmng.model.SysDictValue;
import com.age.back.sysmng.service.ISysDictService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysDictService implements ISysDictService {
	@Autowired
	private SysDictDao sysDictDao;
	@Autowired
	private SysDictValueDao sysDictValueDao;

	@Override
	public void saveOrUpdate(SysDict sysDictVO) {
		SysDict sysDict = null;
		if (StringUtils.isBlank(sysDictVO.getId())) {
			if (sysDictDao.isExistSysDict(sysDictVO, false)) {
				throw new ServiceException("该代码已存在");
			}
			sysDict = new SysDict();
			sysDict.setId(IdUtils.uuid32());
			sysDict.setDictName(sysDictVO.getDictName());
			sysDict.setDictCode(sysDictVO.getDictCode());
			sysDict.setIsDelete(false);
		} else {
			if (sysDictDao.isExistSysDict(sysDictVO, true)) {
				throw new ServiceException("该代码已存在");
			}
			sysDict = get(sysDictVO.getId());
			sysDict.setDictName(sysDictVO.getDictName());
			sysDict.setDictCode(sysDictVO.getDictCode());
		}

		sysDictDao.saveOrUpdate(sysDict);
	}

	public SysDict get(String id) {
		return sysDictDao.get(id);
	}

	public void delete(String id) {
		sysDictDao.delete(id);
	}

	public Page<SysDict> findSysDicts(PageRequest pageRequest, SysDict sysDict) {
		sysDict.setIsDelete(false);
		return sysDictDao.findSysDicts(pageRequest, sysDict);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteAll(List<String> ids) {
		if (checkSysDictValue(ids)) {
			throw new ServiceException("数据字典有值，无法删除");
		}
		sysDictDao.deleteAll(ids);
	}

	/**
	 * 根据系统字典id检查每个字典表是否有值
	 */
	@Override
	public boolean checkSysDictValue(List<String> ids) {
		boolean flag = false;
		for (String id : ids) {
			SysDictValue sysDictValue = new SysDictValue();
			sysDictValue.setSysDict(new SysDict(id));
			List<SysDictValue> list = sysDictValueDao.findSysDictValueList(sysDictValue);
			if (list != null && list.size() > 0) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
