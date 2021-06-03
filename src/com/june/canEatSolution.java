package com.june;

public class canEatSolution {
    public static boolean[] canEat(int[] cs, int[][] qs) {
        int m = cs.length;
        int n = qs.length;
        long[] sum = new long[m+1];
        boolean[] ans = new boolean[n];
        for (int i=1; i<m; i++){
            sum[i] = sum[i-1] + cs[i-1];//前缀和
        }
        for (int j=0; j<n; j++){
            int t = qs[j][0], d = qs[j][1] + 1, c = qs[j][2];
            long a = sum[t] / c + 1, b = sum[t+1];
            ans[j] = a <= d && d <= b;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] candiesCount = new int[]{7, 4, 5, 3, 8};
        int[][] queries = new int[][]{{0, 2, 2}, {4, 2, 4}, {2, 13, 1000000000}};
        boolean res[] = canEat(candiesCount, queries);
        for (boolean re: res){
            System.out.println(re);
        }
    }
}
