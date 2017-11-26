package com.yuntun.yjuser.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

@Component
public class AlidayuUtil {
	@Value("${sms.dayu.appkey}")
	private String APPKEY;
	@Value("${sms.dayu.secret}")
	private String SECRET;
	@Value("${sms.dayu.url}")
	private String URL;
	@Value("${sms.dayu.templatecode}")
	private String TEMPLATECODE;
	@Value("${sms.dayu.param.product}")
	private String PRODUCT;
	private static final String SINGNAME = "注册验证";
	
	public boolean send(String mobile, String code) {
		TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(SINGNAME);
		req.setSmsParamString("{code:'" + code + "',product:'"+PRODUCT+"'}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode(TEMPLATECODE);
		try {
			return client.execute(req).isSuccess();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
