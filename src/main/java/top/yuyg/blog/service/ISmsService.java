package top.yuyg.blog.service;

/**
 * 短信发送接口
 * Created by yuyg on 2018/6/11.
 */
public interface ISmsService {

    /**
     * 指定模板ID 发送简单短信
     * @param captcha 验证码
     * @return
     */
    String loginVerifySms(String captcha);
}
