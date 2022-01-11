package sw.java.elk.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    @Autowired
    ServiceA serviceA;
    public void methodBYB(){
        System.out.println("this is BBB");
    }
}
