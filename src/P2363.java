import java.io.*;
import java.lang.*;
import java.util.*;

// 2363. Merge Similar Items

public class P2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] item : items1) {
            int v = item[0], w = item[1];
            map.merge(v, w, Integer::sum);
        }
        for (int[] item : items2) {
            int v = item[0], w = item[1];
            map.merge(v, w, Integer::sum);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int v : map.keySet()) {
            int w = map.get(v);
            ret.add(List.of(v, w));
        }
        Collections.sort(ret, (a, b) -> Integer.compare(a.get(0), b.get(0)));
        return ret;
    }
}
