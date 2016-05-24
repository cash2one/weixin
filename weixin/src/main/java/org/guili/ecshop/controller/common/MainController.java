package org.guili.ecshop.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页跳转和首页需要信息的跳转
 * @ClassName:   MainController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2013-11-19 下午7:09:51 
 *
 */
@Controller
public class MainController extends BaseProfileController {
	private Logger logger=Logger.getLogger(MainController.class);
	
	@RequestMapping(value = "/errors/404.htm")
	public String error404(HttpServletRequest request,HttpServletResponse response){
		return "error/404error";
	}
	@RequestMapping(value = "/errors/500.htm")
	public String error500(HttpServletRequest request,HttpServletResponse response){
		return "error/404error";
	}

}
