// 3635. Earliest Finish Time for Land and Water Rides II

import java.util.Arrays;

public class P3635_2 {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ret = Integer.MAX_VALUE;
        int n = landStartTime.length, m = waterStartTime.length;
        int minWater = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minWater = Math.min(minWater, waterStartTime[i] + waterDuration[i]);
        }
        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, Math.max(minWater, landStartTime[i]) + landDuration[i]);
        }
        int minLand = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minLand = Math.min(minLand, landStartTime[i] + landDuration[i]);
        }
        for (int i = 0; i < m; i++) {
            ret = Math.min(ret, Math.max(minLand, waterStartTime[i]) + waterDuration[i]);
        }
        return ret;
    }
}
