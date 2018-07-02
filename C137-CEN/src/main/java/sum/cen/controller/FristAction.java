package sum.cen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sum.cen.util.HtmlUtil;

@Controller
@RequestMapping("frist")
public class FristAction {
	  private Logger LOG=Logger.getLogger(FristAction.class);
	
	@RequestMapping("/logins")
	public ModelAndView login(){
		ModelAndView mv=new ModelAndView();
		LOG.debug("this is  FristAction login");
		mv.setViewName("login");
		return mv;
		
	}
	@RequestMapping("/toTest")
	public void toTest(String email,HttpServletResponse response) throws Exception{
		//ModelAndView mv=new ModelAndView();
		LOG.debug("this is  FristAction toTest"+" ss"+email);
		//mv.setViewName("login");
		//return mv;
		//PrintWriter out=response.getWriter();
		//out.print("success.false"+email);
		sendFailMessage(response,email);
		
	}
	 
	public void sendFailMessage(HttpServletResponse response,String msg) throws Exception{
		JSONObject result=new JSONObject();
		 // Map<String,Object> result=new HashMap<String,Object>();
		  result.put("success",false);
		  result.put("msg",msg);
		  response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(result.toString());
			out.flush();
			out.close();
	}

}
