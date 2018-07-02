package sum.cen.entity;

/**
 * 
 * @author cen    2018年6月25日下午3:02:53
 *
 */
public class Content extends PageBean{

	private  long id;
	private  String url;
	private  String content;
	private  int novel_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNovelId() {
		return novel_id;
	}
	public void setNovelId(int novel_id) {
		this.novel_id = novel_id;
	}

}
