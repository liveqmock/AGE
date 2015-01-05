package com.age.back.sysmng.service;

import java.util.List;

import com.age.back.sysmng.model.SysDict;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysDictService {

	public void saveOrUpdate(SysDict sysDict);

	public SysDict get(String id);

	public void delete(String id);

	public Page<SysDict> findSysDicts(PageRequest pageRequest, SysDict sysDict);

	//删除
	public void deleteAll(List<String> list);

	/**
	 * 根据系统字典id检查每个字典表是否有值
	 * @param ids
	 * @return
	 */
	boolean checkSysDictValue(List<String> ids);
}
