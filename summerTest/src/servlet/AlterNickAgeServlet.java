package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDao;
import domain.UserData;

public class AlterNickAgeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		UserData userData = new UserData();
		userData.setAge(age);
		userData.setName(name);
		UserDataDao userDataDao = new UserDataDao();
		userDataDao.AlterAge(userData, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
