package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;

public class DeleteChat extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//int flag = Integer.valueOf(request.getParameter("flag"));
		String username = request.getParameter("username");
		String tousername = request.getParameter("tousername");
		ChatDao chatDao = new ChatDao();
		chatDao.deleteChat(response,username,tousername);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
