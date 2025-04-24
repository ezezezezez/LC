import java.util.*;

// 3096. Minimum Levels to Gain More Points
public class P3096 {
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int cnt = 0;
        for (int v : possible) cnt += v;
        int cur = 0;
        for (int i = 0; i < n - 1; i++) {
            cur += possible[i];
            int a = cur - (i + 1 - cur);
            int b = (cnt - cur) - (n - i - 1 - cnt + cur);
            if (a > b) return i + 1;
        }
        return -1;
    }
}
