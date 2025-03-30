import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 2904. Shortest and Lexicographically Smallest Beautiful String
public class P2904 {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        List<String> list = new ArrayList<>();
        int cnt = 0;
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt += c == '1' ? 1 : 0;
            while (cnt > k) {
                char t = s.charAt(j);
                if (t == '1') cnt--;
                j++;
            }
            if (cnt == k) {
                while (s.charAt(j) == '0') j++;
                if (c == '1') {
                    list.add(s.substring(j, i + 1));
                }
            }
        }
        int mn = Integer.MAX_VALUE;
        for (String str : list) mn = Math.min(mn, str.length());
        String ret = null;
        for (String str : list) {
            if (str.length() == mn && (ret == null || str.compareTo(ret) < 0)) {
                ret = str;
            }
        }
        return ret == null ? "" : ret;
    }
}
