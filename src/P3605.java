// 3605. Minimum Stability Factor of Array

public class P3605 {
    public int minStable(int[] nums, int maxC) {
        int n = nums.length;
        int lo = 0, hi = n, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int[] gcdArr = new int[32];
            int[] left = new int[32];
            int idx = 0;
            boolean valid = true;
            int cnt = maxC;
            for (int i = 0; i < n; i++) {
                for (int p = 0; p < idx; p++) {
                    gcdArr[p] = gcd(gcdArr[p], nums[i]);
                }
                gcdArr[idx] = nums[i];
                left[idx] = i;
                idx++;

                int q = 0;
                int last = -1;
                for (int p = 0; p < idx; p++) {
                    if (gcdArr[p] >= 2 && last != gcdArr[p]) {
                        last = gcdArr[q] = gcdArr[p];
                        left[q] = left[p];
                        q++;
                    }
                }

                idx = q;
                // System.out.println(mid + " " + idx + " " + (i - left[0] + 1) + " " + mid);
                if (idx > 0 && i - left[0] + 1 > mid) {
                    if (cnt == 0) {
                        valid = false;
                    } else {
                        cnt--;
                        idx = 0;
                    }
                }
            }

            // System.out.println(mid + " " + valid);

            if (valid) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return t == -1 ? 0 : t;
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
