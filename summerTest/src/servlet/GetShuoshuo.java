package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShuoshuoDao;
import domain.ShuoshuoData;

public class GetShuoshuo extends HttpServlet {

	
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
		//int visit = Integer.parseInt(request.getParameter("visit"));
		int shuoshuonum = Integer.parseInt(request.getParameter("shuoshuonum"));
		ShuoshuoData shuoshuoData = new ShuoshuoData();
		
		//shuoshuoData.setVisit(visit);
		shuoshuoData.setShuoshuonum(shuoshuonum);
		ShuoshuoDao shuoshuoDao = new ShuoshuoDao();
		shuoshuoDao.GetShuoshuo(shuoshuoData, response);
		
	}

}
