package sum.cen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/druid")
public class DruidAction {
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request) {
		return "durid/index";
	}
}
