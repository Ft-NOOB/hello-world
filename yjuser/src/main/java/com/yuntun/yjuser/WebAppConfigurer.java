package com.yuntun.yjuser;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuntun.utils.AESenc;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
		log.info("Interceptor Start=======================");
        registry.addInterceptor(new CheckLoginInterceptor())
        	.addPathPatterns("/u/**")
        	;
        
        super.addInterceptors(registry);
    }
	
	class CheckLoginInterceptor extends HandlerInterceptorAdapter{
		@Override
		public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
			log.info("session_user_id=============="+(String) request.getSession().getAttribute("userId"));
			if(StringUtils.isNoneBlank((String) request.getSession().getAttribute("userId"))) {
				return true;
			}
			
			String url = request.getRequestURL().toString();
			if (StringUtils.isNotBlank(request.getQueryString())) {
				url += "?" + request.getQueryString();
			}
			log.info("reload======================="+url);
			String tourl = "/login";
			String reload = AESenc.encrypt(url);
			tourl = "/login?reload=" + URLEncoder.encode(reload, "UTF-8");
			
			// 跳转登录
			response.sendRedirect(tourl);
			return false;
		}
	}
	
}
