// 3629. Minimum Jumps to Reach End via Prime Teleportation

import java.util.*;

public class P3629_2 {
    static int MAXV = 1000000;
    static List<Integer>[] fac = new List[MAXV + 5];
    static boolean initialized = false;

    void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(fac, kk -> new ArrayList<>());
        for (int i = 2; i < MAXV; i++) {
            if (fac[i].isEmpty()) {
                for (int j = i; j < MAXV; j += i) {
                    fac[j].add(i);
                }
            }
        }
    }

    Map<Integer, List<Integer>> map = new HashMap<>();

    public int minJumps(int[] nums) {
        init();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int factor : fac[num]) {
                map.computeIfAbsent(factor, kk -> new ArrayList<>()).add(i);
            }
        }
        // System.out.println(map2);

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        boolean[] vis = new boolean[n];
        vis[0] = true;
        while (!q.isEmpty()) {
            int[] data = q.poll();
            int x = data[0], step = data[1];
            // System.out.println(x + " " + step);
            if (x - 1 >= 0 && !vis[x - 1]) {
                vis[x - 1] = true;
                q.offer(new int[]{x - 1, step + 1});
            }
            if (x + 1 < n && !vis[x + 1]) {
                if (x + 1 == n - 1) return step + 1;
                vis[x + 1] = true;
                q.offer(new int[]{x + 1, step + 1});
            }
            if (fac[nums[x]].size() == 1 && map.containsKey(nums[x])) {
                for (int nxt : map.get(nums[x])) {
                    if (!vis[nxt]) {
                        if (nxt == n - 1) return step + 1;
                        vis[nxt] = true;
                        q.offer(new int[]{nxt, step + 1});
                    }
                }
                map.remove(nums[x]);
            }
        }

        return 0;
    }
}
