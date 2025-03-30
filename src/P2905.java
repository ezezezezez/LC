import java.util.ArrayList;
import java.util.List;

// 2905. Find Indices With Index and Value Difference II
public class P2905 {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int mx = -1, mn = Integer.MAX_VALUE;
        int mxIdx = -1, mnIdx = -1;
        for (int i = indexDifference; i < n; i++) {
            int pre = nums[i - indexDifference];
            if (pre > mx) {
                mx = pre;
                mxIdx = i - indexDifference;
            }
            if (pre < mn) {
                mn = pre;
                mnIdx = i - indexDifference;
            }
            if (Math.abs(mx - nums[i]) >= valueDifference) {
                return new int[] {mxIdx, i};
            }
            if (Math.abs(nums[i] - mn) >= valueDifference) {
                return new int[] {mnIdx, i};
            }
        }
        return new int[] {-1, -1};
    }
}
