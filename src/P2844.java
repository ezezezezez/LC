import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2844. Minimum Operations to Make a Special Number
public class P2844 {
    public int minimumOperations(String num) {
        int n = num.length();
        int cnt1 = Integer.MAX_VALUE, cnt2 = Integer.MAX_VALUE;
        outer1:
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c == '0') {
                for (int j = i - 1; j >= 0; j--) {
                    char c2 = num.charAt(j);
                    if (c2 == '0' || c2 == '5') {
                        cnt1 = n - (j + 2);
                        break outer1;
                    }
                }
            }
        }
        outer2:
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c == '5') {
                for (int j = i - 1; j >= 0; j--) {
                    char c2 = num.charAt(j);
                    if (c2 == '2' || c2 == '7') {
                        cnt2 = n - (j + 2);
                        break outer2;
                    }
                }
            }
        }
        int cnt = Math.min(cnt1, cnt2);
        if (cnt != Integer.MAX_VALUE) return cnt;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') return n - 1;
        }
        return n;
    }
}
