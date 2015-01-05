package com.age.back.frame.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.age.back.sysmng.service.ISysUserService;

@Controller
public class LoginController {
	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping("/login")
	public String index() {
		return "login";
	}

	@RequestMapping("/userLogin")
	public String userLogin(String username, String userpwd, HttpServletRequest request) {

		String returnUrl = "redirect:index";
		Subject currentUser = SecurityUtils.getSubject();
		String loginType = request.getParameter("loginType");

		if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(userpwd));
				try {
					currentUser.login(token);
				} catch (UnknownAccountException uae) {
					request.setAttribute("message", "用户账号：" + token.getPrincipal() + "不存在！");
					returnUrl = "login";
				} catch (IncorrectCredentialsException ice) {
					request.setAttribute("message", "密码错误！");
					returnUrl = "login";
				} catch (LockedAccountException lae) {
					request.setAttribute("message", "用户账号：" + token.getPrincipal() + "被锁定！");
					returnUrl = "login";
				} catch (AuthenticationException ae) {
					request.setAttribute("message", "登录异常！");
					returnUrl = "login";
				}

		}

		return returnUrl;
	}

	@RequestMapping("/userExit")
	public void userExit(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}

		request.getSession().invalidate();
	}

	@RequestMapping("/noPremission")
	public String noPremission() {
		return "noPremission";
	}
}