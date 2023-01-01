import java.io.*;
import java.lang.*;
import java.util.*;

// 2453. Destroy Sequential Targets

public class P2453 {
    public int destroyTargets(int[] nums, int space) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> rep = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            rep.put(num % space, Math.min(rep.getOrDefault(num % space, Integer.MAX_VALUE), num));
            map.merge(num % space, 1, Integer::sum);
        }
        int sz = 0, ret = 0;
        // System.out.println(map);
        // System.out.println(rep);
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            if (cnt > sz) {
                sz = cnt;
                ret = rep.get(key);
            } else if (cnt == sz) {
                ret = Math.min(ret, rep.get(key));
            }
        }
        return ret;
    }
}
