package com.yuntun.yjuser.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntun.yjuser.forms.UserForm;
import com.yuntun.yjuser.service.UserService;
import com.yuntun.yjuser.utils.AlidayuUtil;
import com.yuntun.yjuser.utils.MailUtil;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailUtil m;
	
	@Autowired
	private AlidayuUtil d;

	@PostMapping("/register")
	@ResponseBody
	public String register(@Valid UserForm userform) {
		System.out.println(11);
		return "success";
	}
	
	@GetMapping("/test")
	public void test() {
//    	m.send("1181254453@qq.com", "1234", "云屯务集", "注册验证");
//		d.send("13510024763", RandomStringUtils.randomNumeric(4));
		/*return "test";*/
	}
	
}
