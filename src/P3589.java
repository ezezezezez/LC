// 3589. Count Prime-Gap Balanced Subarrays

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P3589 {
    static int MAXV = 50000;
    static boolean[] isp = new boolean[MAXV + 1];

    static {
        Arrays.fill(isp, true);
        isp[0] = false;
        isp[1] = false;
        for (int i = 2; i * i <= MAXV; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= MAXV; j += i) {
                    isp[j] = false;
                }
            }
        }
    }

    public int primeSubarray(int[] nums, int k) {
        int n = nums.length;
        int ret = 0;
        Deque<Integer> mxQ = new ArrayDeque<>(), mnQ = new ArrayDeque<>();
        int last = -1;
        int j = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (isp[nums[i]]) {
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
            if (isp[nums[i]]) {
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
