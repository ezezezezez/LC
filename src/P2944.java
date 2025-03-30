import java.util.Arrays;

// 2944. Minimum Number of Coins for Fruits
public class P2944 {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= i + i + 1 && j < n; j++) {
                f[j + 1] = Math.min(f[j + 1], f[i] + prices[i]);
            }
        }
        // System.out.println(Arrays.toString(f));
        return f[n];
    }
}
