/**
 * Copyright(C) 2017  Luvina Software Company
 * LoginController.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Class xử lý cho màn hình ADM001
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet(Constant.LOGIN_ANOTATION)
public class LoginController extends HttpServlet {
	private ValidateUser validateUser = new ValidateUser();
	private TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();

	/**
	 * Phương thức xử lý cho màn hình ADM001 khi có một request dưới dạng post.
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy tên đăng nhập và mật khẩu từ req
		String loginName = req.getParameter("loginName");
		String password = req.getParameter("password");
		// kiểm tra tài khoản người dùng
		List<String> listError = validateUser.validateLogin(loginName, password);
		// nếu tài khoản hợp lệ
		if (listError.size() == 0) {
			// lưu tên đăng nhập lên session
			HttpSession session = req.getSession(true);
			session.setAttribute("loginName", loginName);
			// chuyển tới ADM002
			resp.sendRedirect(Constant.LIST_USER_ANOTATION + "?type=first");
		} else {
			// nếu tài khoản không hợp lệ thì hiển thị thông báo tại ADM001
			req.setAttribute("listError", listError);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/" + Constant.ADM001);
			dispatcher.forward(req, resp);
		}
	}

	/**
	 * Phương thức xử lý cho màn hình ADM001 khi có một request dưới dạng get.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// KIỂM TRA PHIÊN ĐĂNH NHẬP
		HttpSession session = req.getSession(false);
		if (Common.checkLogin(session)) {
			resp.sendRedirect(Constant.LIST_USER_ANOTATION);
			return;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/" + Constant.ADM001);
		dispatcher.forward(req, resp);
	}
}
