import java.io.*;
import java.lang.*;
import java.util.*;

// 1894. Find the Student that Will Replace the Chalk

public class P1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        for (int cnt : chalk) sum += cnt;
        k %= sum;
        for (int i = 0; i < n; i++) {
            if (chalk[i] > k) {
                return i;
            }
            k -= chalk[i];
        }
        return -1;
    }
}
