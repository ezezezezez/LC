import java.util.ArrayList;
import java.util.List;

// 3233. Find the Count of Numbers Which Are Not Special
public class P3233 {
    public int nonSpecialCount(int l, int r) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(r); i++) {
            boolean flag = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) primes.add(i);
        }
        int cnt = 0;
        for (int prime : primes) {
            int prod = prime * prime;
            if (prod >= l && prod <= r) cnt++;
        }
        return r - l + 1 - cnt;
    }
}
