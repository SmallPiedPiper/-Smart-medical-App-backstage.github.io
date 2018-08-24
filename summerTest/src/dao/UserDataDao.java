package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import utils.CommonUtil;
import utils.JdbcUtils;
import config.Constant;







import utils.JdbcUtils;


import config.Constant;
import domain.UserData;

public class UserDataDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet set = null;
	
	public void showdata(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.DATASHOW, "datashow");
	   
	    try {
	    	connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			String sql = "select * from userdata where name = '"+userData.getName()+"'";
			set = statement.executeQuery(sql);
			
			if(set.next()){
				int id = set.getInt("id");
				String name = set.getString("name");
				String niname = set.getString("niname");
				String touxiang = set.getString("touxiang");
				String profile = set.getString("profile");
				int sex = set.getInt("sex");
				int age = set.getInt("age");
				int identity = set.getInt("identity");
				
				UserData userD = new UserData();
				userD.setId(id);
				userD.setName(name);
				userD.setNiname(niname);
				userD.setTouxiang(touxiang);
				userD.setProflie(profile);
				userD.setSex(sex);
				userD.setAge(age);
				userD.setIdentity(identity);
				
				outMap.put(Constant.RESPONSE_CODE, 1);
				outMap.put("userInfomation", userD);
				CommonUtil.renderJson(response, outMap);
				
			}
			else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				
				CommonUtil.renderJson(response, outMap);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
		
	}
	//修改昵称
    public void AlterNickName(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateNiname");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			String sql = "update userdata set niname = '"+userData.getNiname()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    //修改头像
    public void AlterTouXiang(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateTouXiang");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			String sql = "update userdata set touxiang = '"+userData.getTouxiang()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    //修改性别
    public void AlterSex(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateSex");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			
			String sql = "update userdata set sex = '"+userData.getSex()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    //修改年龄
    public void AlterAge(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateAge");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			
			String sql = "update userdata set age = '"+userData.getAge()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    //修改身份
    public void AlterIdentity(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateIdentity");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			
			String sql = "update userdata set identity = '"+userData.getIdentity()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    //修改简介
    public void AlterProfile(UserData userData,HttpServletResponse response){
		
		Map<String, Object> outMap = new HashMap<String,Object>();
		outMap.put(Constant.USERDATAUPDATE, "dataupdateIdentity");
		
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			
			String sql = "update userdata set profile = '"+userData.getProflie()+"' where name = '"+userData.getName()+"'";
			int result = statement.executeUpdate(sql);
			if(result == 1){
				outMap.put(Constant.RESPONSE_CODE, 1);
				CommonUtil.renderJson(response, outMap);
			}else{
				outMap.put(Constant.RESPONSE_CODE, 2);
				CommonUtil.renderJson(response, outMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(set, statement, connection);
		}
		
    }
    

}
