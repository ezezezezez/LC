// 3629. Minimum Jumps to Reach End via Prime Teleportation

import java.util.*;

public class P3629 {
    static int MAXV = 1000000;
    static boolean[] isp = new boolean[MAXV + 1];

    static {
        Arrays.fill(isp, true);
        isp[0] = false;
        isp[1] = false;
        for (int i = 2; i * i <= MAXV; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= MAXV; j += i) {
                    isp[j] = false;
                }
            }
        }
    }

    boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int n;
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Map<Integer, List<Integer>> map2 = new HashMap<>();

    public int minJumps(int[] nums) {
        n = nums.length;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], kk -> new HashSet<>()).add(i);
        }
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    if (isp[j] && map.containsKey(j)) map2.computeIfAbsent(j, kk -> new ArrayList<>()).add(i);
                    if (isp[num / j] && map.containsKey(num / j))
                        map2.computeIfAbsent(num / j, kk -> new ArrayList<>()).add(i);
                }
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
            if (isp[nums[x]]) {
                for (int nxt : map.get(nums[x])) {
                    if (!vis[nxt]) {
                        if (nxt == n - 1) return step + 1;
                        vis[nxt] = true;
                        q.offer(new int[]{nxt, step + 1});
                    }
                }
            }
            if (map2.containsKey(nums[x])) {
                for (int nxt : map2.get(nums[x])) {
                    // System.out.println(x + " " +step + " " + nxt);
                    if (!vis[nxt]) {
                        if (nxt == n - 1) return step + 1;
                        vis[nxt] = true;
                        q.offer(new int[]{nxt, step + 1});
                    }
                }
            }
        }

        return 0;
    }
}
