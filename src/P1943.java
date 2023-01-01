import java.io.*;
import java.lang.*;
import java.util.*;

// 1943. Describe the Painting

public class P1943 {
    public List<List<Long>> splitPainting(int[][] segments) {
        int n = segments.length;
        List<List<Long>> ret = new ArrayList<>();
        long[][] events = new long[2 * n][3]; // out 0, in 1
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int[] segment = segments[i];
            events[idx++] = new long[]{segment[0], 1, segment[2]};
            events[idx++] = new long[]{segment[1], 0, segment[2]};
        }
        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        // for (long[] event : events) {
        //     System.out.println(Arrays.toString(event));
        // }
        long sum = events[0][2], pre = events[0][0];
        for (int i = 1; i < events.length; i++) {
            long time = events[i][0], type = events[i][1], val = events[i][2];
            if (pre != time) {
                if (sum > 0) ret.add(List.of(pre, time, sum));
                pre = time;
            }
            if (type == 0) {
                sum -= val;
            } else {
                sum += val;
            }
        }
        return ret;
    }
}
