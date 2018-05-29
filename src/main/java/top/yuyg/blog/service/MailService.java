package top.yuyg.blog.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @Author: YU YUGANG
 * @DESCRIPTION:
 * @DATE: CREATED IN  2018/5/16 17:28
 */
@Service
public class MailService {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value(value = "${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    /*发送邮件的方法*/
    @Async
    public void sendSimple(String to, String title, String content){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from); //发送者
            message.setTo(to); //接受者
            message.setSubject(title); //发送标题
            message.setText(content);  //发送内容
            sender.send(message);
            logger.info("发送邮件成功！");
        } catch (Exception e){
            logger.error("发送邮件失败",e);
        }
    }

    @Async
    public void sendAttachmentsMail(String sendTo, String titel, String content) {

        MimeMessage mimeMessage = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText(content,true);
        } catch (Exception e) {
            e.printStackTrace();

        }
        sender.send(mimeMessage);
    }
}
