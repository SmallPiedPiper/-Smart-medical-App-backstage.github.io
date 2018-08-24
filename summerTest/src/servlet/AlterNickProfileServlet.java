package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDao;
import domain.UserData;

public class AlterNickProfileServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       response.setContentType("text/html;charset=utf-8");
       request.setCharacterEncoding("utf-8");
     	response.setCharacterEncoding("utf-8");
		
		String profile = new String(request.getParameter("profile").getBytes(
				"utf-8"), "utf-8");
		String name = request.getParameter("name");
		//String profile = request.getParameter("profile");
		UserData userData = new UserData();
		userData.setProflie(profile);
		userData.setName(name);
		UserDataDao userDataDao = new UserDataDao();
		userDataDao.AlterProfile(userData, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
		
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
