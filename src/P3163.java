import java.util.HashMap;
import java.util.Map;

// 3163. String Compression III
public class P3163 {
    public String compressedString(String word) {
        int n = word.length();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        char pre = word.charAt(0);
        int cnt = 1;
        while (i < n) {
            char cur = word.charAt(i);
            if (pre != cur || cnt == 9) {
                sb.append(cnt).append(pre);
                cnt = 1;
            } else {
                cnt++;
            }
            pre = cur;
            i++;
        }
        sb.append(cnt).append(pre);
        return sb.toString();
    }
}
