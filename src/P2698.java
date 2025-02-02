import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2698. Find the Punishment Number of an Integer
public class P2698 {
    public int punishmentNumber(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid(i * i, 0, i)) {
                // System.out.println(i);
                ret += i * i;
            }
        }
        return ret;
    }

    boolean isValid(int v, int pre, int num) {
        if (v == 0 && pre == num) return true;
        int d = 1;
        while (d <= v) d *= 10;
        d /= 10;
        while (d > 0) {
            int t = v / d;
            // if (d == 1 && pre + t == num) return true;
            if (t > num) return false;
            if (isValid(v % d, pre + t, num)) return true;
            d /= 10;
        }
        return false;
    }
}
