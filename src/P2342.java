import java.io.*;
import java.lang.*;
import java.util.*;

// 2342. Max Sum of a Pair With Equal Sum of Digits

public class P2342 {
    public int maximumSum(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int t = num;
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            map.computeIfAbsent(sum, k -> new ArrayList<>()).add(t);
        }
        for (List<Integer> list : map.values()) Collections.sort(list);
        int ret = -1;
        for (List<Integer> list : map.values()) {
            // System.out.println(list);
            if (list.size() >= 2) {
                int sz = list.size();
                ret = Math.max(ret, list.get(sz - 1) + list.get(sz - 2));
            }
        }
        return ret;
    }
}
