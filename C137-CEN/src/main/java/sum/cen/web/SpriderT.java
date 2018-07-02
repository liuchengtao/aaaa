package sum.cen.web;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpriderT implements PageProcessor{

	private Site site = Site.me().setRetryTimes(1000).setSleepTime(100);
	@Override
    public Site getSite() {
        return site;
    
	}
    @Override
    public void process(Page page) {
    	//page.getHtml().
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    	 String path=page.getHtml().xpath("//h1[@class='vcard-names']").toString();
    	 System.out.println(path);
    }

	public static void main(String[] args) {
		Spider.create(new SpriderT()).addUrl("https://github.com/liuchengtao").thread(5).run();
		//Spider.create(new SpriderT()).test("http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/spider-config.html");

	}


}
