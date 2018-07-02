package sum.cen.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @描述 输出html页面工具类
 * @author cen    2018年6月10日下午2:48:42
 *
 */
public class HtmlUtil {
	/**
	 * 输出json 字符串 →→ HTML页面
	 * @param response
	 * @param str
	 */
	public static void writerJson(HttpServletResponse response,String str){
		writer(response, str);
	}
	
	/**
	 * 输出json对象→HTML页面
	 * @param response
	 * @param obj
	 */
     public static void writerJson(HttpServletResponse response,Object obj){
    	 response.setContentType("application/json");
    	 writer(response,obj);
		
	}
     
     /**
      * 输出str →HTML页面
      * @param response
      * @param htmlStr
      */
     public static void writerHtml(HttpServletResponse response,String htmlStr){
    	 writer(response,htmlStr);
     }
     
     public static void writer(HttpServletResponse response,Object str){
    	 //response.setContentType("text/html;charset=utf-8");
		//  PrintWriter out=response.getWriter();
		//  out.print(result);
    try {
    	response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out= response.getWriter();
		out.print(str);
		out.flush();
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     }

}
