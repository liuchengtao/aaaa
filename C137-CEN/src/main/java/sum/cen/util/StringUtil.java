package sum.cen.util;
/**
 * �ַ���������
 * @author cen    2018��6��5������6:26:06
 *
 */
public class StringUtil { 
     
	//�ж��ַ����Ƿ�Ϊ��,���Ϊ�� ����true �����Ϊ�� ����false
	public  static boolean isBlank(String str){
		if(str==null||str.trim()==""){
			return true;
		}
		return false;
	}
	
	/**
	 * ת���ַ��� �õ���������
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
