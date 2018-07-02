package sum.cen.mapper;

import java.util.List;
/**
 * 
 * @author cen    2018��6��9������11:15:15
 *
 * @param <T>
 */
public interface SysRoleMapper<T> extends BaseMapper<T>{
  
	/**
	 * ��ѯ������ЧȨ�� state=0
	 * @return
	 */
	public List<T> queryAllList();
	
	/**
	 * ͨ���û�Id ��ѯ������ЧȨ��
	 * @return
	 */
	public List<T> queryByUserId(int userId);
}
