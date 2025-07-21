import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3623. Count Number of Trapezoids I
public class P3623 {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        long ret = 0, mod = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            map.merge(y, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>(map.values());
        long pre = 0;

        for (int cnt : list) {
            long cur = 1L * cnt * (cnt - 1) / 2;
            ret = (ret + cur * pre % mod) % mod;
            pre = (pre + cur) % mod;
        }

        return (int) ret;
    }
}
