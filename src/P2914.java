import java.util.HashMap;
import java.util.Map;

// 2914. Minimum Number of Changes to Make Binary String Beautiful
public class P2914 {
    public int minChanges(String s) {
        int n = s.length();
        int ret = 0;
        char cur = 'a';
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cur == c) {
                cnt++;
            } else {
                if (cnt % 2 == 1) {
                    ret++;
                    cur = 'a';
                    cnt = 0;
                } else {
                    cur = c;
                    cnt = 1;
                }
            }
        }
        return ret;
    }
}
