import java.util.*;

// 2749. Minimum Operations to Make the Integer Zero
public class P2749 {
    public int makeTheIntegerZero(int num1, int num2) {
        if (num1 == num2) return -1;
        if (num1 > num2) {
            int ret = 1;
            long v = num1 - num2;
            do {
                // System.out.println(v + " " + ret);
                int cnt = 0;
                for (int i = 0; i < 64; i++) {
                    if ((v & (1L << i)) > 0) {
                        cnt++;
                    }
                }
                if (ret > v && v - num2 <= 0) return -1;
                if (cnt <= ret) return ret;
                v -= num2;
                ret++;
            } while (v > 0);
        }
        return -1;
    }
}
