package sum.cen.mapper;

import java.util.List;

public interface SysMenuMapper<T> extends BaseMapper<T>{
	
	/**
	 * ��ȡ�����˵�
	 * @return
	 */
	public List<T> getRootMenu(Object menuId);
	
	/**
	 * ��ȡ�Ӳ˵�
	 * @return
	 */
	public List<T>  getChildMenu();
	
	/**
	 * ��ѯ������Чϵͳ�˵��б�
	 * @return
	 */
	public List<T>  queryByAll();
	
	/**
	 * ���ݽ�ɫId��ȡ�˵�������Ҫ���˵���
	 */
	public List<T> queryByRoleId(Object roleId);
	
	/**
	 * �����û�id��ѯ���˵��˵� 
	 * @param userId
	 * @return
	 */
	public List<T> queryRootMenuByUserId(Object userId);
	
	/**
	 * �����û�id��ѯ�Ӳ˵��˵�
	 */
	public List<T> queryChildMenuByUserId(Object userId);

}
