package com.age.back.sysmng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.age.back.frame.auth.CollectionUtils;
import com.age.back.sysmng.model.SysResource;
import com.age.back.sysmng.model.SysUser;
import com.age.back.utils.UserUtils;

public class ShiroRealm extends AuthorizingRealm {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected ISysUserService sysUserService;

	@Autowired
	protected ISysResourceService sysResourceService;

	public ISysResourceService getSysResourceService() {
		return sysResourceService;
	}

	public void setSysResourceService(ISysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}

	//shiro默认的链接定义
	private String filterChainDefinitions;

	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions 默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	/**
	 * 验证授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.fromRealm(getName()).iterator().next();
		if (username != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			SysUser user = sysUserService.findSysUserByUserAccount(username);
			List<String> list = new ArrayList<String>();
			List<String> resourceList = sysResourceService.findSysResourcesByUsrAccount(username);
			for (String s : resourceList) {
				list.add(s.replace("/", ":").substring(1));
			}
			info.addStringPermissions(list);
			return info;
		}
		return null;
	}

	/**
	 * 身份验证的意思，是一个动作或事件处理
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = sysUserService.findSysUserByUserAccount(token.getUsername());
		List<SysResource> l = sysResourceService.findSysResourcesByISCA();
		List<String> list = new ArrayList<String>();
		for (SysResource s : l) {
			list.add(s.getUrl());
		}
		if (user != null) {
			String userInfo = StringUtils.isNotEmpty(user.getUserName()) ? user.getUserName() : user.getCa();
			UserUtils.setCurrentUser(user);
			return new SimpleAuthenticationInfo(userInfo, user.getPassword(), getName());
		} else
			return null;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	/**
	* 通过资源集合，将集合中的permission字段内容解析后添加到SimpleAuthorizationInfo授权信息中
	* 
	* @param info SimpleAuthorizationInfo
	* @param authorizationInfo 资源集合
	*/
	private void addPermissions(SimpleAuthorizationInfo info, List<SysResource> authorizationInfo) {
		System.out.println("addPermissions");
		//解析当前用户资源中的permissions
		List<String> temp = CollectionUtils.extractToList(authorizationInfo, "resNo", true);
		List<String> permissions = getValue(temp, "perms\\[(.*?)\\]");
		info.addStringPermissions(permissions);

	}

	/**
	 * 通过正则表达式获取字符串集合的值
	 * 
	 * @param obj 字符串集合
	 * @param regex 表达式
	 * 
	 * @return List
	 */
	private List<String> getValue(List<String> obj, String regex) {
		System.out.println("getValue");
		List<String> result = new ArrayList<String>();
		if (CollectionUtils.isEmpty(obj)) {
			return result;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(StringUtils.join(obj, ","));
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		return result;
	}

}
