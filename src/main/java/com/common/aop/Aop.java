package com.common.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.common.result.Result;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;

@Component
@Aspect
public class Aop {

	private static Logger log = LoggerFactory.getLogger(Aop.class);

	// 切面统一请求参数日志
	@Before(value = "execution( * framework.controller..*.*(..))")
	public void requestLog(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		StringBuilder sb = new StringBuilder();
		sb.append("[请求地址]:" + request.getRequestURL().toString());
		sb.append(" [请求参数]__:");
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			sb.append(paraName + "= " + request.getParameter(paraName) + ",");
		}
		sb.append(" [请求IP]:" + request.getRemoteAddr());
		log.info(sb.toString());
	}

	// 切入点
	@Pointcut("execution( * find*(..))")
	public void Pointcut() {
	}







	// 后置异常通知
	@AfterThrowing(value = "execution( * find*(..))")
	public void throwss() {
		System.out.println("方法异常时执行.....");
	}



	// 后置正常返回
	@AfterReturning(value = "execution(* com.jason.spring.aop.impl.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " end with " + result);
	}



}
