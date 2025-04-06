import java.util.*;

// 3048. Earliest Second to Mark Indices I
public class P3048_2 {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length;
        int lo = 0, hi = m - 1, t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int[] last = new int[n];
            if (check(nums, changeIndices, mid, last)) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t == -1 ? -1 : (t + 1);
    }

    boolean check(int[] nums, int[] changeIndices, int mid, int[] last) {
        Arrays.fill(last, -1);
        for (int i = 0; i <= mid; i++) {
            last[changeIndices[i] - 1] = i;
        }
        for (int i = 0; i < last.length; i++) {
            if (last[i] < 0) return false;
        }
        int cnt = 0;
        for (int i = 0; i <= mid; i++) {
            if (i == last[changeIndices[i] - 1]) {
                if (nums[changeIndices[i] - 1] > cnt) return false;
                cnt -= nums[changeIndices[i] - 1];
            } else {
                cnt++;
            }
        }
        return true;
    }
}
