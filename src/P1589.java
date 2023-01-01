import java.io.*;
import java.lang.*;
import java.util.*;

// 1589. Maximum Sum Obtained of Any Permutation

public class P1589 {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length, m = requests.length;
        int[] d = new int[n];
        for (int[] req : requests) {
            int s = req[0], e = req[1];
            d[s]++;
            if (e + 1 < n) d[e + 1]--;
        }
        List<Integer> list = new ArrayList<>();
        int cur = 0;
        for (int num : d) {
            cur += num;
            list.add(cur);
        }
        Collections.sort(list);
        Arrays.sort(nums);
        int ret = 0;
        int mod = (int)(1e9 + 7);
        for (int i = n - 1; i >= 0; i--) {
            ret = (int)((ret + 1L * nums[i] * list.get(i)) % mod);
        }
        return ret;
    }
}
