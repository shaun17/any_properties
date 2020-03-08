package sw.java.elk.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import sw.java.elk.po.User;
import sw.java.elk.shrio.ShiroConfig;

import java.util.Random;

public class PasswordUtils {

    public static User toMD5( User user){
        if(user==null|| !StringUtils.isNotBlank(user.getUserName()))
            return null;
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        user.setTimes(ShiroConfig.TIME);
        String alogrithmName = "md5";   // 加密算法
        String encodePassword = new SimpleHash(alogrithmName, user.getPassword(), salt, ShiroConfig.TIME).toString();
        user.setPassword(encodePassword);
        return user;
    }


}
