package com.jawa.furniture.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jawa.furniture.common.DateUtils;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/test.do")
	public String index(HttpServletRequest req,HttpServletResponse resp){
		req.setAttribute("test", "hello world");
		
		return "/index";
	}
}
