package sum.cen.util;
/**
 * 字符串工具类
 * @author cen    2018年6月5日下午6:26:06
 *
 */
public class StringUtil { 
     
	//判断字符串是否为空,如果为空 返回true 如果不为空 返回false
	public  static boolean isBlank(String str){
		if(str==null||str.trim()==""){
			return true;
		}
		return false;
	}
	
	/**
	 * 转换字符串 得到整型数据
	 * @param str
	 * @return I
	 */
	public static int parseInt(String str){
		if(str==null){
   			return 0;
		}
		int I=0;
		try {
			I=new Integer(str.trim()).intValue();
		} catch (Exception e) {
			I=0;
		}
		return I;
	}

}
