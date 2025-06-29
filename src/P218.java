import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

// 218. The Skyline Problem
public class P218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        List<int[]> events = new ArrayList<>();
        for (int[] building : buildings) {
            int l = building[0], r = building[1], h = building[2];
            events.add(new int[] {l, h, 1});
            events.add(new int[] {r, h, 0});
        }
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[2] == 1 && b[2] == 0) return -1;
            if (a[2] == 0 && b[2] == 1) return 1;
            return 0;
        });

        List<List<Integer>> ret = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(events.get(0)[1], 1);
        int idx = 1, prevT = events.get(0)[0];
        while (idx < events.size() && events.get(idx)[0] == prevT) {
            map.merge(events.get(idx)[1], 1, Integer::sum);
            idx++;
        }

        int preT = events.get(0)[0], preH = map.lastKey();

        for (int i = idx; i < events.size(); i = idx) {
            int t = events.get(i)[0];
            int j = i;
            for (; j < events.size() && events.get(j)[0] == t; j++) {
                int nt = events.get(j)[0], nh = events.get(j)[1], isStart = events.get(j)[2];
                if (isStart == 1) {
                    map.merge(nh, 1, Integer::sum);
                } else {
                    map.merge(nh, -1, Integer::sum);
                    if (map.get(nh) == 0) map.remove(nh);
                }
            }

            int curH = map.isEmpty() ? 0 : map.lastKey();
            if (curH != preH) {
                ret.add(List.of(preT, preH));
                preT = t;
                preH = curH;
            }

            idx = j;
        }

        ret.add(List.of(preT, 0));

        return ret;
    }
}
