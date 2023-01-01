import java.io.*;
import java.lang.*;
import java.util.*;

// 1386. Cinema Seat Allocation

public class P1390 {
    public int sumFourDivisors(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            int cnt = 0;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    int d = num / i;
                    cnt += i == d ? 1 : 2;
                    if (cnt > 4) break;
                }
            }
            if (cnt > 4) continue;
            if (cnt == 4) {
                for (int i = 1; i * i <= num; i++) {
                    if (num % i == 0) {
                        int d = num / i;
                        ret += i + d;
                    }
                }
            }
        }

        return ret;
    }
}
