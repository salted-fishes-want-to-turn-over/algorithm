package com.june;

import java.util.HashSet;
import java.util.Set;

public class checkSubarraySumSolution {
    public static boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for(int i=1; i<n+1; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        Set<Integer> set= new HashSet<>();
        for(int j=0; j <= n-2; j++){ //j从0开始
            set.add(sum[j] % k);
            if(set.contains(sum[j+2] % k)){ //j从0开始，表示0---（j+2)的子数组之和被k整除
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{23,2,4,6,6};
        int k = 7;
        boolean b = checkSubarraySum(nums, k);
        System.out.println(b);
    }
}
