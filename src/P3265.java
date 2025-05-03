// 3265. Count Almost Equal Pairs I
public class P3265 {
    public int countPairs(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int[] d = new int[10];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int u = nums[i], v = nums[j];
                if (u == v) { ret++; continue; }
                if (u < v) {
                    int t = u;
                    u = v;
                    v = t;
                }
                int idx = 0;
                while (u > 0) {
                    d[idx] = u % 10;
                    u /= 10;
                    idx++;
                }
                outer:
                for (int p = 0; p < idx; p++) {
                    for (int q = p + 1; q < idx; q++) {
                        int t = d[p];
                        d[p] = d[q];
                        d[q] = t;
                        int value = 0;
                        for (int s = idx - 1; s >= 0; s--) {
                            value = 10 * value + d[s];
                        }
                        if (value == v) { ret++; break outer; }
                        t = d[p];
                        d[p] = d[q];
                        d[q] = t;
                    }
                }
            }
        }
        return ret;
    }
}
