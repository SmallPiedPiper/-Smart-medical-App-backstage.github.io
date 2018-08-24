package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChatDao;

public class addChatSerlvet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
		
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userName= request.getParameter("username");
		String toUserName = request.getParameter("tousername");
		String lastText = new String(request.getParameter("lasttext").getBytes("utf-8"), "utf-8");
		String chatdate = request.getParameter("chatdate");
		String lasrPicture = request.getParameter("lastpicture");
		ChatDao chatDao = new ChatDao();
		chatDao.addChat(response, userName,toUserName,lastText,chatdate,lasrPicture);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
		
        request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
