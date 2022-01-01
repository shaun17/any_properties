package sw.java.elk.xunhaun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    ServiceA serviceA;

    @Autowired
    public ServiceB(ServiceA serviceA){
        this.serviceA=serviceA;
    }

}
