package sum.cen.web;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Test implements PageProcessor{
	//�������˼���Ǳ��룬���ʱ�䣬���Դ���
	    private static Site site=Site.me().setRetryTimes(1000).setSleepTime(1000);
	    
	    @Override
	public Site getSite() {
	return site;
	}
	    /**
	     * ���������Ƕ�����Ҫ��ȡ��ҳ������ľ������ݼ������Ƶĵط�
	     */
	 @Override
	public void process(Page page) {
	      //���ݱ�ǩ�����Լ���Ҫ������
	      //��ȡ���������ҳ�е�logoͼƬ�ĵ�ַ
	              String path=page.getHtml().xpath("//*[@class='main-content']/p").css("img", "src").get();
	              System.out.println(path);
	              
	             
	}
	public static void main(String[] args) {
	//���������д������Ҫ��ȡ��ҳ��
	//������ȡ�ľ���webmagic�Ĺ���
	Spider.create(new Test()).addUrl("http://webmagic.io/").thread(5).run();//Strat()Ҳ����
	}


	}
