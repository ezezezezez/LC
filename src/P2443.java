import java.io.*;
import java.lang.*;
import java.util.*;

// 2443. Sum of Number and Its Reverse

public class P2443 {
    public boolean sumOfNumberAndReverse(int num) {
        if (num == 0) return true;
        for (int i = 0; i < num; i++) {
            int t = i;
            int c = 0;
            while (t > 0) {
                int d = t % 10;
                c = 10 * c + d;
                t /= 10;
            }
            if (i + c == num) return true;
        }
        return false;
    }
}
