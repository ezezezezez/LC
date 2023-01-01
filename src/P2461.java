import java.io.*;
import java.lang.*;
import java.util.*;

// 2461. Maximum Sum of Distinct Subarrays With Length K

public class P2461 {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long ret = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        long sum = 0;
        TreeSet<Integer> ts = new TreeSet<>((a, b) -> {
            if (cnt.get(a) != cnt.get(b)) return Integer.compare(cnt.get(b), cnt.get(a));
            return Integer.compare(a, b);
        });

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                int del = nums[i - k];
                ts.remove(del);
                if (cnt.containsKey(nums[i])) ts.remove(nums[i]);
                cnt.merge(del, -1, Integer::sum);
                cnt.merge(nums[i], 1, Integer::sum);
                if (cnt.get(del) > 0) ts.add(del);
                ts.add(nums[i]);
                if (cnt.get(del) == 0) cnt.remove(del);
                sum -= del;
            } else {
                cnt.merge(nums[i], 1, Integer::sum);
            }
            sum += nums[i];
            if (i == k - 1) {
                for (int key : cnt.keySet()) {
                    ts.add(key);
                }
            }
            if (i >= k - 1) {
                // System.out.println(ts);
                if (cnt.get(ts.first()) == 1) {
                    ret = Math.max(ret, sum);
                }
            }
        }
        return ret;
    }

    public long maximumSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        long ret = 0;
        long sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            map.merge(nums[i], 1, Integer::sum);
            if (i >= k) {
                sum -= nums[i - k];
                map.merge(nums[i - k], -1, Integer::sum);
                if (map.get(nums[i - k]) == 0) map.remove(nums[i - k]);
            }
            if (i >= k - 1 && map.size() == k) {
                ret = Math.max(ret, sum);
            }
        }
        return ret;
    }
}
