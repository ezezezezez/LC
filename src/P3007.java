// 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
public class P3007 {
    public long findMaximumNumber(long k, int x) {
        long ret = 0;
        long lo = 1, hi = (long) 1e15, t = -1;
        // long lo = 1, hi = (long) 6, t = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long count = 0;
            for (int i = x; i <= 50; i += x) {
                long b = 1L << i;
                long rem = mid % b;
                count += mid / b * b / 2;
                count += Math.min(Math.max(0, rem - (b / 2 - 1)), b / 2);
                // System.out.println(lo + " " + hi + " " + mid + " " + count + " " + i);
            }
            if (count <= k) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
