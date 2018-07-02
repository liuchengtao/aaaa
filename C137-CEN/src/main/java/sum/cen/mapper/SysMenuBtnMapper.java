package sum.cen.mapper;

import java.util.List;
/**
 * 
 * @author cen    2018��6��23������8:34:18
 *
 * @param <T>
 */
public interface SysMenuBtnMapper<T>  extends BaseMapper<T>{
    public List<T> queryByMenuid(Integer menuid);
	
	public List<T> queryByMenuUrl(String url); 
	
	public void deleteByMenuid(Integer menuid);
	
	public List<T> getMenuBtnByUser(Integer userid); 
	
	
	
	public List<T> queryByAll();
}
