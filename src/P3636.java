import java.util.*;

// 3636. Threshold Majority Queries

public class P3636 {
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] ret = new int[m];
        int blockSize = (int) Math.ceil(n / Math.sqrt(2 * m));

        List<int[]> qs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int l = q[0], r = q[1], thold = q[2];
            if (r - l + 1 > blockSize) {
                qs.add(new int[] {l / blockSize, l, r, i});
                continue;
            }
            for (int j = l; j <= r; j++) {
                add(nums[j]);
            }
            ret[i] = maxCnt >= thold ? minVal : -1;
            cnt.clear();
            maxCnt = minVal = 0;
        }

        qs.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[2], b[2]);
        });
        // System.out.println(maxCnt + " " + minVal + " " + blockSize + " " + qs);

        int r = 0;
        for (int i = 0; i < qs.size(); i++) {
            int[] q = qs.get(i);
            int qid = q[3];
            int r0 = (q[0] + 1) * blockSize;
            if (i == 0 || q[0] != qs.get(i - 1)[0]) {
                r = r0;
                cnt.clear();
                maxCnt = minVal = 0;
            }

            for (; r <= q[2]; r++) {
                add(nums[r]);
            }

            int tmpMaxCnt = maxCnt, tmpMinVal = minVal;
            for (int j = r0 - 1; j >= q[1]; j--) {
                add(nums[j]);
            }

            int thold = queries[qid][2];
            // System.out.println(qid + " " + thold + " " + maxCnt + " ql: " + q[1] + " qr: " + q[2]);
            ret[qid] = maxCnt >= thold ? minVal : -1;

            for (int j = r0 - 1; j >= q[1]; j--) {
                cnt.merge(nums[j], -1, Integer::sum);
            }
            maxCnt = tmpMaxCnt;
            minVal = tmpMinVal;
        }

        return ret;
    }

    Map<Integer, Integer> cnt = new HashMap<>();
    int maxCnt = 0;
    int minVal = 0;
    void add(int x) {
        cnt.merge(x, 1, Integer::sum);
        if (cnt.get(x) > maxCnt) {
            maxCnt = cnt.get(x);
            minVal = x;
        } else if (cnt.get(x) == maxCnt) {
            minVal = Math.min(minVal, x);
        }
    }
}
