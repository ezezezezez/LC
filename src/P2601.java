import java.util.*;

public class P2601 {
    public boolean primeSubOperation(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; i < 1000; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) primes.add(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) continue;
            boolean flag = false;
            for (int j = nums[i + 1] - 1; j >= 1; j--) {
                int diff = nums[i] - j;
                if (primes.contains(diff)) {
                    nums[i] = j;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
