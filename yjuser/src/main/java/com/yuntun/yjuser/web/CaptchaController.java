package com.yuntun.yjuser.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.servlet.KaptchaExtend;

@Controller
public class CaptchaController extends KaptchaExtend {

	@GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.captcha(req, resp);
		System.out.println(req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY));
    }
}
