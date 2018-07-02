package sum.cen.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sum.cen.entity.Content;
import sum.cen.entity.Novel;
import sum.cen.entity.SysMenu;
import sum.cen.service.ContentService;
import sum.cen.service.NovelService;
import sum.cen.service.SysMenuService;
import sum.cen.util.SpriderUtil;

public class SysMenuTest {
	static ApplicationContext context=new ClassPathXmlApplicationContext(
			new String[]{"spring/spring.xml"});
	SysMenuService<SysMenu> sysMenuService=null;
	NovelService<Novel> novelService=null;
	ContentService<Content> contentService=null;
	//@Before
	public void init(){
		 sysMenuService=	(SysMenuService<SysMenu>) context.getBean("sysMenuService");
		 novelService=      (NovelService<Novel>)      context.getBean("novelService");
		 contentService=      (ContentService<Content>)      context.getBean("contentService");
	}
	//@Test
	public void test() throws Exception{
		init();
		//List<SysMenu> list=sysMenuService.queryByAll(); //√√√
		//List<SysMenu> list=sysMenuService.queryByList(null);  //√√√
		//List<SysMenu> list=sysMenuService.getRootMenu(null); //???
		//List<SysMenu> list=sysMenuService.getChildMenu();  //√√√
		//List<SysMenu> list=sysMenuService.queryRootMenuByUserId(3);//√√√
		//List<SysMenu> list=sysMenuService.queryChildMenuByUserId(3);//√√√
		
		//for(int i=0;i<list.size();i++){
		//	System.out.println(list.get(i));
		//}
		//int I=sysMenuService.queryByCount(null);
		//System.out.println(I);
		SysMenu menu=		sysMenuService.queryById(9);
		System.out.println(menu);
		sysMenuService.queryByRoleId(1);
	}
	//@Test
	public void testA() throws Exception{
		sysMenuService.queryByRoleId(1);
	int count=	sysMenuService.queryByCount(null);
		System.out.println(count);
		novelService.queryByCount(null);
		//本地数据库小说
				List<Novel> novelList=novelService.queryByList(null);
				 System.out.println("novelList.size().size()=="+novelList.size());
		//网络小说集合url
		  List<String> netListUrls=SpriderUtil.getBiquge();
		  System.out.println("netListUrls.size()=="+netListUrls.size());
		  //网络小说集合url 筛选之后的 本地数据库没有记录
		  List<String> newListUrls=new ArrayList<String>();
		  
		  for(int i=0;i<10;i++){
			  for(int j=0;j<novelList.size();j++){
			 if(!netListUrls.get(i).equals(novelList.get(j))){
				  newListUrls.add(netListUrls.get(i));
			  }
			  
			  }
		  }
		  System.out.println("newListUrls.size()=="+newListUrls.size());
		  List<Novel> novelLists=  SpriderUtil.getMe(newListUrls);
		  for(int j=0;j<novelLists.size();j++){
			  System.out.println(novelLists.get(j).toString());
			  novelService.add(novelLists.get(j));
		  }
		  String msg="新增"+novelLists.size()+" 本";
		  System.out.println(msg);
	}
	//@Test
	public void testB() throws Exception{
		Novel novel= novelService.queryById(4852);
		 if(novel!=null){
			 //本地该小说的章节数量
			 int zjsl=contentService.queryByNovelId(novel.getId());
			 System.out.print("zjsl=="+zjsl);
			 //小说章节url集合
			 List<String> contentUrls=  SpriderUtil.getContentUrls(novel.getUrl());
			 System.out.println("contentUrls.size()"+contentUrls.size());
			 if(zjsl<contentUrls.size()){
				 System.out.println("zjsl"+zjsl);
			 for(int i=zjsl;i<contentUrls.size();i++){
				 System.out.println(i+"=="+contentUrls.get(i));
				 //小说内容
				String main= SpriderUtil.getContentsByUrl(contentUrls.get(i));
				System.out.println(main);
				Content content=new Content();
				content.setUrl(contentUrls.get(i));
				content.setNovelId(novel.getId());
				content.setContent(main);
				contentService.add(content);
				
			 } }
			 System.out.println("爬取  <<"+novel.getName()+">> 成功  。一共"+(contentUrls.size()-zjsl)+" 章节");
			// sendOkMessage(response,"爬取  <<"+novel.getName()+">> 成功  。一共"+(contentUrls.size()-zjsls)+" 章节"); 
		 }
		
	}
	 //@Test
	 public void testC() throws Exception{
		 int zjsl=contentService.queryByNovelId(4852);
		 int count=contentService.queryByCount(null);
		 System.out.print("zjsl=="+zjsl);
		 System.out.print("count=="+count);
	 }
	// @Test
	 public void testD() throws Exception{
		 String url="https://www.panda.tv/watchhistory";
		Connection cn= Jsoup.connect(url);
		//cn.header(name, value);
		cn.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0");
		//cn.cookie("smid","95dc163c-6990-4ee2-9f62-15ffac8bfd4");
		cn.cookie("__guid","96554777.871508448033869700.1512816597830.453");
		cn.cookie("_uab_collina","150538291972539327239909");
		cn.cookie("_umdata","0823A424438F76ABDF8A56C686093FAF951ECC879021C708525CBD6E86AE498BC50D91099D7A840DCD43AD3E795C914C0FD2DCF8D80413F669CB4BBA5D8859BB");
		cn.cookie("GED_PLAYLIST_ACTIVITY","W3sidSI6IlJzbWsiLCJ0c2wiOjE1MzAyNDcwNTEsIm52IjoxLCJ1cHQiOjE1MzAyNDI4MzIsImx0IjoxNTMwMjQ3MDUxfV0.");
		cn.cookie("Hm_lpvt_204071a8b1d0b2a04c782c44b88eb996","1530250849");
		cn.cookie("Hm_lvt_204071a8b1d0b2a04c782c44b88eb996","1530063770,1530155394,1530178967,1530234259");
		cn.cookie("I","r=42169978&t=086aa30ab91885693fb8981fbc2d8372");
		cn.cookie("M","t=1529128419&v=1.0&mt=&s=db091773d4bea21d946629431db16055&ps=4e30b0c6223597f3ed24ee8c81a9e22c");
		cn.cookie("monitor_count","219");
		cn.cookie("pdft","20171012121117ecd25fcb383b9482417ff7c1f2e22de800c8564fbfef5f6a");
		cn.cookie("pdftv1","c2d5d|162ba3fabc9|58c3|af0a8086|12");
		cn.cookie("R","r=42169978&u=CnaqnGi42169978&n=%R5%86%O2%R5%95%8N3954&le=&m=ZGtkAGZ3Amt0BGL=&im=nUE0pPHmDFHlEvHlEzx3YaOxnJ0hM3ZyZxLjZwH3ZQDjZmtjLzD3MQV5ATR4ZQR3AGZlLmN3MGZ5Lv5dpTp=&p=&i=");
		cn.cookie("smid","95dc163c-6990-4ee2-9f62-15ffac8bfd42");
		cn.cookie("smidV9","20171012121117ecd25fcb383b9482417ff7c1f2e22de800c8564fbfef5f6a");
		//cn.cookie("","");
		//_uab_collina	150538291972539327239909
		//_umdata	0823A424438F76ABDF8A56C686093FAF951ECC879021C708525CBD6E86AE498BC50D91099D7A840DCD43AD3E795C914C0FD2DCF8D80413F669CB4BBA5D8859BB
	//	GED_PLAYLIST_ACTIVITY	W3sidSI6IlJzbWsiLCJ0c2wiOjE1MzAyNDcwNTEsIm52IjoxLCJ1cHQiOjE1MzAyNDI4MzIsImx0IjoxNTMwMjQ3MDUxfV0.
		//Hm_lpvt_204071a8b1d0b2a04c782c44b88eb996	1530250849
	//	Hm_lvt_204071a8b1d0b2a04c782c44b88eb996	1530063770,1530155394,1530178967,1530234259
	//	I	r=42169978&t=086aa30ab91885693fb8981fbc2d8372
	//	M	t=1529128419&v=1.0&mt=&s=db091773d4bea21d946629431db16055&ps=4e30b0c6223597f3ed24ee8c81a9e22c
	//	monitor_count	219
	//	pdft	20171012121117ecd25fcb383b9482417ff7c1f2e22de800c8564fbfef5f6a
		//pdftv1	c2d5d|162ba3fabc9|58c3|af0a8086|12
		//R	r=42169978&u=CnaqnGi42169978&n=%R5%86%O2%R5%95%8N3954&le=&m=ZGtkAGZ3Amt0BGL=&im=nUE0pPHmDFHlEvHlEzx3YaOxnJ0hM3ZyZxLjZwH3ZQDjZmtjLzD3MQV5ATR4ZQR3AGZlLmN3MGZ5Lv5dpTp=&p=&i=
		//smid	95dc163c-6990-4ee2-9f62-15ffac8bfd42
	//	smidV9	20171012121117ecd25fcb383b9482417ff7c1f2e22de800c8564fbfef5f6a
	Response re=	cn.execute();
		System.out.println(re.body());
	 }
	 @Test
	 public void testE() throws Exception{
		// String url="https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)";
		 String url="https://weibo.com/";
			Connection cn= Jsoup.connect(url).method(Method.POST);
		//	Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0
			cn.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:60.0) Gecko/20100101 Firefox/60.0");
			cn.data("username","18153778496");
			cn.data("password","19957161995");
			Response re=	cn.execute();
			re.charset("gbk");
			System.out.println(re.body());
	 }
}
