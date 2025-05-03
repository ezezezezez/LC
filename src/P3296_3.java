import java.util.PriorityQueue;

// 3296. Minimum Number of Seconds to Make Mountain Height Zero
public class P3296_3 {
    public long minNumberOfSeconds(int mh, int[] workerTimes) {
        int n = workerTimes.length;
        long ret = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));;
        for (int w : workerTimes) pq.offer(new long[] {w, w + w, w});
        while (mh > 0) {
            mh--;
            long[] tuple = pq.poll();
            long total = tuple[0], nxt = tuple[1], base = tuple[2];
            ret = total;
            pq.offer(new long[] {total + nxt, nxt + base, base});
        }
        return ret;
    }
}
