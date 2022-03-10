package sw.java.elk.util;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

public final class Codetest implements ApplicationContextInitializer {
    public static void main(String[] args) {
        System.out.println("a".hashCode());
        System.out.println("b".hashCode());
        System.out.println("ab".hashCode());
        System.out.println(Integer.valueOf('a')*31^(2-1)+Integer.valueOf('b'));


    }

    public static boolean method(String[] str1, String[] str2){
        return true;
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        
    }
}
