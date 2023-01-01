import java.io.*;
import java.lang.*;
import java.util.*;

// 2512. Reward Top K Students

public class P2512 {
    public List<Integer> topStudents(String[] pf, String[] nf, String[] report, int[] sid, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> pfSet = new HashSet<>();
        Set<String> nfSet = new HashSet<>();
        for (String w : pf) pfSet.add(w);
        for (String w : nf) nfSet.add(w);
        for (int i = 0; i < sid.length; i++) {
            int id = sid[i];
            String r = report[i];
            String[] tokens = r.split(" ");
            int score = 0;
            for (String w : tokens) {
                if (pfSet.contains(w)) score += 3;
                else if (nfSet.contains(w)) score -= 1;
            }
            map.put(id, score);
        }
        List<Integer> t = new ArrayList<>();
        for (int id : sid) t.add(id);
        Collections.sort(t, (a, b) -> {
            int val1 = map.get(a), val2 = map.get(b);
            if (val1 != val2) return Integer.compare(val2, val1);
            return Integer.compare(a, b);
        });
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) ret.add(t.get(i));
        return ret;
    }
}
