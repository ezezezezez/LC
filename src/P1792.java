import java.io.*;
import java.lang.*;
import java.util.*;

// 1792. Maximum Average Pass Ratio

public class P1792 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        double ret = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double a1 = a[0], a2 = a[1], b1 = b[0], b2 = b[1];
            double c1 = (a2 - a1) / (a2 * (a2 + 1)), c2 = (b2 - b1) / (b2 * (b2 + 1));
            return Double.compare(c2, c1);
        });
        for (int[] klass : classes) {
            pq.offer(klass);
        }
        while (extraStudents-- > 0) {
            int[] cur = pq.poll();
            // System.out.println(cur[0] + " " + cur[1]);
            pq.offer(new int[]{cur[0] + 1, cur[1] + 1});
        }
        double sum = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            sum += 1.0 * cur[0] / cur[1];
        }
        return sum / n;
    }
}
