import java.math.BigInteger;
import java.util.*;

// 3533. Concatenated Divisibility
public class P3533 {
    int[] nums;
    int k;
    BigInteger K;
    int n;
    List<Integer> ret;
    Map<Integer, Integer> num2Len = new HashMap<>();
    BigInteger TEN = BigInteger.valueOf(10L);
    Boolean[][] memo;
    public int[] concatenatedDivisibility(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.K = BigInteger.valueOf(k);
        this.n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            num2Len.put(num, String.valueOf(num).length());
        }
        memo = new Boolean[1 << n][k];
        dfs((1 << n) - 1, 0, new ArrayList<>());
        if (ret == null) return new int[0];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = ret.get(i);
        return res;
    }

    boolean dfs(int mask, int pre, List<Integer> list) {
        if (mask == 0) {
            if (pre != 0) return false;
            StringBuilder sb = new StringBuilder();
            for (int v : list) sb.append(v);
            BigInteger bi = new BigInteger(sb.toString());
            ret = list;
            return true;
        }
        if (memo[mask][pre] != null) return memo[mask][pre];
        boolean res = false;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0) {
                list.add(nums[i]);
                int len = getLen(mask ^ (1 << i));
                int rem = TEN.pow(len).multiply(BigInteger.valueOf(nums[i])).mod(K).intValue();
                if (dfs(mask ^ (1 << i), (pre + rem) % k, list)) {
                    res = true;
                    break;
                }
                list.remove(list.size() - 1);
            }
        }
        memo[mask][pre] = res;
        return res;
    }

    int getLen(int mask) {
        int len = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0) {
                int num = nums[i];
                len += num2Len.get(num);
            }
        }
        return len;
    }
}
