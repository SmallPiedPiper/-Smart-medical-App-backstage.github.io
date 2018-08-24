package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class JdbcUtils2 {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "zj123456";
	
	private final String DRIVE = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/test";
	
	//定义数据库的链接
	private Connection connection;
	//定义sql语句的执行对象
	private PreparedStatement pstmt;
	//定义查询返回的结果集合
	private ResultSet resultSet;
	public JdbcUtils2(){
		try {
			Class.forName(DRIVE);
			System.out.println("注册驱动成功");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//定义获得数据库的链接
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}
	/*
	 * 完成对数据库的表的添加删除和修改的操作
	 */
	public boolean updataByPreparedstatement(String sql,List<Object> params) throws SQLException{
		boolean flag = false;
		int result = -1;//表示当用户执行对数据库的表的添加删除和修改的时候所影响数据库的行数
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if(params != null && !params.isEmpty()){
			for(int i = 0 ; i < params.size() ; i++){
				pstmt.setObject(index++,params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result> 0 ?true:false;
		return flag;
	}
	/*
	 * 查询返回单条记录
	 */
	public Map<String, Object> fiandSimleResult(String sql,List<Object> params) throws SQLException{
		//Map<String, Object> map = new HashMap<String,Object>();
		/*int index = 1;
		pstmt = connection.prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0 ; i < params.size() ; i++){
				pstmt.setObject(index++,params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();//返回查询结果
	    ResultSetMetaData metaData = resultSet.getMetaData();
	    int col_len = metaData.getColumnCount();//获取列的名称*/
		Map<String, Object> map = new HashMap<String,Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0 ; i < params.size() ; i++){
				pstmt.setObject(index++,params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();//获取列的名称
	    while(resultSet.next()){
	    	for(int i = 0 ; i < cols_len;i++){
	    		String cols_name = metaData.getColumnName(i+1);
				Object cols_value = resultSet.getObject(cols_name);
	    		if(cols_value == null){
	    			cols_value ="";
	    		}
	    		map.put(cols_name, cols_value);
	    	}
	    }
		return map;
		
	}
	/*
	 * 返回多行信息
	 */
	public List<Map<String, Object>> fiandMoreResult(String sql,List<Object> params) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0 ; i < params.size() ; i++){
				pstmt.setObject(index++,params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();//获取列的名称
		while(resultSet.next()){
			Map<String, Object> map = new HashMap<String,Object>();
			for(int i = 0 ; i < cols_len ; i++){
				String cols_name = metaData.getColumnLabel(i+1);
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null){
					cols_value ="";
	    		}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	
	public void releaseConn(){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
