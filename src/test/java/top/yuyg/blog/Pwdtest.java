package top.yuyg.blog;

import top.yuyg.blog.model.Vo.UserVo;
import top.yuyg.blog.utils.TaleUtils;
import top.yuyg.blog.model.Vo.UserVo;
import top.yuyg.blog.utils.TaleUtils;

/**
 * Created by yuyg on 2018/05/25.
 */
public class Pwdtest {
    public static void main(String args[]){
        UserVo user = new UserVo();
        user.setUsername("admin");
        user.setPassword("J9lew2irojE23");
        String encodePwd = TaleUtils.MD5encode(user.getUsername() + user.getPassword());
        System.out.println(encodePwd);
    }
}
