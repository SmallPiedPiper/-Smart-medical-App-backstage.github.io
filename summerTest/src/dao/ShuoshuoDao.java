package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import domain.ShuoshuoData;
import domain.ShuoshuoReply;
import utils.CommonUtil;
import utils.JdbcUtils;
import utils.JdbcUtils2;


public class ShuoshuoDao {

	public void PostShuoshuo(ShuoshuoData shuoshuoDate, HttpServletResponse response){
		JdbcUtils2 jdbcUtils = new JdbcUtils2();
		jdbcUtils.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "insert into shuoshuo(name,textcontent,picture,posttime,reply,visit,shuoshuoname) values(?,?,?,?,?,?,?)";
		String name = shuoshuoDate.getName();
		String textContent = shuoshuoDate.getTextContent();
		String pictures = shuoshuoDate.getPictures();
		String posttime = shuoshuoDate.getPosttime();
		int visit = 1;
		int replynum = 0;
		String shuoshuoname = shuoshuoDate.getShuoshuoname();
		
		
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(textContent);
		params.add(pictures);
		params.add(posttime);
		params.add(Integer.valueOf(replynum));
		params.add(Integer.valueOf(visit));
		params.add(shuoshuoname);
		//System.out.println("没有记录1");
		try {
		
			boolean flag = jdbcUtils.updataByPreparedstatement(sql, params);
			outMap.put("PostShuoshuo", "posthuoshuo");
			if(flag == false){
				outMap.put("code", "2");
				CommonUtil.renderJson(response, outMap);
				System.out.println("没有记录2");
			}else{
				outMap.put("code", "1");
				//outMap.put("infor", list);
				CommonUtil.renderJson(response, outMap);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn();
		}
	}
	/*public void GetShuoshuo2(ShuoshuoData shuoshuoDate, HttpServletResponse response){
		
		//String usersql1= "select * from shuoshuo where visit = ?";
		String usersql1 = "select * from shuoshuo where visit = ? order by id desc limit ?,1";
		List<Object> userparams1 = new ArrayList<Object>();
		userparams1.add(1);
		//userparams1.add(shuoshuoDate.getShuoshuonum());
		userparams1.add(0);
		//List<Map<String, Object>> list1 = jdbcUtils.fiandMoreResult(usersql1, userparams1);
	
	}*/
	public void GetShuoshuo(ShuoshuoData shuoshuoDate, HttpServletResponse response){
		JdbcUtils2 jdbcUtils = new JdbcUtils2();
		jdbcUtils.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql= "select * from shuoshuo where visit = ? order by id desc limit ?,10";
		//String usersql= "select * from userdata where name = ?";
		int visit = shuoshuoDate.getVisit();
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add(shuoshuoDate.getShuoshuonum());
		
		try {
			
			List<Map<String, Object>> list = jdbcUtils.fiandMoreResult(sql, params);
			outMap.put("GetShuoshuo", "getshuoshuo");
			if(list == null || list.isEmpty()){
				outMap.put("code", "2");
				CommonUtil.renderJson(response, outMap);
				System.out.println("没有记录2");
			}else{
				List<ShuoshuoData> alllist = new ArrayList<ShuoshuoData>();
				outMap.put("code", "1");
				for(int i = 0 ; i < list.size() ; i++){
					//
					//JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
					//jdbcUtils2.getConnection();
					Map<String, Object> map = list.get(i);
					String username = (String)map.get("name");
					String usersql= "select * from userdata where name = ?";
					List<Object> userparams = new ArrayList<Object>();
					userparams.add(username);
					Map<String, Object> usermap = jdbcUtils.fiandSimleResult(usersql, userparams);
					ShuoshuoData ssd = new ShuoshuoData();
				
					ssd.setId((int)map.get("id"));
					ssd.setName(username);
					ssd.setNiname((String)usermap.get("niname"));
					ssd.setPictures((String)map.get("picture"));
					ssd.setPosttime(map.get("posttime").toString());
					ssd.setTextContent((String)map.get("textcontent"));
					ssd.setReplynum((int)map.get("reply"));
					ssd.setVisit(visit);
					ssd.setUserTouxiang((String)usermap.get("touxiang"));
					ssd.setShuoshuoname((String)map.get("shuoshuoname"));
					alllist.add(ssd);
					
				}
				outMap.put("shuoshuolist", alllist);
				CommonUtil.renderJson(response, outMap);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn();
		}
	}
	public void PostShuoshuoReply(ShuoshuoReply shuoshuoReply, HttpServletResponse response){
		JdbcUtils2 jdbcUtils = new JdbcUtils2();
		jdbcUtils.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql = "insert into shuoshuoreply(name,textcontent,childreplynum,pictures,replyshuoshuoname,replytime) values(?,?,?,?,?,?)";
		                                                         
		String name = shuoshuoReply.getName();
		String textContent = shuoshuoReply.getTextcontent();
		String pictures = shuoshuoReply.getPictures();
		String posttime = shuoshuoReply.getPosttime();
		String shuoshuoname = shuoshuoReply.getShuohsuoname();
		int childreplynum = shuoshuoReply.getChildReplynum();
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(textContent);
		params.add(Integer.valueOf(childreplynum));
		params.add(pictures);
		params.add(shuoshuoname);
		params.add(posttime);
		//System.out.println("没有记录1");
		try {
		
			boolean flag = jdbcUtils.updataByPreparedstatement(sql, params);
			outMap.put("PostShuoshuoReply", "posthuoshuoreply");
			if(flag == false){
				outMap.put("code", "2");
				CommonUtil.renderJson(response, outMap);
				System.out.println("没有记录2");
			}else{
				outMap.put("code", "1");
				//outMap.put("infor", list);
				CommonUtil.renderJson(response, outMap);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn();
		}
	}
	
	public void GetShuoshuoReply(ShuoshuoReply shuoshuoReply, HttpServletResponse response){
		JdbcUtils2 jdbcUtils = new JdbcUtils2();
		jdbcUtils.getConnection();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String sql= "select * from shuoshuoreply where replyshuoshuoname = ?";
		//String usersql= "select * from userdata where name = ?";
		String shuoshuoname = shuoshuoReply.getShuohsuoname();
		List<Object> params = new ArrayList<Object>();
		params.add(shuoshuoname);
		
		
		try {
			
			List<Map<String, Object>> list = jdbcUtils.fiandMoreResult(sql, params);
			outMap.put("GetShuoshuo", "getshuoshuo");
			if(list == null || list.isEmpty()){
				outMap.put("code", "2");
				CommonUtil.renderJson(response, outMap);
				System.out.println("没有记录2");
			}else{
				List<ShuoshuoReply> alllist = new ArrayList<ShuoshuoReply>();
				outMap.put("code", "1");
				for(int i = 0 ; i < list.size() ; i++){
					//
					//JdbcUtils2 jdbcUtils2 = new JdbcUtils2();
					//jdbcUtils2.getConnection();
					Map<String, Object> map = list.get(i);
					String username = (String)map.get("name");
					String usersql= "select * from userdata where name = ?";
					List<Object> userparams = new ArrayList<Object>();
					userparams.add(username);
					Map<String, Object> usermap = jdbcUtils.fiandSimleResult(usersql, userparams);
					ShuoshuoReply ssd = new ShuoshuoReply();
				
					ssd.setId((int)map.get("id"));
					ssd.setName(username);
					ssd.setNickName((String)usermap.get("niname"));
					ssd.setTouxiang((String)usermap.get("touxiang"));
					ssd.setPictures((String)map.get("pictures"));
					ssd.setPosttime(map.get("replytime").toString());
					ssd.setTextcontent((String)map.get("textcontent"));
					ssd.setShuohsuoname((String)map.get("replyshuoshuoname"));
					ssd.setChildReplynum((Integer)map.get("childreplynum"));
					alllist.add(ssd);
					
				}
				outMap.put("shuoshuolist", alllist);
				CommonUtil.renderJson(response, outMap);
			
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn();
		}
	}
	
}
