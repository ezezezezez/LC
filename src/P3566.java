// 3566. Partition Array into Two Equal Product Subsets

import java.util.*;

public class P3566 {
    int n;
    int[] nums;
    long target;
    public boolean checkEqualPartitions(int[] nums, long target) {
        this.nums = nums;
        this.target = target;
        this.n = nums.length;
        return dfs(0, new HashSet<>());
    }

    boolean dfs(int idx, Set<Integer> set) {
        if (idx == n) {
            if (set.isEmpty()) return false;
            long prod = 1;
            for (int num : set) {
                prod *= num;
                if (prod > target) return false;
            }
            if (prod == target) {
                long prod2 = 1;
                boolean empty = true;
                for (int num2 : nums) {
                    if (!set.contains(num2)) {
                        prod2 *= num2;
                        empty = false;
                    }
                }
                if (!empty && prod2 == prod) return true;
            }
            return false;
        }

        if (dfs(idx + 1, set)) return true;

        set.add(nums[idx]);
        if (dfs(idx + 1, set)) return true;
        set.remove(nums[idx]);

        return false;
    }
}
