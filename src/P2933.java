import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2933. High-Access Employees
public class P2933 {
    public List<String> findHighAccessEmployees(List<List<String>> accessTimes) {
        int n = accessTimes.size();
        Map<String, List<Integer>> map = new HashMap<>();
        for (List<String> accessTime : accessTimes) {
            String time = accessTime.get(1);
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(2) - '0') * 10 + (time.charAt(3) - '0');
            map.computeIfAbsent(accessTime.get(0), k -> new ArrayList<>()).add(60 * hour + minute);
        }
        List<String> ret = new ArrayList<>();
        for (String key : map.keySet()) {
            List<Integer> values = map.get(key);
            values.sort((a, b) -> Integer.compare(a, b));
            boolean flag = false;
            for (int i = 2; i < values.size(); i++) {
                if (values.get(i) - values.get(i - 2) < 60) {
                    flag = true;
                    break;
                }
            }
            if (flag) ret.add(key);
        }
        return ret;
    }
}
