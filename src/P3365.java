import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 3365. Rearrange K Substrings to Form Target String

public class P3365 {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += n / k) {
            map.merge(s.substring(i, i + n / k), 1, Integer::sum);
        }
        for (int i = 0; i < n; i += n / k) {
            if (map.containsKey(t.substring(i, i + n / k))) {
                map.merge(t.substring(i, i + n / k), -1, Integer::sum);
                if (map.get(t.substring(i, i + n / k)) == 0) map.remove(t.substring(i, i + n / k));
            } else {
                return false;
            }
        }
        return true;
    }
}
