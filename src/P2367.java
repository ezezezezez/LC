import java.io.*;
import java.lang.*;
import java.util.*;

// 2367. Number of Arithmetic Triplets

public class P2367 {
    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[k] - nums[j] == diff && nums[j] - nums[i] == diff) ret++;
                }
            }
        }
        return ret;
    }
}
