import java.io.*;
import java.lang.*;
import java.util.*;

// 1936. Add Minimum Number of Rungs

public class P1936 {
    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int ret = 0;
        int pre = 0;
        for (int num : rungs) {
            int diff = num - pre;
            if (diff > dist) {
                ret += (diff - 1) / dist;
            }
            pre = num;
        }
        return ret;
    }
}
