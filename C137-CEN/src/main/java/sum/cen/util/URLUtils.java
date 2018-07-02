package sum.cen.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class URLUtils { 
	
	
	
	/**
	 * 组装按钮下URL
	 * @param menuUrl
	 * @param actionUrls
	 * @return
	 */
	public static void getBtnAccessUrls(String menuUrl,String actionUrls,List<String> accessUrls){
	//	System.out.println("test3=="+accessUrls == null);
		if(menuUrl == null || actionUrls == null || accessUrls == null ){
			return;
		}
		String url = "save.do| action.do |/user/manger/abcd.do";
		String[] actions =StringUtils.split(actionUrls , "|");
		String menuUri = StringUtils.substringBeforeLast(menuUrl, "/");
		for(String action : actions){
			action = StringUtils.trim(action);
			if(StringUtils.startsWith(action, "/"))
				accessUrls.add(action);
			else
				accessUrls.add(menuUri+"/"+action);
		}
	//	for(String test:accessUrls){
	//		System.out.println("accessUrls==="+test);
	//	}
	}
	
	public static void main(String[] args) {
//		String requrl = "http://127.0.0.1:8080/ms/sysMenu/menu.shtml";
//		requrl = "https://127.0.0.1:8080/ms/sysMenu/menu.shtml?A=1&B=2#111";
//		String uri = getReqUri(requrl);
//		System.out.println(uri);
//		System.out.println(StringUtils.remove(uri, "/ms/"));
//		System.err.println(getReqUri(requrl));\
		String menu = "/sysMneu/dataList.do";
		String url = "save.do| action.do |/user/manger/abcd.do";
		String[] actions =StringUtils.split(url, "|");
		String menuUri = StringUtils.substringBeforeLast(menu, "/");
		for(String action : actions){
			action = StringUtils.trim(action);
			if(StringUtils.startsWith(action, "/"))
				System.out.println(action);
			else
				System.out.println(menuUri+"/"+action);
		}
	}
	
	
}
