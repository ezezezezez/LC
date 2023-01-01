import java.io.*;
import java.lang.*;
import java.util.*;

// 1488. Avoid Flood in The City

public class P1488 {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ret = new int[n];
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                map.computeIfAbsent(rains[i], k -> new ArrayDeque<>()).offer(i);
            }
        }

        Set<Integer> filled = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a).isEmpty()) return 1;
            if (map.get(b).isEmpty()) return -1;
            return Integer.compare(map.get(a).peek(), map.get(b).peek());
        });
        for (int i = 0; i < n; i++) {
            // System.out.println(i);
            // System.out.println(pq);
            // System.out.println(filled);
            // System.out.println(map);
            // System.out.println("---");
            if (rains[i] > 0) {
                if (filled.contains(rains[i])) return new int[0];
                filled.add(rains[i]);
                ret[i] = -1;
                map.get(rains[i]).poll();
                pq.offer(rains[i]);
            } else if (!pq.isEmpty()) {
                ret[i] = pq.poll();
                filled.remove(ret[i]);
            } else {
                ret[i] = 1;
            }
        }
        return ret;
    }

    public int[] avoidFlood2(int[] rains) {
        Map<Integer, Integer> lakes = new HashMap<>();  // key: lake id, value: prev rain index
        TreeSet<Integer> dryDays = new TreeSet<>();    // index of available dry days

        int[] ans = new int[rains.length];

        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                continue;
            }

            ans[i] = -1;

            int lake = rains[i];
            int lastRainIdx = lakes.getOrDefault(lake, -1);
            if (lastRainIdx != -1) {
                // find earliest dry day after lastRainIdx
                Integer dryDayIndex = dryDays.ceiling(lastRainIdx + 1);
                if (dryDayIndex != null) {
                    ans[dryDayIndex] = lake; // dry the lake
                    dryDays.remove(dryDayIndex);
                } else {
                    return new int[] {};
                }
            }
            lakes.put(lake, i);
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) {
                ans[i] = 1; // any value would be fine
            }
        }
        return ans;
    }
}
