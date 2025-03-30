import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 2981. Find Longest Special Substring That Occurs Thrice I
public class P2981 {
    public int maximumLength(String s) {
        int n = s.length();
        int ret = -1;
        for (int i = 0; i < 26; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append((char) (i + 'a'));
                String sub = sb.toString();
                int idx = s.indexOf(sub);
                int count = 0;
                while (idx != -1) {
                    count++;
                    idx = s.indexOf(sub, idx + 1);
                }
                if (count >= 3) ret = Math.max(ret, sub.length());
            }
        }
        return ret;
    }
}
