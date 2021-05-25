package com.yubajin;

public class maxUncrossedLinesSolution {

    public static void main(String[] args) {
        int m[] = new int[]{1,4,2};
        int n[] = new int[]{1,2,4};
        System.out.println(maxUncrossedLines(m, n));
    }

    private static int maxUncrossedLines(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;
        int[][] dp = new int[m+1][n+1];

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(num1[i-1] == num2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
