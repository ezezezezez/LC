import java.io.*;
import java.lang.*;
import java.util.*;

// 1604. Alert Using Same Key-Card Three or More Times in a One Hour Period

public class P1604 {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> ret = new ArrayList<>();
        int n = keyName.length;
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            map.computeIfAbsent(name, k -> new ArrayList<>()).add(convert(keyTime[i]));
        }
        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            if (list.size() < 3) continue;
            Collections.sort(list);
            int pre = list.get(0);
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - pre <= 60) {
                    ret.add(key);
                    break;
                }
                pre = list.get(i - 1);
            }
        }
        Collections.sort(ret);
        return ret;
    }

    int convert(String keyTime) {
        String[] tokens = keyTime.split(":");
        return Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
    }
}
