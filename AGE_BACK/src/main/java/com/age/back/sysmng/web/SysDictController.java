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

import com.age.back.sysmng.model.SysDict;
import com.age.back.sysmng.service.ISysDictService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysDict")
public class SysDictController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysDictService sysDictService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysDict/list";
	}

	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysDict/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysDict sysDict = sysDictService.get(id);
		request.setAttribute("sysDict", sysDict);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysDict/edit";
	}

	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysDictService.deleteAll(StrToObjUtils.toList(selIds, ","));
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

	@RequestMapping("save")
	public void save(SysDict sysDictVO, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysDictService.saveOrUpdate(sysDictVO);
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
	public void getList(PageRequest pageRequest, SysDict sysDict, HttpServletRequest request, HttpServletResponse response) {
		Page<SysDict> page = sysDictService.findSysDicts(pageRequest, sysDict);
		ServletUtils.responseJson(response, page.toGridMap());
	}
}