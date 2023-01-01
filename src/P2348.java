import java.io.*;
import java.lang.*;
import java.util.*;

// 2348. Number of Zero-Filled Subarrays

public class P2348 {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] != 0) continue;
            j = i;
            while (i < n && nums[i] == 0) i++;
            long c = i - j;
            ret += (c + 1) * c / 2;
            i--;
        }
        return ret;
    }
}
