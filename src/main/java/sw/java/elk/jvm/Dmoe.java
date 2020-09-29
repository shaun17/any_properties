package sw.java.elk.jvm;

/**
 * @Author wenrenhao
 * @Date 2020-09-12 19:22
 * @Version 1.0
 */
public class Dmoe {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);
        String str2 = new StringBuilder("str").append("ing").toString();
        System.out.println(str2.intern()==str2);
    }
}
