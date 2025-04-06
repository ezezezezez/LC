import java.util.ArrayList;
import java.util.List;

// 3076. Shortest Uncommon Substring in an Array
public class P3076_2 {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ret = new String[n];
        for (int i = 0; i < n; i++) {
            String str = arr[i];
            int m = str.length();
            int lo = 1, hi = m, t = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                boolean valid = false;
                for (int j = 0; j <= m - mid; j++) {
                    String sub = str.substring(j, j + mid);
                    boolean vvalid = true;
                    for (int k = 0; k < n; k++) {
                        if (k == i) continue;
                        // if (!search(arr[k], sub).isEmpty()) {
                        if (arr[k].contains(sub)) {
                            vvalid = false;
                            break;
                        }
                    }
                    if (vvalid) {
                        valid = true;
                        if (ret[i] == null || sub.length() < ret[i].length() || sub.compareTo(ret[i]) < 0) {
                            ret[i] = sub;
                        }
                    }
                }
                if (valid) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if (ret[i] == null) ret[i] = "";
        }
        return ret;
    }
}
