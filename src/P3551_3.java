import java.util.Arrays;

// 3551. Minimum Swaps to Sort by Digit Sum
public class P3551_3 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            arr[i][0] = i;
            arr[i][1] = nums[i];
            arr[i][2] = sum;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            return Integer.compare(a[1], b[1]);
        });
        int ret = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            int cur = i;
            int cnt = 0;
            while (!vis[cur]) {
                vis[cur] = true;
                cnt++;
                cur = arr[cur][0];
            }
            ret += cnt - 1;
        }
        return ret;
    }
}
