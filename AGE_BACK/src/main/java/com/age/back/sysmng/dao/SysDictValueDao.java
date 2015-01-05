package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysDictValue;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysDictValueDao extends HibernateDao<SysDictValue, java.lang.String> {

	public Page<SysDictValue> findSysDictValues(PageRequest pageRequest, SysDictValue sysDictValue) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysDictValue where 1=1");
		if (sysDictValue != null) {
			if (StringUtils.isNotBlank(sysDictValue.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysDictValue.getId());
			}
			if (StringUtils.isNotBlank(sysDictValue.getDictName())) {
				sb.append(" and dictName like :dictName");
				values.put("dictName", "%" + sysDictValue.getDictName() + "%");
			}
			if (StringUtils.isNotBlank(sysDictValue.getDictValue())) {
				sb.append(" and dictValue like :dictValue");
				values.put("dictValue", "%" + sysDictValue.getDictValue() + "%");
			}
			if (sysDictValue.getSysDict() != null) {
				sb.append(" and sysDict=:sysDict");
				values.put("sysDict", sysDictValue.getSysDict());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysDictValue> findSysDictValueList(SysDictValue sysDictValue) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysDictValue where 1=1");
		if (sysDictValue != null) {
			if (StringUtils.isNotBlank(sysDictValue.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysDictValue.getId());
			}
			if (StringUtils.isNotBlank(sysDictValue.getDictName())) {
				sb.append(" and dictName like :dictName");
				values.put("dictName", "%" + sysDictValue.getDictName() + "%");
			}
			if (StringUtils.isNotBlank(sysDictValue.getDictValue())) {
				sb.append(" and dictValue like :dictValue");
				values.put("dictValue", "%" + sysDictValue.getDictValue() + "%");
			}
			if (sysDictValue.getSysDict() != null) {
				sb.append(" and sysDict=:sysDict");
				values.put("sysDict", sysDictValue.getSysDict());
			}
		}
		return find(sb.toString(), values);
	}

	public void deleteAll(List<String> ids) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "delete SysDictValue where id in (:ids)";
		values.put("ids", ids);
		batchExecute(hql, values);
	}
}
