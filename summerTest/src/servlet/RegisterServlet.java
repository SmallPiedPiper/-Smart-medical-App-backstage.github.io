package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import domain.User;

public class RegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  identity = null;
		String username = request.getParameter("name");
		String password = request.getParameter("pass");
		identity= request.getParameter("identity");
		
		String yanzhengma = request.getParameter("yanzhengma");
		User insertUser = new User();
		insertUser.setPassword(password);
		insertUser.setUsername(username);
		insertUser.setIdentity(identity);
	
		insertUser.setYanzhengma(yanzhengma);
		UserDao userDao = new UserDao();
		userDao.register(insertUser, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
