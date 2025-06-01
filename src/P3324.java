// 3324. Find the Sequence of Strings Appeared on the Screen

import java.util.ArrayList;
import java.util.List;

public class P3324 {
    public List<String> stringSequence(String target) {
        int n = target.length();
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = 'a';
            sb.append(c);
            ret.add(sb.toString());
            while (c != target.charAt(i)) {
                c = (char) (c + 1);
                sb.setCharAt(sb.length() - 1, c);
                ret.add(sb.toString());
            }
        }
        return ret;
    }
}
