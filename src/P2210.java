import java.io.*;
import java.lang.*;
import java.util.*;

// 2210. Count Hills and Valleys in an Array

public class P2210 {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        int pre = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] != pre) list.add(nums[i]);
            pre = nums[i];
        }
        int ret = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            int left = list.get(i - 1), right = list.get(i + 1);
            int cur = list.get(i);
            if (cur > left && cur > right) ret++;
            if (cur < left && cur < right) ret++;
        }
        return ret;
    }
}
