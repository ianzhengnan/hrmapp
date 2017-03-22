package com.ian.hrmapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ian.hrmapp.domain.User;
import com.ian.hrmapp.util.common.HrmConstants;

public class AuthorizedInterceptor implements HandlerInterceptor{

	private static final String[] IGNORE_URI = {"/loginForm", "/login", "/404.html"};
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		boolean flag = false;
		
		String servletPath = request.getServletPath();
		
		for (String uri : IGNORE_URI) {
			if (servletPath.contains(uri)) {
				flag = true;
				break;
			}
		}
		
		if(!flag){
			User user = (User) request.getSession().getAttribute(HrmConstants.USER_SESSION);
			if (user == null) {
				request.setAttribute("message", "请先登录再访问网站！");
				request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
				return flag;
			}else{
				flag = true;
			}
		}
		return flag;
	}
}
