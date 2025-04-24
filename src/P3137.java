import java.util.HashMap;
import java.util.Map;

// 3137. Minimum Number of Operations to Make Word K-Periodic
public class P3137 {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += k) {
            map.merge(word.substring(i, i + k), 1, Integer::sum);
        }
        int mx = 0;
        for (int cnt : map.values()) mx = Math.max(mx, cnt);
        return n / k - mx;
    }
}
