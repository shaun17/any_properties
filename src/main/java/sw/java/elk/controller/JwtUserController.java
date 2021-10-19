package sw.java.elk.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sw.java.elk.po.User;
import sw.java.elk.service.primary.UserService;
import sw.java.elk.shrio.jwt.JwtToken;
import sw.java.elk.shrio.jwt.JwtUtil;

//@RestController
@Slf4j
public class JwtUserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    @RequestMapping(value = "/jwt/login", method = RequestMethod.GET)
    public Object insertRedis(String username, String password) {

        logger.info("==========" + username + password);
        JwtUtil jwtUtil = new JwtUtil();
        String encode = jwtUtil.encode(username, 100000, null);
        User byName = userService.findByName(username);
        logger.info(JSONObject.toJSONString(byName));
        redisTemplate.opsForValue().set(username, JSONObject.toJSONString(byName));
        Subject subject = SecurityUtils.getSubject();
//        JwtToken jwtToken = new JwtToken(encode);
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(rememberMe);
        try {
//            subject.login(jwtToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
        return encode;
    }

    @RequestMapping("/jwt/")
    @ResponseBody
    public String index() {
        return "no permission";
    }

    @RequiresPermissions("permission1")
    @RequestMapping("/permission1")
    public String permission1(){
        return "had permission1 ";
    }

    @RequiresPermissions("permission5")
    @RequestMapping("/permission5")
    public String permission5(){
        return "had permission1 ";
    }

    @RequiresRoles("role1")
    @RequestMapping("/rolt1")
    public String rolt1(){
        return "had role1 ";
    }

    @RequestMapping("/role2")
    @RequiresRoles("role2")
    public String rolt2(){
        return "had role1 ";
    }
}
