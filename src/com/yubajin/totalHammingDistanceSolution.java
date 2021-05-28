package com.yubajin;

/**
 * 477. 汉明距离总和
 */
public class totalHammingDistanceSolution {
    public static int totalHammingDistance(int[] nums) {
        int res = 0 ;
        for(int i=0; i<32; i++){
            int s1=0,s2=0;
            for(int num:nums){
                if(((num>>i)&1) == 1){
                    s1++;
                }else {
                    s2++;
                }
            }
            res += s1 * s2;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,14,2};
        int i = totalHammingDistance(nums);
        System.out.println(i);

    }

}
