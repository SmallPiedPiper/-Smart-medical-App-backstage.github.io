package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;

public class PostChatMessageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String usernaem = new String(request.getParameter("username").getBytes("utf-8"), "utf-8");
		String tousername = new String(request.getParameter("tousername").getBytes("utf-8"), "utf-8");
		String text = new String(request.getParameter("text").getBytes("utf-8"), "utf-8");
		String picture = new String(request.getParameter("picture").getBytes("utf-8"), "utf-8");
		String date = new String(request.getParameter("date").getBytes("utf-8"), "utf-8");
		ChatDao chatDao = new ChatDao();
		chatDao.sendChatMessages(response, usernaem,tousername,text,picture,date);
	    
	}

}
