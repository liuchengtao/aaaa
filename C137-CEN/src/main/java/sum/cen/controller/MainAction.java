package sum.cen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;





















import sum.cen.annotation.Auth;
import sum.cen.entity.SysMenu;
import sum.cen.entity.SysMenuBtn;
import sum.cen.entity.SysUser;
import sum.cen.entity.TreeNode;
import sum.cen.service.SysMenuBtnService;
import sum.cen.service.SysMenuService;
import sum.cen.service.SysUserService;
import sum.cen.util.SessionUtil;
import sum.cen.util.StringUtil;
import sum.cen.util.TreeUtil;
import sum.cen.util.URLUtils;






@Controller
public class MainAction extends BaseAction{
	@Autowired
	private SysUserService<SysUser> sysUserService;
	@Autowired
	private SysMenuService<SysMenu> sysMenuService;
	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;
	  
	  //登录页面
	@Auth(verifyLogin=false,verifyURL=false)
	  @RequestMapping("/login")
	  public String login(){
		  
		  return "login";
	  }
	  
	  //登录逻辑
	  @Auth(verifyLogin=false,verifyURL=false)
	  @RequestMapping("/toLogin")
	  public void toLogin(String email,String pwd,String verifyCode,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 System.out.println("test qwsd6665");
		  //  String vCode=SessionUtil.getValidateCode(request);
		//  SessionUtil.removeValidatecode(request);  //得到验证码 并且清空验证码 
		  //验证码是否为空
		  /*if(StringUtil.isBlank(vCode)){
			  //TODO
			  this.sendFailMessage(response, "验证码不能为空");
			  return;
		  }
		  //验证码是否正确
		  if(!verifyCode.toLowerCase().equals(vCode)){
			//TODO
			  this.sendFailMessage(response, "验证码错误");
			  return;
		  }*/
		  //用户账号是否为空
		  if(StringUtil.isBlank(email)){
			  //TODO
			  this.sendFailMessage(response, "this account not null");
			  return ;
		  }
		  //用户密码是否为空
		  if(StringUtil.isBlank(pwd)){
			  //TODO
			  this.sendFailMessage(response, "this password not null");
			  return ;
		  }
		SysUser user=  (SysUser) sysUserService.queryLogin(email, pwd);
		//登录失败 
		if(user==null){
			//TODO
			this.sendFailMessage(response, "login failure");
			return ;
		}
		//账号禁用
		if(1==user.getState()){
			//TODO
			this.sendFailMessage(response, "the account forbid");
			return ;
		}
		//设置User到Session 
		SessionUtil.setUser(request, user);
		//request.getSession().setAttribute("user1",user);
		//登录成功
		//TODO 
		this.sendOkMessage(response, "login ok");
	  }
	  @Auth(verifyLogin=false,verifyURL=false)
	  @RequestMapping("/logout")
	  public String logout(HttpServletRequest request){
		  SessionUtil.removeUser(request);
		  return "login";
	  }
	  
	  /**
	   *  按钮权限控制
	   * @param url
	   * @param request
	   * @param response
	   * @throws Exception
	   */
	  @Auth(verifyURL=false)
	  @RequestMapping("/getActionBtn")
	  public void getActionBtn(String url,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//  Map<String, Object> result = new HashMap<String, Object>();
		  JSONObject result=new JSONObject();
			List<String> actionTypes = new ArrayList<String>();
			//判断是否超级管理员
			if(SessionUtil.isAdmin(request)){
				result.put("allType", true);
			}else{
				URL url1  = new URL(url);
				String menuUrl =url1.getPath();
				menuUrl = StringUtils.remove(menuUrl,request.getContextPath());
				//获取权限按钮
				actionTypes = SessionUtil.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
				result.put("allType", false);
				result.put("types", actionTypes);
			}
			result.put("success", true);
			 writeJson(result, response);
			//HtmlUtil.writerJson(response, result);
	  }
	  /**
	   * 主页面
	   * @param request
	   * @param response
	   */
	  @Auth(verifyURL=false)
	  @RequestMapping("/main")
	  public ModelAndView  main(HttpServletRequest request,HttpServletResponse response){
		SysUser user=  SessionUtil.getUser(request);
		List<SysMenu> rootMenus = null;   //一级菜单
		List<SysMenu> childMenus = null;  // 二级菜单
		List<SysMenuBtn> childBtns=null;
		//超级管理员
		if(1==user.getSuperAdmin()){
			 rootMenus=sysMenuService.getRootMenu(null);        //查询所有根菜单
			childMenus=sysMenuService.getChildMenu();           //查询所有二级菜单
		}else{
			rootMenus=sysMenuService.queryRootMenuByUserId(user.getId());
			childMenus=sysMenuService.queryChildMenuByUserId(user.getId());
			childBtns = sysMenuBtnService.getMenuBtnByUser(user.getId());//按钮操作
			//TODO
			buildData(childMenus,childBtns,request); //构建必要的数据

		}
		TreeUtil treeutil=new TreeUtil(rootMenus,childMenus);
	    List<TreeNode> list=	treeutil.getTreeNode();
	    Map<String,Object> result = new HashMap<String, Object>();
	    result.put("menuList", list);
	    ModelAndView mv=new ModelAndView("main/main",result);
		  return mv;
	  }
	  
	  /**
		 * 构建树形菜单
		 * @return
		 */
		public List<TreeNode> treeMenu(){
			List<SysMenu> rootMenus = sysMenuService.getRootMenu(null);//根节点
			List<SysMenu> childMenus = sysMenuService.getChildMenu();//子节点
			List<SysMenuBtn> childBtns = sysMenuBtnService.queryByAll();
			TreeUtil util = new TreeUtil(rootMenus,childMenus,childBtns);
			return util.getTreeNode();
		}
	  public void buildData(List<SysMenu> childMenus,List<SysMenuBtn> childBtns,HttpServletRequest request){
		  //构建能够访问的url集合
		//能够访问的url列表
			List<String> accessUrls  = new ArrayList<String>();
			//菜单对应的按钮
			Map<String,List> menuBtnMap = new HashMap<String,List>(); 
			for(SysMenu menu: childMenus){
				//判断URL是否为空       ==判断它是不是子菜单
				if(StringUtils.isNotBlank(menu.getUrl())){
					List<String> btnTypes = new ArrayList<String>();
					for(SysMenuBtn btn  : childBtns){
						if(menu.getId().equals(btn.getMenuid())){
							btnTypes.add(btn.getBtnType());
							//按钮的actionUrl getId.do|save.do
							URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(),accessUrls);
						}
					}
					for(String test:accessUrls){
								System.out.println("accessUrls-btn==="+test);
							}
					menuBtnMap.put(menu.getUrl(), btnTypes);
					//菜单的action dataList.do|/sysMenu/getMenuTree.do
					URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),accessUrls);
					for(String test:accessUrls){
						System.out.println("accessUrls-menu==="+test);
					}
					//菜单的url /sysMenu/menu.shtml
					accessUrls.add(menu.getUrl());
				}
			}
			
			SessionUtil.setAccessUrls(request, accessUrls);//设置可访问的URL
			SessionUtil.setMemuBtnMap(request, menuBtnMap); //设置可用的按钮
		  
	  }
	
	
}
