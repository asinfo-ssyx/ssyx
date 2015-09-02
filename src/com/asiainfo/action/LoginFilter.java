package com.asiainfo.action;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asiainfo.util.StringUtils;

public class LoginFilter implements javax.servlet.Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filter) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println(request.getServletPath());
//		if(request.getSession().getAttribute("perUser")==null
//				&& !"/errorms.jsp".equals(request.getServletPath())
//				&& !"/permission.jsp".equals(request.getServletPath()) && !"/activeAction!createFromBass".equals(request.getServletPath())){
//			request.getSession().setAttribute("errorMs", "ÇëÏÈµÇÂ¼");
//			response.sendRedirect(request.getContextPath()+ "/errorms.jsp");
//		}else{
			filter.doFilter(req, res);
//		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
