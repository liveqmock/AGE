package com.age.back.sysmng.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.age.back.sysmng.common.SysMngContstant;
import com.age.back.sysmng.model.SysBulletin;
import com.age.back.utils.UserUtils;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.orm.hibernate.HibernateDao;
import com.age.core.utils.DateUtils;

@Repository
public class SysBulletinDao extends HibernateDao<SysBulletin, java.lang.String> {

	public Page<SysBulletin> findSysBulletins(PageRequest pageRequest, SysBulletin sysBulletin) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysBulletin where 1=1");
		if (sysBulletin != null) {
			if (StringUtils.isNotBlank(sysBulletin.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysBulletin.getId());
			}
			if (StringUtils.isNotBlank(sysBulletin.getTitle())) {
				sb.append(" and title=:title");
				values.put("title", sysBulletin.getTitle());
			}
			if (StringUtils.isNotBlank(sysBulletin.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysBulletin.getContent());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublishTime())) {
				sb.append(" and publishTime=:publishTime");
				values.put("publishTime", sysBulletin.getPublishTime());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublisher())) {
				sb.append(" and publisher=:publisher");
				values.put("publisher", sysBulletin.getPublisher());
			}
			if (StringUtils.isNotBlank(sysBulletin.getStatus())) {
				sb.append(" and status=:status");
				values.put("status", sysBulletin.getStatus());
			}
			if (StringUtils.isNotBlank(sysBulletin.getRecCreateTime())) {
				sb.append(" and recCreateTime=:recCreateTime");
				values.put("recCreateTime", sysBulletin.getRecCreateTime());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	public List<SysBulletin> findSysBulletins(SysBulletin sysBulletin) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysBulletin where 1=1");
		if (sysBulletin != null) {
			if (StringUtils.isNotBlank(sysBulletin.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysBulletin.getId());
			}
			if (StringUtils.isNotBlank(sysBulletin.getTitle())) {
				sb.append(" and title=:title");
				values.put("title", sysBulletin.getTitle());
			}
			if (StringUtils.isNotBlank(sysBulletin.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysBulletin.getContent());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublishTime())) {
				sb.append(" and publishTime=:publishTime");
				values.put("publishTime", sysBulletin.getPublishTime());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublisher())) {
				sb.append(" and publisher=:publisher");
				values.put("publisher", sysBulletin.getPublisher());
			}
			if (StringUtils.isNotBlank(sysBulletin.getStatus())) {
				sb.append(" and status=:status");
				values.put("status", sysBulletin.getStatus());
			}
			if (StringUtils.isNotBlank(sysBulletin.getRecCreateTime())) {
				sb.append(" and recCreateTime=:recCreateTime");
				values.put("recCreateTime", sysBulletin.getRecCreateTime());
			}
		}
		return find(sb.toString(), values);
	}

	/**
	 * 公告列表查询
	 */
	public Page<SysBulletin> findBulletinList(PageRequest pageRequest, SysBulletin sysBulletin) {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("from SysBulletin where 1=1");
		if (sysBulletin != null) {
			if (StringUtils.isNotBlank(sysBulletin.getId())) {
				sb.append(" and id=:id");
				values.put("id", sysBulletin.getId());
			}
			if (StringUtils.isNotBlank(sysBulletin.getTitle())) {
				sb.append(" and title like :title");
				values.put("title", "%" + sysBulletin.getTitle() + "%");
			}
			if (StringUtils.isNotBlank(sysBulletin.getContent())) {
				sb.append(" and content=:content");
				values.put("content", sysBulletin.getContent());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublishTime())) {
				sb.append(" and publishTime=:publishTime");
				values.put("publishTime", sysBulletin.getPublishTime());
			}
			if (StringUtils.isNotBlank(sysBulletin.getPublisher())) {
				sb.append(" and publisher=:publisher");
				values.put("publisher", sysBulletin.getPublisher());
			}
			if (StringUtils.isNotBlank(sysBulletin.getStatus())) {
				sb.append(" and status=:status");
				values.put("status", sysBulletin.getStatus());
			}
			if (StringUtils.isNotBlank(sysBulletin.getRecCreateTime())) {
				sb.append(" and recCreateTime=:recCreateTime");
				values.put("recCreateTime", sysBulletin.getRecCreateTime());
			}
		}
		return findPage(pageRequest, sb.toString(), values);
	}

	/**
	 * 撤销或发布公告
	 * @param ids
	 * @param status
	 */
	public void releaseOrCancelSysBulletin(List<String> ids, String status) {
		Map<String, Object> values = new HashMap<String, Object>();
		String hql = "update SysBulletin set status=:status ";
		if (SysMngContstant.STATUS_RELESE.equals(status)) {
			//发布状态=1
			hql += ",publishTime=:publishTime,publisher=:publisher ";
			//公告发布时间
			values.put("publishTime", DateUtils.currentDatetime());
			//发布人
			values.put("publisher", UserUtils.getCurrentUser().getId());
		}
		hql += "where id in(:ids)";
		values.put("status", status);
		values.put("ids", ids);
		batchExecute(hql, values);
	}

	public Integer findMaxOrderNo() {
		String hql = "select max(orderNo) from SysBulletin";
		Integer maxOrderNo = findUnique(hql, new HashMap<String, String>());
		return (maxOrderNo == null ? 0 : maxOrderNo) + 5;
	}
}
