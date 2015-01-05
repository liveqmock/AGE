package com.age.back.utils;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

public class AopTargetUtils {

	
	/**
	 * 获取 目标对象
	 * @param proxy 代理对象
	 * @return 
	 * @throws Exception
	 */
	public static Object getTarget(Object proxy) throws Exception {
        
		if(!AopUtils.isAopProxy(proxy)) {
			return proxy;//不是代理对象
		}else{
			return getTargetObject(proxy);
		}
	}
	private static Object getTargetObject(Object proxy) throws Exception {
        Advised aopProxy = (Advised)proxy;
        return aopProxy.getTargetSource().getTarget();
         
	}
	
}
	