package sum.cen.web;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Csdn implements PageProcessor {

    private static String username = "qq598535550";// ����csdn�û���
    private static int size = 0;// ��ץȡ������������

    // ץȡ��վ��������ã����������롢ץȡ��������Դ�����
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    // process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
    public void process(Page page) {
        // �б�ҳ
    /*    if (!page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/\\d+").match()) {
            // �����������ҳ
            page.addTargetRequests(page.getHtml().xpath("//div[@id='article_list']").links()// �޶������б��ȡ����
                    .regex("/" + username + "/article/details/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// �����滻�������urlת���ɾ���url
                    .all());
            // ��������б�ҳ
            page.addTargetRequests(page.getHtml().xpath("//div[@id='papelist']").links()// �޶������б�ҳ��ȡ����
                    .regex("/" + username + "/article/list/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// �����滻�������urlת���ɾ���url
                    .all());
            // ����ҳ
        } else {
            size++;// ����������1
            // ��CsdnBlog������ץȡ�������ݣ�����������ݿ�
          //  System System = new System();
            // ���ñ��
            
            System.out.println("uuid=="+
                    page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/(\\d+)").get().toString()
                    );
            // ���ñ���
            System.out.println(
                    page.getHtml().xpath("//div[@class='article_title']//span[@class='link_title']/a/text()").get().toString());
            // ��������
            System.out.println(
                    page.getHtml().xpath("//div[@class='article_r']/span[@class='link_postdate']/text()").get().toString());
            // ���ñ�ǩ�������ж������,���ָ
            System.out.println(page.getHtml()
                    .xpath("//div[@class='article_l']/span[@class='link_categories']/a/allText()").all().toString());
        
        }*/
    }

    

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("�����濪ʼ�������ĵȴ�һ�����ݵ���������...");
        startTime = System.currentTimeMillis();
        // ���û�������ҳ��ʼץ������5���̣߳���������
        Spider.create(new Csdn()).addUrl("http://blog.csdn.net/" + username).thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("�������������ץȡ" + size + "ƪ���£���ʱԼ" + ((endTime - startTime) / 1000) + "�룬�ѱ��浽���ݿ⣬����գ�");
    }}