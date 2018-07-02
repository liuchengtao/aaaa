package sum.cen.entity;

import sum.cen.util.StringUtil;

/**
 * myBatis 分页工具类
 * @author cen    2018年6月6日上午9:57:30
 *
 */
public class PageHelper {
	String sql="select * from t where t.name=? "
			+ "order by " //order
			+ "name "     //orderfiled
			+ "desc "     //sort
			+ "limit (?-1)*10" //page
			+ ","                          //limitQuery
			+ "10";            //pageSize
	private  String order;
	private  String orderfiled;
	private  String sort;
	private  String limitQuery=" ";
	private  int page=1;
	private  int pageSize=10;
	private  int rows=0;
	
	public PageHelper(){
		super();
	}
	//是否启用order by排序
	public void isOrderBy(String orderfiled){
		if(StringUtil.isBlank(orderfiled)){
			order="";
		}
		order=" order by "+ orderfiled;
	}
	
	//降序or 升序 默认升序
	public void isDesc(String desc){
		if(StringUtil.isBlank(desc)||desc=="desc"){
		   sort=" ";	
		}
		sort=" desc ";
	}
	
	//分页  默认第1页 10条
	public  void querySql(int page,int pageSize,int rows){
		if(rows<=0){
			//TODO
			 return;
		}else {
			limitQuery=" limit "+(page-1)*pageSize+", "+pageSize;
		}
		
		
	}
	 
	
	
	
	
	
	public String getLimitQuery() {
		return limitQuery;
	}
	public void setLimitQuery(String limitQuery) {
		this.limitQuery = limitQuery;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderfiled() {
		return orderfiled;
	}
	public void setOrderfiled(String orderfiled) {
		this.orderfiled = orderfiled;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

}
