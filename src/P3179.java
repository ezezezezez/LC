import java.util.HashMap;
import java.util.Map;

// 3179. Find the N-th Value After K Seconds
public class P3179 {
    public int valueAfterKSeconds(int n, int k) {
        int ret = 0, mod = (int) Math.round(1e9 + 7);
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 0; i < k; i++) {
            int[] nxt = new int[n];
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + arr[j]) % mod;
                nxt[j] = sum;
            }
            arr = nxt;
        }
        return arr[n - 1];
    }
}
