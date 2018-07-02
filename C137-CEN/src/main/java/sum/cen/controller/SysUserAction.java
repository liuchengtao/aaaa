package sum.cen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;








import sum.cen.entity.SysRoleRel;
import sum.cen.entity.SysUser;
import sum.cen.service.SysUserService;
/**
 * 
 * @author cen    2018年6月5日下午8:31:52
 *
 */

@Controller
@RequestMapping("/sysUser")
public class SysUserAction extends BaseAction {
	@Autowired
	private SysUserService<SysUser> sysUserService;
	
	@RequestMapping("/list")
	public String list(){
		return "/sys/sysUser";
	}
	@RequestMapping("/dataList")
	public void dataList(SysUser model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<SysUser> list=sysUserService.queryByList(model);
		            int total= sysUserService.queryByCount(model);
		          //  for(int i=0;i<dataList.size();i++){
		          //  System.out.println("testtime22=="+dataList.get(i).getCreateTime().toString());
		         //   dataList.get(i).setCreateTime(dataList.get(i).getCreateTime().toString()); 
		         //   }
		            JsonConfig jsonConfig = new JsonConfig();
		    	//	jsonConfig.setExcludes(new String[]{"orderList"});
		    		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		    		JSONArray dataList=JSONArray.fromObject(list,jsonConfig);
		JSONObject  result=new JSONObject();
		//Map<String,Object> result = new HashMap<String,Object>();
		result.put("rows", dataList);
		result.put("total",total);
		 // JSONObject json = JSONObject.fromObject(result);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);
		//writeJson(result, response);
	}
	/**
	 * 新增用户 或者修改用户
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(SysUser model,HttpServletResponse response) throws Exception{
	int count=	sysUserService.queryByCount(model);
	if(model.getId()==null){     //新增用户
		if(count>0){
			System.out.println("count=="+count);
			//TODO 用户已存在
			sendFailMessage(response,"用户已存在!");
			return;
			//System.out.println("测试=="+count);
		}else{
		model.setDeleted(0);
		sysUserService.add(model);
		sendOkMessage(response,"新增用户成功");
		return ;
		}
	}else{
	//修改用户
	if(count>1){
		//TODO 用户邮件名已存在
		sendFailMessage(response,"用户邮件重复!");
		return ;
		
	}
	sysUserService.updateBySelective(model);
	sendOkMessage(response,"修改用户成功");
	return ;
	}
	//TODO
	// JSONObject result=new JSONObject();
	//  result.put("success",true);
	//  result.put("msg","用户保存成功");
	 // response.setCharacterEncoding("UTF-8");
	//  response.setContentType("application/json");
	//  PrintWriter out=response.getWriter();
	//  out.print(result);
	}
	/**
	 * 删除用户
	 * @param ids
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public void delete(int id,HttpServletResponse response) throws Exception{
		sysUserService.delete(id);
		sendOkMessage(response,"用户删除成功");
		//TODO
		//删除成功
		/*JSONObject result=new JSONObject();
		  result.put("success",true);
		  result.put("msg","用户删除成功");
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);*/
	}
	/**
	 * 通过id获取用户
	 * @param id
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/getId")
	public void getIds(int id,HttpServletResponse response) throws Exception{
	SysUser user=	 sysUserService.queryById(id);
	if(user==null){
		//TODO 没有用户存在
		/*JSONObject result=new JSONObject();
		  result.put("success",false);
		  result.put("msg","用户不存在");
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);
		return;*/
		sendFailMessage(response,"用户不存在");
		return ;
	}
	//TODO 成功 返回user
	JSONObject result=new JSONObject();
	  result.put("success",true);
	  result.put("data",user);
	 /* response.setCharacterEncoding("UTF-8");
	  response.setContentType("application/json");
	  PrintWriter out=response.getWriter();
	  out.print(result);*/
	writeJson(result,response);
	}
	/**
	 * 操作员授权
	 */
	@RequestMapping("/userRole") 
	public String ToRole(){
		return "/sys/sysUserRole";
	}
	@RequestMapping("/userList") 
	public void  userList(SysUser model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		model.setState(0);
		dataList(model,request,response);
	}

	/**
	 * 查询用户信息
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUser") 
	public void getUser(Integer id,HttpServletResponse response)  throws Exception{
		SysUser bean  = sysUserService.queryById(id);
		if(bean  == null){
			sendFailMessage(response, "没有找到对应的记录!");
			return;
		}
		Integer[] roleIds = null;
		List<SysRoleRel>  roles  =sysUserService.getUserRole(bean.getId());
		if(roles != null){
			roleIds = new Integer[roles.size()];
			int i = 0;
			for(SysRoleRel rel : roles ){
				roleIds[i] = rel.getRoleId();
				i++;
			}
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		JSONObject result=new JSONObject();
		data.put("id", bean.getId());
		data.put("email", bean.getEmail());
		data.put("roleIds", roleIds);
		result.put("success", true);
		result.put("data", data);
		super.writeJson(result, response);
		
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addUserRole")
	public void addUserRole(Integer id,Integer roleIds[],HttpServletResponse response) throws Exception{
		sysUserService.addUserRole(id, roleIds);
		sendOkMessage(response, "保存成功");
	}
}
