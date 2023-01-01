import java.io.*;
import java.lang.*;
import java.util.*;

// 1749. Maximum Absolute Sum of Any Subarray
public class P1749 {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int curPos = 0, curNeg = 0;
        for (int i = 0; i < n; i++) {
            curPos = Math.max(0, curPos + nums[i]);
            curNeg = Math.min(0, curNeg + nums[i]);
            ret = Math.max(ret, Math.max(curPos, -curNeg));
        }
        return ret;
    }
}
