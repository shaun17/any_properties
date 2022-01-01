package sw.java.elk.xunhaun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    ServiceB serviceB;

    @Autowired
    public ServiceA(ServiceB serviceB){
        this.serviceB=serviceB;
    }
}
