package com.yuntun.yjuser.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yuntun.yjuser.Testc;

public abstract class BaseController {
	@Autowired
	private HttpServletResponse httpServletResponse;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private HttpSession httpSession;
	
	@ModelAttribute
	public void getx() {
		System.out.println(Testc.getErrorLine());
	}
	
}
