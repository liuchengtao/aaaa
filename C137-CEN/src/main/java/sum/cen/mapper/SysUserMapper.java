package sum.cen.mapper;

import sum.cen.entity.SysUser;

/**
 * 
 * @author cen    2018年6月6日上午9:19:36
 *
 * @param <T>
 */
public interface SysUserMapper<T> extends BaseMapper<T>{
       
	/**
	 * 登录检查
	 * @param u
	 * @return
	 */
	public T queryLogin(SysUser u);
	
	/**
	 * 查询邮件总数 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
