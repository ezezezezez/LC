import java.util.ArrayList;
import java.util.List;

// 3211. Generate Binary Strings Without Adjacent Zeros
public class P3211 {
    int n;
    public List<String> validStrings(int n) {
        this.n = n;
        List<String> ret = new ArrayList<>();
        dfs(0, -1, new StringBuilder(), ret);
        return ret;
    }

    void dfs(int idx, int last, StringBuilder cur, List<String> ret) {
        if (idx == n) {
            ret.add(cur.toString());
            return;
        }
        if (last == -1 || last == 1) {
            cur.append('0');
            dfs(idx + 1, 0, cur, ret);
            cur.deleteCharAt(cur.length() - 1);
        }
        cur.append('1');
        dfs(idx + 1, 1, cur, ret);
        cur.deleteCharAt(cur.length() - 1);
    }
}
