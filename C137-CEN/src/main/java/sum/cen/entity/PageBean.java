package sum.cen.entity;

import sum.cen.util.StringUtil;

/**
 * 
 * @author cen    2018��6��19������3:23:28
 *
 */
public class PageBean { 
	
	private int page; //��ǰҳ
	private  int rows; //ÿҳ��ʾ����
	private  String sort; //�����ֶ�
	private String  order; //������
	private  String queryLimit;
	private  String  orderLimit;
	//limit 10,100;
	public String getQueryLimit() {
		//TODO
		queryLimit="limit "+(page-1)*rows+","+rows;
		return queryLimit;
	}
	public void setQueryLimit(String queryLimit) {
		this.queryLimit = queryLimit;
	}
	public String getOrderLimit() {
		if(!StringUtil.isBlank(sort)){
			orderLimit=" order by "+sort+" "+order;
		}
		return orderLimit;
	}
	public void setOrderLimit(String orderLimit) {
		this.orderLimit = orderLimit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	

}
