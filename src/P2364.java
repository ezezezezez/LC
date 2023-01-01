import java.io.*;
import java.lang.*;
import java.util.*;

// 2364. Count Number of Bad Pairs

public class P2364 {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int cur = i - nums[i];
            cnt += map.getOrDefault(cur, 0);
            map.merge(cur, 1, Integer::sum);
        }
        // System.out.println(cnt);
        return 1L * n * (n - 1) / 2 - cnt;
    }
}
