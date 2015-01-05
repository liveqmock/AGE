package com.age.back.frame.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping("/error/403")
	public String error403(HttpServletRequest request) {
		return "/error/403";
	}
}