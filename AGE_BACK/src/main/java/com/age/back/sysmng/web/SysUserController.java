package com.age.back.sysmng.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.model.SysUser;
import com.age.back.sysmng.service.ISysUserService;
import com.age.back.utils.UserUtils;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysUser")
public class SysUserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysUser/list";
	}

	@RequestMapping("create")
	public String create(HttpServletRequest request) {
		String orgId = ServletUtils.getStringParameter(request, "orgId");
		request.setAttribute("orgId", orgId);
		return "/sysmng/sysUser/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysUser sysUser = sysUserService.get(id);
		request.setAttribute("sysUser", sysUser);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysUser/edit";
	}

	@RequestMapping("editPwd")
	public String editPwd(HttpServletRequest request, HttpServletResponse response) {
		return "/sysmng/sysUser/editPwd";
	}

	@RequestMapping("saveNewPwd")
	public void saveNewPwd(HttpServletRequest request, HttpServletResponse response) {
		String newPassword = ServletUtils.getStringParameter(request, "newPassword");
		String oldPassword = ServletUtils.getStringParameter(request, "oldPassword");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysUser sysUser = UserUtils.getCurrentUser();

			if (DigestUtils.md5Hex(oldPassword).equals(sysUser.getPassword())) {

				sysUser.setPassword(DigestUtils.md5Hex(newPassword));
				sysUserService.saveOrUpdate(sysUser);
				map.put("isSuccess", true);
				map.put("message", "操作成功");
			} else {
				map.put("isSuccess", false);
				map.put("message", "原密码输入不正确");
			}
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("message", "操作失败");
			logger.error(e.getMessage(), e);
		}
		ServletUtils.responseJson(response, map);
	}

	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysUserService.deleteSysUsers(StrToObjUtils.toList(selIds, ","));
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
		SysUser sysUser = sysUserService.get(id);
		request.setAttribute("sysUser", sysUser);
		return "/sysmng/sysUser/view";
	}

	@RequestMapping("save")
	public void save(SysUser sysUserVO, HttpServletRequest request, HttpServletResponse response) {
		String orgId = ServletUtils.getStringParameter(request, "orgId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysUserService.save(sysUserVO, orgId);

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
	public void getList(PageRequest pageRequest, SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
		String orgId = ServletUtils.getStringParameter(request, "orgId");
		Page<SysUser> page = sysUserService.findSysUsersByOrgId(pageRequest, orgId);
		ServletUtils.responseJson(response, page.toGridMap());
	}

	@RequestMapping("getList2")
	public void getList2(PageRequest pageRequest, SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
		Page<SysUser> page = sysUserService.findSysUsers(pageRequest, sysUser);
		ServletUtils.responseJson(response, page.toGridMap());
	}
}