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

import com.age.back.sysmng.model.SysTempFlow;
import com.age.back.sysmng.model.SysTempStep;
import com.age.back.sysmng.service.ISysTempFlowService;
import com.age.back.sysmng.service.ISysTempStepService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysTempFlow")
public class SysTempFlowController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ISysTempFlowService sysTempFlowService;
	/******************************************************************************/
	@Autowired
	ISysTempStepService sysTempStepService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysTempFlow/list";
	}

	/**
	 * 流程模板列表
	 * 
	 * @param request
	 * @param response
	 * @param sysTempFlow
	 */
	@RequestMapping("getTempFlowList")
	public void getTempFlowList(HttpServletRequest request, PageRequest pageRequest, HttpServletResponse response, SysTempFlow sysTempFlow) {

		Page<SysTempFlow> sysTempFlows = sysTempFlowService.findSysTempFlows(pageRequest, sysTempFlow);
		ServletUtils.responseJson(response, sysTempFlows.toGridMap());
	}

	/**
	 * 打开新增模板页面
	 * 
	 * @return
	 */
	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysTempFlow/edit";
	}

	/**
	 * 打开修改流程模板页面
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysTempFlow sysTempFlow = sysTempFlowService.get(id);
		request.setAttribute("sysTempFlow", sysTempFlow);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysTempFlow/edit";
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

			sysTempFlowService.deleteSysTemps(StrToObjUtils.toList(selIds, ","));
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
	 * @param sysRoleVO
	 * @param request
	 * @param response
	 */
	@RequestMapping("save")
	public void save(SysTempFlow sysTempFlowOV, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		sysTempFlowService.saveOrUpdate(sysTempFlowOV);
		try {
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
	 * 分配环节
	 * @param request
	 * @return
	 */
	@RequestMapping("assignStep")
	public String assignStep(HttpServletRequest request) {
		String sysTempFlowID = ServletUtils.getStringParameter(request, "sysTempFlowID");
		request.setAttribute("sysTempFlowID", sysTempFlowID);
		return "/sysmng/sysTempFlow/assignStep";
	}

	/**
	 * 所属模板环节列表
	 * 
	 * @param request
	 * @param response
	 * @param sysTempFlow
	 */
	@RequestMapping("getTempStepListToFlow")
	public void getTempStepListToFlow(HttpServletRequest request, PageRequest pageRequest, HttpServletResponse response, SysTempStep sysTempStep) {
		//流程ID
		String flowID = request.getParameter("sysTempFlowID");
		Page<SysTempStep> sysTempSteps = sysTempStepService.findSysTempStepsToFlow(pageRequest, sysTempStep, flowID);

		ServletUtils.responseJson(response, sysTempSteps.toGridMap());
	}

	/**
	 * 环节模板列表
	 * 
	 * @param request
	 * @param response
	 * @param sysTempFlow
	 */
	@RequestMapping("getTempStepList")
	public void getTempStepList(HttpServletRequest request, PageRequest pageRequest, HttpServletResponse response, SysTempStep sysTempStep) {

		Page<SysTempStep> sysTempSteps = sysTempStepService.findSysTempSteps(pageRequest, sysTempStep);

		ServletUtils.responseJson(response, sysTempSteps.toGridMap());
	}

}