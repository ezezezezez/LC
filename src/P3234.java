import java.util.ArrayList;
import java.util.List;

// 3234. Count the Number of Substrings With Dominant Ones
public class P3234 {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int ret = 0;
        List<Integer> zeros = new ArrayList<>();
        zeros.add(-1);
        int head = 0, tail = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int curZero = 0;
            if (c == '0') {
                int endIdx = tail;
                int last = i;
                while (endIdx >= head) {
                    int nxt = zeros.get(endIdx);
                    curZero++;
                    int need = curZero * curZero;
                    if (need >= i + 1) break;
                    int curOne = i - nxt - curZero;
                    if (curOne >= need) {
                        ret += Math.min(i - curZero - need + 1, last) - nxt;
                    }
                    endIdx--;
                    last = nxt;
                }
                zeros.add(i);
                tail++;
                int count = head == 0 ? tail - head : tail - head + 1;
                if (count * count > n) head++;
            } else {
                int endIdx = tail;
                int nxt = zeros.get(endIdx);
                ret += i - nxt;
                endIdx--;
                if (endIdx != -1) curZero++;
                int last = nxt;
                while (endIdx >= head) {
                    nxt = zeros.get(endIdx);
                    int need = curZero * curZero;
                    if (need >= i + 1) break;
                    int curOne = i - nxt - curZero;
                    if (curOne >= need) {
                        ret += Math.min(i - curZero - need + 1, last) - nxt;
                    }
                    endIdx--;
                    curZero++;
                    last = nxt;
                }
            }
            // System.out.println(ret);
            // System.out.println(zeros);
            // System.out.println(head + " " + tail);
            // System.out.println();
        }
        return ret;
    }
}
