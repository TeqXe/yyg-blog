package top.yuyg.blog.service.impl;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.yuyg.blog.service.ISmsService;

import java.io.IOException;

/**
 * 发送短信接口实现类
 * Created by yuyg on 2018/6/11.
 */
@Service
public class SmsServiceImpl implements ISmsService{

    // 短信应用SDK AppID
    @Value(value = "${sms.appid}")
    int appid; // 1400开头

    // 短信应用SDK AppKey
    @Value(value = "${sms.appkey}")
    String appkey = "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad";

    // 需要发送短信的手机号码
    @Value(value = "${sms.phoneNumber}")
    String phoneNumber;

    // 短信模板ID，需要在短信应用中申请
    // NOTE: 这里的模板ID`7839`只是一个示例，
    // 真实的模板ID需要在短信控制台中申请
    @Value(value = "${sms.templateId}")
    int templateId;

    // 签名
    // 真实的签名需要在短信控制台中申请，另外
    // 签名参数使用的是`签名内容`，而不是`签名ID`
    @Value(value = "${sms.smsSign}")
    String smsSign;


    /**
     * 登录发送验证码
     * @param captcha 验证码
     * @return
     */
    @Override
    public String loginVerifySms(String captcha) {
        // 指定模板ID单发短信
        try {
            String[] params = new String[]{captcha};
            String[] phoneNumbers = new String[]{phoneNumber};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
            //String result = "{\"result\":0,\"errmsg\":\"OK\",\"ext\":\"\",\"detail\":[{\"result\":0,\"errmsg\":\"OK\",\"mobile\":\"*********\",\"nationcode\":\"86\",\"sid\":\"8:92jq1fHb7XuFkU29iGc20180612\",\"fee\":1}]}";
            JSONObject resultObj = new JSONObject(result.toString().trim());
            if ("OK".equalsIgnoreCase((String) resultObj.get("errmsg"))){
                return "success";
            } else {
                return "error";
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return "error";
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return "error";
        }
    }
}
