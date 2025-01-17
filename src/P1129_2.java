import java.util.*;

// 1129. Shortest Path with Alternating Colors
// bidirectional BFS

public class P1129_2 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        Map<Integer, Set<Integer>> reds = new HashMap<>();
        Map<Integer, Set<Integer>> blues = new HashMap<>();

        for (int[] redEdge : redEdges) {
            reds.computeIfAbsent(redEdge[0], k -> new HashSet<>()).add(redEdge[1]);
        }

        for (int[] blueEdge : blueEdges) {
            blues.computeIfAbsent(blueEdge[0], k -> new HashSet<>()).add(blueEdge[1]);
        }

        bfs(n, ret, reds, blues, 0);

        return ret;
    }

    private void bfs(int n, int[] ret, Map<Integer, Set<Integer>> reds, Map<Integer, Set<Integer>> blues, int startNode) {
        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startNode, 0, 0}); // Start with color 0 (blue)
        queue.offer(new int[]{startNode, 0, 1}); // Start with color 1 (red)
        visited[startNode][0] = true;
        visited[startNode][1] = true;
        ret[startNode] = 0;

        while (!queue.isEmpty()) {
            int[] nodeInfo = queue.poll();
            int node = nodeInfo[0];
            int dist = nodeInfo[1];
            int color = nodeInfo[2];

            Map<Integer, Set<Integer>> neighbors = color == 0 ? reds : blues; // Alternate the color
            for (int next : neighbors.getOrDefault(node, Collections.emptySet())) {
                int nextColor = color ^ 1;
                if (!visited[next][nextColor]) {
                    visited[next][nextColor] = true;
                    ret[next] = ret[next] == -1 ? dist + 1 : Math.min(ret[next], dist + 1);
                    queue.offer(new int[]{next, dist + 1, nextColor});
                }
            }
        }
    }
}