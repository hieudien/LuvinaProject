/**
 * Copyright(C) 2017  Luvina Software Company
 * RequestEncodingFilter.java, 07-02-2017 Doan Trong Hieu
 */
package manageuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Lớp Filter set encoding cho request
 * @author LA-AM
 *
 */
@WebFilter(urlPatterns = "*")
public class RequestEncodingFilter implements Filter {

	/*
	 * set encoding cho request
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		chain.doFilter(req, res);
	}

}
