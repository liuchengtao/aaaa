package sum.cen.mapper;

import java.util.List;

public interface SysMenuMapper<T> extends BaseMapper<T>{
	
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<T> getRootMenu(Object menuId);
	
	/**
	 * 获取子菜单
	 * @return
	 */
	public List<T>  getChildMenu();
	
	/**
	 * 查询所有有效系统菜单列表
	 * @return
	 */
	public List<T>  queryByAll();
	
	/**
	 * 根据角色Id获取菜单（不需要父菜单）
	 */
	public List<T> queryByRoleId(Object roleId);
	
	/**
	 * 根据用户id查询父菜单菜单 
	 * @param userId
	 * @return
	 */
	public List<T> queryRootMenuByUserId(Object userId);
	
	/**
	 * 根据用户id查询子菜单菜单
	 */
	public List<T> queryChildMenuByUserId(Object userId);

}
