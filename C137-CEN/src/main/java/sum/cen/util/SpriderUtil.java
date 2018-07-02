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
 * ������Ȥ����վС˵ ������
 * @author cen    2018��6��25������4:28:57
 *
 */
public class SpriderUtil {
	private static String ��Ȥ��="https://www.xxbiquge.com";
	private static Logger logger=Logger.getLogger(SpriderUtil.class);
	
	//�õ�С˵url����
	public static List<String> getBiquge(){
		List<String> list=new ArrayList<String>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("�������","https://www.xxbiquge.com/xclass/1/1.html");
		map.put("��������","https://www.xxbiquge.com/xclass/2/1.html");
		map.put("��������","https://www.xxbiquge.com/xclass/3/1.html");//����map��һ��
		map.put("��ʷ����","https://www.xxbiquge.com/xclass/4/1.html");
		map.put("�ƻ�����","https://www.xxbiquge.com/xclass/5/1.html");
		map.put("���ξ���","https://www.xxbiquge.com/xclass/6/1.html");
		map.put("ŮƵƵ��","https://www.xxbiquge.com/xclass/7/1.html");
		for(String o:map.keySet()){
			Connection cn=Jsoup.connect(map.get(o));
		try {
			//�õ��ɲ�����HTMLҳ�����
			Document doc=cn.get();
			//�õ�С˵����
		Element newscontent=doc.getElementById("newscontent");
	   Elements s2=	newscontent.getElementsByClass("s2");
		for(Element e:s2){
		String url=	e.getElementsByTag("a").first().attr("href");
			list.add(��Ȥ��+url);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("����Ŀ��url����url={ }"+map.get(o),e);
		}
		
	}
		return list;
	}
	
	//����С˵url�õ�С˵��Ϣ
		public static List<Novel> getMe(List<String> urls){
			List<Novel> list=new ArrayList<Novel>();
			for(int i=0;i<urls.size();i++){
				//System.out.println(urls.get(i));
				Novel n=new Novel();
			//	String url=��Ȥ��+urls.get(i);
				String url=urls.get(i);
				Connection cn=Jsoup.connect(url);
				
				try {
					Document doc=cn.get();
				Element info=doc.getElementById("info");
				//С˵��
				n.setName(info.getElementsByTag("h1").get(0).text());
				//С˵url
				n.setUrl(url);
				//С˵����
				n.setTag(doc.getElementsByClass("con_top").first().getElementsByTag("a").get(1).text());
				//С˵����
				n.setUser_no(info.getElementsByTag("p").get(0).text());
				//С˵״̬
				//n.setStatu(info.getElementsByTag("p").get(1).text());
				//С˵������ʱ��
				//n.setLast_time(info.getElementsByTag("p").get(2).text());
				//С˵�������½�
				//n.setNew_title(info.getElementsByTag("p").get(3).getElementsByTag("a").first().text());
				//С˵����
				n.setMessage(doc.getElementById("intro").getElementsByTag("p").get(0).text());
				//С˵ͼƬ��ַ
				n.setPic(doc.getElementById("fmimg").getElementsByTag("img").first().attr("src"));
				n.setCreate_time(new Date(System.currentTimeMillis()));
				n.setUpdate_time(new Date(System.currentTimeMillis()));
				list.add(n);
				} catch (IOException e) {
					logger.debug("����Ŀ��url����url={ } "+url+"||");
				}
			}
			return list;
		}
		//����С˵url �õ������½ڵ�url
		public static List<String> getContentUrls(String baseUrl){
			List<String> urls=new ArrayList<String>();
			Connection cn=Jsoup.connect(baseUrl);
			logger.debug("����Ŀ��url��Ӧ״̬��Ϊ={ }"+baseUrl+"||"+cn.response().statusCode());
			Document doc;
			try {
				doc = cn.get();
				Elements es=	doc.getElementById("list").getElementsByTag("dd");
				for(Element ee:es){
				String url	=ee.getElementsByTag("a").first().attr("href");
					urls.add(��Ȥ��+url);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return urls;
			
			
		}
		//����С˵�½�url �õ�����
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
