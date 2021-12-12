package sw.java.elk.key;

import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public  class D {
    public static void main(String[] args) {
        int[][] a =new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Arrays.stream(a).forEach(x-> System.out.println(Arrays.toString(x)));
        int[][] m = m(a);
        Arrays.stream(a).forEach(x-> System.out.println(Arrays.toString(x)));

    }
    public static int[][] m(int[][] matrix){

        int n = matrix.length;
        int[][] res = new int[n][n];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                res[j][n-i-1]=matrix[i][j];
            }
        }
        matrix = res;
        Arrays.stream(matrix).forEach(x-> System.out.println(Arrays.toString(x)));

//        Arrays.stream(res).forEach(x-> System.out.println(Arrays.toString(x)));
//        for (int i = 0; i < matrix.length; ++i) {
//            for (int j = 0; j < matrix.length; ++j) {
//                matrix[i][j] = res[i][j];
//            }
//        }
        return matrix;
    }
}
