import java.util.*;
import java.io.*;
import java.lang.*;

// 1024. Video Stitching

public class P1024 {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> Integer.compare(a[0], b[0]));
        int n = clips.length;

        int ret = 0;
        int limit = 0, idx = 0;
        while (idx < n && clips[idx][0] <= limit) {
            int old = limit;
            while (idx < n && clips[idx][0] <= old) {
                limit = Math.max(limit, clips[idx][1]);
                idx++;
            }
            ret++;
            if (limit >= time) return ret;
        }
        return -1;
    }
}
