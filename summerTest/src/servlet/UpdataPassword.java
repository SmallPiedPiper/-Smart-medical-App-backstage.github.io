package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import domain.User;

public class UpdataPassword extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String yanzhengma = request.getParameter("yanzhengma"); 
		User insertUser = new User();
		insertUser.setUsername(name);
		insertUser.setPassword(pass);
		insertUser.setYanzhengma(yanzhengma);
		
		UserDao userDao = new UserDao();
		userDao.updatapassword(insertUser, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		}

}
