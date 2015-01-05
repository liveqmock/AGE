package com.age.back.frame.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.utils.UserUtils;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		String url = "login";
		if (UserUtils.getCurrentUser() == null) {
			request.setAttribute("message", "请先登录！");
		} else {
			url = "index";
			request.setAttribute("username", UserUtils.getCurrentUser() == null ? "" : UserUtils.getCurrentUser()
					.getUserName());
		}
		return url;
	}
}