import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2834. Find the Minimum Possible Sum of a Beautiful Array
public class P2834 {
    public int minimumPossibleSum(int n, int target) {
        long ret = 0, mod = (long) (1e9 + 7);
        if (n > target / 2) {
            ret += (1L + target / 2) * (target / 2) / 2;
            ret %= mod;
            n -= target / 2;
            long first = target, last = first + n - 1;
            ret += (first + last) * n / 2;
        } else {
            ret += (1L + n) * n / 2;
        }
        ret %= mod;
        return (int) ret;
    }
}
