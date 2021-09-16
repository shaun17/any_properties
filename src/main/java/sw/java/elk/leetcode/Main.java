package sw.java.elk.leetcode;

import java.util.Arrays;

/**
 * @Author wenrenhao
 * @Date 2021-08-15 02:02
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        A a = new A("a",11);
        A b = a ;
        b.setName("b");
        System.out.println(25%5);
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
