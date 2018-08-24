package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDao;
import domain.UserData;

public class AlterNickIdentityServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int identity = Integer.parseInt(request.getParameter("identity"));
		UserData userData = new UserData();
		userData.setIdentity(identity);
		userData.setName(name);
		UserDataDao userDataDao = new UserDataDao();
		userDataDao.AlterIdentity(userData, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
