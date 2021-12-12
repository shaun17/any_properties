package sw.java.elk.util;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public final class Codetest implements ApplicationContextInitializer {
    public static void main(String[] args) {
        String[] s2 = {"a","b","c","a","d","c"};
        String[] s1 = {"a"};
        System.out.println(method(s1,s2));

    }

    public static boolean method(String[] str1, String[] str2){
        Set set = new HashSet<>();
        boolean result = true;
        for(int i=0;i<str1.length;i++){
            set.add(str1[i]);
        }
        for(int i=0;i<str2.length;i++){
            if(set.contains(str2[i])){
                continue;
            }else{
                result = false;
            }
        }
        return result;
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        
    }
}
