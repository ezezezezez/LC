import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// 2662. Minimum Cost of a Path With Special Roads
public class P2662 {
    int m, n;
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int startX = start[0] - 1, startY = start[1] - 1;
        int targetX = target[0] - 1, targetY = target[1] - 1;
        m = targetX + 1;
        n = targetY + 1;
        Map<Long, Map<Long, Integer>> map = new HashMap<>();
        for (int[] road : specialRoads) {
            int x1 = road[0] - 1, y1 = road[1] - 1, x2 = road[2] - 1, y2 = road[3] - 1, roadCost = road[4];
            Map<Long, Integer> map2 = map.computeIfAbsent(encode(x1, y1), k -> new HashMap<>());
            map2.merge(encode(x2, y2), roadCost, Math::min);
        }
        Map<Long, Integer> cost = new HashMap<>();
        cost.put(encode(startX, startY), 0);
        Queue<Long> q = new ArrayDeque<>();
        q.offer(encode(startX, startY));
        while (!q.isEmpty()) {
            long pair = q.poll();
            int x = (int) (pair / n), y = (int) (pair % n);
            int curCost = cost.get(pair);
            int destCost = curCost + Math.abs(x - targetX) + Math.abs(y - targetY);
            if (cost.get(encode(targetX, targetY)) == null || destCost < cost.get(encode(targetX, targetY))) {
                cost.put(encode(targetX, targetY), destCost);
            }
            for (long s : map.keySet()) {
                int sx = (int) (s / n), sy = (int) (s % n);
                int moveCost = curCost + Math.abs(x - sx) + Math.abs(y - sy);
                Map<Long, Integer> nMap = map.get(s);
                for (long key : nMap.keySet()) {
                    int nx = (int) (key / n), ny = (int) (key % n);
                    int roadCost = nMap.get(key);
                    int nextCost = moveCost + roadCost;
                    if (cost.get(encode(nx, ny)) == null || nextCost < cost.get(encode(nx, ny))) {
                        // System.out.println(nx + " " + ny);
                        cost.put(encode(nx, ny), nextCost);
                        q.offer(encode(nx, ny));
                    }
                }
            }
        }
        return cost.get(encode(targetX, targetY));
    }

    long encode(long x, long y) {
        return x * n + y;
    }
}
