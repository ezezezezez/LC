import java.io.*;
import java.lang.*;
import java.util.*;

// 1497. Check If Array Pairs Are Divisible by k

public class P1503 {
    public int getLastMoment(int n, int[] left, int[] right) {
        int ret = 0;
        for (int pos : left) ret = Math.max(ret, pos);
        for (int pos : right) ret = Math.max(ret, n - pos);
        return ret;
    }
}
