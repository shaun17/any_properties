package sw.java.elk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sw.java.elk.datasourceconfig.DataSourceType;

@Aspect
@Component
public class DataSourceAop {

    @Before( "execution(* sw.java.elk.service.primary..*.*(..))")
    public void setDataSource2test01() {
        System.err.println("primary");
        if(DataSourceType.getDataBaseType().compareTo(DataSourceType.DataBaseType.DATA01)!=0){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.DATA01);

        }
    }

    @Before("execution(* sw.java.elk.service.second..*.*(..))")
    public void setDataSource2test02() {
        System.err.println("second");
        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.DATA02);
    }
}
