/**
 * Copyright(C) 2017  Luvina Software Company
 * LoginFilter.java, 07/02/2017 Doan Trong Hieu
 */
package manageuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;

/**
 * Lớp filter có tác dụng kiểm tra login tại các trang
 * 
 * @author Doan Trong Hieu
 *
 */
@WebFilter(urlPatterns = "*.login")
public class LoginFilters implements Filter {
	/**
	 * bộ lọc đăng nhập
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		// kiểm tra phiên đăng nhập
		if (session.getAttribute("loginName") != null) {
			chain.doFilter(req, res);
		} else {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(Constant.INDEX);
		}

	}
}
