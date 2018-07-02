package sum.cen.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * ��������
 * @author cen    2018��6��18������11:19:40
 *
 */
public class TreeNode { 
private String id;
	
	private Integer dataId;
	
	private String text;
	
	private String url;
	
	private String state;//value closed,open
	
	private boolean checked; //
	
	private Integer parentId;
	
	private Map<String,Object> attributes = new HashMap<String, Object>();
	
	private List<TreeNode>  children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}


}
