package com.june;

import java.util.HashMap;
import java.util.Map;

/***
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。

 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *  
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 */

public class findMaxLengthSolution {

    /**
     * 前缀和 + 哈希表
     *
     * 1、将nums[i] 为 0 的值当做 −1 处理。
     * 从而将问题转化为：如何求得最长一段区间和为 0 的子数组
     *
     * 2、使用「哈希表」来记录「某个前缀和出现的最小下标」是多少
     * map key为前缀和， value为下标位置
     *
     * 3、map的value相等时，为一个连续的符合条件的子数组
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {

        int n = nums.length;
        int[] sum = new int[n+1];
        int ans = 0;
        for(int i=1; i<n+1; i++){  //前缀和
            nums[i-1] = (nums[i-1]==1?1:-1);
            sum[i] = sum[i-1]+nums[i-1];
        }
        Map<Integer, Integer> map = new HashMap<>(); //哈希表 key存sum前缀和
        for(int i=0; i<=n-2; i++){
            if(!map.containsKey(sum[i])) {
                map.put(sum[i], i);
            }
            if(map.containsKey(sum[i+2])){ //答案非0，子数组长度至少为2
                ans = Math.max(ans, (i+2) - (map.get(sum[i+2])));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0};
        int maxLength = findMaxLength(nums);
        System.out.println(maxLength);
    }
}
