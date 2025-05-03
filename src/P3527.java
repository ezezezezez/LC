import java.util.*;

// 3527. Find the Most Common Response
public class P3527 {
    public String findCommonResponse(List<List<String>> responses) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (List<String> a : responses) {
            Set<String> set = new HashSet<>();
            for (String resp : a) {
                set.add(resp);
            }
            for (String resp : set) {
                map.merge(resp, 1, Integer::sum);
            }
        }
        int mx = 0;
        for (int cnt : map.values()) {
            if (cnt > mx) mx = cnt;
        }
        for (String key : map.keySet()) {
            if (map.get(key) == mx) {
                list.add(key);
            }
        }
        Collections.sort(list, (a, b) -> a.compareTo(b));
        return list.get(0);
    }
}
