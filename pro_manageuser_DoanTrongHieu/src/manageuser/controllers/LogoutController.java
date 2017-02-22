/**
 * Copyright(C) 2017  Luvina Software Company
 * LogoutController.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;

/**
 * Class xử lý khi User logout khỏi hệ thống.
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet(Constant.LOGOUT_ANOTATION)
public class LogoutController extends HttpServlet {
	/**
	 * Phương thức xử lý khi người dùng click vào logout. Thực hiện xóa session
	 * và chuyển tiếp về trang Index.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// hủy sesion
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// chuyển về trang chủ
		resp.sendRedirect(req.getContextPath());
	}
}
