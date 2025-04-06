import java.util.PriorityQueue;

// 3075. Maximize Happiness of Selected Children
public class P3075 {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int hap : happiness) pq.offer(hap);
        long round = 0, ret = 0;
        while (round < k) {
            int v = pq.poll();
            if (v - round <= 0) break;
            ret += v - round;
            round++;
        }
        return ret;
    }
}
