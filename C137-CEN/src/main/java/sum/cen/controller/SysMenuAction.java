package sum.cen.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;






















import sum.cen.entity.SysMenu;
import sum.cen.entity.SysMenuBtn;
import sum.cen.entity.TreeNode;
import sum.cen.service.SysMenuBtnService;
import sum.cen.service.SysMenuService;
import sum.cen.util.TreeUtil;

/**
 * 
 * @author cen    2018年6月21日下午8:20:26
 *
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuAction extends BaseAction{
	@Autowired
	private SysMenuService<SysMenu> sysMenuService;
	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenubtnService;
    
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/menu")
	public String list(){
		return "sys/sysMenu";
	}
	
	@RequestMapping("/dataList")
	public void dataList(SysMenu model,HttpServletRequest request,HttpServletResponse response) throws Exception{
      model.setDeleted(0);
		List<SysMenu>  list=		sysMenuService.queryByList(model);
    int total= sysMenuService.queryByCount(model);
   JSONObject result =new  JSONObject();
    result.put("rows", list);
    result.put("total",total);
	 response.setCharacterEncoding("UTF-8");
	 response.setContentType("application/json");
	  PrintWriter out=response.getWriter();
	  out.print(result);
	}
	
	@RequestMapping("/save")
	public void save(SysMenu model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		       //设置菜单按钮数据
				List<SysMenuBtn> btns = getReqBtns(request);
				model.setBtns(btns);
				if(model.getId() == null){
					model.setDeleted(0);
					sysMenuService.add(model);
				}else{
					sysMenuService.update(model);
				}
				sendOkMessage(response, "保存成功~");
		
	}
	@RequestMapping("/getId")
	public void getId(int id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 System.out.println("id=="+id);
		SysMenu sysmenu=sysMenuService.queryById(id);
		if(sysmenu==null){
			sendFailMessage(response,"没有记录!");
			return ;
		}
		List<SysMenuBtn> btns = sysMenubtnService.queryByMenuid(id);
		sysmenu.setBtns(btns);
		JSONObject result=new JSONObject();
		  result.put("success",true);
		  result.put("data",sysmenu);
		writeJson(result,response);
	}
	@RequestMapping("/delete")
	public void delete(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		sysMenuService.delete(id);
		sendOkMessage(response,"菜单删除成功");
	}
	@RequestMapping("/getMenuTree")
	public void getMenuTree(Integer id,HttpServletResponse response) throws Exception{
		List<TreeNode> menuTree = treeMenu();
		//JSONObject result=new JSONObject();
		//result.put("menuTree",menuTree);
		JSONArray result=JSONArray.fromObject(menuTree);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);
		//writeJson(result,response);
	//	HtmlUtil.writerJson(response, menuTree);
	}
	/**
	 * 构建树形菜单
	 * @return
	 */
	public List<TreeNode> treeMenu(){
		List<SysMenu> rootMenus = sysMenuService.getRootMenu(null);//根节点
		List<SysMenu> childMenus = sysMenuService.getChildMenu();//子节点
		List<SysMenuBtn> childBtns = sysMenubtnService.queryByAll();
		TreeUtil util = new TreeUtil(rootMenus,childMenus,childBtns);
		return util.getTreeNode();
	}
	/**
	 * 获取请求数据中的菜单按钮数据
	 * @param request
	 * @return
	 */
	public List<SysMenuBtn> getReqBtns(HttpServletRequest request){
		List<SysMenuBtn> btnList= new ArrayList<SysMenuBtn>();
		String[] btnId  = request.getParameterValues("btnId");
		String[] btnName  = request.getParameterValues("btnName");
		String[] btnType  = request.getParameterValues("btnType");
		String[] actionUrls  = request.getParameterValues("actionUrls");
		String[] deleteFlag  = request.getParameterValues("deleteFlag");
		for (int i = 0; i < btnId.length; i++) {
			if(StringUtils.isNotBlank(btnName[i]) && StringUtils.isNotBlank(btnType[i])){
				SysMenuBtn btn = new SysMenuBtn();
				if(StringUtils.isNotBlank(btnId[i]) && NumberUtils.isNumber(btnId[i])){
					btn.setId(NumberUtils.toInt(btnId[i]));
				}
				btn.setBtnName(btnName[i]);
				btn.setBtnType(btnType[i]);
				btn.setActionUrls(actionUrls[i]);
				btn.setDeleteFlag(deleteFlag[i]);
				btnList.add(btn);
			}
		}
		return btnList;}
}
