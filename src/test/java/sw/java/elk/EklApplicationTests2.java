package sw.java.elk;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sw.java.elk.po.TableA;
import sw.java.elk.relay.ServiceA;
import sw.java.elk.relay.ServiceB;
import sw.java.elk.service.primary.TableAService;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EklApplicationTests2 extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ServiceA serviceA;
    @Autowired
    ServiceB serviceB;

    @Test
    public void tebleQuery(){
        serviceA.methodBYA();
    }


}
