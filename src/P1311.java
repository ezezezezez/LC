import java.util.*;
import java.io.*;
import java.lang.*;

// 1311. Get Watched Videos by Your Friends

public class P1311 {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());

        for (int i = 0; i < n; i++) {
            for (int friend : friends[i]) {
                g[i].add(friend);
            }
        }

        Deque<Integer> dq = new ArrayDeque<>();
        int step = 0;
        dq.offer(id);
        boolean[] vis = new boolean[n];
        vis[id] = true;

        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int node = dq.poll();
                for (int nxt : g[node]) {
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        dq.offer(nxt);
                    }
                }
            }
            step++;
            if (step == level) break;
        }

        Map<String, Integer> map = new HashMap<>();
        while (!dq.isEmpty()) {
            int node = dq.poll();
            for (String video : watchedVideos.get(node)) {
                map.merge(video, 1, Integer::sum);
            }
        }

        List<String> ret = new ArrayList<>();
        for (String video : map.keySet()) ret.add(video);

        Collections.sort(ret, (a, b) -> {
            if (map.get(a) != map.get(b)) return Integer.compare(map.get(a), map.get(b));
            return a.compareTo(b);
        });

        return ret;
    }
}
