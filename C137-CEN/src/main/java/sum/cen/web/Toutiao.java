package sum.cen.web;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Toutiao  implements PageProcessor{
	private static String baseUrl="https://www.toutiao.com/ch/news_hot";
	//uuid="w:0f59a5e8adca4c19bb57d127ffd09926"; 
	//tt_webid=6572449276042855950; 
	//WEATHER_CITY=%E5%8C%97%E4%BA%AC; 
//	tt_webid=6572449276042855950; 
	//UM_distinctid=1644b0bbabbaf-0bf5d78d84ebb1-7a6b1735-100200-1644b0bbabc36b; 
	//CNZZDATA1259612802=1795438513-1530266878-https%253A%252F%252Fwww.baidu.com%252F%7C1530266878; 
	//csrftoken=2d0b1699620e9ad57f5130b771d21d9c; sso_login_status=0; 
	//__tasessionId=hjzc9o1w41530270567370
	private Site site = Site.me()
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0")
			.setDomain("https://www.toutiao.com")
	         .addCookie("uuid","w:0f59a5e8adca4c19bb57d127ffd09926")
			.addCookie("tt_webid","6572449276042855950")
			.addCookie("WEATHER_CITY","%E5%8C%97%E4%BA%AC")
			.addCookie("UM_distinctid","1644b0bbabbaf-0bf5d78d84ebb1-7a6b1735-100200-1644b0bbabc36b")
			.addCookie("CNZZDATA1259612802","1795438513-1530266878-https%253A%252F%252Fwww.baidu.com%252F%7C1530266878")
			.addCookie("csrftoken","2d0b1699620e9ad57f5130b771d21d9c")
			.addCookie("sso_login_status","0")
	         .addCookie("__tasessionId","hjzc9o1w41530270567370");
			
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		System.out.println(page.getHtml().toString());
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public static void main(String[] args) {
	   Spider.create(new Toutiao()).test(baseUrl);
		//Spider.create(new Toutiao()).addUrl(baseUrl).run();

	}
}
