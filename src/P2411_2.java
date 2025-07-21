// 2411. Smallest Subarrays With Maximum Bitwise OR

public class P2411_2 {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];

        int[] or = new int[31];
        int[] right = new int[31];
        int idx = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int p = 0; p < idx; p++) {
                or[p] |= nums[i];
            }
            or[idx] = nums[i];
            right[idx] = i;
            idx++;

            int q = 0;
            int last = -1;
            for (int p = 0; p < idx; p++) {
                if (last != or[p]) {
                    last = or[q] = or[p];
                    right[q] = right[p];
                    q++;
                }
            }

            idx = q;
            int r = idx > 1 ? right[1] + 1 : i;
            ret[i] = r - i + 1;
        }

        return ret;
    }
}
