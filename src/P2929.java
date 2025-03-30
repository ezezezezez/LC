import java.util.ArrayList;
import java.util.List;

// 2929. Distribute Candies Among Children II
public class P2929 {
    public long distributeCandies(int n, int limit) {
        if (3L * limit < n) return 0;
        long ret = 0;
        for (int i = 0; i <= limit && i <= n; i++) {
            int j = n - i;
            if (j > 2L * limit) continue;
            if (j <= limit) {
                ret += j + 1;
            } else {
                j -= limit;
                ret += limit - j + 1;
            }
        }
        return ret;
    }
}
