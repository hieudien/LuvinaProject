/**
 * Copyright(C) 2017  Luvina Software Company
 * ListUserController.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.UserInfor;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Class xử lý cho màn hình ADM002
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet("/"+Constant.LIST_USER_ANOTATION)
public class ListUserController extends HttpServlet {
	private TblUserLogicImpl userLogic = new TblUserLogicImpl();
	private MstGroupLogicImpl groupLogic = new MstGroupLogicImpl();

	/**
	 * Phương thức xử lý khi có một request dưới dạng get.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * Phương thức xử lý khi có một request dưới dạng post.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		// Lấy kiểu thao tác của người dùng tạo page ADM002
		String type = (String) req.getParameter("type");
		// nếu là lần đầu vào page
		if (Constant.FIRST.equals(type)) {
			session.setAttribute("sortType", Constant.FULL_NAME);
			session.setAttribute("listSortType", new String[] { Constant.ASC, Constant.ASC, Constant.DESC });
			session.setAttribute("searchName", "");
			session.setAttribute("groupId", 0);
			session.setAttribute("currentPage", 1);
		}
		//
		// lấy giá trị từ thao tác của người dùng
		String currentPage = req.getParameter("value");
		// lấy tên tìm kiếm
		String searchName = req.getParameter("name");
		// nếu tên tìm kiếm null (không phải case search) thì lấy tên từ
		// session
		if (searchName == null) {
			searchName = (String) session.getAttribute("searchName");
		}
		// lấy nhóm tìm kiếm
		int searchGroupId = 0;
		String groupId = req.getParameter("group_id");
		if (groupId == null) {
			searchGroupId = (int) session.getAttribute("groupId");
		} else {
			searchGroupId = Integer.parseInt(groupId);
		}
		// lấy giới hạn bản ghi/ trang
		int limit = Common.getLimit();
		// tổng số user theo id và tên
		int totalUser = userLogic.getTotalUsers(searchGroupId, searchName);
		// tổng số trang
		int totalPage = Common.getTotalPage(totalUser, limit);
		// trang hiện tại
		int currentPageInt = 1;
		// lấy kiểu sắp xếp
		String sortType = req.getParameter("value");
		// nếu kiểu sắp xếp null
		if (sortType == null) {
			// thì lấy kiểu sắp xếp từ session
			sortType = (String) session.getAttribute("sortType");
			if (sortType == null) {
				// nếu vẫn null thì gán mặc định
				sortType = Constant.FULL_NAME;
			}
		}
		// lấy danh sách kiểu sắp xếp
		String[] listSortType = (String[]) session.getAttribute("listSortType");

		// kiểm tra thao tác của người dùng
		if (Constant.SEARCH.equals(type)) {
			// set tên tìm kiếm
			session.setAttribute("searchName", searchName);
//			session.setAttribute("currentPage", 1);
			// set id nhóm tìm kiếm
//			try {
//				searchGroupId = Integer.parseInt(groupId);
//			} catch (NumberFormatException e) {
//				searchGroupId = 1;
//			}
			session.setAttribute("groupId", searchGroupId);
			session.setAttribute("currentPage", 1);
			totalUser = userLogic.getTotalUsers(searchGroupId, searchName);
		}
		// nếu type = sắp xếp
		if (Constant.SORT.equals(type)) {
			currentPage = "1";
			switch (sortType) {
			case Constant.FULL_NAME:
				if (Constant.ASC.equals(listSortType[0])) {
					listSortType[0] = Constant.DESC;
				} else {
					listSortType[0] = Constant.ASC;
				}
				listSortType[1] = Constant.ASC;
				listSortType[2] = Constant.DESC;
				break;
			case Constant.CODE_LEVEL:
				if (Constant.ASC.equals(listSortType[1])) {
					listSortType[1] = Constant.DESC;
				} else {
					listSortType[1] = Constant.ASC;
				}
				listSortType[0] = Constant.ASC;
				listSortType[2] = Constant.DESC;
				break;
			case Constant.END_DATE:
				if (Constant.ASC.equals(listSortType[2])) {
					listSortType[2] = Constant.DESC;
				} else {
					listSortType[2] = Constant.ASC;
				}
				listSortType[0] = Constant.ASC;
				listSortType[1] = Constant.ASC;
				break;
			default:
				break;
			}
			session.setAttribute("listSortType", listSortType);
		}
		// nếu type = pagging
		if (Constant.PAGE.equals(type)) {
			try {
				currentPageInt = Integer.parseInt(currentPage);
			} catch (NumberFormatException e) {
				currentPageInt = 1;
			}
			session.setAttribute("currentPage", currentPageInt);
		}

		int offSet = Common.getOffset(currentPageInt, limit);

		List<Integer> listPaging = Common.getListPaging(totalUser, limit, currentPageInt);
		// lấy danh sách các nhóm
		List<MstGroup> listGroup = groupLogic.getAllGroup();
		listGroup.add(0, new MstGroup(0, Constant.ALL));
		// lấy danh sách user theo điều kiện thích hợp
		List<UserInfor> listUser = userLogic.getListUsers(offSet, limit, searchGroupId, searchName, sortType,
				listSortType[0], listSortType[1], listSortType[2]);
		// set các điều kiện hiển thị vào request

		req.setAttribute("searchGroupId", searchGroupId);
		req.setAttribute("searchName", searchName);
		req.setAttribute("listSortType", listSortType);
		req.setAttribute("listGroup", listGroup);
		req.setAttribute("listUser", listUser);
		req.setAttribute("listPaging", listPaging);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("currentPage", currentPage);
		// chuyển tới màn hình ADM002
		RequestDispatcher dispatcher = req.getRequestDispatcher("/" + Constant.ADM002);
		dispatcher.forward(req, resp);
	}
}
