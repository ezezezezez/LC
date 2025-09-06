// 3633. Earliest Finish Time for Land and Water Rides I

public class P3633 {
    public int earliestFinishTime(int[] lst, int[] ld, int[] wst, int[] wd) {
        int n = lst.length, m = wst.length;
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int ls = lst[i], le = ls + ld[i];
            for (int j = 0; j < m; j++) {
                int ws = wst[j], we = ws + wd[j];
                if (we <= ls || le <= ws) {
                    ret = Math.min(ret, Math.max(le, we));
                } else {
                    ret = Math.min(ret, Math.min(le + wd[j], we + ld[i]));
                }
            }
        }

        return ret;
    }
}
