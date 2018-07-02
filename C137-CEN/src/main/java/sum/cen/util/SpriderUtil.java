package sum.cen.util;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sum.cen.entity.Novel;


/**
 * 解析笔趣阁网站小说 工具类
 * @author cen    2018年6月25日下午4:28:57
 *
 */
public class SpriderUtil {
	private static String 笔趣阁="https://www.xxbiquge.com";
	private static Logger logger=Logger.getLogger(SpriderUtil.class);
	
	//得到小说url集合
	public static List<String> getBiquge(){
		List<String> list=new ArrayList<String>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("玄幻奇幻","https://www.xxbiquge.com/xclass/1/1.html");
		map.put("武侠仙侠","https://www.xxbiquge.com/xclass/2/1.html");
		map.put("都市言情","https://www.xxbiquge.com/xclass/3/1.html");//遍历map第一个
		map.put("历史军事","https://www.xxbiquge.com/xclass/4/1.html");
		map.put("科幻灵异","https://www.xxbiquge.com/xclass/5/1.html");
		map.put("网游竞技","https://www.xxbiquge.com/xclass/6/1.html");
		map.put("女频频道","https://www.xxbiquge.com/xclass/7/1.html");
		for(String o:map.keySet()){
			Connection cn=Jsoup.connect(map.get(o));
		try {
			//得到可操作的HTML页面对象
			Document doc=cn.get();
			//得到小说连接
		Element newscontent=doc.getElementById("newscontent");
	   Elements s2=	newscontent.getElementsByClass("s2");
		for(Element e:s2){
		String url=	e.getElementsByTag("a").first().attr("href");
			list.add(笔趣阁+url);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("解析目标url出错！url={ }"+map.get(o),e);
		}
		
	}
		return list;
	}
	
	//解析小说url得到小说信息
		public static List<Novel> getMe(List<String> urls){
			List<Novel> list=new ArrayList<Novel>();
			for(int i=0;i<urls.size();i++){
				//System.out.println(urls.get(i));
				Novel n=new Novel();
			//	String url=笔趣阁+urls.get(i);
				String url=urls.get(i);
				Connection cn=Jsoup.connect(url);
				
				try {
					Document doc=cn.get();
				Element info=doc.getElementById("info");
				//小说名
				n.setName(info.getElementsByTag("h1").get(0).text());
				//小说url
				n.setUrl(url);
				//小说分类
				n.setTag(doc.getElementsByClass("con_top").first().getElementsByTag("a").get(1).text());
				//小说作者
				n.setUser_no(info.getElementsByTag("p").get(0).text());
				//小说状态
				//n.setStatu(info.getElementsByTag("p").get(1).text());
				//小说最后更新时间
				//n.setLast_time(info.getElementsByTag("p").get(2).text());
				//小说最后更新章节
				//n.setNew_title(info.getElementsByTag("p").get(3).getElementsByTag("a").first().text());
				//小说描述
				n.setMessage(doc.getElementById("intro").getElementsByTag("p").get(0).text());
				//小说图片地址
				n.setPic(doc.getElementById("fmimg").getElementsByTag("img").first().attr("src"));
				n.setCreate_time(new Date(System.currentTimeMillis()));
				n.setUpdate_time(new Date(System.currentTimeMillis()));
				list.add(n);
				} catch (IOException e) {
					logger.debug("解析目标url出错！url={ } "+url+"||");
				}
			}
			return list;
		}
		//解析小说url 得到所有章节的url
		public static List<String> getContentUrls(String baseUrl){
			List<String> urls=new ArrayList<String>();
			Connection cn=Jsoup.connect(baseUrl);
			logger.debug("连接目标url响应状态吗为={ }"+baseUrl+"||"+cn.response().statusCode());
			Document doc;
			try {
				doc = cn.get();
				Elements es=	doc.getElementById("list").getElementsByTag("dd");
				for(Element ee:es){
				String url	=ee.getElementsByTag("a").first().attr("href");
					urls.add(笔趣阁+url);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return urls;
			
			
		}
		//解析小说章节url 得到内容
		public static String getContentsByUrl(String contentUrl){
			StringBuffer con=new StringBuffer();
			Document doc;
			try {
				doc = Jsoup.connect(contentUrl).get();
				String title=doc.getElementsByClass("bookname").first().getElementsByTag("h1").first().text();
				String content=	doc.getElementById("content").text();
				 Pattern pattern1 = Pattern
					       .compile("[ ????]");
		 	 String[] strs=pattern1.split(content);
			   con.append(title+"|");
			   for(String str:strs){
			   con.append(str+"|");}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return con.toString();
			
		}
}
