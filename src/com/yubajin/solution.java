package com.yubajin;

import java.util.*;

public class solution {

    /***
     * 停在原地的方案数
     * @param steps
     * @param arrLen
     * @return
     */
    public static int numWays(int steps, int arrLen) {

        final int MODULO = 1000000007;
        int maxColumn = Math.min(steps, arrLen-1);
        int dp[][] = new int[steps+1][maxColumn+1];
        dp[0][0] = 1;
        for(int i=1; i<=steps; i++){
            for(int j=0; j<=maxColumn; j++){
                dp[i][j] = dp[i-1][j];  //dp[i][j]=dp[i−1][j−1]+dp[i−1][j]+dp[i−1][j+1]
                if(j-1 >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                }
                if(j+1 <= maxColumn) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return dp[steps][0];
    }

    /***
     * 整数转罗马数字
     */
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) { //需要循环，比如30为XXX，
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    /***
     * 罗马数转整数
     */
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for(int i=0; i<n; i++){
            int value = symbolValues.get(s.charAt(i));
            if(i<n-1 && value<symbolValues.get(s.charAt(i+1))){  //前面字符比后面字符小，则做减法；否则做加法
                ans -= value;
            }else{
                ans += value;
            }
        }
        return ans;
    }

    /**
     * 数组中两个数的最大异或值
     * @param args
     */
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            Set<Integer> seen = new HashSet<Integer>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            for (int num : nums) {
                // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
                // 只需将其右移 k 位
                seen.add(num >> k);
            }

            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;

            // 枚举 i
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) { //aj = x^ai
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                // 即为 x = x*2
                x = xNext - 1;
            }
        }
        return x;
    }


    /**
     * 二叉树的堂兄弟节点
     */
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }

    // x 的信息
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;

    // y 的信息
    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        // 如果两个节点都找到了，就可以提前退出遍历
        // 即使不提前退出，对最坏情况下的时间复杂度也不会有影响
        if (xFound && yFound) {
            return;
        }

        dfs(node.left, depth + 1, node);

        if (xFound && yFound) {
            return;
        }

        dfs(node.right, depth + 1, node);
    }

    /**
     * 形成两个异或相等数组的三元组数目
     * @param arr
     * @return
     */
    public static int countTriplets(int[] arr){
        int res = 0;
        for(int i=0; i<arr.length-1; i++){
            int xor = arr[i];
            for (int k=i+1; k<arr.length; k++){
                xor ^= arr[k];
                //两数相等，推理得到两数异或得零
                if(xor == 0){
                    res += k - i; //并且个数为k-i
                }
            }
        }
        return res;
    }

    /***
     * 找出第K大的异或坐标值
     * @param matrix
     * @param k
     * @return
     */
    public static int kthLargestValue(int[][] matrix, int k){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m+1][n+1];

        List<Integer> res = new ArrayList<Integer>();
        for (int i=1; i<=m; i++){
            for (int j=1; j<=n; j++){
                /***
                 * 主要理解这里是由异或性质可以推理处理的
                 */
                pre[i][j] = pre[i-1][j-1] ^ pre[i-1][j] ^ pre[i][j-1] ^ matrix[i-1][j-1];
                res.add(pre[i][j]);
            }
        }
        Collections.sort(res, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        return res.get(k-1);
    }

    /***
     * 前K个高频词汇
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        List<String> rec = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        //字符串按照字符串对应的频率排序，如果频率相同，则按字符串顺序排序
        Collections.sort(rec, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                /***
                 * cnt.get(word2) - cnt.get(word1) 表示rec按cnt.get(word)逆序排序
                 * 如果word在map中对应的值相同(即cnt.get(word1) == cnt.get(word2)), 则按字符顺序排序(即word1.compareTo(word2))
                 */
                return cnt.get(word1) == cnt.get(word2) ? word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1);
            }
        });
        return rec.subList(0, k);
    }

    /***
     * 不相交的线
     * @param nums1
     * @param nums2
     * @return
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1]; //表示nums1第i个元素和nums2第j个元素相交
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

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

    /***
     * 使所有区间的异或结果为零
     * @param nums
     * @param k
     * @return
     */
    public static int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        int[][] f = new int[k][max];
        int[] g = new int[k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            if (i == 0) { // 第 0 列：只需要考虑如何将该列变为 xor 即可
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else { // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    f[i][xor] = g[i - 1] + cnt; // 整列替换
                    for (int cur : map.keySet()) { // 部分替换
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,5,2,1,7,3,4,7};
        int k = 3;
        System.out.println(minChanges(nums, k));

//        int[] arr = new int[]{2,3,1,6,7};
//        int[] arr1 = new int[]{1,1,1,1,1};
//        System.out.println(countTriplets(arr));
//        System.out.println(countTriplets(arr1));

//        int[] arr = new int[]{1,1,2};
//        System.out.println(xorGame(arr));

//        int[][] matrix = new int[][]{{5,2},{1,6}};
//        int k = 1;
//        int res = kthLargestValue(matrix, k);
//        System.out.println(res);

//        int m[] = new int[]{1,4,2};
//        int n[] = new int[]{1,2,4};
//        System.out.println(maxUncrossedLines(m, n));

//        String words[] = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
//        int k = 2;
//        List<String> strings = topKFrequent(words, k);
//        System.out.println(strings);

//        int steps = 4;
//        int arrLen = 2;
//        int numWay = solution.numWays(steps, arrLen);
//        System.out.println("numWays: " + numWay);

//        int[] nums = new int[]{3,10,5,25,2,8}; //28  k=4 25:11001
//        int[] nums = new int[]{3,10,5,17,2,8}; //26  k=4 17:10001
//        int maximumXOR = new solution().findMaximumXOR(nums);
//        System.out.println(maximumXOR);


    }
}
