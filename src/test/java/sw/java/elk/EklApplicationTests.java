package sw.java.elk;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import sw.java.elk.po.Pojo;
import sw.java.elk.po.TableA;
import sw.java.elk.po.User;
import sw.java.elk.service.primary.TableAService;
import sw.java.elk.service.primary.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class EklApplicationTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    RedisTemplate redisTemplate;


    class Colther {
        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String name;
        private double price;
        private String color;
        private int age;

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getColor() {
            return color;
        }

        public int getAge() {
            return age;
        }
    }

    @Test
    public void insertCart() {
        Colther clo = new Colther();
        clo.setAge(12);
        clo.setName("jack");
        clo.setPrice(12.3);
        clo.setColor("黑色");
        System.out.println(JSONObject.toJSONString(clo));
        redisTemplate.opsForHash().put("cart:userId_123456", "0003", JSONObject.toJSONString(clo));
    }

    @Test
    public void findCart() {
        List values = redisTemplate.opsForHash().values("cart:userId_123456");
        System.out.println(values);

    }

    @Test
    public void updateCart() {
        Colther clo = new Colther();
        clo.setAge(12);
        clo.setName("小jack");
        clo.setPrice(12.3);
        clo.setColor("大红色");
        redisTemplate.opsForHash().put("cart:userId_123456", "0003", JSONObject.toJSONString(clo));
    }

    @Test
    public void delCart() {
        Object o = redisTemplate.opsForHash().get("hmset", "name");
        System.out.println(o);
    }

    @Test
    public void method() {
        assert 1 == 2;
        System.out.printf("aaaa");
    }
    @Autowired
    TableAService tableAService;

    @Test
    public void tebleQuery(){
        List<Map<String, Object>> maps = tableAService.queryList();
        System.out.println(maps.toString());
    }

    @Test
    @Transactional
    @Rollback
    public void tebleInsert(){
        TableA tableA = new TableA();
        int result  = tableAService.insertOne(tableA);
        System.out.println(result);
    }

}
