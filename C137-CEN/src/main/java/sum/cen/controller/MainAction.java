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
	  
	  //��¼ҳ��
	@Auth(verifyLogin=false,verifyURL=false)
	  @RequestMapping("/login")
	  public String login(){
		  
		  return "login";
	  }
	  
	  //��¼�߼�
	  @Auth(verifyLogin=false,verifyURL=false)
	  @RequestMapping("/toLogin")
	  public void toLogin(String email,String pwd,String verifyCode,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 System.out.println("test qwsd6665");
		  //  String vCode=SessionUtil.getValidateCode(request);
		//  SessionUtil.removeValidatecode(request);  //�õ���֤�� ���������֤�� 
		  //��֤���Ƿ�Ϊ��
		  /*if(StringUtil.isBlank(vCode)){
			  //TODO
			  this.sendFailMessage(response, "��֤�벻��Ϊ��");
			  return;
		  }
		  //��֤���Ƿ���ȷ
		  if(!verifyCode.toLowerCase().equals(vCode)){
			//TODO
			  this.sendFailMessage(response, "��֤�����");
			  return;
		  }*/
		  //�û��˺��Ƿ�Ϊ��
		  if(StringUtil.isBlank(email)){
			  //TODO
			  this.sendFailMessage(response, "this account not null");
			  return ;
		  }
		  //�û������Ƿ�Ϊ��
		  if(StringUtil.isBlank(pwd)){
			  //TODO
			  this.sendFailMessage(response, "this password not null");
			  return ;
		  }
		SysUser user=  (SysUser) sysUserService.queryLogin(email, pwd);
		//��¼ʧ�� 
		if(user==null){
			//TODO
			this.sendFailMessage(response, "login failure");
			return ;
		}
		//�˺Ž���
		if(1==user.getState()){
			//TODO
			this.sendFailMessage(response, "the account forbid");
			return ;
		}
		//����User��Session 
		SessionUtil.setUser(request, user);
		//request.getSession().setAttribute("user1",user);
		//��¼�ɹ�
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
	   *  ��ťȨ�޿���
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
			//�ж��Ƿ񳬼�����Ա
			if(SessionUtil.isAdmin(request)){
				result.put("allType", true);
			}else{
				URL url1  = new URL(url);
				String menuUrl =url1.getPath();
				menuUrl = StringUtils.remove(menuUrl,request.getContextPath());
				//��ȡȨ�ް�ť
				actionTypes = SessionUtil.getMemuBtnListVal(request, StringUtils.trim(menuUrl));
				result.put("allType", false);
				result.put("types", actionTypes);
			}
			result.put("success", true);
			 writeJson(result, response);
			//HtmlUtil.writerJson(response, result);
	  }
	  /**
	   * ��ҳ��
	   * @param request
	   * @param response
	   */
	  @Auth(verifyURL=false)
	  @RequestMapping("/main")
	  public ModelAndView  main(HttpServletRequest request,HttpServletResponse response){
		SysUser user=  SessionUtil.getUser(request);
		List<SysMenu> rootMenus = null;   //һ���˵�
		List<SysMenu> childMenus = null;  // �����˵�
		List<SysMenuBtn> childBtns=null;
		//��������Ա
		if(1==user.getSuperAdmin()){
			 rootMenus=sysMenuService.getRootMenu(null);        //��ѯ���и��˵�
			childMenus=sysMenuService.getChildMenu();           //��ѯ���ж����˵�
		}else{
			rootMenus=sysMenuService.queryRootMenuByUserId(user.getId());
			childMenus=sysMenuService.queryChildMenuByUserId(user.getId());
			childBtns = sysMenuBtnService.getMenuBtnByUser(user.getId());//��ť����
			//TODO
			buildData(childMenus,childBtns,request); //������Ҫ������

		}
		TreeUtil treeutil=new TreeUtil(rootMenus,childMenus);
	    List<TreeNode> list=	treeutil.getTreeNode();
	    Map<String,Object> result = new HashMap<String, Object>();
	    result.put("menuList", list);
	    ModelAndView mv=new ModelAndView("main/main",result);
		  return mv;
	  }
	  
	  /**
		 * �������β˵�
		 * @return
		 */
		public List<TreeNode> treeMenu(){
			List<SysMenu> rootMenus = sysMenuService.getRootMenu(null);//���ڵ�
			List<SysMenu> childMenus = sysMenuService.getChildMenu();//�ӽڵ�
			List<SysMenuBtn> childBtns = sysMenuBtnService.queryByAll();
			TreeUtil util = new TreeUtil(rootMenus,childMenus,childBtns);
			return util.getTreeNode();
		}
	  public void buildData(List<SysMenu> childMenus,List<SysMenuBtn> childBtns,HttpServletRequest request){
		  //�����ܹ����ʵ�url����
		//�ܹ����ʵ�url�б�
			List<String> accessUrls  = new ArrayList<String>();
			//�˵���Ӧ�İ�ť
			Map<String,List> menuBtnMap = new HashMap<String,List>(); 
			for(SysMenu menu: childMenus){
				//�ж�URL�Ƿ�Ϊ��       ==�ж����ǲ����Ӳ˵�
				if(StringUtils.isNotBlank(menu.getUrl())){
					List<String> btnTypes = new ArrayList<String>();
					for(SysMenuBtn btn  : childBtns){
						if(menu.getId().equals(btn.getMenuid())){
							btnTypes.add(btn.getBtnType());
							//��ť��actionUrl getId.do|save.do
							URLUtils.getBtnAccessUrls(menu.getUrl(), btn.getActionUrls(),accessUrls);
						}
					}
					for(String test:accessUrls){
								System.out.println("accessUrls-btn==="+test);
							}
					menuBtnMap.put(menu.getUrl(), btnTypes);
					//�˵���action dataList.do|/sysMenu/getMenuTree.do
					URLUtils.getBtnAccessUrls(menu.getUrl(), menu.getActions(),accessUrls);
					for(String test:accessUrls){
						System.out.println("accessUrls-menu==="+test);
					}
					//�˵���url /sysMenu/menu.shtml
					accessUrls.add(menu.getUrl());
				}
			}
			
			SessionUtil.setAccessUrls(request, accessUrls);//���ÿɷ��ʵ�URL
			SessionUtil.setMemuBtnMap(request, menuBtnMap); //���ÿ��õİ�ť
		  
	  }
	
	
}
