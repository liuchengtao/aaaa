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
 * @author cen    2018��6��18������11:11:31
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
	public void saveBtns(int menuId,List<SysMenuBtn> btns){
		if(btns==null|| btns.isEmpty()){  //?
			return ;
		}
		for(SysMenuBtn btn :btns){
			if(btn.getId()!=null&&btn.getDeleteFlag().equals("1")){
				sysMenuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuId);
			if(btn.getId()==null){
				sysMenuBtnService.add(btn);
			}else{
				sysMenuBtnService.update(btn);
			}
			
		}
		
	}
	
	public void add(SysMenu menu){
		super.add((T) menu);
		saveBtns(menu.getId(),menu.getBtns());
	}
	@Override
	public void delete(Object id)  {
		    super.delete(id);
		   //ɾ��������ϵ
			sysRoleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			sysMenuBtnService.deleteByMenuid((Integer)id);
		
	}


}
