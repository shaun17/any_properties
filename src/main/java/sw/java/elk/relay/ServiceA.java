package sw.java.elk.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    @Autowired
    ServiceB serviceB;

    public void methodBYA(){
        serviceB.methodBYB();
        System.out.println("this is AAA");
    }
}
