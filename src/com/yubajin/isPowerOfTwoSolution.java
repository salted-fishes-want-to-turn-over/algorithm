package com.yubajin;

public class isPowerOfTwoSolution {
    public static boolean isPowerOfTwo(int n) {
        boolean res = false;
        int count = 0;
        for(int i=31; i>=0; i--){
            if(count > 1){
                break;
            }
            if(((n >> i) & 1) == 1){
                count++;
            }
        }
        if(count == 1){
            res = true;
        }
        return res;
    }

    public static boolean isPowerOfTwo2(int n) {
        if(n<=0){
            return false;
        }
        if((n & (n-1)) == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int a = 214748364;
        boolean powerOfTwo = isPowerOfTwo2(a);
        System.out.println(powerOfTwo);
    }
}
