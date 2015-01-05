package com.age.back.sysmng.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.sysmng.dao.SysResourceDao;
import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.service.ISysResourceService;
import com.age.back.utils.UserUtils;
import com.age.core.utils.IdUtils;

@Service
public class SysResourceService implements ISysResourceService {
	@Autowired
	private SysResourceDao sysResourceDao;

	public void saveOrUpdate(SysResource sysResourceVO, String parentId) {
		SysResource sysResource = null;
		if (StringUtils.isBlank(sysResourceVO.getId())) {
			sysResource = new SysResource();
			sysResource.setId(IdUtils.uuid32());
			sysResource.setIsDelete(false);
			if (StringUtils.isEmpty(parentId)) {
				sysResource.setIsLeaf(true);
			} else {
				SysResource parentSysResource = sysResourceDao.get(parentId);
				parentSysResource.setIsLeaf(false);
				sysResourceDao.saveOrUpdate(parentSysResource);
				sysResource.setParentSysResource(parentSysResource);
				sysResource.setIsLeaf(true);
			}
			int orderNo = sysResourceDao.findMaxOrderNo();
			sysResourceVO.setOrderNo(orderNo);
		} else {
			sysResource = sysResourceDao.get(sysResourceVO.getId());
		}

		sysResource.setResNo(sysResourceVO.getResNo());
		sysResource.setResName(sysResourceVO.getResName());
		sysResource.setDisplayName(sysResourceVO.getDisplayName());
		sysResource.setType(sysResourceVO.getType());
		sysResource.setUrl(sysResourceVO.getUrl());
		sysResource.setOrderNo(sysResourceVO.getOrderNo());
		sysResource.setResDesc(sysResourceVO.getResDesc());
		sysResource.setCaFlag(sysResourceVO.getCaFlag());
		sysResourceDao.saveOrUpdate(sysResource);
	}

	public void saveOrUpdate(SysResource sysResource) {
		sysResourceDao.saveOrUpdate(sysResource);
	}

	public SysResource get(String id) {
		return sysResourceDao.get(id);
	}

	public void delete(String id) {
		SysResource sysResource = sysResourceDao.get(id);
		SysResource parentSysResource = sysResource.getParentSysResource();

		sysResourceDao.delete(id);

		if (parentSysResource != null) {
			List<SysResource> sysResources = sysResourceDao.findSysResourcesByParentId(parentSysResource.getId());
			if (sysResources.isEmpty()) {
				parentSysResource.setIsLeaf(true);
				sysResourceDao.saveOrUpdate(parentSysResource);
			}
		}
	}

	public List<SysResource> findSysResourcesByParentId(String parentId) {

		return sysResourceDao.findSysResourcesByParentId(parentId);
	}

	public void deleteSysResources(List<String> ids) {

		sysResourceDao.deleteSysResources(ids);
	}

	public List<SysResource> findSysResources() {
		return sysResourceDao.findSysResources();
	}

	@Override
	public List<String> findSysResourcesByUsrAccount(String account) {
		// TODO Auto-generated method stub
		return sysResourceDao.findResuorceNameByUsrAccount(account);
	}

	@Override
	public List<Object> findCurrentUserMenus() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysUser", UserUtils.getCurrentUser());
		//所有菜单
		List<SysResource> list = sysResourceDao.findSysResourcesByParams(params);

		List<Object> menu = new ArrayList<Object>();
		for (SysResource sr : list) {
			if (sr.getParentSysResource() == null) {
				//二级子菜单
				List<SysResource> childrenList = new ArrayList<SysResource>();
				Map<String, Object> data = new HashMap<String, Object>();
				for (SysResource childrenSR : list) {
					if (childrenSR.getParentSysResource() == sr) {
						childrenList.add(childrenSR);
					}
				}
				data.put("rootModule", sr);
				data.put("children", childrenList);
				menu.add(data);
			}
		}
		return menu;
	}

	/**
	 * JSONObject
	 */
	public List<?> findCurrentUserMenus2() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysUser", UserUtils.getCurrentUser());
		//所有菜单
		List<SysResource> list = sysResourceDao.findSysResourcesByParams(params);
		Map<SysResource, LinkedList<SysResource>> m = new TreeMap<SysResource, LinkedList<SysResource>>(new Comparator<SysResource>() {
			@Override
			public int compare(SysResource o1, SysResource o2) {
				return o1.getOrderNo() - o2.getOrderNo();
			}
		});
		JSONArray menu = new JSONArray();
		for (SysResource sr : list) {
			if (sr.getParentSysResource() != null) {
				SysResource parent = sr.getParentSysResource();
				LinkedList<SysResource> linkedList = m.get(parent);
				if (linkedList == null)
					linkedList = new LinkedList<SysResource>();
				linkedList.add(sr);
				m.put(parent, linkedList);
			}
		}
		for (SysResource key : m.keySet()) {
			LinkedList<SysResource> linkedList = m.get(key);
			JSONObject obj = new JSONObject();
			JSONArray children = new JSONArray();
			for (SysResource sysResource : linkedList) {
				JSONObject o = new JSONObject();
				o.accumulate("id", sysResource.getId());
				o.accumulate("text", sysResource.getDisplayName());
				o.accumulate("url", sysResource.getUrl());
				children.add(o);
			}
			obj.accumulate("id", key.getId());
			obj.accumulate("text", key.getDisplayName());
			obj.accumulate("url", key.getUrl());
			obj.accumulate("children", children);
			menu.add(obj);
		}
		return menu;
	}

	public List<SysResource> findSysResourcesByISCA() {
		return sysResourceDao.findCASysResources();
	}
}
