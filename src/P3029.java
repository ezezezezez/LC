import java.util.HashMap;
import java.util.Map;

// 3029. Minimum Time to Revert Word to Initial State I
public class P3029 {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int ret = 1;
        int s = k;
        while (s < n && !word.startsWith(word.substring(s, n))) {
            s += k;
            ret++;
        }
        return ret;
    }
}
