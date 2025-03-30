import java.util.HashMap;
import java.util.Map;

// 3021. Alice and Bob Playing Flower Game
public class P3021 {
    public long flowerGame(int n, int m) {
        long ret = 0;
        int odd = (m + 1) / 2, even = m - odd;
        for (int i = 1; i <= n; i++) {
            ret += i % 2 == 0 ? odd : even;
        }
        return ret;
    }
}
