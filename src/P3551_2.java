import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3551. Minimum Swaps to Sort by Digit Sum
public class P3551_2 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            int sum = 0;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            arr[i][0] = sum;
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = arr[i][2];
        }
        boolean[] vis = new boolean[n];
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;

            int j = i;
            int sz = 0;
            while (!vis[j]) {
                vis[j] = true;
                j = p[j];
                sz++;
            }

            ret += sz - 1;
        }
        return ret;
    }
}
