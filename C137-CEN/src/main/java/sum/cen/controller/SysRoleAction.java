package sum.cen.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import sum.cen.entity.SysMenu;
import sum.cen.entity.SysMenuBtn;
import sum.cen.entity.SysRole;
import sum.cen.entity.SysRoleRel;
import sum.cen.service.SysMenuBtnService;
import sum.cen.service.SysMenuService;
import sum.cen.service.SysRoleRelService;
import sum.cen.service.SysRoleService;

@Controller
@RequestMapping("/sysRole")
public class SysRoleAction extends BaseAction{
	  @Autowired
	  private SysRoleService<SysRole> sysRoleService;
	  @Autowired
	  private  SysRoleRelService<SysRoleRel> sysRoleRelService;
	  @Autowired
	  private  SysMenuService<SysMenu>  sysMenuService;
	  @Autowired
	  private  SysMenuBtnService<SysMenuBtn>  sysMenuBtnService;
	
	  @RequestMapping("/role")
	public String page(){
		return "sys/sysRole";
	}
	  @RequestMapping("/dataList")
	  public void dataList(SysRole role,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<SysRole> dataList=  sysRoleService.queryByList(role);
		  int count=sysRoleService.queryByCount(role);
		  JSONObject result=new JSONObject();
		  result.put("rows",dataList);
		  result.put("total",count);
		  writeJson(result,response);
	  }
	
	  
	//���ݽ�ɫid��ѯ���в˵��Լ�����Ӧ�İ�ť
	  @RequestMapping("/getId")
	  public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		  //�õ���ɫ����
		SysRole sysRole=    sysRoleService.queryById(id);
		if(sysRole!=null){
			//�ý�ɫȨ�޶�Ӧ�����в˵�  
		//	List<SysRoleRel> roleRels=sysRoleRelService.queryByRoleId(sysRole.getId(), 0);
			List<SysMenu>    menus=sysMenuService.queryByRoleId(sysRole.getId());
			Integer[] menuIds = null;
			if(menus!=null){
				menuIds=new Integer[menus.size()];
				int i=0;
			for(SysMenu menu:menus){
				  menuIds[i]=menu.getId();
				  i++;
			}
			}
			//�ý�ɫȨ�޶�Ӧ�����а�ť
			List<SysRoleRel> roleRels=sysRoleRelService.queryByRoleId(sysRole.getId(), 2);
			Integer[] btnIds = null;
			if(roleRels!=null){
				btnIds=new Integer[roleRels.size()];
				int i=0;
				for(SysRoleRel rel:roleRels){
					btnIds[i]=rel.getObjId();
					i++;
				}
				
			}
			Map<String,Object> data = BeanUtils.describe(sysRole);
			data.put("menuIds",menuIds);
			data.put("btnIds", btnIds);
			//data.put("success",true);
			//JSONObject result=JSONObject.fromObject(data);
			JSONObject result=new JSONObject();
			result.put("data", data);
			result.put("success",true);
			writeJson(result,response);
			
		}
	  }
	  @RequestMapping("/save")
	  public void save(SysRole role,Integer[] menuIds,Integer[] btnIds,HttpServletResponse response) throws Exception{
		  if(role.getId()==null){  //������ɫ
			  sysRoleService.add(role, menuIds, btnIds);
		  }else{                     //�޸Ľ�ɫ
			  sysRoleService.update(role, menuIds, btnIds);
		  }
		  sendOkMessage(response,"����ɹ�~~~");
	  }
	  @RequestMapping("/delete")
	  public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 //   Integer[] ids=new Integer[1];
		  //    ids[0]=id;
		  sysRoleService.delete(id);
		  sendOkMessage(response,"ɾ���ɹ�~~~");
	  }
	  @RequestMapping("/loadRoleList")
		public void loadRoleList(HttpServletResponse response) throws Exception{
			List<SysRole>  roloList = sysRoleService.queryAllList();
		//JSONObject result=JSONObject.fromObject(roloList);//.fromObject(roloList);
			JSONArray result=JSONArray.fromObject(roloList);
		//result.put("result",roloList);
			response.setCharacterEncoding("UTF-8");
			 response.setContentType("application/json");
			  PrintWriter out=response.getWriter();
			  out.print(result);;
		}

}
