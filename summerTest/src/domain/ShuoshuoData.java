package domain;

public class ShuoshuoData {
	private int Id;
	private String Name;
	private String Niname;
	private String Posttime;
	private String TextContent;
	private int Replynum;
	private int Visit;
	private String Pictures;
	private String UserTouxiang;
	private String Shuoshuoname;
	private int Shuoshuonum;
	public int getShuoshuonum() {
		return Shuoshuonum;
	}
	public void setShuoshuonum(int shuoshuonum) {
		Shuoshuonum = shuoshuonum;
	}
	public String getUserTouxiang() {
		return UserTouxiang;
	}
	public void setUserTouxiang(String userTouxiang) {
		UserTouxiang = userTouxiang;
	}
	public String getShuoshuoname() {
		return Shuoshuoname;
	}
	public void setShuoshuoname(String shuoshuoname) {
		Shuoshuoname = shuoshuoname;
	}
	public int getVisit() {
		return Visit;
	}
	public void setVisit(int visit) {
		Visit = visit;
	}
	public String getPosttime() {
		return Posttime;
	}
	public void setPosttime(String posttime) {
		Posttime = posttime;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNiname() {
		return Niname;
	}
	public void setNiname(String niname) {
		Niname = niname;
	}
	public String getPictures() {
		return Pictures;
	}
	public void setPictures(String pictures) {
		Pictures = pictures;
	}
	public String getTextContent() {
		return TextContent;
	}
	public void setTextContent(String textContent) {
		TextContent = textContent;
	}
	public int getReplynum() {
		return Replynum;
	}
	public void setReplynum(int replynum) {
		Replynum = replynum;
	}
	

}
