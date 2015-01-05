package com.age.back.sysmng.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.service.ISysResourceService;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysMenu")
public class SysMenuController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysResourceService sysResourceService;

	@RequestMapping("getMenus")
	public void getRootModules(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletUtils.responseJson(response, sysResourceService.findCurrentUserMenus());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@RequestMapping("getMenus2")
	public void getRootModules2(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletUtils.responseText(response, sysResourceService.findCurrentUserMenus2().toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
