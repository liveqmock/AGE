package com.age.back.sysmng.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.service.ISysResourceService;
import com.age.back.sysmng.service.ISysRoleResourceService;
import com.age.core.exception.ServiceException;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysResource")
public class SysResourceController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysResourceService sysResourceService;
	@Autowired
	private ISysRoleResourceService sysRoleResourceService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysResource/list";
	}

	@RequestMapping("create")
	public String create(HttpServletRequest request) {
		String parentId = ServletUtils.getStringParameter(request, "parentId");
		request.setAttribute("parentId", parentId);
		return "/sysmng/sysResource/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysResource sysResource = sysResourceService.get(id);
		request.setAttribute("sysResource", sysResource);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysResource/edit";
	}

	@RequestMapping("remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysResource sysResource = sysResourceService.get(id);
			if (sysResource.getIsLeaf()) {
				sysResourceService.delete(id);
				map.put("isSuccess", true);
				map.put("message", "操作成功");
			} else {
				map.put("isSuccess", false);
				map.put("message", "存在子资源，无法删除！");
			}
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("message", "操作失败");
			logger.error(e.getMessage(), e);
		}
		ServletUtils.responseJson(response, map);
	}

	@RequestMapping("view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysResource sysResource = sysResourceService.get(id);
		request.setAttribute("sysResource", sysResource);
		return "/sysmng/sysResource/view";
	}

	@RequestMapping("save")
	public void save(SysResource sysResourceVO, HttpServletRequest request, HttpServletResponse response) {
		String parentId = ServletUtils.getStringParameter(request, "parentId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysResourceService.saveOrUpdate(sysResourceVO, parentId);
			map.put("isSuccess", true);
			map.put("message", "操作成功");
			logger.info("sysResourceVO:" + sysResourceVO.getCaFlag());
		} catch (ServiceException e) {
			map.put("isSuccess", false);
			map.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("message", "操作失败");
			logger.error(e.getMessage(), e);
		}
		ServletUtils.responseJson(response, map);
	}

	@RequestMapping("getTreeGirdList")
	public void getTreeGirdList(HttpServletRequest request, HttpServletResponse response) {
		String parentId = ServletUtils.getStringParameter(request, "parentId");
		List<SysResource> sysResources = sysResourceService.findSysResourcesByParentId(parentId);
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		//下标
		int index = 0;
		for (SysResource sysResource : sysResources) {
			index++;
			jsonStr.append("{");
			jsonStr.append("\"id\":\"" + sysResource.getId() + "\",");
			jsonStr.append("\"resName\":\"" + sysResource.getResName() + "\",");
			jsonStr.append("\"resNo\":\"" + sysResource.getResNo() + "\",");
			jsonStr.append("\"type\":\"" + sysResource.getType() + "\",");
			jsonStr.append("\"typeCN\":\"" + sysResource.getTypeCN() + "\",");
			jsonStr.append("\"orderNo\":\"" + sysResource.getOrderNo() + "\",");
			jsonStr.append("\"isLeaf\":\"" + sysResource.getIsLeaf() + "\",");
			jsonStr.append("\"caFlag\":\"" + sysResource.getCaFlag() + "\",");
			jsonStr.append("\"parentId\":\"" + (sysResource.getParentSysResource() == null ? "" : sysResource.getParentSysResource().getId()) + "\",");
			jsonStr.append("\"state\":\"" + (sysResource.getIsLeaf() ? "open" : "closed") + "\"");

			jsonStr.append("}");
			if (index < sysResources.size()) {
				jsonStr.append(",");
			}
		}
		jsonStr.append("]");
		ServletUtils.responseText(response, jsonStr.toString());
	}

	@RequestMapping("assignResource")
	public String assignResource(HttpServletRequest request) {
		String sysRoleId = ServletUtils.getStringParameter(request, "sysRoleId");
		request.setAttribute("sysRoleId", sysRoleId);
		return "/sysmng/sysResource/assignResource";
	}

	@RequestMapping("saveSysRoleResources")
	public void saveSysRoleResources(HttpServletRequest request, HttpServletResponse response) {
		String sysRoleId = ServletUtils.getStringParameter(request, "sysRoleId");
		String resourceIds = ServletUtils.getStringParameter(request, "resourceIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> resourceIdsList = StrToObjUtils.toList(resourceIds, ",");
			sysRoleResourceService.saveSysRoleResources(sysRoleId, resourceIdsList);
			map.put("isSuccess", true);
			map.put("message", "操作成功");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("message", "操作失败");
			logger.error(e.getMessage(), e);
		}
		ServletUtils.responseJson(response, map);
	}

	@RequestMapping("getTreeList")
	public void getTreeList(HttpServletRequest request, HttpServletResponse response) {
		String sysRoleId = ServletUtils.getStringParameter(request, "sysRoleId");
		List<SysResource> sysResources = sysResourceService.findSysResourcesByParentId(null);
		List<SysResource> assignedSysResources = sysRoleResourceService.findSysResourcesBySysRoleId(sysRoleId);
		List<SysResource> allSysResources = sysResourceService.findSysResources();
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		//下标
		int index = 0;
		for (SysResource sysResource : sysResources) {
			index++;
			jsonStr.append("{");
			jsonStr.append("\"id\":\"" + sysResource.getId() + "\",\"text\":\"" + sysResource.getResName() + "\"");
			if (assignedSysResources.contains(sysResource)) {
				jsonStr.append(",\"checked\":\"true\"");
			}
			appendChildSysResource(jsonStr, sysResource, allSysResources, assignedSysResources);
			jsonStr.append("}");
			if (index < sysResources.size()) {
				jsonStr.append(",");
			}

		}
		jsonStr.append("]");
		ServletUtils.responseText(response, jsonStr.toString());
	}

	private void appendChildSysResource(StringBuffer jsonStr, SysResource curSysResource, List<SysResource> allSysResources, List<SysResource> assignedSysResources) {

		jsonStr.append(",\"children\":[");
		boolean isAppendChild = false;
		for (SysResource sysResource : allSysResources) {
			SysResource parentSysResource = sysResource.getParentSysResource();
			if (parentSysResource != null && parentSysResource.getId().equals(curSysResource.getId())) {
				jsonStr.append("{");
				jsonStr.append("\"id\":\"" + sysResource.getId() + "\",\"text\":\"" + sysResource.getResName() + "\"");
				if (assignedSysResources.contains(sysResource)) {
					jsonStr.append(",\"checked\":\"true\"");
				}

				appendChildSysResource(jsonStr, sysResource, allSysResources, assignedSysResources);

				jsonStr.append("}");
				jsonStr.append(",");

				isAppendChild = true;
			}
		}
		jsonStr.delete(jsonStr.lastIndexOf(","), jsonStr.length());
		if (isAppendChild)
			jsonStr.append("]");
	}
}