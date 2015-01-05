package com.age.back.sysmng.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.common.SysMngContstant;
import com.age.back.sysmng.model.SysTempFlow;
import com.age.back.sysmng.model.SysTempFlowStep;
import com.age.back.sysmng.model.SysTempStep;
import com.age.back.sysmng.service.ISysTempFlowStepService;
import com.age.back.sysmng.service.ISysTempStepService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.IdUtils;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysTempFlowStep")
public class SysTempFlowStepController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ISysTempStepService sysTempStepService;
	@Autowired
	ISysTempFlowStepService sysTempFlowStepService;

	/******************************************************************************/

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysTempStep/list";
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

		sysTempStep.setState(String.valueOf(SysMngContstant.SYS_MNG_STATE_ENABLED));//启用
		Page<SysTempStep> sysTempSteps = sysTempStepService.findSysTempSteps(pageRequest, sysTempStep);
		ServletUtils.responseJson(response, sysTempSteps.toGridMap());
	}

	/**
	 * 打开新增环节页面
	 * 
	 * @return
	 */
	@RequestMapping("selectStep")
	public String selectStep(HttpServletRequest request, HttpServletResponse response) {
		String sysTempFlowID = ServletUtils.getStringParameter(request, "sysTempFlowID");
		String orderNo = request.getParameter("orderNo");
		request.setAttribute("sysTempFlowID", sysTempFlowID);
		request.setAttribute("orderNo", orderNo);
		return "/sysmng/sysTempFlowStep/list";
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
		SysTempStep sysTempStep = null;
		boolean updateFlag = false;

		if (StringUtils.isBlank(sysTempStepOV.getId())) {
			sysTempStep = new SysTempStep();
			sysTempStep.setId(IdUtils.uuid32());

		} else {
			sysTempStep = sysTempStepService.get(sysTempStepOV.getId());

		}

		sysTempStep.setStepName(sysTempStepOV.getStepName());
		sysTempStep.setDisplayName(sysTempStepOV.getDisplayName());
		sysTempStep.setRemark(sysTempStepOV.getRemark());
		sysTempStep.setOperateUrl(sysTempStepOV.getOperateUrl());
		sysTempStep.setOwnerName(sysTempStepOV.getOwnerName());
		sysTempStep.setOwnerType(sysTempStepOV.getOwnerType());
		sysTempStep.setOrderNo(sysTempStepOV.getOrderNo());
		sysTempStep.setOwnerId(sysTempStepOV.getOwnerId());
		sysTempStep.setStepNo(sysTempStepOV.getStepNo());

		sysTempStepService.saveOrUpdate(sysTempStep);
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
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("shureSelect")
	public void shureSelect(HttpServletRequest request, HttpServletResponse response) {
		String flowID = request.getParameter("sysTempFlowID");
		String orderNo = request.getParameter("orderNo");

		String selIds = ServletUtils.getStringParameter(request, "selIds");
		List<String> stepIds = StrToObjUtils.toList(selIds, ",");

		for (String id : stepIds) {
			SysTempFlowStep sysTempFlowStep = new SysTempFlowStep();
			sysTempFlowStep.setId(IdUtils.uuid32());
			SysTempFlow sysTempFlow = new SysTempFlow();
			sysTempFlow.setId(flowID);
			sysTempFlowStep.setSysTempFlow(sysTempFlow);

			SysTempStep sysTempStep = new SysTempStep();
			sysTempStep.setId(id);
			sysTempFlowStep.setSysTempStep(sysTempStep);

			sysTempFlowStep.setOrderNo(new BigDecimal(orderNo));

			sysTempFlowStepService.saveOrUpdate(sysTempFlowStep);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			map.put("isSuccess", true);
			map.put("message", "操作成功");
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
	@RequestMapping("flowStepEdit")
	public String flowStepEdit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysTempStep sysTempStep = sysTempStepService.get(id);
		request.setAttribute("sysTempStep", sysTempStep);
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysTempFlowStep/flowStepEdit";
	}

	/**
	 * 删除
	 * @param sysTempStepOV
	 * @param request
	 * @param response
	 */
	@RequestMapping("removeAll")
	public void removeAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = ServletUtils.getStringParameter(request, "selIds");
		String sysTempFlowID = ServletUtils.getStringParameter(request, "sysTempFlowID");
		sysTempFlowStepService.remove(ids, sysTempFlowID);
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

}