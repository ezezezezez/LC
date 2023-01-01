import java.io.*;
import java.lang.*;
import java.util.*;

// 1705. Maximum Number of Eaten Apples

public class P1705 {
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        int ret = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] == i) {
                pq.poll();
            }
            if (apples[i] > 0) {
                pq.offer(new int[]{i + days[i], apples[i]});
            }
            if (!pq.isEmpty()) {
                ret++;
                pq.peek()[1]--;
                if (pq.peek()[1] == 0) {
                    pq.poll();
                }
            }
        }
        int i = n;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (pq.isEmpty()) break;
            int[] cur = pq.poll();
            int time = cur[0], appleCnt = cur[1];
            int mn = Math.min(time - i, appleCnt);
            ret += mn;
            i += mn;
        }
        return ret;
    }
}
