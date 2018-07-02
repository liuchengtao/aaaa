package sum.cen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import sum.cen.entity.SysRoleRel;
import sum.cen.entity.SysRoleRel.RelType;
import sum.cen.entity.SysUser;
import sum.cen.mapper.BaseMapper;
import sum.cen.mapper.SysUserMapper;

/**
 * 
 * @author cen    2018��6��6������2:45:21
 *
 * @param <SysUser>
 */
@Service("sysUserService")
public class SysUserService<T> extends BaseService<T>{
	private final static Logger LOG=Logger.getLogger(SysUserService.class);
	
	@Autowired
    private SysUserMapper<T> mapper;
	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;
	@Override
	public SysUserMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	@Override
	public void delete(Object ids)  {
		super.delete(ids);
		
			sysRoleRelService.deleteByObjId((Integer)ids, RelType.USER.key);
		
	}
	//�û���¼
	public T queryLogin(String email,String pwd){
		SysUser u=new SysUser();
		u.setEmail(email);
		u.setPwd(pwd);
		return getMapper().queryLogin(u);
	}
	//��ѯ�û���������
	public int getUserCountByEmail(String email){
		return getMapper().getUserCountByEmail(email);
	}
	/**
	 * ��ѯ�û�Ȩ��
	 * @param userId
	 * @return
	 */
	public List<SysRoleRel> getUserRole(Integer userId){
		return sysRoleRelService.queryByObjId(userId,RelType.USER.key);
	}
	
	/**
	 * ����û�Ȩ��
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(Integer userId,Integer[] roleIds) throws Exception{
		if(userId == null ||  roleIds == null || roleIds.length < 1 ){ 
			return;
		}
		//���������ϵ
		sysRoleRelService.deleteByObjId(userId, RelType.USER.key);
		for(Integer roleId :roleIds ){ 
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(RelType.USER.key);
			sysRoleRelService.add(rel);
		}
	}
	

}
