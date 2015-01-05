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

import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.service.ISysRoleService;
import com.age.back.sysmng.service.ISysUserRoleService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysRole")
public class SysRoleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysRole/list";
	}

	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysRole/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysRole sysRole = sysRoleService.get(id);
		request.setAttribute("sysRole", sysRole);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysRole/edit";
	}

	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysRoleService.deleteSysRoles(StrToObjUtils.toList(selIds, ","));
			map.put("isSuccess", true);
			map.put("message", "操作成功");
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
		SysRole sysRole = sysRoleService.get(id);
		request.setAttribute("sysRole", sysRole);
		return "/sysmng/sysRole/view";
	}

	@RequestMapping("save")
	public void save(SysRole sysRoleVO, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysRoleService.saveOrUpdate(sysRoleVO);
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

	@RequestMapping("getList")
	public void getList(PageRequest pageRequest, SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
		Page<SysRole> page = sysRoleService.findSysRoles(pageRequest, sysRole);
		ServletUtils.responseJson(response, page.toGridMap());
	}

	@RequestMapping("assignRole")
	public String assignRole(HttpServletRequest request) {
		String sysUserId = ServletUtils.getStringParameter(request, "sysUserId");
		request.setAttribute("sysUserId", sysUserId);
		return "/sysmng/sysRole/assignRole";
	}

	@RequestMapping("saveSysUserRoles")
	public void saveSysUserRoles(SysRole sysRoleVO, HttpServletRequest request, HttpServletResponse response) {
		String sysUserId = ServletUtils.getStringParameter(request, "sysUserId");
		String roleIds = ServletUtils.getStringParameter(request, "roleIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> roleIdsList = StrToObjUtils.toList(roleIds, ",");
			sysUserRoleService.saveSysUserRoles(sysUserId, roleIdsList);
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
		String sysUserId = ServletUtils.getStringParameter(request, "sysUserId");
		List<SysRole> sysRoles = sysRoleService.findSysRoles();
		List<SysRole> assignedSysRoles = sysUserRoleService.findSysRolesBySysUserId(sysUserId);

		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		//下标
		int index = 0;
		for (SysRole sysRole : sysRoles) {
			index++;
			jsonStr.append("{");
			jsonStr.append("\"id\":\"" + sysRole.getId() + "\",\"text\":\"" + sysRole.getRoleName() + "\"");
			if (assignedSysRoles.contains(sysRole)) {
				jsonStr.append(",\"checked\":\"true\"");
			}
			jsonStr.append("}");
			if (index < sysRoles.size()) {
				jsonStr.append(",");
			}

		}
		jsonStr.append("]");
		ServletUtils.responseText(response, jsonStr.toString());
	}
}