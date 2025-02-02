import java.util.*;

public class P2587 {
    public int maxScore(int[] nums) {
        int n = nums.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];
        Arrays.sort(arr, (a, b) -> {
            if (a < 0 && b < 0) return Integer.compare(b, a);
            if (a > 0) return -1;
            if (b > 0) return 1;
            return Integer.compare(b, a);
        });
        long sum = 0;
        int ret = 0;
        for (int num : arr) {
            sum += num;
            if (sum > 0) ret++;
        }
        return ret;
    }
}
