package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysDictValue;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysDictValueService {

	public void saveOrUpdate(SysDictValue sysDictValue);

	public SysDictValue get(String id);

	public void delete(String id);

	public Page<SysDictValue> findSysDictValues(PageRequest pageRequest, SysDictValue sysDictValue);

	public void deleteAll(List<String> list);
}
