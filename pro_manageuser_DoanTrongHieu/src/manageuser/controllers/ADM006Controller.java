/**
 * Copyright(C) 2017  Luvina Software Company
 * AddUserInputController.java, 05/02/2017 Doan Trong Hieu
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;

/**
 * Class xử lý thêm User vào DB
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet(Constant.ADM006)
public class ADM006Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/ADM006.jsp");
		dispatcher.forward(req, resp);
	}
}
