import java.util.ArrayList;
import java.util.List;

// 3309. Maximum Possible Number by Binary Concatenation
public class P3309 {
    public int maxGoodNumber(int[] nums) {
        int n = nums.length;
        int ret = 0;
        List<int[]> orders = new ArrayList<>();
        orders.add(new int[] {0, 1, 2});
        orders.add(new int[] {0, 2, 1});
        orders.add(new int[] {1, 0, 2});
        orders.add(new int[] {1, 2, 0});
        orders.add(new int[] {2, 0, 1});
        orders.add(new int[] {2, 1, 0});
        for (int[] order : orders) {
            StringBuilder sb = new StringBuilder();
            for (int idx : order) sb.append(Integer.toBinaryString(nums[idx]));
            ret = Math.max(ret, Integer.parseInt(sb.toString(), 2));
        }
        return ret;
    }
}
