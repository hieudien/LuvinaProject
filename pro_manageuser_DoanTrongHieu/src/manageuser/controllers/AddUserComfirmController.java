/**
 * Copyright(C) 2017  Luvina Software Company
 * AddUserComfirmController.java, 16-02-2017 Doan Trong Hieu
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

import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.UserInforAdd;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLoginImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * @author LA-AM
 *
 */
@WebServlet({ "/" + Constant.ADD_USER_CONFIRM, Constant.ADD_USER_OK })
public class AddUserComfirmController extends HttpServlet {
	private TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
	private TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
	private MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
	private MstJapanLoginImpl mstJapanLoginImpl = new MstJapanLoginImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String urlRequest =
		// req.getRequestURI().substring(req.getContextPath().length());
		String sessionId = req.getParameter("sessionId");
		HttpSession session = req.getSession(true);
		UserInforAdd userInforAdd = (UserInforAdd) session.getAttribute(sessionId);
		req.setAttribute("userInfo", userInforAdd);
		req.setAttribute("sessionId", sessionId);
		// Lấy danh sách các group trong db
		req.setAttribute("listGroup", mstGroupLogicImpl.getAllGroup());
		req.setAttribute("listJapan", mstJapanLoginImpl.getAllJapan());
		RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.ADM004);
		dispatcher.forward(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy session id từ req
		String sessionId = req.getParameter("sessionId");
		// lấy userInfoAđ từ session
		HttpSession session = req.getSession(true);
		UserInforAdd userInforAdd = (UserInforAdd) session.getAttribute(sessionId);
		// tạo câu thông báo LỖI tại màn ADM006
		String message = MessageErrorProperties.getErrorMessage(Constant.ER015);
		try {
			// nếu thêm bản ghi mới thành công thì set giá trị thông báo thành
			// công
			if (tblUserLogicImpl.createUserInfo(userInforAdd)) {
				message = MessageErrorProperties.getErrorMessage(Constant.MSG001);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// gửi thông báo qua req rồi chuyển tới màn ADM006
		req.setAttribute("message", message);
		RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.ADM006);
		dispatcher.forward(req, resp);
	}
}
