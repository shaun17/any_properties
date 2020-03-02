package sw.java.elk;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sw.java.elk.po.Pojo;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EklApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
//        Pojo po = new Pojo();
//        po.setName("pojo");
//        po.setHobby("computer");
//        po.setAge(12);
//        po.setAddress("beijing");
        Map map = new HashMap();
        map.put("name", "shaun");
        map.put("age", "12");
        map.put("addr", "shanghai");

//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForHash().put("one","name","tom");
        redisTemplate.opsForHash().put("one","age",12);
        System.out.println(redisTemplate.opsForHash().entries("one"));

    }

}
