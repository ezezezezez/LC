import java.util.Arrays;

// 3133. Minimum Array End
public class P3133 {
    public long minEnd(int n, int x) {
        long ret = x + n - 1;
        long t = n - 1, prod = 1, sum = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & x) > 0) {
                sum += (1 << i);
            } else {
                if (i > 0 && ((1 << (i - 1)) & x) > 0) {
                    // System.out.println(sum + " " + prod);
                    ret += t / prod * sum;
                    sum = 0;
                }
                prod *= 2;
            }
        }
        return ret;
    }
}
