import java.util.Arrays;

// 3207. Maximum Points After Enemy Battles
public class P3208 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int ret = 0;
        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i + n] = colors[i];
        }
        int[] f = new int[arr.length + 1];
        f[1] = 1;
        for (int i = 1; i < 2 * n; i++) {
            f[i + 1] = arr[i] == arr[i - 1] ? 1 : (f[i] + 1);
            if (i >= n && f[i + 1] >= k) ret++;
        }
        return ret;
    }
}
