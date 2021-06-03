package com.yubajin;

/***
 * 统计两数「二进制表示中不同位」个数的几种方式
 */
public class hammingDistanceSolution {
    public static int hammingDistance(int x, int y) {
        int count = 0;
        if(x==y){
            return 0;
        }
        while(x!=0 || y!=0){
            if(x%2 != y%2){ // x%2 和 x&1类似
                count++;
            }
            x=x/2;          // x/2 和 x>>1类似
            y=y/2;
        }
        return count;
    }

    public static int hammingDistance1(int x, int y) {
        int ans = 0;
        for(int i=31; i>=0; i--){
            int a = (x>>i) & 1, b = (y>>i) & 1;
            ans += a^b;
        }
        return ans;
    }

    public static void toBin(int x){
        print(x);
        System.out.println();
        System.out.println("-----------------------");
        print1(x);
    }

    public static void print(int x){
        while(x!=0){
            System.out.print(x%2);
            x=x/2;
        }
    }

    static void print1(int x){
        if(x==0){
            return;
        }
        print(x/2);
        System.out.print(x%2);
    }

    public static void main(String[] args) {
        toBin(10);
//        int i = hammingDistance1(1, 4);
//        System.out.println(i);
    }

}
