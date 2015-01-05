package com.age.back.sysmng.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.age.back.sysmng.model.SysBulletin;
import com.age.back.sysmng.model.SysBulletinFile;
import com.age.back.sysmng.model.SysRole;
import com.age.back.sysmng.service.ISysBulletinService;
import com.age.back.sysmng.service.ISysRoleService;
import com.age.core.exception.ServiceException;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.web.ServletUtils;

@Controller
@RequestMapping("/sysmng/sysBulletin")
public class SysBulletinController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysBulletinService sysBulletinService;
	@Autowired
	private ISysRoleService sysRoleService;

	@RequestMapping("list")
	public String list() {
		return "/sysmng/sysBulletin/list";
	}

	/**
	 * 公告管理列表
	 * @param request
	 * @param response
	 * @param sysBulletin
	 */
	@RequestMapping("getTreeList")
	public void getTreeList(HttpServletRequest request, PageRequest pageRequest, HttpServletResponse response, SysBulletin sysBulletin) {
		Page<SysBulletin> sysBulletinVOs = sysBulletinService.findSysBulletin(pageRequest, sysBulletin);
		ServletUtils.responseJson(response, sysBulletinVOs.toGridMap());
	}

	/**
	 * 打开新增公告页面
	 * @return
	 */
	@RequestMapping("create")
	public String create() {
		return "/sysmng/sysBulletin/edit";
	}

	/**
	 * 打开修改公告页面
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(HttpServletRequest request, HttpServletResponse responset) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysBulletin sysBulletin = sysBulletinService.get(id);
		List<SysBulletinFile> list = sysBulletinService.findSysBulletinFile(id);
		request.setAttribute("sysBulletin", sysBulletin);
		request.setAttribute("list", list);
		request.setAttribute("size", list.size());
		request.setAttribute("pageType", "edit");
		return "/sysmng/sysBulletin/edit";
	}

	/**
	 * 新增或修改公告操作
	 * @param sysRoleVO
	 * @param sysBulletin
	 * @param request
	 * @param response
	 */
	@RequestMapping("save")
	public String save(SysBulletin sysBulletin, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		String message = "";
		String id = sysBulletin.getId();
		try {
			sysBulletin = sysBulletinService.saveOrUpdate(request, sysBulletin);
			message = "操作成功";
		} catch (ServiceException e) {
			message = e.getMessage();
			logger.error(e.getMessage(), e);
			if (StringUtils.isNotBlank(id)) {
				return "redirect:/sysmng/sysBulletin/edit?id=" + id;
			} else {
				return "redirect:/sysmng/sysBulletin/create";
			}
		} catch (Exception e) {
			message = "操作失败";
			logger.error(e.getMessage(), e);
			if (StringUtils.isNotBlank(id)) {
				return "redirect:/sysmng/sysBulletin/edit?id=" + id;
			} else {
				return "redirect:/sysmng/sysBulletin/create";
			}
		}
		attr.addFlashAttribute("message", message);
		request.setAttribute("message", message);
		return "redirect:/sysmng/sysBulletin/edit?id=" + sysBulletin.getId();
	}

	@RequestMapping("ckeditorUploadr")
	public void ckeditorUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			sysBulletinService.ckeditorUpload(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * 查看公告
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("view")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String id = ServletUtils.getStringParameter(request, "id");
		SysBulletin sysBulletin = sysBulletinService.get(id);
		request.setAttribute("sysBulletin", sysBulletin);
		return "/sysmng/sysBulletin/view";
	}

	/**
	 * 发布或撤销
	 * @param request
	 * @param response
	 */
	@RequestMapping("releaseOrCancel")
	public void releaseOrCancel(HttpServletRequest request, HttpServletResponse response) {
		//要撤销或发布的数据
		String selIds = ServletUtils.getStringParameter(request, "selIds");
		//0=初始；1=发布；2=撤销；
		String status = ServletUtils.getStringParameter(request, "status");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> resourceIdsList = StrToObjUtils.toList(selIds, ",");
			sysBulletinService.releaseOrCancelSysBulletin(resourceIdsList, status);
			map.put("isSuccess", true);
			map.put("message", "操作成功");
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

	@RequestMapping("/showImg")
	public void showImg(HttpServletRequest request, HttpServletResponse response) {
		try {
			//获取图片路径
			String realPath = request.getParameter("path");
			File file = new File(realPath);
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			//获取输出流
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("image/jpeg");
			//在页面上输出图片
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}