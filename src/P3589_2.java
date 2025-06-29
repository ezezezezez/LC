// 3589. Count Prime-Gap Balanced Subarrays

import java.util.ArrayDeque;
import java.util.Deque;

public class P3589_2 {
    private static final int MX = 50_001;
    private static final boolean[] notp = new boolean[MX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        notp[1] = true;
        for (int i = 2; i * i < MX; i++) {
            if (notp[i]) {
                continue;
            }
            for (int j = i * i; j < MX; j += i) {
                notp[j] = true;
            }
        }
    }

    public int primeSubarray(int[] nums, int k) {
        init();

        int n = nums.length;
        int ret = 0;
        Deque<Integer> mxQ = new ArrayDeque<>(), mnQ = new ArrayDeque<>();
        int last = -1;
        int j = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (!notp[nums[i]]) {
                while (!mxQ.isEmpty() && mxQ.peekLast() < nums[i]) {
                    mxQ.pollLast();
                }
                while (!mnQ.isEmpty() && mnQ.peekLast() > nums[i]) {
                    mnQ.pollLast();
                }
                mxQ.offerLast(nums[i]);
                mnQ.offerLast(nums[i]);
                while (mxQ.peekFirst() - mnQ.peekFirst() > k) {
                    if (nums[j] == mxQ.peekFirst()) mxQ.pollFirst();
                    if (nums[j] == mnQ.peekFirst()) mnQ.pollFirst();
                    j++;
                }
            }
            // System.out.println(j);
            if (!notp[nums[i]]) {
                dp[i + 1] = last - j + 1;
                last = i;
            } else {
                dp[i + 1] = dp[i];
            }
        }

        for (int v : dp) ret += v;

        return ret;
    }
}
