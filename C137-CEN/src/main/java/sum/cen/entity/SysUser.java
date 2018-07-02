package sum.cen.entity;
/**
 * 
 * @author cen    2018年6月5日下午8:27:46
 *
 */
public class SysUser extends PageBean{
	private Integer id;//   id主键
	private String email;//   邮箱也是登录帐号
	private String pwd;//   登录密码
	private String nickName;//   昵称
	private Integer state;//   状态 0=可用,1=禁用
	private Integer loginCount;//   登录总次数
	private java.sql.Timestamp loginTime;//   最后登录时间
	private Integer deleted;//   删除状态 0=未删除,1=已删除
	private java.sql.Timestamp createTime;//   创建时间
	private java.sql.Timestamp updateTime;//   修改时间
	private Integer createBy;//   创建人
	private Integer updateBy;//   修改人
	private Integer superAdmin;//超级管理员
	private String roleStr;//用户权限, 按","区分
	private  PageHelper pageHelper=new PageHelper();
	
	public PageHelper getPageHelper() {
		return pageHelper;
	}
	public void setPageHelper(PageHelper pageHelper) {
		this.pageHelper = pageHelper;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public java.sql.Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(java.sql.Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}
	public String getRoleStr() {
		return roleStr;
	}
	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", email=" + email + ", pwd=" + pwd
				+ ", nickName=" + nickName + ", state=" + state
				+ ", loginCount=" + loginCount + ", loginTime=" + loginTime
				+ ", deleted=" + deleted + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createBy=" + createBy
				+ ", updateBy=" + updateBy + ", superAdmin=" + superAdmin
				+ ", roleStr=" + roleStr + ", pageHelper=" + pageHelper + "]";
	}
	
	

}
