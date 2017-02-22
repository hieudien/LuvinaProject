/**
 * Copyright(C) 2017  Luvina Software Company
 * AddUserInputController.java, 05/02/2017 Doan Trong Hieu
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.webkit.BackForwardList;

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.UserInforAdd;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLoginImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Class xử lý thêm User vào DB
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet({ Constant.ADD_USER, Constant.ADD_USER_VALIDATE })
public class AddUserInputController extends HttpServlet {
	private MstGroupLogicImpl groupLogic = new MstGroupLogicImpl();
	private MstJapanLoginImpl mstJapanLoginImpl = new MstJapanLoginImpl();

	/**
	 * Phương thức xử lí khi có request dưới dạng get
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setDataLogicADM003(req, resp);
		UserInforAdd userInfor = setValue(req, resp);
		String sessionId = Common.generateSessionId();
		req.setAttribute("sessionId", sessionId);
		req.setAttribute("userInfo", userInfor);

		RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.ADM003);
		dispatcher.forward(req, resp);
	}

	/**
	 * Phuong thức set giá trị mặc định cho input
	 * 
	 * @param req
	 * @param resp
	 */
	private UserInforAdd setValue(HttpServletRequest req, HttpServletResponse resp) {
		UserInforAdd userInfor = new UserInforAdd();
		// lấy url req để phân biệt trường hợp lần đầu vào và trường hợp click
		// xác nhận
		String urlRequest = req.getRequestURI().substring(req.getContextPath().length());
		String type = req.getParameter("type");
		// nếu là back từ 004 về thì giữ nguyên data
		if (Constant.BACK.equals(type)) {
			// nếu là trường hợp back từ ADM004
			String sessionId = req.getParameter("sessionId");
			HttpSession session = req.getSession(true);
			userInfor = (UserInforAdd) session.getAttribute(sessionId);
			userInfor.setPassword(Constant.EMPTY_STRING);
			userInfor.setRePassword(Constant.EMPTY_STRING);
			String[] birthDayArray = Common.dateStringToArray(userInfor.getBirthday());
			req.setAttribute("birthDayArray", birthDayArray);
			String[] startDayArray = Common.dateStringToArray(userInfor.getStartDate());
			req.setAttribute("startDayArray", startDayArray);
			String[] endDayArray = Common.dateStringToArray(userInfor.getEndDate());
			req.setAttribute("endDayArray", endDayArray);
			req.setAttribute("userInfo", userInfor);
		} else if (Constant.ADD_USER.equals(urlRequest)) {
			// trường hợp lần đầu vào thì set các thông tin mặc định là rỗng
			userInfor.setEmail(Constant.EMPTY_STRING);
			userInfor.setFullName(Constant.EMPTY_STRING);
			userInfor.setTel(Constant.EMPTY_STRING);
			userInfor.setTotal(Constant.EMPTY_STRING);
			userInfor.setUserName(Constant.EMPTY_STRING);
			userInfor.setKatakanaName(Constant.EMPTY_STRING);
			userInfor.setPassword(Constant.EMPTY_STRING);
			userInfor.setRePassword(Constant.EMPTY_STRING);
		} else if (Constant.ADD_USER_VALIDATE.equals(urlRequest)) {
			// System.out.println((String) req.getParameter("userName"));
			// System.out.println((String) req.getParameter("groupId"));
			// System.out.println((String) req.getParameter("fullName"));
			// System.out.println((String) req.getParameter("katakanaName"));
			// System.out.println((String) req.getParameter("email"));
			// System.out.println((String) req.getParameter("tel"));
			// System.out.println((String) req.getParameter("password"));
			// System.out.println((String) req.getParameter("rePassword"));
			// System.out.println((String) req.getParameter("codeLevel"));
			// System.out.println((String) req.getParameter("total"));

			userInfor.setUserName((String) req.getParameter("userName"));
			userInfor.setGroupId((String) req.getParameter("groupId"));
			userInfor.setFullName((String) req.getParameter("fullName"));
			userInfor.setKatakanaName((String) req.getParameter("katakanaName"));
			// lấy ngày sinh
			String birthYear = (String) req.getParameter("birthYear");
			String birthMonth = (String) req.getParameter("birthMonth");
			String birthDay = (String) req.getParameter("birthDay");
			String userBirthday = Common.inputDateToString(birthYear, birthMonth, birthDay);
			String[] birthDayArray = Common.dateStringToArray(userBirthday);
			req.setAttribute("birthDayArray", birthDayArray);
			userInfor.setBirthday(userBirthday);

			userInfor.setEmail((String) req.getParameter("email"));
			userInfor.setTel((String) req.getParameter("tel"));
			userInfor.setPassword((String) req.getParameter("password"));
			userInfor.setRePassword((String) req.getParameter("rePassword"));
			userInfor.setCodeLevel((String) req.getParameter("codeLevel"));
			// lấy ngày bắt đầu
			String startYear = (String) req.getParameter("startYear");
			String startMonth = (String) req.getParameter("startMonth");
			String startDay = (String) req.getParameter("startDay");
			String userStartDate = Common.inputDateToString(startYear, startMonth, startDay);
			String[] startDayArray = Common.dateStringToArray(userStartDate);
			req.setAttribute("startDayArray", startDayArray);
			userInfor.setStartDate(userStartDate);
			// lấy ngày hết hạn
			String endYear = (String) req.getParameter("endYear");
			String endMonth = (String) req.getParameter("endMonth");
			String endDay = (String) req.getParameter("endDay");
			String userEndDate = Common.inputDateToString(endYear, endMonth, endDay);
			String[] endDayArray = Common.dateStringToArray(userEndDate);
			req.setAttribute("endDayArray", endDayArray);
			userInfor.setEndDate(userEndDate);

			userInfor.setTotal((String) req.getParameter("total"));
		}
		return userInfor;

	}

	/**
	 * Set thông tin cho các trường combo box
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setDataLogicADM003(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Lấy danh sách các group trong db
		List<MstGroup> listGroup = groupLogic.getAllGroup();
		listGroup.add(0, new MstGroup(0, Constant.CHOOSE_ONE));

		// lấy danh sách trình độ tiếng Nhật trong db
		List<MstJapan> listJapan = mstJapanLoginImpl.getAllJapan();
		listJapan.add(0, new MstJapan("", Constant.CHOOSE_ONE));

		// lấy danh sách năm
		ArrayList<Integer> listYear = Common.getListYear(Constant.YEAR_START, Common.getYearNow());
		ArrayList<Integer> endYear = Common.getListYear(Constant.YEAR_START, Common.getYearNow() + 1);
		// lấy danh sách tháng
		ArrayList<Integer> listMonth = Common.getListMonth();
		// lấy danh sách ngày
		ArrayList<Integer> listDay = Common.getListDay();
		// lấy ngày hiện tại
		int[] currentDate = Common.getCurrentDate();
		// set các thuộc tính lên request
		req.setAttribute("listGroup", listGroup);
		req.setAttribute("listJapan", listJapan);
		req.setAttribute("listYear", listYear);
		req.setAttribute("endYear", endYear);
		req.setAttribute("listMonth", listMonth);
		req.setAttribute("listDay", listDay);
		req.setAttribute("birthDayArray", currentDate);
		req.setAttribute("startDayArray", currentDate);
		req.setAttribute("endDayArray", currentDate);
	}

	/**
	 * Phương thức xử lí khi có request dưới dạng POST
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sessionId = req.getParameter("sessionId");
		// lấy thông tin đã nhập để validate
		UserInforAdd userInfor = setValue(req, resp);
		// danh sách lỗi sau khi validate
		ArrayList<String> listError = ValidateUser.validateUserInfo(userInfor);
		setDataLogicADM003(req, resp);
		// nếu không có lỗi thì chuyển sang ADM004
		if (listError.size() == 0) {
			HttpSession session = req.getSession(true);
			session.setAttribute(sessionId, userInfor);
			resp.sendRedirect(Constant.ADD_USER_CONFIRM + "?sessionId=" + sessionId);
			// RequestDispatcher dispatcher = req
			// .getRequestDispatcher(Constant.ADD_USER_CONFIRM + "?sessionId=" +
			// sessionId);
			// dispatcher.forward(req, resp);
		} else {
			// nếu có lỗi thì hiển thị thông báo ở ADM003
			// set lỗi lên req
			req.setAttribute("listError", listError);
			// điền combo box
			// setDataLogicADM003(req, resp);
			// set thông tin đã nhập
			setValue(req, resp);
			req.setAttribute("userInfo", userInfor);
			// hiển thị thông báo ở ADM003
			RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.ADM003);
			dispatcher.forward(req, resp);
		}

	}
}
