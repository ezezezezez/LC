import java.util.*;

// 3315. Construct the Minimum Bitwise Array II
public class P3315 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num % 2 == 0) {
                ret[i] = -1;
            } else {
                int j = 0;
                while ((num & (1 << j)) > 0) j++;
                ret[i] = num - (1 << (j - 1));
            }
        }
        return ret;
    }
}
