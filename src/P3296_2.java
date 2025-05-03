// 3296. Minimum Number of Seconds to Make Mountain Height Zero
public class P3296_2 {
    public long minNumberOfSeconds(int mh, int[] workerTimes) {
        int n = workerTimes.length;
        long lo = 1, hi = (long) 9e15, t = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long sum = 0;
            for (int time : workerTimes) {
                long x = (long) Math.floor(Math.sqrt(mid * 2 / time));
                if (x * (x + 1) <= mid * 2 / time) {
                    sum += x;
                } else if (x - 1 > 0) {
                    sum += x - 1;
                }
            }
            if (sum >= mh) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
