package sum.cen.entity;

import java.sql.Date;
/**
 * 
 * @author cen    2018年6月25日下午2:01:39
 *
 */
public class Novel extends PageBean {


	private int id;
	private  String name;
	private  String url;
	private  String tag;
	private  String user_no;
	private  String message;
	private  String pic;
	private Date  create_time;
	private Date  update_time;
	private  int subCount;   //本地章节
	private  int netCount;   //网络章节
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	public int getNetCount() {
		return netCount;
	}
	public void setNetCount(int netCount) {
		this.netCount = netCount;
	}
	public Novel(int id2, String url2) {
		// TODO Auto-generated constructor stub
		this.id=id2;
		this.url=url2;
	}
	public Novel() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "Novel [id=" + id + ", name=" + name + ", url=" + url + ", tag="
				+ tag + ", user_no=" + user_no + ", message=" + message
				+ ", pic=" + pic + ", create_time=" + create_time
				+ ", update_time=" + update_time + ", subCount=" + subCount
				+ ", netCount=" + netCount + "]";
	}
	
}
