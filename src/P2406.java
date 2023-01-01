import java.io.*;
import java.lang.*;
import java.util.*;

// 2406. Divide Intervals Into Minimum Number of Groups

public class P2406 {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int n = intervals.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            int a = interval[0], b = interval[1];
            if (!pq.isEmpty() && pq.peek() < a) {
                pq.poll();
            }
            pq.offer(b);
        }
        return pq.size();
    }
}
