package sw.java.elk.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;
import sw.java.elk.po.Pojo;
import sw.java.elk.rabbit.IMessageService;
import sw.java.elk.rabbit.MQConstant;
import sw.java.elk.service.primary.TableAService;
import sw.java.elk.service.primary.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object insertRedis(String username, String password) {

        logger.info("==========" + username + password);
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(10000);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(rememberMe);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
        return "{\"Msg\":\"登陆成功\",\"state\":\"success\"}";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "no permission";
    }
}
