package sum.cen.entity;


public class SysMenuBtn  extends PageBean{
	
	
	private String actionUrls;//   url注册，用"," 分隔 。用于权限控制URL
	private String deleteFlag; //删除标记，与数据库字段无�?1=删除,其他不删�?

		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getId() {
}