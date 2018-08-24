package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDao;
import domain.UserData;

public class AlterNickNameServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String niname = new String(request.getParameter("niname").getBytes(
				"utf-8"), "utf-8");
		String name = request.getParameter("name");
		//String niname = request.getParameter("niname");
		UserData userData = new UserData();
		userData.setNiname(niname);
		userData.setName(name);
		UserDataDao userDataDao = new UserDataDao();
		userDataDao.AlterNickName(userData, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
