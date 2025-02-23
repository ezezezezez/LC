import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2811. Check if it is Possible to Split Array
public class P2811 {
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        if (n <= 2) return true;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) + nums.get(i + 1) >= m) return true;
        }
        return false;
    }
}
