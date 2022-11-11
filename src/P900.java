import java.util.*;
import java.io.*;
import java.lang.*;

// 900. RLE Iterator
public class P900 {
    int[] encoding;
    int len;
    int curnum = -1, curcnt = 0, curpos = 0;
    public P900(int[] encoding) {
        this.encoding = encoding;
        this.len = encoding.length;
        curnum = encoding[1];
        curcnt = encoding[0];
    }

    public int next(int n) {
        int min = Math.min(curcnt, n);
        // System.out.println(min + " " + curcnt + " " + n);
        if (min == n) {
            curcnt -= min;
            return curnum;
        } else if (min == 0) {
            curpos += 2;
            if (curpos >= len) return -1;
            curnum = encoding[curpos + 1];
            curcnt = encoding[curpos];
            return next(n);
        } else {
            int d = n - curcnt;
            curpos += 2;
            if (curpos >= len) {
                curcnt = 0;
                return -1;
            }
            curnum = encoding[curpos + 1];
            curcnt = encoding[curpos];
            return next(d);
        }
    }
}
