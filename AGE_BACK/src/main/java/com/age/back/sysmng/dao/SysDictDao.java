package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysDict;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysDictDao extends HibernateDao<SysDict, java.lang.String> {

	public Page<SysDict> findSysDicts(PageRequest pageRequest, SysDict sysDict) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysDict where 1=1");
		if (sysDict != null) {
			if (StringUtils.isNotBlank(sysDict.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysDict.getId());
			}
			if (StringUtils.isNotBlank(sysDict.getDictName())) {
				sb.append(" and dictName like :dictName");
				values.put("dictName", "%" + sysDict.getDictName() + "%");
			}
			if (StringUtils.isNotBlank(sysDict.getDictCode())) {
				sb.append(" and dictCode like :dictCode");
				values.put("dictCode", "%" + sysDict.getDictCode() + "%");
			}
			if (sysDict.getIsDelete() != null) {
				sb.append(" and isDelete=:isDelete");
				values.put("isDelete", sysDict.getIsDelete());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	//删除
	public void deleteAll(List<String> ids) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "update SysDict set  isDelete = '1' where id in (:ids)";
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	//查看dictCode是否存在
	public boolean isExistSysDict(SysDict SysDict, boolean updateFlag) {
		String hql = "";
		if (updateFlag) {//更新数据时判断
			hql = "from SysDict where  dictCode is not null and dictCode=:dictCode and id <>'" + SysDict.getId() + "'";
		} else {//新增数据时判断
			hql = "from SysDict where  dictCode is not null and dictCode=:dictCode";
		}
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("dictCode", SysDict.getDictCode());
		return !find(hql, values).isEmpty();
	}
}
