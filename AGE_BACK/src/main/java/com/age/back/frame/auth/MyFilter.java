package com.age.back.frame.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyFilter extends FormAuthenticationFilter {
	//判断url的CA需求
	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		boolean result = true;
		HttpServletRequest req = WebUtils.toHttp(request);
		final String URL = req.getServletPath();
		return result;
	}
}
