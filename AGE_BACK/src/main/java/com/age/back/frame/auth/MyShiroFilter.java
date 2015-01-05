package com.age.back.frame.auth;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyShiroFilter extends FormAuthenticationFilter {
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest req = WebUtils.toHttp(request);

		HttpServletResponse res = WebUtils.toHttp(response);
		if (req.getHeader("x-requested-with") != null
				&& req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			res.setHeader("sessionStatus", "sessionOut");
			res.getWriter().write("session 超时");
		} else {
			String loginUrl = getLoginUrl();
			WebUtils.issueRedirect(request, response, loginUrl);
		}
	}
}
