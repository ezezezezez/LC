import java.io.*;
import java.lang.*;
import java.util.*;

// 2145. Count the Hidden Sequences
// Note: can't compare 2 Integer class instances in the following way:
// List<List<Integer>> list = new ArrayList<>();
// list.add(List.of(1, 1));
// list.add(List.of(1, 2));
// Collections.sort(list, (a, b) -> {
//     if (a.get(0) != b.get(0)) return Integer.compare(a.get(0), b.get(0)); // the comparison in the if statement is wrong!!!
//     return Integer.compare(a.get(1), b.get(1));
// })
// This is wrong because a.get(0) != b.get(0) is comparing 2 different object references instead of 2 primitive int values!!!

public class P2146 {
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length, n = grid[0].length;
        // System.out.println(m * n);
        List<List<Integer>> ret = new ArrayList<>();
        List<List<Long>> temp = new ArrayList<>();
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{start[0], start[1]});
        if (grid[start[0]][start[1]] >= pricing[0] && grid[start[0]][start[1]] <= pricing[1]) temp.add(List.of((long)start[0], (long)start[1], 0L));
        boolean[][] vis = new boolean[m][n];
        vis[start[0]][start[1]] = true;
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        long step = 0;

        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = dq.poll();
                int x = cur[0], y = cur[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dir[j], ny = y + dir[j + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || vis[nx][ny] || grid[nx][ny] == 0) continue;
                    dq.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                    if (grid[nx][ny] >= pricing[0] && grid[nx][ny] <= pricing[1]) temp.add(List.of((long)nx, (long)ny, step + 1));
                }
            }
            step++;
            if (step > Integer.MAX_VALUE) System.out.println(step);
        }
        // System.out.println(temp.size());

        Collections.sort(temp, (a, b) -> {
            if (a.get(2).longValue() != b.get(2).longValue()) return Long.compare(a.get(2).longValue(), b.get(2).longValue());
            if (grid[a.get(0).intValue()][a.get(1).intValue()] != grid[b.get(0).intValue()][b.get(1).intValue()]) return Long.compare(grid[a.get(0).intValue()][a.get(1).intValue()], grid[b.get(0).intValue()][b.get(1).intValue()]);
            if (a.get(0).intValue() != b.get(0).intValue()) return Long.compare(a.get(0), b.get(0));
            return Long.compare(a.get(1), b.get(1));
        });
        for (int i = 0; i < k && i < temp.size(); i++) {
            ret.add(List.of(temp.get(i).get(0).intValue(), temp.get(i).get(1).intValue()));
        }
        return ret;
    }
}
