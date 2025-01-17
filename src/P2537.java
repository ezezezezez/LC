import java.io.*;
import java.lang.*;
import java.util.*;

public class P2537 {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], kk -> new ArrayList<>()).add(i);
        }
        long ret = 0;
        long cur = 0;
        long window = 0;
        int l = 0;
        for (int r = 1; r < n; r++) {
            ret += cur;
            int num = nums[r];
            List<Integer> list = map.get(num);
            int ll = search2(list, l), rr = search1(list, r - 1);
            // System.out.println("ll " + ll + " rr " + rr);
            if (ll != -1 && rr != -1 && list.get(ll) < r && list.get(rr) >= l) window += rr - ll + 1;
            if (window >= k) {
                ret++;
                cur++;
            }
            while (window >= k) {
                int last = nums[l];
                l++;
                list = map.get(last);
                int ll2 = search2(list, l), rr2 = search1(list, r);
                // System.out.println("l " + l + " r " + r);
                // System.out.println("ret " + ret + " cur " + cur + " window " + window + " ll2 " + ll2 + " rr2 " + rr2);
                if (ll2 != -1 && rr2 != -1 && list.get(ll2) <= r && list.get(rr2) >= l) window -= rr2 - ll2 + 1;
                if (window >= k) {
                    ret++;
                    cur++;
                }
            }
        }
        return ret;
    }

    public int search1(List<Integer> list, int idx) {
        int l = 0, r = list.size() - 1;
        int ret = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (list.get(mid) <= idx) {
                ret = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ret;
    }

    public int search2(List<Integer> list, int idx) {
        int l = 0, r = list.size() - 1;
        int ret = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= idx) {
                ret = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ret;
    }
}
