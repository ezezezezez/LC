import java.util.ArrayList;
import java.util.List;

// 3076. Shortest Uncommon Substring in an Array
public class P3076 {
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
                        if (!search(arr[k], sub).isEmpty()) {
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

    List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == pattern.length()) {
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            int j = maxMatchLengths[i - 1];
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = maxMatchLengths[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }
            maxMatchLengths[i] = j;
        }
        return maxMatchLengths;
    }
}
