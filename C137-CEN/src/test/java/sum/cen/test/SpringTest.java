package sum.cen.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sum.cen.entity.SysUser;
import sum.cen.service.SysUserService;

/**
 * 
 * @author cen    2018年6月8日上午9:39:43
 *
 */
public class SpringTest {
	static ApplicationContext context=new ClassPathXmlApplicationContext(
			new String[]{"spring/spring.xml"});
	SysUserService<SysUser> sysUserService=null;
	public void init(){
	 sysUserService=	(SysUserService<SysUser>) context.getBean("sysUserService");
	}
	//
	//@Test
	public void tests(){
		init();
		try {
		SysUser user=	sysUserService.queryById(1);
		System.out.println(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testA() throws Exception{
		init();
		SysUser user1=new SysUser();
		user1.setPage(1);
		user1.setRows(10);
		user1.setOrder("desc");
		user1.setSort("id");
		System.out.println(user1.getOrderLimit());
		System.out.println(user1.getQueryLimit());
		//user1.setId(1);
		List<SysUser> users=sysUserService.queryByList(user1);
		for(int i=0;i<users.size();i++){
			System.out.println(users.get(i));
			
		}
		
	}

}
