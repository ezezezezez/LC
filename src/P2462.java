import java.io.*;
import java.lang.*;
import java.util.*;

// 2462. Total Cost to Hire K Workers

public class P2462 {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        int left = 0, right = n - 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (costs[a] != costs[b]) return Integer.compare(costs[a], costs[b]);
            return Integer.compare(a, b);
        });
        while (left < candidates) {
            pq.offer(left++);
        }
        while (right >= left && right >= n - candidates) {
            pq.offer(right--);
        }
        // System.out.println(left + " " + right + " " + pq);
        long ret = 0;
        while (k-- > 0) {
            int idx = pq.poll();
            ret += costs[idx];
            if (left <= right) {
                if (idx < left) {
                    pq.offer(left++);
                } else {
                    pq.offer(right--);
                }
            }
        }
        return ret;
    }
}
