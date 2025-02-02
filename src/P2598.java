import java.util.*;

public class P2598 {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.floorMod(nums[i], value);
        }
        Arrays.sort(arr);
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : arr) {
            Set<Integer> set = map.computeIfAbsent(num, k -> new HashSet<>());
            set.add(num + set.size() * value);
        }
        int ret = 0;
        while (true) {
            int v = ret % value;
            if (!map.containsKey(v)) break;
            if (!map.get(v).contains(ret)) break;
            ret++;
        }
        return ret;
    }
}
