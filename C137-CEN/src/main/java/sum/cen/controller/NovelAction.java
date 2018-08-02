package sum.cen.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sum.cen.entity.Content;
import sum.cen.entity.Novel;
import sum.cen.entity.NovelUser;
import sum.cen.entity.NovelUtil;
import sum.cen.service.ContentService;
import sum.cen.service.NovelService;
import sum.cen.service.NovelUserService;
import sum.cen.util.SpriderUtil;
import sum.cen.util.StringUtil;

/**
 * 
 * @author cen    2018��6��25������3:08:17
 *
 */
@Controller
@RequestMapping("/novel")
public class NovelAction extends BaseAction{
	private final static Logger LOG=Logger.getLogger(NovelAction.class);
	@Autowired
	private NovelService<Novel> novelService;  //С˵��Ϣ
	@Autowired
	private ContentService<Content> contentService;  //С˵�½�
	@Autowired
	private  NovelUserService<NovelUser> novelUserService; //��ͨ�û�
	
	/**
	 * С˵�б�ҳ��
	 * @return
	 */
	@RequestMapping("/list")
	public String toPage(){
		return "sys/novel";
	}
	
	/**
	 * С˵�б���
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")
	public void list(Novel novel,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Novel> novelList=novelService.queryByList(novel);
		for(int i=0;i<novelList.size();i++){
			//�����½���
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
	 * ����С˵
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void  save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//�������ݿ�С˵
		List<Novel> novelList=novelService.queryByList(null);
		List<String> novelList1=new ArrayList<String>();
		for(Novel novel:novelList){
			novelList1.add(novel.getUrl());
		}
		//����С˵����url
	  List<String> netListUrls=SpriderUtil.getBiquge();
	  
	  //����С˵����url ɸѡ֮��� �������ݿ�û�м�¼
	  List<String> newListUrls=new ArrayList<String>();
	  System.out.println("netListUrls size=="+netListUrls.size());
	  for(int i=0;i<netListUrls.size();i++){
		
		  if(!novelList1.contains(netListUrls.get(i))){
			  System.out.println("netListUrls.get(i)=="+netListUrls.get(i));
			  System.out.println("novelList.get(i)=="+novelList.get(i));
			  newListUrls.add(netListUrls.get(i));
		  }
	  }
	  System.out.println("С˵�жϼ���,Ԥ��=="+newListUrls.size()+"��");
	  //
	  List<Novel> novelLists=  SpriderUtil.getMe(newListUrls);
	  System.out.println("novelLists size=="+novelLists.size());
	  for(int j=0;j<novelLists.size();j++){
		  System.out.println(novelLists.get(j).getUrl());
		  novelService.add(novelLists.get(j));
	  }
	  String msg="����"+novelLists.size()+" ��";
	  //����ɹ�
	  JSONObject result=new JSONObject();
	  result.put("success", true);
	  result.put("msg", msg);
	  writeJson(result,response);
	}
	/**
	 * ��ȡС˵�½�
	 * @throws Exception 
	 */
	@RequestMapping("/getContents")
	public void getContents(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Novel novel= novelService.queryById(id);
		 if(novel!=null){
			 //���ظ�С˵���½�����
			 int zjsl=contentService.queryByNovelId(novel.getId());
			 System.out.print("zjsl=="+zjsl);
			 //С˵�½�url����
			 List<String> contentUrls=  SpriderUtil.getContentUrls(novel.getUrl());
			 System.out.println("contentUrls.size()"+contentUrls.size());
			 if(zjsl<contentUrls.size()){
			 for(int i=zjsl;i<contentUrls.size();i++){
				 //С˵����
				String main= SpriderUtil.getContentsByUrl(contentUrls.get(i));
				Content content=new Content();
				content.setUrl(contentUrls.get(i));
				content.setNovelId(novel.getId());
				content.setContent(main);
				contentService.add(content);
				
			 } }
			 sendOkMessage(response,"��ȡ  <<"+novel.getName()+">> �ɹ�  ��һ��"+(contentUrls.size()-zjsl)+" �½�"); 
		 }else{
		 
		sendFailMessage(response,"С˵������");}
	}
	@RequestMapping("/qiantai")
	public String qt(){
		return "/2";
	}
	 @RequestMapping("/query")
	 public ModelAndView queryNovel(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			 Map<String,Object> result = new HashMap<String, Object>();
			//JSONObject result=new JSONObject();
	   if(novels.size()>0){
		//request.setAttribute("name","ok");
		result.put("name","ok");
		//request.setAttribute("novel",novels.get(0));
	}else{
	//request.setAttribute("name","��С˵��δ���룡��");
		result.put("name","��С˵��δ���룡��");
	}
	  Novel nn=novels.get(0);
	  LOG.info(nn.toString());
	  nn.setUpdate_time(null);
	  nn.setCreate_time(null);
		result.put("novel",nn);
	    ModelAndView mv=new ModelAndView("/2",result);
	  // mv.setViewName("/2");
	   // writeJson(result,response);
     return mv;
	 }
	 /**
	  * չʾǰ̨�ַ�����������
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
   /**
    * ����С˵
    * @param novelId
    * @param novelName
    * @param response
    * @throws Exception
    */
   @RequestMapping("/down")
   public  void down(String novelId,String novelName,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   LOG.info("novelName=="+novelName);
	   if(!StringUtil.isBlank(novelId)&&!StringUtil.isBlank(novelName)){
		   //ͨ��С˵id��ѯ��Ӧ���½���Ŀ
		 int I=  contentService.queryByNovelId(novelId);
		 String   new_value = new String(novelName.getBytes("ISO-8859-1"),"UTF-8"); //С˵��
		 LOG.info("new_value=="+new_value);
		 String name1= new_value+".txt";
		 LOG.info("name1=="+name1);
		 String name2=new String(name1.getBytes("gbk"),"iso8859-1");
		 LOG.info("name2=="+name2);
		 response.setContentType("application/x-msdownload");
		 response.setHeader("Content-Disposition","attachment;filename="+name2);
		OutputStream out= response.getOutputStream();
		//
		 StringBuffer  user_ip=request.getRequestURL(); //http://localhost:8090/novel/down.do
		 String user_ip1=request.getRemoteAddr();
		 if(I>0){ //�������ݿ����½�����
			List<Content> list= contentService.queryContentByNovelId(novelId);
			
			   for(int i=0;i<list.size();i++){
				  String contents=   list.get(i).getContent();
				  String[] atext=contents.split("\\|");
					for(String at:atext){
						out.write(at.getBytes("utf-8"));
						out.write('\n');
					}
					
					
			   }
			   NovelUser t=new NovelUser();
			   t.setUser_ip(user_ip1);
			   t.setNovelId(Integer.parseInt(novelId));
			   novelUserService.add(t);
		    }
		 else{
			    out.write("��С˵��δ����".getBytes("utf-8"));
				out.write('\n');
		 }
		 out.flush();
		  out.close();
	  
	   }
	   
   }
   
}
