import java.io.*;
import java.lang.*;
import java.util.*;

// 1726. Tuple with Same Product

public class P1727 {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] h = new int[n];
        int ret = 0;
        for (int i = 0; i < m; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                h[j] = matrix[i][j] == 1 ? 1 + h[j] : 0;
                if (h[j] > 0) pq.offer(h[j]);
            }
            // System.out.println(i + " " + pq);
            while (!pq.isEmpty()) {
                ret = Math.max(ret, pq.size() * pq.poll());
            }
        }
        return ret;
    }

    public int largestSubmatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] h = new int[n];
        int ret = 0;
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                h[j] = matrix[i][j] == 1 ? 1 + h[j] : 0;
                if (h[j] > 0) list.add(h[j]);
            }
            Collections.sort(list);
            // System.out.println(i + " " + pq);
            for (int j = 0; j < list.size(); j++) {
                ret = Math.max(ret, list.get(j) * (list.size() - j));
            }
        }
        return ret;
    }
}
