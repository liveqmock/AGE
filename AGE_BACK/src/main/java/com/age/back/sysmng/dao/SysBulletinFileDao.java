package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.model.SysBulletinFile;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;

@Repository
public class SysBulletinFileDao extends HibernateDao<SysBulletinFile, java.lang.String> {

	public Page<SysBulletinFile> findSysBulletinFiles(PageRequest pageRequest, SysBulletinFile sysBulletinFile) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysBulletinFile where 1=1");
		if (sysBulletinFile != null) {
			if (StringUtils.isNotBlank(sysBulletinFile.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysBulletinFile.getId());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getFileName())) {
				sb.append(" and fileName=:fileName");
				values.put("fileName", sysBulletinFile.getFileName());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getFileUrl())) {
				sb.append(" and fileUrl=:fileUrl");
				values.put("fileUrl", sysBulletinFile.getFileUrl());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getRecCreateTime())) {
				sb.append(" and recCreateTime=:recCreateTime");
				values.put("recCreateTime", sysBulletinFile.getRecCreateTime());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysBulletinFile> findSysBulletinFiles(SysBulletinFile sysBulletinFile) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysBulletinFile where 1=1");
		if (sysBulletinFile != null) {
			if (StringUtils.isNotBlank(sysBulletinFile.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysBulletinFile.getId());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getFileName())) {
				sb.append(" and fileName=:fileName");
				values.put("fileName", sysBulletinFile.getFileName());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getFileUrl())) {
				sb.append(" and fileUrl=:fileUrl");
				values.put("fileUrl", sysBulletinFile.getFileUrl());
			}
			if (StringUtils.isNotBlank(sysBulletinFile.getRecCreateTime())) {
				sb.append(" and recCreateTime=:recCreateTime");
				values.put("recCreateTime", sysBulletinFile.getRecCreateTime());
			}
			if (sysBulletinFile.getSysBulletin() != null) {
				sb.append(" and sysBulletin.id=:sysBulletinId");
				values.put("sysBulletinId", sysBulletinFile.getSysBulletin().getId());
			}
		}
		sb.append(" order by orderNo");
		return find(sb.toString(), values);
	}

	public void deleteSysBulletinFile(List<String> list) {
		String hql = "delete from SysBulletinFile where id in (:list)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("list", list);
		batchExecute(hql, values);
	}

	/**
	 * 取得最大的orderNo
	 * @return
	 */
	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysBulletinFile";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
