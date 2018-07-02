package sum.cen.web;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class TieBa implements PageProcessor{
     
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	
	@Override
	public void process(Page page) {
		 String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";
		                  //http://blog.sina.com.cn/s/blog_58ae76e80100s2mo.html
		// TODO Auto-generated method stub
		//String ht=page.getHtml().toString();
		//System.out.println(ht);
		String tr=page.getHtml().xpath("//div[@class=\"articleList\"]").toString();
	//int q=	page.getHtml().xpath("//div[@class=\"articleList\"]").all().size();
	System.out.println(	 page.getHtml().xpath("//div[@class=\"articleList\"]").regex("http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html").all().toString());
		//System.out.println(q+" "+tr);
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static void main(String[] args) {
		String baseUrl="http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html";
		// TODO Auto-generated method stub
		//Spider.create(new TieBa()).test("https://tieba.baidu.com/f?ie=utf-8&kw=%E8%83%8C%E9%94%85&fr=search");
       Spider.create(new TieBa()).addUrl(baseUrl).thread(5).run();
	}

}
