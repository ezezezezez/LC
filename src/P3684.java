// 3684. Maximize Sum of At Most K Distinct Elements

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P3684 {
    public int[] maxKDistinct(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        list.add(nums[n - 1]);
        for (int i = n - 2; i >= 0 && list.size() < k; i--) {
            if (nums[i] != nums[i + 1]) {
                list.add(nums[i]);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ret[i] = list.get(i);
        return ret;
    }
}
