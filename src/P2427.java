import java.io.*;
import java.lang.*;
import java.util.*;

// 2427. Number of Common Factors

public class P2427 {
    public int commonFactors(int a, int b) {
        int ret = 0;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) ret++;
        }
        return ret;
    }
}
