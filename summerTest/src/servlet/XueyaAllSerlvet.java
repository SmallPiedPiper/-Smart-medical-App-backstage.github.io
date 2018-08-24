package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.XueyaDao;

public class XueyaAllSerlvet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("text/html;charset=utf-8");
			
	     request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 String mothod = request.getServletPath();
		 String mothodName = mothod.substring(1, mothod.length()-3);
		 try {
			Method method =getClass().getDeclaredMethod(mothodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 //System.out.print(mothod.substring(1, mothod.length()-3));
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("text/html;charset=utf-8");
	     request.setCharacterEncoding("utf-8");
	     response.setCharacterEncoding("utf-8");
	     doGet(request, response);
	     
	}
	
	public void getXueya(HttpServletRequest request, HttpServletResponse response){
		XueyaDao xueyaDao = new XueyaDao();
		String username = request.getParameter("username");
		xueyaDao.getXueya(response, username);
		System.out.print("hello getXueya");
	}
	
	public void postXueyaData(HttpServletRequest request, HttpServletResponse response){
		System.out.print("hello getXueya");
		String username = request.getParameter("username");
		double gaoya = Double.valueOf(request.getParameter("gaoya"));
		double diya = Double.valueOf(request.getParameter("diya"));
		double maibo = Double.valueOf(request.getParameter("maibo"));
		double xinlv = Double.valueOf(request.getParameter("xinlv"));
		
		String posttime = request.getParameter("posttime");
		
		XueyaDao xueyaDao = new XueyaDao();
		xueyaDao.postXueyaData(response, username,gaoya,diya,maibo,xinlv,posttime);
		
		System.out.print("hello getXueya");
		
		
	}

}
