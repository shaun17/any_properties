package sw.java.elk;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("sw.java.elk.dao")
@ComponentScan(basePackages = {"sw.java.elk.*"})
public class EklApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EklApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            if(name.contains("table")){
                System.out.println(name);
            }
        }


    }

    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(EklApplication.class);
        logger.info("测试log");

        for (int i = 0; i < 10; i++) {
            logger.error("something wrong. id={}; name=Ryan-{};", i, i);
        }
    }
}
