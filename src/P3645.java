import java.util.Arrays;

// 3645. Maximum Total from Optimal Activation Order
public class P3645 {
    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        long ret = 0;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = value[i];
            arr[i][1] = limit[i];
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        });
        int cnt = 0;
        int j = 0, lim = arr[0][1];
        for (int i = 0; i < n; i++) {
            if (i < j) continue;
            ret += arr[i][0];
            cnt++;
            if (cnt >= lim) {
                while (j < n && arr[j][1] == lim) {
                    cnt = Math.max(0, cnt - 1);
                    j++;
                }
                if (j < n) lim = arr[j][1];
            }
        }
        return ret;
    }
}
