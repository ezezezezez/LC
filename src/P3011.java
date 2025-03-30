import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3011. Find if Array Can Be Sorted
public class P3011 {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int pre = 100;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            int bitCount = Integer.bitCount(nums[i]);
            if (bitCount != pre) {
                list.add(new ArrayList<>());
            }
            list.get(list.size() - 1).add(nums[i]);
            pre = bitCount;
        }
        pre = 0;
        for (List<Integer> l : list) {
            Collections.sort(l);
            for (int num : l) {
                if (num < pre) return false;
                pre = num;
            }
        }
        return true;
    }
}
