import java.util.*;

// 2708. Maximum Strength of a Group
public class P2708 {
    long mx = Long.MIN_VALUE;
    public long maxStrength(int[] nums) {
        int n = nums.length;
        dfs(nums, 0, new ArrayList<>());
        return mx;
    }

    void dfs(int[] nums, int idx, List<Integer> picked) {
        int n = nums.length;
        if (idx == n) {
            if (picked.isEmpty()) return;
            long t = 1;
            for (int v : picked) t *= v;
            mx = Math.max(mx, t);
            return;
        }
        picked.add(nums[idx]);
        dfs(nums, idx + 1, picked);
        picked.remove(picked.size() - 1);
        dfs(nums, idx + 1, picked);
    }
}
