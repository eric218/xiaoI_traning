package eastrobot.robotdev.ticketsystem.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Component
public class SendMessageUtil {
	@Value("#{setting.msgNetGateUrl}")
	private String msgNetGateUrl;
	@Value("#{setting.appKey}")
	private String appKey;
	@Value("#{setting.appSecret}")
	private String appSecret;
	@Value("#{setting.signName}")
	private String signName;
	@Value("#{setting.naiveRefuseMsgTemplate}")
	private String naiveRefuseMsgTemplate;
	@Value("#{setting.naiveThroughMsgTemplate}")
	private String naiveThroughMsgTemplate;
	@Value("#{setting.reasonableRefuseMsgTemplate}")
	private String reasonableRefuseMsgTemplate;
	@Value("#{setting.ldrkhyzmThroughCertificate}")
	private String ldrkhyzmThroughCertificate;
	@Value("#{setting.szbjfwzThroughCertificate}")
	private String szbjfwzThroughCertificate;
	
	private static Logger logger = Logger.getLogger(SendMessageUtil.class);
	/**
	 * @Description: 发送短信
	 * @param phoneNum  申请人电话  phone
	 * @param name  申请人姓名  proposerName
	 * @param tkId  申请单号  formNo
	 * @param tkFn  申请类型  formType
	 * @param text	审核结果    默认拒绝短信模板/默认通过短信模板
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 23, 2017 1:58:23 PM 
	 *
	 */
	public String sendMessage(String phoneNum,String name,String tkId,int tkFn,String text){
		//发送短信的方法在这里写
		//配置client
		TaobaoClient client = new DefaultTaobaoClient(msgNetGateUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setRecNum(phoneNum);
		if(text.equals("默认拒绝短信模板")){
			//默认拒绝
			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("ticketid", tkId);
			req.setSmsParamString(json.toJSONString());
			req.setSmsTemplateCode(naiveRefuseMsgTemplate);
		}else if(text.equals("默认通过短信模板")){
			//默认通过
			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("ticketid", tkId);
			if(tkFn == 5){
				json.put("certificate", szbjfwzThroughCertificate);
			}else if(tkFn == 4){
				json.put("certificate", ldrkhyzmThroughCertificate);
			}
			req.setSmsParamString(json.toJSONString());
			req.setSmsTemplateCode(naiveThroughMsgTemplate);
		}else{
			//带原因通过
			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("ticketid", tkId);
			json.put("reason", text);
			req.setSmsParamString(json.toJSONString());
			req.setSmsTemplateCode(reasonableRefuseMsgTemplate);
		}
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "fail";
		}
		logger.info(rsp.getBody());
		return "success";
	}

}
