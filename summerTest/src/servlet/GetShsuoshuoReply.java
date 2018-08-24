package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShuoshuoDao;
import domain.ShuoshuoData;
import domain.ShuoshuoReply;

public class GetShsuoshuoReply extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String Shuoshuoname = request.getParameter("shuoshuoname");
		ShuoshuoReply shuoshuoReply = new ShuoshuoReply();
		shuoshuoReply.setShuohsuoname(Shuoshuoname);
		ShuoshuoDao shuoshuoDao = new ShuoshuoDao();
		shuoshuoDao.GetShuoshuoReply(shuoshuoReply, response);
	}

}
