import java.util.*;

// 3679. Minimum Discards to Balance Inventory

public class P3679 {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length;
        Deque<Integer> dq = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int arrival = arrivals[i];
            while (!dq.isEmpty() && dq.peekFirst() <= i - w) {
                int idx = dq.pollFirst();
                map.merge(arrivals[idx], -1, Integer::sum);
            }
            if (map.getOrDefault(arrival, 0) + 1 > m) {
                ret++;
            } else {
                dq.offerLast(i);
                map.merge(arrival, 1, Integer::sum);
            }
        }
        return ret;
    }
}
