import java.util.PriorityQueue;

public class P2594 {
    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        long lo = 0, hi = Long.MAX_VALUE;
        long ret = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long cnt = 0;
            for (int rank : ranks) {
                long a = mid / rank;
                long v = (long) Math.sqrt(a);
                if (rank * v * v > mid) v = v - 1;
                cnt += v;
            }
            if (cnt >= cars) {
                ret = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ret;
    }
}
