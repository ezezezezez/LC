import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2610 {
    public List<List<Integer>> findMatrix(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && nums[j] == nums[i]) j++;
            while (list.size() < j - i) list.add(new ArrayList<>());
            for (int k = i; k < j; k++) list.get(k - i).add(nums[i]);
            i = j - 1;
        }
        return list;
    }
}
