import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 3295. Report Spam Message
public class P3295 {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> set = new HashSet<>();
        for (String w : bannedWords) set.add(w);
        int ret = 0;
        for (String msg : message) {
            if (set.contains(msg)) {
                ret++;
                if (ret >= 2) return true;
            }
        }
        return false;
    }
}
