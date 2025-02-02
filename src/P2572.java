import java.util.ArrayList;
import java.util.List;

public class P2572 {
    int M = (int)(1e9 + 7);
    int[] squares = new int[] {4, 9, 16, 25};
    int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    public int squareFreeSubsets(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        int oneCnt = 0;
        for (int num : nums) {
            if (num == 1) {
                oneCnt++;
                continue;
            }
            boolean flag = true;
            for (int square : squares) {
                if (num % square == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(num);
            }
        }
        List<Integer> list2 = new ArrayList<>();
        for (int num : list) {
            list2.add(encode(num));
        }
        int[] dp = new int[1 << primes.length];
        dp[0] = 1;
        int mask = (1 << primes.length) - 1;
        int ret = 0;
        for (int num : list2) {
            for (int j = 0; j < (1 << primes.length); j++) {
                if ((j & num) > 0) continue;
                dp[j | num] = (dp[j | num] + dp[j]) % M;
            }
        }
        for (int j = 1; j < (1 << primes.length); j++) {
            ret = (ret + dp[j]) % M;
        }
        ret = (int)(ret * pow(2, oneCnt) % M);
        int oneSubSet = (int)((pow(2, oneCnt) - 1 + M) % M);
        ret = (int)((ret + oneSubSet) % M);
        return ret;
    }

    long pow(long x, long y) {
        long ret = 1;
        long cur = x;
        while (y != 0) {
            if ((y & 1) != 0) {
                ret = ret * cur % M;
            }
            cur = cur * cur % M;
            y >>= 1;
        }
        return ret;
    }

    int encode(int num) {
        int ret = 0;
        for (int i = 0; i < primes.length; i++) {
            int prime = primes[i];
            if (num % prime == 0) {
                ret |= 1 << i;
            }
        }
        return ret;
    }
}
