package com.yuntun.yjuser.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
	@Autowired
    private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
    private String username;
    
	public void send(String account, String code, String product, String text) {
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(username, product); // 发件人
			helper.setTo("1181254453@qq.com"); // 收件人
	        helper.setSubject(product+"会员"+text); // 邮件主题
	        StringBuffer sb = new StringBuffer();
            sb.append("验证码： <strong>")
            .append(code)
            .append("</strong> ，您正在进行").append(text).append("操作，请在5分钟内正确输入，切勿泄露于他人，感谢您的支持！");
	        helper.setText(sb.toString(), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	javaMailSender.send(mimeMessage);
    }
    
}
