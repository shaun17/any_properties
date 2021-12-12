package sw.java.elk.key;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;

public abstract class B {
    String defaultPro;
    private String privatePro;
    public String PublicPropro;
    protected String protectPro;
    public static void main(String[] args) {
        byte b=0;
        b=1+2;  //1.正确
        byte b1=1;
        byte b2=2;
        int c =b1-b2;  //2.出错了
    }

    public void method1(){
        String a = "abc";
        String b = "qwezxc";
        int minLen = Math.min(a.length(),b.length());
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =0;i<minLen;i++){
            stringBuffer.append(a.charAt(i)).append(b.charAt(i));
        }
        if(minLen==a.length()){
            stringBuffer.append(b.substring(minLen));
        }else{
            stringBuffer.append(a.substring(minLen));
        }
        System.out.println(stringBuffer);
    }
    public  abstract void me();
}
