import java.io.*;
import java.lang.*;
import java.util.*;

// 2195. Append K Integers With Minimal Sum

public class P2195 {
    public long minimalKSum(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        long sum = 0;
        long pre = 0;
        for (int i = 0; i < n; i++) {
            long diff = nums[i] - pre;
            if (diff == 0) continue;
            if (diff > 1) {
                if (diff - 1 >= k) {
                    long canAdd = k, s = pre + 1;
                    sum += (s + s + canAdd - 1) * canAdd / 2;
                    k -= canAdd;
                    break;
                } else {
                    long canAdd = diff - 1, s = pre + 1;
                    sum += (s + s + canAdd - 1) * canAdd / 2;
                    k -= canAdd;
                }
            }
            // System.out.println(sum);
            pre = nums[i];
        }
        if (k > 0) {
            long canAdd = k, s = pre + 1;
            sum += (s + s + canAdd - 1) * canAdd / 2;
        }
        return sum;
    }
}
