import java.util.*;

// 2761. Prime Pairs With Target Sum
public class P2761 {
    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> set = new HashSet<>(eratosthenes(n));
        for (int i = 2; i + i <= n; i++) {
            if (!set.contains(i)) continue;
            if (!set.contains(n - i)) continue;
            ret.add(List.of(i, n - i));
        }
        return ret;
    }

    List<Integer> eratosthenes(int n) {
        int[] q = new int[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (q[i] == 1) continue;
            int j = i * 2;
            while (j <= n) {
                q[j] = 1;
                j +=i ;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (q[i] == 0)
                primes.add(i);
        }
        return primes;
    }
}
