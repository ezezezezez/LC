import java.util.*;
import java.io.*;
import java.lang.*;

// 1296. Divide Array in Sets of K Consecutive Numbers

public class P1296 {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);

        while (!map.isEmpty()) {
            int key = map.firstKey();
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(key + i)) return false;
                map.merge(key + i, -1, Integer::sum);
                if (map.get(key + i) == 0) map.remove(key + i);
            }
        }
        return true;
    }
}
