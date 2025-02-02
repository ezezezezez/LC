import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 2645. Minimum Additions to Make Valid String
public class P2645 {
    public int addMinimum(String word) {
        int n = word.length();
        int ret = 0;
        char expect = 'a';
        char[] next = new char[256];
        next['a'] = 'b';
        next['b'] = 'c';
        next['c'] = 'a';
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c != expect) {
                ret++;
                i--;
            }
            expect = next[expect];
        }
        if (expect == 'b') ret += 2;
        else if (expect == 'c') ret++;
        return ret;
    }
}
