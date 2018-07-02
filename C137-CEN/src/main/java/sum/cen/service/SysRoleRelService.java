package sum.cen.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sum.cen.entity.SysRoleRel;
import sum.cen.mapper.SysRoleRelMapper;

/**
 * 
 * @author cen    2018��6��10������10:08:57
 *
 * @param <T>
 */
@Service("sysRoleRelService")
public class SysRoleRelService<T> extends BaseService<T>{
	@Autowired
	private SysRoleRelMapper<T>  mapper;

	@Override
	public
	SysRoleRelMapper<T> getMapper() {
		return mapper;
	}
	 
	public List<SysRoleRel> queryByRoleId(Integer roleId,Integer relType){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("roleId",roleId);
		param.put("relType",relType);
		return mapper.queryByRoleId(param);
	}
	
	public List<SysRoleRel> queryByObjId(Integer objId,Integer relType){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("objId",objId);
		param.put("relType",relType);
		return mapper.queryByObjId(param);
	}
	/**
	 * ���ݹ�������id,��������ɾ�� 
	 * @param objId
	 * @param relType
	 */
	public void deleteByObjId(Integer objId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		getMapper().deleteByObjId(param);
	}
	
	/**
	 * ���ݽ�ɫidɾ�� 
	 * @param roleId
	 */
	public void deleteByRoleId(Integer roleId){
		deleteByRoleId(roleId,null);
	}
	
	/**
	 *  ���ݽ�ɫid,��������ɾ�� 
	 * @param roleId
	 * @param relType
	 */
	public void deleteByRoleId(Integer roleId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		getMapper().deleteByRoleId(param);
	}
	
	
    
}
