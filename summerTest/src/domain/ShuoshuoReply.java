package domain;

public class ShuoshuoReply {

	private int id;
	private String Name;
	private String NickName;
	private String Touxiang;
	private String Posttime;
	private String Textcontent;
	private int ChildReplynum;
	private String Pictures;
	private String Shuohsuoname;
	public String getShuohsuoname() {
		return Shuohsuoname;
	}
	public void setShuohsuoname(String shuohsuoname) {
		Shuohsuoname = shuohsuoname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getTouxiang() {
		return Touxiang;
	}
	public void setTouxiang(String touxiang) {
		Touxiang = touxiang;
	}
	public String getPosttime() {
		return Posttime;
	}
	public void setPosttime(String ppsttime) {
		this.Posttime = ppsttime;
	}
	public String getTextcontent() {
		return Textcontent;
	}
	public void setTextcontent(String textcontent) {
		Textcontent = textcontent;
	}
	public int getChildReplynum() {
		return ChildReplynum;
	}
	public void setChildReplynum(int childReplynum) {
		ChildReplynum = childReplynum;
	}
	public String getPictures() {
		return Pictures;
	}
	public void setPictures(String pictures) {
		Pictures = pictures;
	}
	
	
}
