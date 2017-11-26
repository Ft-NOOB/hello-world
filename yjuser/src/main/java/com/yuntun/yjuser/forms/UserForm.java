package com.yuntun.yjuser.forms;

import com.yuntun.yjuser.annotation.SessionCheck;

public class UserForm {
	
	private String phone; // 手机
	
	private String mail; // 邮箱
	
	private String password; // 密码
	
	private String passwordConfirm; // 确认密码
	
	private String code; // 手机/邮箱验证码
	
	@SessionCheck(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)
	private String captcha; // 页面验证码

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
