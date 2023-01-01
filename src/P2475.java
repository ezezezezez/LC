import java.io.*;
import java.lang.*;
import java.util.*;

// 2475. Number of Unequal Triplets in Array

public class P2475 {
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k]) ret++;
                }
            }
        }
        return ret;
    }
}
