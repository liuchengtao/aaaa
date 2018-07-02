package sum.cen.mapper;

import java.util.List;
import java.util.Map;

import sum.cen.entity.SysRoleRel;
/**
 * 
 * @author cen    2018年6月9日上午11:29:20
 *
 */
public interface SysRoleRelMapper<T> extends BaseMapper<T> {

	/**
	 * 根据权限Id查询关联角色列表
	 * @param param
	 * @return
	 */
	public List<SysRoleRel> queryByRoleId(Map<String,Object> param);
	
	
	/**
	 * 根据用户或菜单或按钮Id 查询关联角色列表
	 * @param param
	 * @return
	 */
	public List<SysRoleRel> queryByObjId(Map<String,Object> param);
	
	
     public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
}
