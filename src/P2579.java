import java.util.Arrays;
import java.util.Comparator;

public class P2579 {
    public long coloredCells(int n) {
        if (n == 1) return 1;
        long ret = 1;
        for (int i = 2; i <= n; i++) {
            ret = ret + 4L * (i - 1);
        }
        Comparator.comparing((int[] a) -> a[0]).thenComparing((int[] a) -> a[1]);
        return ret;
    }
}
