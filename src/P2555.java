import java.util.HashSet;
import java.util.Set;

public class P2555 {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            int pos = prizePositions[i];
            int lo = -1, hi = i - 1, t = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (mid < 0) {
                    lo = mid + 1;
                } else if (prizePositions[mid] < pos - k) {
                    t = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            arr[i] = Math.max(arr[i - 1], i - t);
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int pos = prizePositions[i];
            int lo = -1, hi = i - 1, t = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (mid < 0) {
                    lo = mid + 1;
                } else if (prizePositions[mid] < pos - k) {
                    t = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (t == -1) ret = Math.max(ret, i + 1);
            else {
                ret = Math.max(ret, i - t + arr[t]);
            }
        }
        return ret;
    }
}
