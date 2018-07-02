package sum.cen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sum.cen.service.SysUserService;

@Controller

public class PageAction {
	   @Autowired
      private SysUserService<?> sysUserService;
      
	 @RequestMapping(value = "{pageName}", method = RequestMethod.GET)
	    public String toPage(@PathVariable("pageName") String pageName) {
		 System.out.println("test");
	        return pageName;
	    }
}
