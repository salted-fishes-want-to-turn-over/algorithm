package com.yubajin;

public class xorGameSolution {
    /***
     * 黑板异或游戏
     * 主要异或推断出元素为偶数或者所有元素异或值为0是先手能赢
     * @param nums
     * @return
     */
    public static boolean xorGame(int[] nums) {
        if(nums.length % 2 == 0){
            return true;
        }
        int xor = 0;
        for(int i = 0; i < nums.length; i++){
            xor = xor ^ nums[i];
        }
        return xor == 0;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,1,2};
        System.out.println(xorGame(arr));
    }
}
