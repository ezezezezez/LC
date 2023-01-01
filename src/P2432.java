import java.io.*;
import java.lang.*;
import java.util.*;

// 2432. The Employee That Worked on the Longest Task

public class P2432 {
    public int hardestWorker(int n, int[][] logs) {
        int m = logs.length;
        int pre = 0;
        int ret = 0, mx = 0;
        for (int[] log : logs) {
            int id = log[0], time = log[1];
            if (time - pre > mx) {
                mx = time - pre;
                ret = id;
            } else if (time - pre == mx) {
                ret = Math.min(ret, id);
            }
            pre = time;
        }
        return ret;
    }
}
