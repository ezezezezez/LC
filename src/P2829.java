import java.util.List;

// 2829. Determine the Minimum Sum of a k-avoiding Array
public class P2829 {
    public int minimumSum(int n, int k) {
        int ret = 0;
        int i = 1;
        for (; i <= k / 2 && i <= n; i++) ret += i;
        if (i > n) return ret;
        int j = i;
        while (i <= n) {
            ret += k + i - j;
            i++;
        }
        return ret;
    }
}
