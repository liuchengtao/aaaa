package sum.cen.entity;
/**
 * 
 * @author cen    2018��6��5������8:27:46
 *
 */
public class SysUser extends PageBean{
	private Integer id;//   id����
	private String email;//   ����Ҳ�ǵ�¼�ʺ�
	private String pwd;//   ��¼����
	private String nickName;//   �ǳ�
	private Integer state;//   ״̬ 0=����,1=����
	private Integer loginCount;//   ��¼�ܴ���
	private java.sql.Timestamp loginTime;//   ����¼ʱ��
	private Integer deleted;//   ɾ��״̬ 0=δɾ��,1=��ɾ��
	private java.sql.Timestamp createTime;//   ����ʱ��
	private java.sql.Timestamp updateTime;//   �޸�ʱ��
	private Integer createBy;//   ������
	private Integer updateBy;//   �޸���
	private Integer superAdmin;//��������Ա
	private String roleStr;//�û�Ȩ��, ��","����
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
