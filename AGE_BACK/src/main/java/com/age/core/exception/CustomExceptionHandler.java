package com.age.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.age.core.utils.web.ServletUtils;

public class CustomExceptionHandler extends SimpleMappingExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {

		String msg = "\n" + "exception url: " + request.getRequestURL().toString() + "\n" + "exception detail：";
		logger.error(msg, exception);

		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request
				.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {

			if (exception instanceof UnauthorizedException) {
				return new ModelAndView("/error/403");
			} else if (exception instanceof Exception) {
				return new ModelAndView("/error/defaultError");
			}

		} else {// JSON格式返回   
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSuccess", false);
			map.put("message", "系统发生内部错误,操作失败!");
			ServletUtils.responseJson(response, map);
		}

		return null;
	}
}
