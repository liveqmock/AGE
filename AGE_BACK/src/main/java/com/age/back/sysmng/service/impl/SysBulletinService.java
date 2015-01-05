package com.age.back.sysmng.service.impl;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.age.back.sysmng.common.SysMngContstant;
import com.age.back.sysmng.dao.SysBulletinDao;
import com.age.back.sysmng.dao.SysBulletinFileDao;
import com.age.back.sysmng.model.SysBulletin;
import com.age.back.sysmng.model.SysBulletinFile;
import com.age.back.sysmng.service.ISysBulletinService;
import com.age.back.utils.FileUploadUtils;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;
import com.age.core.utils.DateUtils;
import com.age.core.utils.IdUtils;
import com.age.core.utils.StrToObjUtils;
import com.age.core.utils.spring.SpringPropertyConfigurer;

@Service
public class SysBulletinService implements ISysBulletinService {
	@Autowired
	private SysBulletinDao sysBulletinDao;
	@Autowired
	private SysBulletinFileDao sysBulletinFileDao;

	@Override
	public SysBulletin saveOrUpdate(HttpServletRequest request, SysBulletin sysBulletin) throws Exception {
		SysBulletin bulletin = null;
		if (StringUtils.isBlank(sysBulletin.getId())) {
			//新规
			bulletin = new SysBulletin();
			bulletin.setId(IdUtils.uuid32());
			bulletin.setStatus(String.valueOf(SysMngContstant.STATUS_INIT));
			bulletin.setRecCreateTime(DateUtils.currentDatetime());
			int orderNo = sysBulletinDao.findMaxOrderNo();
			bulletin.setOrderNo(orderNo);
		} else {
			//存在
			bulletin = sysBulletinDao.get(sysBulletin.getId());
			bulletin.setOrderNo(sysBulletin.getOrderNo());

			//删除页面删除的附件
			String deleteId = request.getParameter("deleteId");
			if (StringUtils.isNotBlank(deleteId)) {
				sysBulletinFileDao.deleteSysBulletinFile(StrToObjUtils.toList(deleteId, ","));
			}
		}
		//标题
		bulletin.setTitle(sysBulletin.getTitle());
		//内容
		bulletin.setContent(sysBulletin.getContent());

		sysBulletinDao.saveOrUpdate(bulletin);

		//保存文件
		//获取上传的文件对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("_upfile");
		int orderNo = 0;
		for (MultipartFile mf : fileList) {
			if (mf.getSize() > 0) {
				orderNo = sysBulletinFileDao.findMaxOrderNo();
				SysBulletinFile sbf = new SysBulletinFile();
				sbf.setId(IdUtils.uuid32());
				sbf.setSysBulletin(bulletin);
				sbf.setRecCreateTime(DateUtils.currentDatetime());

				String url = FileUploadUtils.saveUploadFileToDisk(mf, multipartRequest, sbf.getId());
				sbf.setFileUrl(url);
				sbf.setOrderNo(orderNo);
				sbf.setFileName(mf.getOriginalFilename());
				sysBulletinFileDao.saveOrUpdate(sbf);
			}
		}
		return bulletin;
	}

	/**
	 * 根据id查看公告
	 * @param id
	 * @return
	 */
	@Override
	public SysBulletin get(String id) {
		return sysBulletinDao.get(id);
	}

	/**
	 * 撤销或发布公告
	 */
	@Override
	public void releaseOrCancelSysBulletin(List<String> ids, String status) {
		sysBulletinDao.releaseOrCancelSysBulletin(ids, status);
	}

	/**
	 * 查找公告列表
	 */
	@Override
	public Page<SysBulletin> findSysBulletin(PageRequest pageRequest, SysBulletin sysBulletin) {
		Page<SysBulletin> page = new Page<SysBulletin>();
		page = sysBulletinDao.findBulletinList(pageRequest, sysBulletin);
		if (page != null && page.getResult().size() > 0) {
			for (SysBulletin s : page.getResult()) {
				if (s != null && StringUtils.isNotBlank(s.getStatus())) {
					s.setStatus(SysMngContstant.getStatusCN(s.getStatus()));
				} else {
					s.setStatus("未知状态");
				}
			}
		}
		return page;
	}

	@Override
	public List<SysBulletinFile> findSysBulletinFile(String sysBulletinId) {
		SysBulletinFile sbf = new SysBulletinFile();
		sbf.setSysBulletin(new SysBulletin(sysBulletinId));
		return sysBulletinFileDao.findSysBulletinFiles(sbf);
	}

	@Override
	public String ckeditorUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();

		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");

		//获取上传的文件对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upload");
		String fileName = file.getOriginalFilename();
		String expandedName = ""; // 文件扩展名
		if (StringUtils.isNotBlank(fileName)) {
			if (fileName.indexOf(".") != -1) {
				expandedName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			}
		}
		if ("jpg".equals(expandedName) || "jpeg".equals(expandedName) || "png".equals(expandedName) || "gif".equals(expandedName) || "bmp".equals(expandedName)) {
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'','文件格式不正确（必须为.jpg/.jpeg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}

		if (file.getSize() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return null;
		}

		String url = FileUploadUtils.saveUploadFileToDisk(file, multipartRequest, IdUtils.uuid32());
		url = url.replace("\\", "\\\\");
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
		String uploadPath = SpringPropertyConfigurer.getProperty("file.upload.path");
		String returnPath = basePath + "/sysmng/sysBulletin/showImg" + "?path=" + uploadPath + "/" + url;
		// 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + returnPath + "','')"); // 相对路径用于显示图片
		out.println("</script>");
		return null;
	}
}
