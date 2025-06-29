import java.util.HashMap;
import java.util.Map;

// 3591. Check if Any Element Has Prime Frequency

public class P3591 {
    public boolean checkPrimeFrequency(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        for (int cnt : map.values()) {
            if (isPrime(cnt)) return true;
        }
        return false;
    }

    boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
