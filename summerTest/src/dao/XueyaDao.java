package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import utils.CommonUtil;
import utils.JdbcUtils2;

public class XueyaDao {
	
	/**
	 * 获取高压数据
	 * @param response
	 * @param username
	 */
	public void getXueya(HttpServletResponse response, String username){
		
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "select * from xueyadata where username = ? order by posttime desc limit 1";
		
		List<Object> pam = new ArrayList<Object>();
		pam.add(username);
		
		try {
			String XueyaOrnotsql = "select * from xueyadata where username = ?";
			List<Object> pamOrnot = new ArrayList<Object>();
			pamOrnot.add(username);
			Map<String, Object> xueyaOrnotData = jdbcUtils2.fiandSimleResult(XueyaOrnotsql, pamOrnot);
			
			if(xueyaOrnotData.isEmpty() ||  xueyaOrnotData.size() <= 0){
				outMap.put("code", 2);
			}else{
				Map<String, Object> xueyaData = jdbcUtils2.fiandSimleResult(sql, pam);
				if(!xueyaData.isEmpty() && xueyaData.size() > 0){
				    outMap.put("xueyaData", xueyaData);
				    outMap.put("code", 1);
				}else{
					outMap.put("code", 2);
				}
			}
			CommonUtil.renderJson(response, outMap);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			jdbcUtils2.releaseConn();
		}
		
	}
	
	/**
	 * 上传血压数据
	 * @param response
	 * @param username
	 */
	public void postXueyaData(HttpServletResponse response, Object...args){
		
		Boolean result= false;
		JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
		Connection connection = jdbcUtils2.getConnection();
		Map<String, Object> outMap = new HashMap<String,Object>();
		String sql = "insert into xueyadata(username,gaoya,diya,maibo,xinlv,posttime) values(?,?,?,?,?,?)";
		List<Object> pam = new ArrayList<Object>();
		pam.add(args[0]);
		pam.add(args[1]);
		pam.add(args[2]);
		pam.add(args[3]);
		pam.add(args[4]);
		pam.add(args[5]);
		
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
		}finally{
			jdbcUtils2.releaseConn();
		}
		
		
		
	}

	
}
