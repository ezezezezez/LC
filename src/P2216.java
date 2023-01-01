import java.io.*;
import java.lang.*;
import java.util.*;

// 2216. Minimum Deletions to Make Array Beautiful

public class P2216 {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int pre = nums[0];
        int del = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == pre) {
                ret++;
                del++;
            } else {
                pre = i + 1 < n ? nums[i + 1] : -1;
                i++;
            }
        }
        return (n - del) % 2 == 0 ? ret : (ret + 1);
    }
}
