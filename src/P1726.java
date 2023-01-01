import java.io.*;
import java.lang.*;
import java.util.*;

// 1726. Tuple with Same Product

public class P1726 {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                map.merge(nums[i] * nums[j], 1, Integer::sum);
            }
        }
        int ret = 0;
        // System.out.println(map);
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            ret += cnt * (cnt - 1) / 2 * 8;
        }
        return ret;
    }
}
