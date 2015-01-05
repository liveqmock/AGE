package com.age.back.sysmng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.age.back.sysmng.model.SysBulletin;
import com.age.back.sysmng.model.SysBulletinFile;
import com.age.core.orm.Page;
import com.age.core.orm.PageRequest;

public interface ISysBulletinService {

	public SysBulletin saveOrUpdate(HttpServletRequest request, SysBulletin bulletin) throws Exception;

	public String ckeditorUpload(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 根据id查看公告
	 * @param id
	 * @return
	 */
	public SysBulletin get(String id);

	/**
	 * 撤销或发布公告
	 * @param ids
	 * @param status 撤销或发布状态
	 */
	public void releaseOrCancelSysBulletin(List<String> ids, String status);

	/**
	 * 查找公告列表
	 */
	public Page<SysBulletin> findSysBulletin(PageRequest pageRequest, SysBulletin sysBulletin);

	public List<SysBulletinFile> findSysBulletinFile(String sysBulletinId);
}
