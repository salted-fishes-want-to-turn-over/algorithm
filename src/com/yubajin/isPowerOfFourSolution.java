package com.yubajin;

public class isPowerOfFourSolution {
    public static boolean isPowerOfFour(int n) {
        return (n>0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFour(9));
    }
}
