package sum.cen.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import sum.cen.entity.SysUser;

/**
 *  session 操作 工具类
 * @author cen    2018年6月18日上午10:16:11
 *
 */
public class SessionUtil {
   private static final String SESSION_VALIDATECODE="session_validatecode";  //验证码
   
   
   private static final String SESSION_USER         = "session_user";         //登录用户
   
   
   private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
	
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮
   
   public static SysUser getUser(HttpServletRequest request){
	   return (SysUser)request.getSession(true).getAttribute(SESSION_USER);
   }
   /**
    * 设置用户到session域中
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
    * 设置值对象到session
    * @param request
    * @param value
    * @param key
    */
   public static void setSttr(HttpServletRequest request,Object value,String key){
	   request.getSession(true).setAttribute(key,value);
   }
   /**
    * 获取session中的值
    * @param request
    * @param key
    * @return
    */
   public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
   /**
    * 获取验证码
    * @param request
    * @return
    */
   public static String getValidateCode(HttpServletRequest request){
	   return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
   }
   /**
    * 设置验证码
    * @param request
    * @param validateCode
    */
   public static void setValidateCode(HttpServletRequest request,String validateCode){
	   request.getSession(true).setAttribute(SESSION_VALIDATECODE,  validateCode);
   }
   
   /**
    * 删除验证码
    * @param request
    */
   public static void removeValidatecode(HttpServletRequest request){
	   removeAttr(request,SESSION_VALIDATECODE);
   }
   /**
    * 从Session中删除指定key值
    * @param request
    * @param key
    */
   public static void removeAttr(HttpServletRequest request,String key){
	   request.getSession(true).removeAttribute(key);
   }
   public static boolean isAdmin(HttpServletRequest request){ //判断登录用户是否超级管理员
		 SysUser user =  getUser(request);
		 if(user == null  || user.getSuperAdmin() != 1){
			 return false;
		 }
		 return true;
	 }
   /**
    * 设置可访问权限url集合
    * @param request
    * @param urls
    */
   public static void setAccessUrls(HttpServletRequest request,List<String> urls){
	   setSttr(request,urls,SESSION_ACCESS_URLS);
   }
   /**
    * 判断url是否可访问
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
    * 设置菜单按钮
    * @param request
    * @param btnMap
    */
   public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //判断登录用户是否超级管理员
		 setSttr(request,btnMap,SESSION_MENUBTN_MAP);
	 }
	 
	 /**
	  * 获取菜单按钮
	  * @param request
	  * @param btnMap
	  */
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //判断登录用户是否超级管理员
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
}
