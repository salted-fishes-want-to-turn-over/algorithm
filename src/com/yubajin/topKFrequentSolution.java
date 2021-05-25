package com.yubajin;

import java.util.*;

public class topKFrequentSolution {

    public static void main(String[] args) {
        String words[] = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> strings = topKFrequent(words, k);
        System.out.println(strings);
    }

    private static List<String> topKFrequent(String[] words, int k) {

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        ArrayList<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            res.add(entry.getKey());
        }
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o2) == map.get(o1) ? o1.compareTo(o2) : map.get(o2) - map.get(o1);
            }
        });

        return res.subList(0, k);
    }
}
