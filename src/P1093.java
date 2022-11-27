import java.util.*;
import java.io.*;
import java.lang.*;

// 1093. Statistics from a Large Sample

public class P1093 {
    public double[] sampleStats(int[] count) {
        int n = count.length;
        double[] ret = new double[5];
        ret[0] = 255; ret[1] = 0;
        double sum = 0;
        long tot = 0;
        double mx = 0, mxCnt = count[0];
        for (int i = 0; i < n; i++) {
            if (count[i] > 0) {
                ret[0] = Math.min(ret[0], i);
                ret[1] = Math.max(ret[1], i);
                sum += 1L * count[i] * i;
                tot += count[i];
                if (count[i] > mxCnt) {
                    mxCnt = count[i];
                    mx = i;
                }
            }
        }
        ret[2] = sum / tot;

        long cur = 0;
        for (int i = 0; i < n; i++) {
            cur += count[i];
            if (cur >= tot / 2.0) {
                if (tot % 2 == 1) {
                    // System.out.println(i);
                    ret[3] = i;
                } else {
                    if (cur > tot / 2) {
                        ret[3] = i;
                    } else {
                        int a = i, b = i + 1;
                        while (count[b] == 0) b++;
                        ret[3] = (a + b) / 2.0;
                    }
                }
                break;
            }
        }

        ret[4] = mx;

        return ret;
    }
}
