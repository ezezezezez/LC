import java.util.ArrayList;
import java.util.List;

// 2929. Distribute Candies Among Children II
public class P2930 {
    long mod = (long) (1e9 + 7);
    public int stringCount(int n) {
        if (n < 4) return 0;
        long ret = 1;
        long a = 1, b = 1, c = 1;
        for (int i = 0; i < n; i++) {
            ret = ret * 26 % mod;
            a = a * 25 % mod;
            b = b * 24 % mod;
            c = c * 23 % mod;
        }
        long rev = add(minus(3 * a, 3 * b), c);
        long d = n, e = n, f = n;
        for (int i = 0; i < n - 1; i++) {
            d = d * 25 % mod;
            e = e * 24 % mod;
            f = f * 23 % mod;
        }
        long rev2 = minus(d, minus(2 * e, f));
        ret = minus(ret, add(rev, rev2));
        List<Integer> integers = new ArrayList<>();
        return (int) ret;
    }

    long add(long a, long b) {
        return (a + b) % mod;
    }

    long minus(long a, long b) {
        return (a - b + mod) % mod;
    }
}
