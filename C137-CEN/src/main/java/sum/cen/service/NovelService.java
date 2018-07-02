package sum.cen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import sum.cen.entity.SysRoleRel;
import sum.cen.entity.SysRoleRel.RelType;
import sum.cen.entity.SysUser;
import sum.cen.mapper.BaseMapper;
import sum.cen.mapper.NovelMapper;
import sum.cen.mapper.SysUserMapper;

/**
 * 
 * @author cen    2018年6月25日下午3:04:37
 *
 * @param <T>
 */
@Service("novelService")
public class NovelService<T> extends BaseService<T>{
	private final static Logger LOG=Logger.getLogger(NovelService.class);
	
	@Autowired
    private NovelMapper<T> mapper;
	
	@Override
	public NovelMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
		
	
	

}
