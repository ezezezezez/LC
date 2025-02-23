import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2857. Count Pairs of Points With Distance k
public class P2857 {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        int n = coordinates.size();
        int ret = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> co = coordinates.get(i);
            int x = co.get(0), y = co.get(1);
            for (int j = 0; j <= k; j++) {
                int px = j ^ x, py = (k - j) ^ y;
                ret += map.getOrDefault(encode(px, py), 0);
            }
            map.merge(encode(x, y), 1, Integer::sum);
        }
        return ret;
    }

    long encode(long x, long y) {
        return x * 100001L + y;
    }
}
