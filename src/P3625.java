import java.util.*;

// 3625. Count Number of Trapezoids II
public class P3625 {
    public int countTrapezoids(int[][] points) {
        int n = points.length;

        Map<Double, List<Set<Integer>>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
            int x1 = p1[0], y1 = p1[1];
            for (int j = i + 1; j < n; j++) {
                int[] p2 = points[j];
                int x2 = p2[0], y2 = p2[1];

                double k;
                if (x1 == x2) {
                    k = Double.NaN;
                } else if (y1 == y2) {
                    k = 0;
                } else {
                    k = 1.0 * (y2 - y1) / (x2 - x1);
                }
                if (!map.containsKey(k)) {
                    map.computeIfAbsent(k, key -> new ArrayList<>()).add(new HashSet<>());
                    map.get(k).get(0).add(i);
                    map.get(k).get(0).add(j);
                } else {
                    List<Set<Integer>> list = map.get(k);
                    boolean found = false;
                    for (Set<Integer> set : list) {
                        if (set.contains(i) || set.contains(j)) {
                            set.add(i);
                            set.add(j);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        Set<Integer> newSet = new HashSet<>();
                        newSet.add(i);
                        newSet.add(j);
                        list.add(newSet);
                    }
                }

                // System.out.println(i + " " + j + " " + k + " " + map);
            }
        }

        // System.out.println(map);

        int cnt = 0;
        for (List<Set<Integer>> list : map.values()) {
            int pre = 0;
            for (Set<Integer> set : list) {
                int size = set.size();
                int cur = size * (size - 1) / 2;
                cnt += cur * pre;
                pre += cur;
            }
            // System.out.println(list + " " + cnt);
        }

        return cnt - countParallelograms(points);
    }

    int countParallelograms(int[][] points) {
        int n = points.length;
        Map<Integer, Map<Integer, Map<Double, Integer>>> pMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
            int x1 = p1[0], y1 = p1[1];
            for (int j = i + 1; j < n; j++) {
                int[] p2 = points[j];
                int x2 = p2[0], y2 = p2[1];
                int xMid = x1 + x2, yMid = y1 + y2;

                double k;
                if (x1 == x2) {
                    k = Double.NaN;
                } else if (y1 == y2) {
                    k = 0;
                } else {
                    k = 1.0 * (y2 - y1) / (x2 - x1);
                }
                pMap.computeIfAbsent(xMid, kk -> new HashMap<>()).computeIfAbsent(yMid, kk -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int pCnt = 0;
        for (int x : pMap.keySet()) {
            for (int y : pMap.get(x).keySet()) {
                int pre = 0;
                for (double k : pMap.get(x).get(y).keySet()) {
                    int cur = pMap.get(x).get(y).get(k);
                    pCnt += pre * cur;
                    pre += cur;
                }
            }
        }

        return pCnt;
    }
}
