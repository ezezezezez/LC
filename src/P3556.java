import java.util.*;

// 3556. Sum of Largest Prime Substrings
public class P3556 {
    public long sumOfLargestPrimes(String s) {
        int ret = 0;
        Set<Long> set = new HashSet<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                long v = 0;
                for (int k = 0; k < sub.length(); k++) {
                    v = 10 * v + sub.charAt(k) - '0';
                }
                if (isPrime(v)) set.add(v);
            }
        }
        List<Long> list = new ArrayList<>(set);
        Collections.sort(list);
        if (list.size() >= 3) return list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3);
        for (long v : list) ret += v;
        return ret;
    }

    private boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
