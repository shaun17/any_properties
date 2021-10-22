package sw.java.elk;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
      Stack s  = new Stack();
      s.add(1);
      s.pop();
        System.out.println(s.isEmpty());
    }
    public static void temp(){
        ThreadPoolExecutor s = new ThreadPoolExecutor(1,2,100, TimeUnit.MINUTES,new SynchronousQueue<>());
        s.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        });
    }
    public static void metod(){
        go:
        for(int i=0;i<10;i++){
            System.out.println("i:"+i);

            for(int j=0;j<10;j++){
                System.out.println("j:"+j);
                continue go;
            }
        }

    }

    public static void sort(int[] arr, int l, int r){
        int[][] aa = new int[][]{};
        Arrays.sort(aa,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o1[0]-o2[0];
            }
        });

    }

    public static int qsort(int[] arr, int l, int r){
        int t = arr[l];
        int a = l;
        while(l<r){
            while(l<r && t<arr[r]){
                r--;
            }
            while(l<r && arr[l]<=t){
                l++;
            }
            if(r>l){
                arr[r]=arr[r]^arr[l];
                arr[l]=arr[r]^arr[l];
                arr[r]=arr[r]^arr[l];
            }
        }
        arr[a]=arr[l];
        arr[l]=t;
        return l;

    }
}
