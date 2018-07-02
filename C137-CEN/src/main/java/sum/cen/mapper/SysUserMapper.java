package sum.cen.mapper;

import sum.cen.entity.SysUser;

/**
 * 
 * @author cen    2018��6��6������9:19:36
 *
 * @param <T>
 */
public interface SysUserMapper<T> extends BaseMapper<T>{
       
	/**
	 * ��¼���
	 * @param u
	 * @return
	 */
	public T queryLogin(SysUser u);
	
	/**
	 * ��ѯ�ʼ����� 
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
