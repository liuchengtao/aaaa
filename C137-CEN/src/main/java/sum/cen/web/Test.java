package sum.cen.web;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Test implements PageProcessor{
	//这个的意思就是编码，间隔时间，重试次数
	    private static Site site=Site.me().setRetryTimes(1000).setSleepTime(1000);
	    
	    @Override
	public Site getSite() {
	return site;
	}
	    /**
	     * 这个里面就是对你想要爬取的页面里面的具体内容加以限制的地方
	     */
	 @Override
	public void process(Page page) {
	      //根据标签查找自己想要的数据
	      //爬取的是这个网页中的logo图片的地址
	              String path=page.getHtml().xpath("//*[@class='main-content']/p").css("img", "src").get();
	              System.out.println(path);
	              
	             
	}
	public static void main(String[] args) {
	//这个里面填写你所想要爬取的页面
	//而我爬取的就是webmagic的官网
	Spider.create(new Test()).addUrl("http://webmagic.io/").thread(5).run();//Strat()也可以
	}


	}
