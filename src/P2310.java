import java.io.*;
import java.lang.*;
import java.util.*;

// 2310. Sum of Numbers With Units Digit K

public class P2310 {
    public int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        if (k == 0) return num % 10 == 0 ? 1 : -1;
        int cnt = 1;
        while (num - cnt * k >= 0 && (num - cnt * k) % 10 != 0) {
            cnt++;
        }
        return num - cnt * k < 0 ? -1 : cnt;
    }
}
