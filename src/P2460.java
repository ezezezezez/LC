import java.io.*;
import java.lang.*;
import java.util.*;

// 2460. Apply Operations to an Array

public class P2460 {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            }
        }
        int[] ret = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) ret[idx++] = nums[i];
        }
        return ret;
    }
}
