package sw.java.elk.leetcode;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

/**
 * @Author wenrenhao
 * @Date 2021-08-15 02:02
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        byte[] abcs = Base64.getDecoder().decode("qqwwiijjuujjh=");
        System.out.println(new String(abcs));
        String s = Base64.getEncoder().encodeToString(abcs);
        System.out.println(s);

    }


}

class A{
     String name  ="a";
    int age = 1;
    public A(String a ,int age){
        this.name= a;
        this.age=age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
