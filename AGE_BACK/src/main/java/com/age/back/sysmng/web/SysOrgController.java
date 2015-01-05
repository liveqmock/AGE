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

import com.age.back.sysmng.model.SysOrg;
import com.age.back.sysmng.service.ISysOrgService;
import com.age.core.exception.ServiceException;
import com.age.core.utils.StrUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysOrg")
public class SysOrgController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysOrgService sysOrgService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysOrg/list";
	}

	@RequestMapping("create")
	public String create(HttpServletRequest request) {
		String parentId = ServletUtils.getStringParameter(request, "parentId");
		request.setAttribute("parentId", parentId);
		return "/sysmng/sysOrg/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysOrg sysOrg = sysOrgService.get(id);
		request.setAttribute("sysOrg", sysOrg);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysOrg/edit";
	}

	@RequestMapping("remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysOrg sysOrg = sysOrgService.get(id);
			if (sysOrg.getIsLeaf()) {
				sysOrgService.delete(id);
				map.put("isSuccess", true);
				map.put("message", "操作成功");
			} else {
				map.put("isSuccess", false);
				map.put("message", "存在子机构，无法删除！");
			}
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

	@RequestMapping("view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysOrg sysOrg = sysOrgService.get(id);
		request.setAttribute("sysOrg", sysOrg);
		return "/sysmng/sysOrg/view";
	}

	@RequestMapping("save")
	public void save(SysOrg sysOrgVO, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String parentId = ServletUtils.getStringParameter(request, "parentId");
		try {
			sysOrgService.saveOrUpdate(sysOrgVO, parentId);

			map.put("isSuccess", true);
			map.put("message", "操作成功");
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
		List<SysOrg> sysOrgs = sysOrgService.findSysOrgsByParentId(parentId);
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		//下标
		int index = 0;
		for (SysOrg sysOrg : sysOrgs) {
			index++;
			jsonStr.append("{");
			jsonStr.append("\"id\":\"" + sysOrg.getId() + "\",");
			jsonStr.append("\"orgName\":\"" + StrUtils.changeNullToStr(sysOrg.getOrgName()) + "\",");
			jsonStr.append("\"orderNo\":\"" + StrUtils.changeNullToStr(sysOrg.getOrderNo()) + "\",");
			jsonStr.append("\"phone\":\"" + StrUtils.changeNullToStr(sysOrg.getPhone()) + "\",");
			jsonStr.append("\"orgName\":\"" + (sysOrg.getOrgName()) + "\",");
			jsonStr.append("\"isLeaf\":\"" + sysOrg.getIsLeaf() + "\",");
			jsonStr.append("\"parentId\":\"" + (sysOrg.getParentSysOrg() == null ? "" : sysOrg.getParentSysOrg().getId()) + "\",");
			jsonStr.append("\"state\":\"" + (sysOrg.getIsLeaf() ? "open" : "closed") + "\"");

			jsonStr.append("}");
			if (index < sysOrgs.size()) {
				jsonStr.append(",");
			}
		}
		jsonStr.append("]");
		ServletUtils.responseText(response, jsonStr.toString());
	}

	@RequestMapping("getTreeList")
	public void getTreeList(HttpServletRequest request, HttpServletResponse response) {
		String parentId = ServletUtils.getStringParameter(request, "parentId");

		List<SysOrg> sysOrgs = sysOrgService.findSysOrgsByParentId(parentId);
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		//下标
		int index = 0;
		for (SysOrg sysOrg : sysOrgs) {
			index++;
			jsonStr.append("{");
			jsonStr.append("\"id\":\"" + sysOrg.getId() + "\",\"text\":\"" + sysOrg.getOrgName() + "\"");
			jsonStr.append(",\"attributes\":{\"isLeaf\":\"" + sysOrg.getIsLeaf() + "\",\"parentOrgId\":\"" + (sysOrg.getParentSysOrg() == null ? "" : sysOrg.getParentSysOrg().getId()) + "\"");

			List<SysOrg> childSysOrgs = sysOrgService.findSysOrgsByParentId(sysOrg.getId());
			if (childSysOrgs.size() > 0) {
				jsonStr.append(",\"hasChildren\":\"true\"}");
				jsonStr.append(",\"state\": \"closed\"");
			} else {
				jsonStr.append(",\"hasChildren\":\"false\"}");
			}
			jsonStr.append("}");
			if (index < sysOrgs.size()) {
				jsonStr.append(",");
			}

		}
		jsonStr.append("]");
		ServletUtils.responseText(response, jsonStr.toString());
	}
}