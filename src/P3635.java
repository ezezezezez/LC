// 3635. Earliest Finish Time for Land and Water Rides II

import java.util.Arrays;

public class P3635 {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ret = Integer.MAX_VALUE;
        int n = landStartTime.length, m = waterStartTime.length;
        int[][] lands = new int[n][3], waters = new int[m][3];
        for (int i = 0; i < n; i++) {
            lands[i][0] = landStartTime[i];
            lands[i][1] = landDuration[i];
            lands[i][2] = lands[i][0] + lands[i][1];
        }
        for (int i = 0; i < m; i++) {
            waters[i][0] = waterStartTime[i];
            waters[i][1] = waterDuration[i];
            waters[i][2] = waters[i][0] + waters[i][1];
        }
        Arrays.sort(lands, (a, b) -> Integer.compare(a[2], b[2]));
        Arrays.sort(waters, (a, b) -> Integer.compare(a[2], b[2]));
        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, Math.max(waters[0][2], lands[i][0]) + lands[i][1]);
        }
        for (int i = 0; i < m; i++) {
            ret = Math.min(ret, Math.max(lands[0][2], waters[i][0]) + waters[i][1]);
        }
        return ret;
    }
}
