package sum.cen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sum.cen.mapper.SysMenuBtnMapper;

@Service
public class SysMenuBtnService<T> extends BaseService<T> {
	@Autowired
    private SysMenuBtnMapper<T> mapper;
	public List<T> queryByAll(){
		return getMapper().queryByAll();
	}
	
	
	
	public List<T> queryByMenuid(Integer menuid){
		return getMapper().queryByMenuid(menuid);
	}
	
	public List<T> queryByMenuUrl(String url){
		return getMapper().queryByMenuUrl(url);
	}
	
	public void deleteByMenuid(Integer menuid){
		getMapper().deleteByMenuid(menuid);
	}
	
	public List<T> getMenuBtnByUser(Integer userid){
		return getMapper().getMenuBtnByUser(userid);
	}
    
	@Override
	public SysMenuBtnMapper<T> getMapper() {
		return mapper;
	}

}
