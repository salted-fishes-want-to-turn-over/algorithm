package com.yubajin;

import java.util.Stack;

public class reverseParenthesesSolution {
    /***
     * 反转每对括号间的子串
     * @param s
     * @return
     */
    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++){

            if(arr[i] == '('){
                stack.push(i);
            }

            if(arr[i] == ')'){
                reverse(arr, stack.pop()+1, i-1);
            }
        }

        for(int i=0; i<arr.length; i++){
            if(arr[i] != '(' && arr[i] != ')'){
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

    public static void reverse(char[] arr, int left, int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "(u(love)i)";
        String res = reverseParentheses(s);
        System.out.println(res);
    }
}
