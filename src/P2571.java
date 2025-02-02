import java.util.HashSet;
import java.util.Set;

public class P2571 {
    public int minOperations(int n) {
        int cnt = 0;
        while (n > 0) {
            String s = Integer.toBinaryString(n);
            // System.out.println(s);
            int len = s.length();
            for (int i = len - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') continue;
                int j = i;
                while (j >= 0 && s.charAt(j) == '1') j--;
                if (i - j == 1) {
                    n -= 1 << (len - 1 - i);
                } else {
                    for (int k = i; k > j; k--) n -= 1 << (len - 1 - k);
                    n += 1 << (len - 1 - j);
                }
                i = j + 1;
                cnt++;
                break;
            }
        }
        return cnt;
    }
}
