package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class getAllUserDataSerlvet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("text/html;charset=utf-8");
			
	     request.setCharacterEncoding("utf-8");
	     response.setCharacterEncoding("utf-8");
	     UserDao userDao = new UserDao();
	     userDao.getAllUserData(response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("text/html;charset=utf-8");
	     request.setCharacterEncoding("utf-8");
	     response.setCharacterEncoding("utf-8");
	     doPost(request, response);
		
	}

}
