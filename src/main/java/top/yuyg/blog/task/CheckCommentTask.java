package top.yuyg.blog.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.yuyg.blog.service.ICommentService;
import top.yuyg.blog.service.MailService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YU YUGANG
 * @DESCRIPTION:
 * @DATE: CREATED IN  2018/6/2 13:36
 */
@Component
public class CheckCommentTask {

    @Resource
    private ICommentService iCommentService;

    @Autowired
    private MailService mailService;

    @Value("${spring.mail.loginRemain}")
    private String receive;


    @Scheduled(cron = "0 0 6-22/2 * * ?")
    public void demo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = iCommentService.getUnmanagedCount();
        if(count >= 10){
            mailService.sendSimple(receive,"评论审核提醒","温馨提示，截止"+ sdf.format(new Date())+",您的网站博客共有"+count+"条评论尚未被审核！请及时登录处理！");
        }
        System.out.println("截止"+ sdf.format(new Date())+",有"+count+"条评论尚未被审核！请及时处理！");
    }
}
