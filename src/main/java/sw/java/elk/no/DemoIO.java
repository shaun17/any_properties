package sw.java.elk.no;

import java.io.*;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class DemoIO {
    public static void main(String[] args) throws IOException {
        String s = "";
        LinkedList list = new LinkedList();
        FileReader f = new FileReader("/Users/shaun/Desktop/demo.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/shaun/Desktop/demo2.txt"));
        PrintWriter p = new PrintWriter(bufferedWriter);
        BufferedReader bufferedReader = new BufferedReader(f);
        while ((s = bufferedReader.readLine()) != null) {
p.println(s);       }


    }


}
