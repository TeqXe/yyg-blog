package top.yuyg.blog;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yuyg.blog.service.ISmsService;

import javax.annotation.Resource;

/**
 * Created by yuyg on 2018/06/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendSmsTest {

    @Resource
    private ISmsService iSmsService;

    @Test
    public void sendSms(){
        /*String result = iSmsService.loginVerifySms("666666");
        //{"result":0,"errmsg":"OK","ext":"","detail":[{"result":0,"errmsg":"OK","mobile":"*********","nationcode":"86","sid":"8:92jq1fHb7XuFkU29iGc20180612","fee":1}]}
        System.out.println(result);*/
    }

    @Test
    public void figureResult(){
        //{"result":0,"errmsg":"OK","ext":"","detail":[{"result":0,"errmsg":"OK","mobile":"***********","nationcode":"86","sid":"8:92jq1fHb7XuFkU29iGc20180612","fee":1}]}
        String result = "{\"result\":0,\"errmsg\":\"OK\",\"ext\":\"\",\"detail\":[{\"result\":0,\"errmsg\":\"OK\",\"mobile\":\"**********\",\"nationcode\":\"86\",\"sid\":\"8:92jq1fHb7XuFkU29iGc20180612\",\"fee\":1}]}";
        JSONObject obj = new JSONObject(result);
        int code = (int)obj.get("result");
        System.out.println(code);
    }

    @Test
    public void generateCode(){
        System.out.println((int)((Math.random()*9+1)*100000));
    }
}
