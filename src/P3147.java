import java.util.Arrays;

// 3147. Taking Maximum Energy From the Mystic Dungeon
public class P3147 {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] f = new int[n];
        for (int i = 1; i <= k; i++) {
            for (int j = n - i; j >= 0; j -= k) {
                f[j] = (j + k < n ? f[j + k] : 0) + energy[j];
            }
        }
        int ret = Integer.MIN_VALUE;
        for (int v : f) ret = Math.max(ret, v);
        return ret;
    }
}
