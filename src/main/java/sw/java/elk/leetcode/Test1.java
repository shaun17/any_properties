package sw.java.elk.leetcode;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wenrenhao
 * @Date 2021-09-10 20:44
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,4,7,4,6,89,0,6,3,2,5,78,9,5};
        int[] temp = new int[arr.length];
//        quicksort(arr,0,arr.length-1);
        guibing(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] bolobolo(int[] nums){
        for(int i =0;i<nums.length-1;i++){
            for(int j=i;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    nums[i]=nums[i]^nums[j];
                    nums[j]=nums[i]^nums[j];
                    nums[i]=nums[i]^nums[j];
                }

            }
        }
        return nums;
    }


    public static void quicksort(int[] nums, int left, int right){
        if(right>left){
            int mid = mid(nums, left, right);
            quicksort(nums,left,mid-1);
            quicksort(nums,mid+1,right);
        }
    }
    public static int mid(int[] nums, int left, int right){
        int start = left;
        int base = nums[left];
        while(left<right){
            while(right>left && nums[right]>=base){
                right--;
            }
            while(right>left && nums[left]<=base){
                left++;
            }
            if(right>left){
                nums[right]=nums[right]^nums[left];
                nums[left]=nums[right]^nums[left];
                nums[right]=nums[right]^nums[left];
            }
        }
        nums[start]=nums[left];
        nums[left]=base;
        return left;
    }


    public static void guibing(int[] nums, int left, int right, int[] temp){
        if(right>left){
            int mid = (right+left)/2;
            guibing(nums,left,mid,temp);
            guibing(nums,mid+1,right,temp);
            guibing_merge(nums,left,mid,right,temp);
        }

    }
    public static void guibing_merge(int[] nums, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid+1;
        int t=0;
        while(i<=mid && j<=right){
            if(nums[i]<=nums[j]){
                temp[t++]=nums[i++];
            }else{
                temp[t++]=nums[j++];
            }
        }
        while(i<=mid ){
            temp[t++]=nums[i++];
        }
        while(j<=right ){
            temp[t++]=nums[j++];
        }
        t=0;
        while(left<=right){
           nums[left++]=temp[t++];
        }
    }

}
