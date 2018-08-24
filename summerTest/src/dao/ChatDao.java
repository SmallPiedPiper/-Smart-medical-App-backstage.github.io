package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import utils.CommonUtil;
import utils.JdbcUtils2;

public class ChatDao {
	
	/**
	 * 获取某个用户所有聊天列表
	 * @param username
	 * @param response
	 */
	public void getListChat(String username,HttpServletResponse response){
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "select * from listchat where username = ? and flag = 0 order by chatdate desc";
		List<Object> pam = new ArrayList<Object>();
		pam.add(username);
		try {
			List<Map<String, Object>> lisMap = jdbcUtils2.fiandMoreResult(sql, pam);
			if(lisMap != null && lisMap.size() > 0)
			    outMap.put("listChat", lisMap);
			CommonUtil.renderJson(response, outMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
			
	}
	
	/**
	 * 删除个聊天列表
	 * @param response
	 * @param args
	 */
	public void deleteChat(HttpServletResponse response,Object ...args){
		Boolean result = false;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "update listchat set flag = 1 where username = ? and tousername = ?";
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[0]);
		pam.add(args[1]);
		
		try {
			result = jdbcUtils2.updataByPreparedstatement(sql, pam);
			if(result == true)
		       	outMap.put("code", 1);
			else
				outMap.put("code", 2);
			CommonUtil.renderJson(response, outMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
		
	}
	
	/**
	 * 增加会话
	 * @param response
	 * @param args
	 */
	public void addChat(HttpServletResponse response, Object ... args){
		
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sqlwhe = "select * from listchat where username = ? and tousername = ?";
		List<Object> pamwhe = new ArrayList<Object>();
		pamwhe.add(args[0]);
		pamwhe.add(args[1]);
		try{
			Map<String, Object> map = jdbcUtils2.fiandSimleResult(sqlwhe, pamwhe);
			if(map.size() > 0){
				Boolean resultUp = false;
				String sqlup = "update listchat set lasttext = ?, flag = 0,chatdate = ?,lastpicture= ? where username = ? and tousername= ?";
				List<Object> pamup = new ArrayList<Object>();
				pamup.add(args[2]);
				pamup.add(args[3]);
				pamup.add(args[4]);
				pamup.add(args[0]);
				pamup.add(args[1]);
				
				
				resultUp = jdbcUtils2.updataByPreparedstatement(sqlup, pamup);
				if(resultUp == true){
					outMap.put("code", 1);
				}else{
					outMap.put("code", 2);
				}
			}else{
				Boolean result = false;
			    String sql = "insert into listchat(username,tousername,flag,lasttext,chatdate,lastpicture) values(?,?,?,?,?,?)";
			    List<Object> pamIn = new ArrayList<Object>();
			    pamIn.add(args[0]);
			    pamIn.add(args[1]);
			    pamIn.add(0);
			    pamIn.add(args[2]);
			    pamIn.add(args[3]);
			    pamIn.add(args[4]);
			    result = jdbcUtils2.updataByPreparedstatement(sql, pamIn);
			    
			    if(result == true){
			    	outMap.put("code", 1);
			    }else{
			    	outMap.put("code", 2);
			    }
			}
			CommonUtil.renderJson(response, outMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
		
		
		
		
	}
	
	/**
	 * 获取所有聊天信息
	 * @param username
	 * @param response
	 */
	public void getListChatMessages(HttpServletResponse response, Object ... args){
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		List<Object> pam1 = new ArrayList<Object>();
		
		
	    String sql = "select * from chatmessages where (username= ? and tousername= ?) or (username= ? and tousername = ?) order by date asc";
		
		System.out.print("a"+args[0]+args[1]);
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[0]);
		pam.add(args[1]);
		pam.add(args[1]);
		pam.add(args[0]);
		
		try {
			List<Map<String, Object>> lisMap = jdbcUtils2.fiandMoreResult(sql, pam);
			outMap.put("listChat", lisMap);
			CommonUtil.renderJson(response, outMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
		
	}
	
	/**
	 * 发送信息
	 * @param response
	 * @param args
	 */
	public void sendChatMessages(HttpServletResponse response, Object ... args){
		
		Boolean result = false;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "insert into chatmessages(username, tousername, text, picture, date,flag,readed) values(?,?,?,?,?,?,?)";
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[0]);
		pam.add(args[1]);
		pam.add(args[2]);
		pam.add(args[3]);
		pam.add(args[4]);
		pam.add(0);
		pam.add(0);
		try {
			connection.setAutoCommit(false);
			result = jdbcUtils2.updataByPreparedstatement(sql, pam);
			if(result == true){
				Boolean addRe1 = addChatMoth(jdbcUtils2, args[0],args[1],args[2],args[4],args[3]);
				Boolean addR2 = addChatMoth(jdbcUtils2, args[1],args[0],args[2],args[4],args[3]);
				
				if(addRe1 == true && addR2 == true){
					connection.commit();
					outMap.put("code", 1);
				}else{
					connection.rollback();
					outMap.put("code", 2);
				}
				
			}   
			else{
				connection.rollback();
				outMap.put("code", 2);
			}
			CommonUtil.renderJson(response, outMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
	}
	
	/**
	 * 接收信息
	 * @param response
	 * @param userName
	 */
	public void getWhetherMessage(HttpServletResponse response, String userName){
		//Boolean result = false;
		int num = 0;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		
		String sql1 = "update chatmessages set flag = 1 where tousername = "+userName+"";
		
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "select count(*) from chatmessages where tousername = ? and flag = 0";
		List<Object> pam = new ArrayList<Object>();
		pam.add(userName);
		
		try {
			List<Map<String, Object>> lisMap = jdbcUtils2.fiandMoreResult(sql, pam);
			num = Integer.valueOf(lisMap.get(0).get("count(*)").toString());
			System.out.print(lisMap.get(0).get("count(*)")+"-->");
			if(num > 0){
		       	outMap.put("code", 1);
		       	jdbcUtils2.updataByPreparedstatement(sql1, null);
			}
			else if(num == 0)
				outMap.put("code", 2);
			CommonUtil.renderJson(response, outMap);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
		
	}
	
	/**
	 * 标记消息已读
	 * @param response
	 * @param args
	 */
	public void setMessageReaded(HttpServletResponse response, Object ... args){
		
		Boolean result = null;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "update chatmessages set readed = 1 where username = ? and tousername = ?";
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[1]);
		pam.add(args[0]);
		try {
			
			result = jdbcUtils2.updataByPreparedstatement(sql, pam);
			if(result == true){
				outMap.put("code", 1);
			}else{
				outMap.put("code", 2);
			}
			CommonUtil.renderJson(response, outMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
	}
	
	/**
	 * 获取未读信息个数
	 * @param response
	 * @param args
	 */
	public void getMessageNotReadedNum(HttpServletResponse response, Object ... args){
		Boolean result = null;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql= "select * from chatmessages where username = ? and tousername = ? and readed = 0";
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[0]);
		pam.add(args[1]);
        try {
			
        	
			List<Map<String, Object>> list  = jdbcUtils2.fiandMoreResult(sql, pam);
			if(!list.isEmpty() && list.size() > 0){
				outMap.put("num", list.size());
			}else{
				outMap.put("num", 0);
			}
			
			CommonUtil.renderJson(response, outMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils2.releaseConn();
		}
	}
	/**
	 * 增加会话列表
	 * @param jdbcUtils2
	 * @param args
	 * @return
	 */
	public Boolean addChatMoth(JdbcUtils2 jdbcUtils2,Object ...args){
		
		String sqlwhe = "select * from listchat where username = ? and tousername = ?";
		List<Object> pamwhe = new ArrayList<Object>();
		pamwhe.add(args[0]);
		pamwhe.add(args[1]);
		try{
			Map<String, Object> map = jdbcUtils2.fiandSimleResult(sqlwhe, pamwhe);
			if(map.size() > 0){
				Boolean resultUp = false;
				String sqlup = "update listchat set lasttext = ?, flag = 0,chatdate = ?,lastpicture= ? where username = ? and tousername= ?";
				List<Object> pamup = new ArrayList<Object>();
				pamup.add(args[2]);
				pamup.add(args[3]);
				pamup.add(args[4]);
				pamup.add(args[0]);
				pamup.add(args[1]);
				
				
				resultUp = jdbcUtils2.updataByPreparedstatement(sqlup, pamup);
				if(resultUp == true){
					return true;
				}else{
					return false;
				}
			}else{
				Boolean result = false;
			    String sql = "insert into listchat(username,tousername,flag,lasttext,chatdate,lastpicture) values(?,?,?,?,?,?)";
			    List<Object> pamIn = new ArrayList<Object>();
			    pamIn.add(args[0]);
			    pamIn.add(args[1]);
			    pamIn.add(0);
			    pamIn.add(args[2]);
			    pamIn.add(args[3]);
			    pamIn.add(args[4]);
			    result = jdbcUtils2.updataByPreparedstatement(sql, pamIn);
			    
			    if(result == true){
			    	return true;
			    }else{
			    	return false;
			    }
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	

}
