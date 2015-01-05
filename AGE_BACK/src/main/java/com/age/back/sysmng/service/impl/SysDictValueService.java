package com.age.back.sysmng.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysDictValueDao;
import com.age.back.sysmng.model.SysDictValue;
import com.age.back.sysmng.service.ISysDictValueService;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;

@Service
public class SysDictValueService implements ISysDictValueService {
	@Autowired
	private SysDictValueDao sysDictValueDao;

	public void saveOrUpdate(SysDictValue sysDictValueVO) {
		SysDictValue sysDictValue = null;
		if (StringUtils.isBlank(sysDictValueVO.getId())) {
			sysDictValue = new SysDictValue();
			sysDictValue.setId(IdUtils.uuid32());
			sysDictValue.setDictName(sysDictValueVO.getDictName());
			sysDictValue.setDictValue(sysDictValueVO.getDictValue());
			sysDictValue.setSysDict(sysDictValueVO.getSysDict());
		} else {
			sysDictValue = get(sysDictValueVO.getId());
			sysDictValue.setDictName(sysDictValueVO.getDictName());
			sysDictValue.setDictValue(sysDictValueVO.getDictValue());
		}
		sysDictValueDao.saveOrUpdate(sysDictValue);
	}

	public SysDictValue get(String id) {
		return sysDictValueDao.get(id);
	}

	public void delete(String id) {
		sysDictValueDao.delete(id);
	}

	public Page<SysDictValue> findSysDictValues(PageRequest pageRequest, SysDictValue sysDictValue) {
		return sysDictValueDao.findSysDictValues(pageRequest, sysDictValue);
	}

	@Override
	public void deleteAll(List<String> list) {
		sysDictValueDao.deleteAll(list);
	}
}
