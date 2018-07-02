package sum.cen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sum.cen.entity.Content;
import sum.cen.entity.Novel;
import sum.cen.entity.NovelUtil;
import sum.cen.service.ContentService;
import sum.cen.service.NovelService;
import sum.cen.util.SpriderUtil;

/**
 * 
 * @author cen    2018年6月25日下午3:08:17
 *
 */
@Controller
@RequestMapping("/novel")
public class NovelAction extends BaseAction{
	@Autowired
	private NovelService<Novel> novelService;
	@Autowired
	private ContentService<Content> contentService;
	
	/**
	 * 小说列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String toPage(){
		return "sys/novel";
	}
	
	/**
	 * 小说列表集合
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")
	public void list(Novel novel,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Novel> novelList=novelService.queryByList(novel);
		for(int i=0;i<novelList.size();i++){
			//本地章节数
		int subCount=	  contentService.queryByNovelId(novelList.get(i).getId());
		novelList.get(i).setSubCount(subCount);
			
		}
		int count=novelService.queryByCount(novel); 
		 JsonConfig jsonConfig = new JsonConfig();
	jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));  
	    		JSONArray dataList=JSONArray.fromObject(novelList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows",dataList);
		result.put("total",count);
		writeJson(result,response);
	}
	
	/**
	 * 新增小说
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void  save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//本地数据库小说
		List<Novel> novelList=novelService.queryByList(null);
		
		//网络小说集合url
	  List<String> netListUrls=SpriderUtil.getBiquge();
	  
	  //网络小说集合url 筛选之后的 本地数据库没有记录
	  List<String> newListUrls=new ArrayList<String>();
	  System.out.println("netListUrls size=="+netListUrls.size());
	  for(int i=0;i<netListUrls.size();i++){
		  for(int j=0;j<novelList.size();j++){
		  if(!netListUrls.get(i).equals(novelList.get(j))){
			  newListUrls.add(netListUrls.get(i));
		  }
		  
		  }
	  }
	  //
	  List<Novel> novelLists=  SpriderUtil.getMe(newListUrls);
	  System.out.println("novelLists size=="+novelLists.size());
	  for(int j=0;j<novelLists.size();j++){
		  System.out.println(novelLists.get(j).getUrl());
		  novelService.add(novelLists.get(j));
	  }
	  String msg="新增"+novelLists.size()+" 本";
	  //输出成功
	  JSONObject result=new JSONObject();
	  result.put("success", true);
	  result.put("msg", msg);
	  writeJson(result,response);
	}
	/**
	 * 爬取小说章节
	 * @throws Exception 
	 */
	@RequestMapping("/getContents")
	public void getContents(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Novel novel= novelService.queryById(id);
		 if(novel!=null){
			 //本地该小说的章节数量
			 int zjsl=contentService.queryByNovelId(novel.getId());
			 System.out.print("zjsl=="+zjsl);
			 //小说章节url集合
			 List<String> contentUrls=  SpriderUtil.getContentUrls(novel.getUrl());
			 System.out.println("contentUrls.size()"+contentUrls.size());
			 if(zjsl<contentUrls.size()){
			 for(int i=zjsl;i<contentUrls.size();i++){
				 //小说内容
				String main= SpriderUtil.getContentsByUrl(contentUrls.get(i));
				Content content=new Content();
				content.setUrl(contentUrls.get(i));
				content.setNovelId(novel.getId());
				content.setContent(main);
				contentService.add(content);
				
			 } }
			 sendOkMessage(response,"爬取  <<"+novel.getName()+">> 成功  。一共"+(contentUrls.size()-zjsl)+" 章节"); 
		 }else{
		 
		sendFailMessage(response,"小说不存在");}
	}
	@RequestMapping("/qiantai")
	public String qt(){
		return "/2";
	}
	 @RequestMapping("/query")
	 public void queryNovel(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//ModelAndView mv=new ModelAndView();
		 String keyword=request.getParameter("keyword");
			String   new_value = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(new_value);
			Novel model=new Novel();
			model.setName(new_value);
			model.setPage(1);
			model.setRows(10);
			List<Novel> novels=novelService.queryByList(model);
//	  Novel novel=ns.getIdByName(new_value);
			// Map<String,Object> result = new HashMap<String, Object>();
			JSONObject result=new JSONObject();
	   if(novels.size()>0){
		//request.setAttribute("name","ok");
		result.put("name","ok");
		//request.setAttribute("novel",novels.get(0));
	}else{
	//request.setAttribute("name","该小说暂未瘦入！！");
		result.put("name","该小说暂未瘦入！！");
	}
	  Novel nn=novels.get(0);
	  nn.setUpdate_time(null);
	  nn.setCreate_time(null);
		result.put("novel",nn);
	    ModelAndView mv=new ModelAndView("/2",result);
	  // mv.setViewName("/2");
	    writeJson(result,response);
  //   return mv;
	 }
	 /**
	  * 展示前台字符云所需数据
	 * @throws Exception 
	  */
   @RequestMapping("/loadList")
   public void getListByAjax(Novel novel,HttpServletResponse response) throws Exception{
	   novel.setPage(1);
	   novel.setRows(50);
	   List<Novel> novels=novelService.queryByList(novel);
	   System.out.println(novels.size());
	   List<NovelUtil> dataList=new ArrayList<NovelUtil>();
	   for(int i=0;i<novels.size();i++){
		   NovelUtil nu=new NovelUtil();
		   nu.setName(novels.get(i).getName());
		//   nu.setValue(20-i);
		   dataList.add(nu);
	   }
	   JSONObject result=new JSONObject();
	   result.put("dataList",dataList);
	   writeJson(result,response);
   }
   
}
