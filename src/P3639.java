// 3639. Minimum Time to Activate String

public class P3639 {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        int lo = 0, hi = n - 1, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            char[] cs = s.toCharArray();
            long cnt = 0;
            int pre = -1;
            for (int i = 0; i <= mid; i++) cs[order[i]] = '*';
            for (int i = 0; i < n; i++) {
                if (cs[i] == '*') {
                    cnt += i + 1;
                    pre = i;
                } else {
                    cnt += pre + 1;
                }
                if (cnt >= k) break;
            }
            // System.out.println(mid + " " + cnt);
            if (cnt >= k) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
