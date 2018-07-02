package sum.cen.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @���� ���htmlҳ�湤����
 * @author cen    2018��6��10������2:48:42
 *
 */
public class HtmlUtil {
	/**
	 * ���json �ַ��� ���� HTMLҳ��
	 * @param response
	 * @param str
	 */
	public static void writerJson(HttpServletResponse response,String str){
		writer(response, str);
	}
	
	/**
	 * ���json�����HTMLҳ��
	 * @param response
	 * @param obj
	 */
     public static void writerJson(HttpServletResponse response,Object obj){
    	 response.setContentType("application/json");
    	 writer(response,obj);
		
	}
     
     /**
      * ���str ��HTMLҳ��
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
