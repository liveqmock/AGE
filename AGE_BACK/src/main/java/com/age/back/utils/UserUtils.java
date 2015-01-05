package com.age.back.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.age.back.common.Constants;
import com.age.back.sysmng.model.SysUser;

public class UserUtils {

	public static final void setCurrentUser(SysUser user) {

		Subject currentUser = SecurityUtils.getSubject();

		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(Constants.CURRENT_USER, user);
			}
		}
	}

	public static final SysUser getCurrentUser() {

		Subject currentUser = SecurityUtils.getSubject();

		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				SysUser user = (SysUser) session.getAttribute(Constants.CURRENT_USER);
				if (null != user) {
					return user;
				}
			}
		}
		return null;
	}
}
