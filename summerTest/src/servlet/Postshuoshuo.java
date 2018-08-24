package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShuoshuoDao;
import domain.ShuoshuoData;

public class Postshuoshuo extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//request.setCharacterEncoding("UTF-8");
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
		//String TextContent = request.getParameter("textcontent");
		String TextContent = new String(request.getParameter("textcontent").getBytes(
				"utf-8"), "utf-8");
		
		
		String Picture = request.getParameter("picture");
		String posttime = request.getParameter("posttime");
		String shuoshuoname = request.getParameter("shuoshuoname");
		int replynum = 0;
		int  visit = 1;
        System.out.print(Name);		
		ShuoshuoData shuoshuoData = new ShuoshuoData();
		shuoshuoData.setName(Name);
		shuoshuoData.setTextContent(TextContent);
		shuoshuoData.setPictures(Picture);
		shuoshuoData.setPosttime(posttime);
		shuoshuoData.setReplynum(replynum);
		shuoshuoData.setVisit(visit);
		shuoshuoData.setShuoshuoname(shuoshuoname);
		
		ShuoshuoDao shuoshuoDao = new ShuoshuoDao();
		shuoshuoDao.PostShuoshuo(shuoshuoData, response);
		
		System.out.print("1111");
		
		
	}

}
