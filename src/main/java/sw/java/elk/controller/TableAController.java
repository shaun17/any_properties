package sw.java.elk.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sw.java.elk.po.Pojo;
import sw.java.elk.service.TableAService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TableAController {
    @Autowired
    TableAService tableAService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Object get(){
        return tableAService.queryList();
    }

    @RequestMapping(value = "/insertredis",method = RequestMethod.GET)
    public Object insertRedis(String key){
        Pojo pojo = new Pojo();
        pojo.setAddress("深圳");
        pojo.setAge(12);
        pojo.setHobby("play basketball");
        pojo.setName("tom");
        redisTemplate.opsForValue().set(key, pojo);
        return "success";
    }
    @RequestMapping(value = "/insertredismap",method = RequestMethod.GET)
    public Object insertredismap(String key){
        Map map = new HashMap();
        map.put("map1","map1");
        map.put("map2","map2");

        redisTemplate.opsForValue().set(key, map);
        return "success";
    }
    @RequestMapping(value = "/getfromredis",method = RequestMethod.GET)
    public Object getfromredis(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Object tom = redisTemplate.opsForValue().get(key);
        System.out.println(tom);
        return tom;
    }

}
