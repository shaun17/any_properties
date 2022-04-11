package sw.java.elk.demo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test1 {
    static String add1 = new String("aaaa");
    static String add2 = new String("bbbb");
    public static void main(String[] args) {
        A a = new A("aa", 1, add1);
        B b = new B();
        System.out.println(a.toString());
        BeanUtils.copyProperties(a, b);
        add1 = new String("ccc");
        a.setAddress(add1);
        System.out.println(a.toString());
        System.out.println(b.toString());
    }


}
@Data
class A {
    A(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    private String name;
    private Integer age;
    private String address;

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
@Data
class B {
    private String name;
    private Integer age;
    private String address;

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}