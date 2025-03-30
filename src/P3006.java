import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3006. Find Beautiful Indices in the Given Array I
public class P3006 {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        List<Integer> idxA = new ArrayList<>();
        List<Integer> idxB = new ArrayList<>();
        for (int i = 0; i <= n - a.length(); i++) {
            boolean same = true;
            for (int j = 0; j < a.length(); j++) {
                if (cs[i + j] != ca[j]) {
                    same = false;
                    break;
                }
            }
            if (same) idxA.add(i);
        }
        for (int i = 0; i <= n - b.length(); i++) {
            boolean same = true;
            for (int j = 0; j < b.length(); j++) {
                if (cs[i + j] != cb[j]) {
                    same = false;
                    break;
                }
            }
            if (same) idxB.add(i);
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0, j = 0; i < idxA.size(); i++) {
            while (j < idxB.size() && idxB.get(j) < idxA.get(i) && Math.abs(idxB.get(j) - idxA.get(i)) > k) {
                j++;
            }
            if (j < idxB.size() && Math.abs(idxB.get(j) - idxA.get(i)) <= k) {
                ret.add(idxA.get(i));
            }
        }
        return ret;
    }
}
