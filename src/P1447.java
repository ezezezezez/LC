import java.io.*;
import java.lang.*;
import java.util.*;

// 1447. Simplified Fractions

public class P1447 {
    public List<String> simplifiedFractions(int n) {
        List<String> ret = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    ret.add(j + "/" + i);
                }
            }
        }

        return ret;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
