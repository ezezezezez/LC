import java.util.HashSet;
import java.util.Set;

// 3296. Minimum Number of Seconds to Make Mountain Height Zero
public class P3296 {
    public long minNumberOfSeconds(int mh, int[] workerTimes) {
        int n = workerTimes.length;
        long lo = 1, hi = (long) 9e15, t = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long sum = 0;
            for (int time : workerTimes) {
                long lo1 = 0, hi1 = mh, t1 = -1;
                while (lo1 <= hi1) {
                    long mid1 = lo1 + (hi1 - lo1) / 2;
                    long sec = time * (mid1 + 1) * mid1 / 2;
                    if (sec <= mid) {
                        t1 = mid1;
                        lo1 = mid1 + 1;
                    } else {
                        hi1 = mid1 - 1;
                    }
                }
                if (t1 != -1) {
                    sum += t1;
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
