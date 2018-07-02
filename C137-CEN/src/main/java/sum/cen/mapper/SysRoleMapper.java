package sum.cen.mapper;

import java.util.List;
/**
 * 
 * @author cen    2018年6月9日上午11:15:15
 *
 * @param <T>
 */
public interface SysRoleMapper<T> extends BaseMapper<T>{
  
	/**
	 * 查询所有有效权限 state=0
	 * @return
	 */
	public List<T> queryAllList();
	
	/**
	 * 通过用户Id 查询所有有效权限
	 * @return
	 */
	public List<T> queryByUserId(int userId);
}
