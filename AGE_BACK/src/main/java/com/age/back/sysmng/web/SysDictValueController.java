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
import com.age.back.sysmng.model.SysDictValue;
import com.age.back.sysmng.service.ISysDictValueService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysDictValue")
public class SysDictValueController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysDictValueService sysDictValueService;

	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String sysDictId = ServletUtils.getStringParameter(request, "sysDictId");
		request.setAttribute("sysDictId", sysDictId);
		return "/sysmng/sysDictValue/list";
	}

	@RequestMapping("create")
	public String create(HttpServletRequest request, HttpServletResponse response) {
		String sysDictId = ServletUtils.getStringParameter(request, "sysDictId");
		request.setAttribute("sysDictId", sysDictId);
		return "/sysmng/sysDictValue/edit";
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysDictValue sysDictValue = sysDictValueService.get(id);
		request.setAttribute("sysDictValue", sysDictValue);
		String sysDictId = ServletUtils.getStringParameter(request, "sysDictId");
		request.setAttribute("sysDictId", sysDictId);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysDictValue/edit";
	}

	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysDictValueService.deleteAll(StrToObjUtils.toList(selIds, ","));
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
		SysDictValue sysDictValue = sysDictValueService.get(id);
		request.setAttribute("sysDictValue", sysDictValue);
		return "/sysmng/sysDictValue/view";
	}

	@RequestMapping("save")
	public void save(SysDictValue sysDictValueVO, HttpServletRequest request, HttpServletResponse response) {
		String sysDictId = ServletUtils.getStringParameter(request, "sysDictId");
		request.setAttribute("sysDictId", sysDictId);
		sysDictValueVO.setSysDict(new SysDict(sysDictId));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysDictValueService.saveOrUpdate(sysDictValueVO);
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
	public void getList(PageRequest pageRequest, SysDictValue sysDictValue, HttpServletRequest request, HttpServletResponse response) {
		String sysDictId = ServletUtils.getStringParameter(request, "sysDictId");
		request.setAttribute("sysDictId", sysDictId);
		sysDictValue.setSysDict(new SysDict(sysDictId));
		Page<SysDictValue> page = sysDictValueService.findSysDictValues(pageRequest, sysDictValue);
		ServletUtils.responseJson(response, page.toGridMap());
	}
}