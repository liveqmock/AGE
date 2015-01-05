package com.age.back.sysmng.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.model.SysParam;
import com.age.back.sysmng.service.ISysParamService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysParam")
public class SysParamController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysParamService sysParamService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysParam/list";
	}

	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysParam/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysParam sysParam = sysParamService.get(id);
		request.setAttribute("sysParam", sysParam);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysParam/edit";
	}

	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysParamService.deleteSysParams(StrToObjUtils.toList(selIds, ","));
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
		SysParam sysParam = sysParamService.get(id);
		request.setAttribute("sysParam", sysParam);
		return "/sysmng/sysParam/view";
	}

	@RequestMapping("save")
	public void save(SysParam sysParamVO, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysParamService.saveOrUpdate(sysParamVO);
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
	public void getList(PageRequest pageRequest, SysParam sysParam, HttpServletRequest request, HttpServletResponse response) {
		Page<SysParam> page = sysParamService.findSysParams(pageRequest, sysParam);
		ServletUtils.responseJson(response, page.toGridMap());
	}
}