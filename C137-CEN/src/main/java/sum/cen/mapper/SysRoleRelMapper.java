package sum.cen.mapper;

import java.util.List;
import java.util.Map;

import sum.cen.entity.SysRoleRel;
/**
 * 
 * @author cen    2018��6��9������11:29:20
 *
 */
public interface SysRoleRelMapper<T> extends BaseMapper<T> {

	/**
	 * ����Ȩ��Id��ѯ������ɫ�б�
	 * @param param
	 * @return
	 */
	public List<SysRoleRel> queryByRoleId(Map<String,Object> param);
	
	
	/**
	 * �����û���˵���ťId ��ѯ������ɫ�б�
	 * @param param
	 * @return
	 */
	public List<SysRoleRel> queryByObjId(Map<String,Object> param);
	
	
     public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
}
