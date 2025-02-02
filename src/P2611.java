import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P2611 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[][] diff = new int[n][2];
        for (int i = 0; i < n; i++) diff[i] = new int[] {reward1[i] - reward2[i], i};
        Arrays.sort(diff, (a, b) -> Integer.compare(a[0], b[0]));
        int ret = 0;
        for (int i = 0; i < k; i++) {
            ret += reward1[diff[n - 1 - i][1]];
        }
        for (int i = k; i < n; i++) {
            ret += reward2[diff[n - 1 - i][1]];
        }
        return ret;
    }
}
