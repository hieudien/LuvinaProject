/**
 * Copyright(C) 2017  Luvina Software Company
 * DetailUserController.java, 05/02/2017 Doan Trong Hieu
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
 * Class xử lý view chi tiết 1 User.
 * 
 * @author Doan Trong Hieu
 *
 */
@WebServlet(Constant.USER_DETAIL)
public class DetailUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = req
		.getRequestDispatcher("/jsp/ADM005.jsp");
	dispatcher.forward(req, resp);
    }
}
