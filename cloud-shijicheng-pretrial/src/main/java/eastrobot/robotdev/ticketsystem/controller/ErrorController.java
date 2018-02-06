package eastrobot.robotdev.ticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import eastrobot.robotdev.ticketsystem.constant.UriConstant;

@Controller
public class ErrorController {
	
	@ResponseBody
	@RequestMapping(value = UriConstant.PAGE_NOT_FOUND)
	public ModelAndView pageNotFound(){
		return new ModelAndView("errorpages/404");
	}
}
