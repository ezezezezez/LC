import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2961. Double Modular Exponentiation
public class P2961 {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        int n = variables.length;
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] var = variables[i];
            int a = var[0], b = var[1], c = var[2], m = var[3];
            long v = pow(pow(a, b, 10), c, m);
            if (v == target) ret.add(i);
        }
        return ret;
    }

    long pow(long x, long y, long mod) {
        long ret = 1;
        long cur = x;
        while (y != 0) {
            if ((y & 1) != 0) {
                ret = ret * cur % mod;
            }
            cur = cur * cur % mod;
            y >>= 1;
        }
        return ret;
    }
}
