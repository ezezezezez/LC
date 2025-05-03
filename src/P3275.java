import java.util.PriorityQueue;

// 3275. K-th Nearest Obstacle Queries
public class P3275 {
    public int[] resultsArray(int[][] queries, int k) {
        int m = queries.length;
        int[] ret = new int[m];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));;
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int x = query[0], y = query[1];
            int dist = Math.abs(x) + Math.abs(y);
            if (pq.size() < k || pq.peek() >= dist) pq.offer(dist);
            if (pq.size() > k) pq.poll();
            ret[i] = pq.size() < k ? -1 : pq.peek();
        }
        return ret;
    }
}
