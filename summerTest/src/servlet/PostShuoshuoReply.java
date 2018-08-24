package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShuoshuoDao;
import domain.ShuoshuoReply;

public class PostShuoshuoReply extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		this.doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String Name = request.getParameter("name");
		String TextContent = new String(request.getParameter("textcontent").getBytes(
				"utf-8"), "utf-8");
		String Pictures = request.getParameter("pictures");
		String posttime = request.getParameter("posttime");
	    String ShuoshuoName = request.getParameter("shuoshuoname");
		int ChildReply = Integer.parseInt(request.getParameter("replynum"));
		ShuoshuoReply shuoshuoReply = new ShuoshuoReply();
		shuoshuoReply.setName(Name);
		shuoshuoReply.setTextcontent(TextContent);
		shuoshuoReply.setPictures(Pictures);
		shuoshuoReply.setPosttime(posttime);
		shuoshuoReply.setChildReplynum(ChildReply);
		shuoshuoReply.setShuohsuoname(ShuoshuoName);
		ShuoshuoDao shuoshuoDao = new ShuoshuoDao();
		shuoshuoDao.PostShuoshuoReply(shuoshuoReply, response);
	}

}
