package sum.cen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import sum.cen.entity.SysMenu;
import sum.cen.entity.SysMenuBtn;
import sum.cen.entity.SysRoleRel;
import sum.cen.entity.SysRoleRel.RelType;
import sum.cen.mapper.SysMenuMapper;
/**
 * 
 * @author cen    2018年6月18日上午11:11:31
 *
 * @param <T>
 */
@Service
public class SysMenuService<T> extends BaseService<T> {
	@Autowired
	private SysMenuMapper<T> mapper;
	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;
	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;

	@Override
	public
	SysMenuMapper<T> getMapper() {
		return mapper;
	}
	public List<T> getRootMenu(Object menuId){
	return	mapper.getRootMenu(menuId);
	}
	public List<T> getChildMenu(){
		return mapper.getChildMenu();
	}
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	public List<T> queryByRoleId(Object roleId){
		return mapper.queryByRoleId(roleId);
	}
	public List<T> queryRootMenuByUserId(Object userId){
		return mapper.queryRootMenuByUserId(userId);
	}
	public List<T> queryChildMenuByUserId(Object userId){
		return mapper.queryChildMenuByUserId(userId);
	}
	@Override
	public void delete(Object id)  {
		super.delete(id);
		//删除关联关系
		
			sysRoleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			sysMenuBtnService.deleteByMenuid((Integer)id);
		
	}


}
