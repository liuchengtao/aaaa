package sum.cen.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import sum.cen.entity.SysUser;

/**
 *  session ���� ������
 * @author cen    2018��6��18������10:16:11
 *
 */
public class SessionUtil {
   private static final String SESSION_VALIDATECODE="session_validatecode";  //��֤��
   
   
   private static final String SESSION_USER         = "session_user";         //��¼�û�
   
   
   private static final String SESSION_ACCESS_URLS = "session_access_urls"; //ϵͳ�ܹ����ʵ�URL
	
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //ϵͳ�˵���ť
   
   public static SysUser getUser(HttpServletRequest request){
	   return (SysUser)request.getSession(true).getAttribute(SESSION_USER);
   }
   /**
    * �����û���session����
    * @param request
    * @param value
    */
   public static void setUser(HttpServletRequest request,SysUser user){
	   request.getSession(true).setAttribute(SESSION_USER, user);
   }
   public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
   /**
    * ����ֵ����session
    * @param request
    * @param value
    * @param key
    */
   public static void setSttr(HttpServletRequest request,Object value,String key){
	   request.getSession(true).setAttribute(key,value);
   }
   /**
    * ��ȡsession�е�ֵ
    * @param request
    * @param key
    * @return
    */
   public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
   /**
    * ��ȡ��֤��
    * @param request
    * @return
    */
   public static String getValidateCode(HttpServletRequest request){
	   return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
   }
   /**
    * ������֤��
    * @param request
    * @param validateCode
    */
   public static void setValidateCode(HttpServletRequest request,String validateCode){
	   request.getSession(true).setAttribute(SESSION_VALIDATECODE,  validateCode);
   }
   
   /**
    * ɾ����֤��
    * @param request
    */
   public static void removeValidatecode(HttpServletRequest request){
	   removeAttr(request,SESSION_VALIDATECODE);
   }
   /**
    * ��Session��ɾ��ָ��keyֵ
    * @param request
    * @param key
    */
   public static void removeAttr(HttpServletRequest request,String key){
	   request.getSession(true).removeAttribute(key);
   }
   public static boolean isAdmin(HttpServletRequest request){ //�жϵ�¼�û��Ƿ񳬼�����Ա
		 SysUser user =  getUser(request);
		 if(user == null  || user.getSuperAdmin() != 1){
			 return false;
		 }
		 return true;
	 }
   /**
    * ���ÿɷ���Ȩ��url����
    * @param request
    * @param urls
    */
   public static void setAccessUrls(HttpServletRequest request,List<String> urls){
	   setSttr(request,urls,SESSION_ACCESS_URLS);
   }
   /**
    * �ж�url�Ƿ�ɷ���
    * @param request
    * @param url
    * @return
    */
   public static boolean isAccessUrl(HttpServletRequest request,String url){ 
		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
			 return false;
		 }
		 return true;
	 }
   /**
    * ���ò˵���ť
    * @param request
    * @param btnMap
    */
   public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //�жϵ�¼�û��Ƿ񳬼�����Ա
		 setSttr(request,btnMap,SESSION_MENUBTN_MAP);
	 }
	 
	 /**
	  * ��ȡ�˵���ť
	  * @param request
	  * @param btnMap
	  */
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //�жϵ�¼�û��Ƿ񳬼�����Ա
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
}
