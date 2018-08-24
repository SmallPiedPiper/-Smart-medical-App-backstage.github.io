package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JButton;

import org.apache.commons.collections.map.HashedMap;

import other.MobServer;
import utils.CommonUtil;
import utils.JdbcUtils;
import utils.JdbcUtils2;
import config.Constant;
import domain.User;

public class UserDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet set = null;

	//登陆
	public void login(User user, HttpServletResponse response) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put(Constant.RESPONSE, "login");
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			String sql = "select * from user where name = '"+user.getUsername()+"' and pass='"+user.getPassword()+"'"; 
			
			set = statement.executeQuery(sql);
			
			User loginUser = null;
			
			if(set.next()) {//代表查询有这个人,则登陆成功	
				
				String username = set.getString("name");
				String password = set.getString("pass");
				Integer identity = set.getInt("identity");
				
				loginUser = new User();
				loginUser.setPassword(password);
				loginUser.setUsername(username);
				loginUser.setIdentity(identity.toString());
				outMap.put(Constant.RESPONSE_CODE, "1");
				//outMap.put("codee", "1");
				outMap.put("userInfomation", loginUser);
				CommonUtil.renderJson(response, outMap);
			}else {//直接返回用户名或密码错误
				outMap.put(Constant.RESPONSE_CODE, "2");
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
	}
	
	//修改
	
	
	//注册
	
	public void register(User user, HttpServletResponse response) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put(Constant.RESPONSE, "register");
		String yanzhengma = user.getYanzhengma();
		String name = user.getUsername();
		String password = user.getPassword();
		String identity1 = user.getIdentity();
		
		String urlMain = "https://webapi.sms.mob.com/sms/verify";
		String pinjieUrl = "appkey=16122de33dd7f&amp;phone=" + name
				+ "&amp;zone=86&amp;&amp;code=" + yanzhengma;
		String result1 = MobServer.requestData(urlMain, pinjieUrl);
		String resultString = result1.substring(result1.length() - 4,
				result1.length() - 1);

		System.out.print(result1);
		
		/*try {
			PrintWriter out = response.getWriter();
			out.print(resultString);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		if(resultString.equals("200")){
			try {
				int identity = Integer.parseInt(identity1);
				connection = JdbcUtils.getConnection();
				statement = connection.createStatement();
				//String sql = "insert into uss values(null,'"+user.getUsername()+"','"+user.getPassword()+"')";
				
				String sqllog = "select * from user where name = '"+name+"'";
				set = statement.executeQuery(sqllog);
				if(set.next()){
					outMap.put(Constant.RESPONSE_CODE, "4");
					CommonUtil.renderJson(response, outMap);
				}
				else{
				
					String sql = "insert into user(name,pass,identity) values('"+name+"','"+password+"','"+identity+"')";
					
					int result = statement.executeUpdate(sql);
					if(result == 1){//插入成功
						outMap.put(Constant.RESPONSE_CODE, "1");
						CommonUtil.renderJson(response, outMap);
						String niNick = "江湖路人";
						
						String sqls = "insert into userdata(name,identity,niname) values('"+name+"','"+identity+"','"+niNick+"')";
						int result11 = statement.executeUpdate(sqls);
					}else {//插入失败
						outMap.put(Constant.RESPONSE_CODE, "2");
						CommonUtil.renderJson(response, outMap);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUtils.release(set, statement, connection);
			}

		}else {
			System.out.println(3);
			outMap.put(Constant.RESPONSE_CODE, "3");
			CommonUtil.renderJson(response, outMap);
		}
						
	}
	
	public void updatapassword(User user, HttpServletResponse response) {
		Map<String, Object> outMap = new HashMap<String, Object>();

		outMap.put(Constant.RESPONSE, "register");
		String yanzhengma = user.getYanzhengma();
		String name = user.getUsername();
		//String password = user.getPassword();
		
		String urlMain = "https://webapi.sms.mob.com/sms/verify";
		String pinjieUrl = "appkey=16122de33dd7f&amp;phone=" + name
				+ "&amp;zone=86&amp;&amp;code=" + yanzhengma;
		String result1 = MobServer.requestData(urlMain, pinjieUrl);
		String resultString = result1.substring(result1.length() - 4,
				result1.length() - 1);
        if(resultString.equals("200")){

        	//int identity = Integer.parseInt(identity1);
			
			try {
				connection = JdbcUtils.getConnection();
				statement = connection.createStatement();
				String sqllog = "select * from user where name = '"+name+"'";
				set = statement.executeQuery(sqllog);
				if(set.next()){
					String sql = "update user set  pass = '"+user.getPassword()+"' where name = '"+user.getUsername()+"'"; 
					int result = statement.executeUpdate(sql);
					if(result == 1){//更新成功
						outMap.put(Constant.RESPONSE_CODE, "1");
						CommonUtil.renderJson(response, outMap);
					}else {//更新失败
						outMap.put(Constant.RESPONSE_CODE, "2");
						CommonUtil.renderJson(response, outMap);
					}
					
				}
				else{
					outMap.put(Constant.RESPONSE_CODE, "4");
					CommonUtil.renderJson(response, outMap);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUtils.release(set, statement, connection);
			}
			

        }else
        {
        	outMap.put(Constant.RESPONSE_CODE, "3");
			CommonUtil.renderJson(response, outMap);
        }
		System.out.print(result1);

	}
	
	/**
	 * 获取所有用户信息
	 * @param user
	 * @param response
	 */
	public void getAllUserData(HttpServletResponse response) {
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "select * from userdata";
		try {
			
			List<Map<String, Object>> allUserData = jdbcUtils2.fiandMoreResult(sql, null);
			outMap.put("allUserData", allUserData);
			CommonUtil.renderJson(response, outMap);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			jdbcUtils2.releaseConn();
		}
		
	}
}
