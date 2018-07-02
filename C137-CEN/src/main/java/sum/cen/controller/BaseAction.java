package sum.cen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;





import sum.cen.util.MyEditor;
import net.sf.json.JSONObject;

/**
 * 
 * @author cen    2018年6月7日上午10:04:45
 *
 */
public class BaseAction {
	private final static String MSG="msg";
	private final static String SUCCESS="success";
	
	
	@InitBinder  
	   protected void initBinder(WebDataBinder binder) {  
			 binder.registerCustomEditor(Date.class, new CustomDateEditor(
	                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
			 binder.registerCustomEditor(int.class,new MyEditor()); 
	   }  
	
	public void sendFailMessage(HttpServletResponse response,String msg) throws Exception{
		JSONObject result=new JSONObject();
		result.put(SUCCESS,false);
		result.put(MSG,msg);
		 response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);
	}
	public void sendOkMessage(HttpServletResponse response,String msg) throws Exception{
		JSONObject result=new JSONObject();
		result.put(SUCCESS,true);
		result.put(MSG,msg);
		 response.setCharacterEncoding("UTF-8");
		  response.setContentType("application/json");
		  PrintWriter out=response.getWriter();
		  out.print(result);
	}
	/**
	 * 输出json数据
	 * @param result
	 * @throws Exception 
	 */
	public void writeJson(JSONObject result,HttpServletResponse response) throws Exception{
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/json,charset=utf-8");
		  PrintWriter out=response.getWriter();
		  out.print(result);
	}
	
	/**
	 * 输出json
	 * @param o
	 * @param response
	 * @throws Exception 
	 */
	public void writeJsonObj(Object o , HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json,charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(o);
	}
     
}
