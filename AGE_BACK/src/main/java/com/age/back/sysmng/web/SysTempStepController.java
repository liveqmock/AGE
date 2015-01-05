package com.age.back.sysmng.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.model.SysTempStep;
import com.age.back.sysmng.service.ISysTempStepService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysTempStep")
public class SysTempStepController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ISysTempStepService sysTempStepService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysTempStep/list";
	}

	/**
	 * 环节模板列表
	 */
	@RequestMapping("getTempStepList")
	public void getTempStepList(HttpServletRequest request, PageRequest pageRequest, HttpServletResponse response, SysTempStep sysTempStep) {

		Page<SysTempStep> sysTempSteps = sysTempStepService.findSysTempSteps(pageRequest, sysTempStep);
		ServletUtils.responseJson(response, sysTempSteps.toGridMap());
	}

	/**
	 * 打开新增环节页面
	 */
	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysTempStep/edit";
	}

	/**
	 * 保存环节
	 * @param sysTempStepOV
	 * @param request
	 * @param response
	 */
	@RequestMapping("save")
	public void save(SysTempStep sysTempStepOV, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysTempStepService.saveOrUpdate(sysTempStepOV);
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

	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysTempStepService.deleteSysSteps(StrToObjUtils.toList(selIds, ","));
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

	/**
	 * 打开修改环节模板页面
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysTempStep sysTempStep = sysTempStepService.get(id);
		request.setAttribute("sysTempStep", sysTempStep);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysTempStep/edit";
	}

	/**
	 * 选择拥有者
	 */
	@RequestMapping("chooseOwner_{type}")
	public String chooseOwner(@PathVariable(value = "type") String type) {
		return "/sysmng/sysTempStep/chooseOwner_" + type;
	}

}