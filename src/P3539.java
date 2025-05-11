import java.util.Arrays;

// 3539. Find Sum of Array Product of Magical Sequences
public class P3539 {
    public static final int mod = 1_000_000_007;
    public static final int MX = 31;

    public static final long[] fac = new long[MX]; // fac[i] = i!
    public static final long[] invFac = new long[MX]; // invFac[i] = i!^-1

    static {
        fac[0] = 1;
        for (int i = 1; i < MX; i++) {
            fac[i] = fac[i - 1] * i % mod;
        }

        invFac[MX - 1] = pow(fac[MX - 1], mod - 2);
        for (int i = MX - 1; i > 0; i--) {
            invFac[i - 1] = invFac[i] * i % mod;
        }
    }

    public static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return res;
    }

    // nCr
    public long comb(int n, int r) {
        return r < 0 || r > n ? 0 : fac[n] * invFac[r] % mod * invFac[n - r] % mod;
    }

    int n;
    int[][] powV;
    int[][][][] memo;

    public int magicalSum(int m, int k, int[] nums) {
        this.n = nums.length;
        memo = new int[n][m + 1][m / 2 + 1][k + 1];
        for (int[][][] a : memo) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        powV = new int[n][31];
        for (int i = 0; i < n; i++) powV[i][0] = 1;
        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j < n; j++) {
                powV[j][i] = (int) ((long) powV[j][i - 1] * nums[j] % mod);
            }
        }
        int v = dfs(0, m, 0, k);
        return (int) (v * fac[m] % mod);
    }

    int dfs(int i, int leftM, int s, int leftK) {
        int bc = Integer.bitCount(s);
        if (leftK == 0 && (leftM > 0 || bc > 0)) {
            return 0;
        }
        if (i == n) {
            return leftM == 0 && bc == leftK ? 1 : 0;
        }
        // if (leftK == -1) System.out.println("?? " + leftM);
        if (memo[i][leftM][s][leftK] != -1) return memo[i][leftM][s][leftK];
        long ret = 0;
        for (int j = 0; j <= leftM; j++) {
            int ns = s + j;
            int bit = ns & 1;
            int r = dfs(i + 1, leftM - j, ns >> 1, leftK - bit);
            ret = (ret + (long) r * powV[i][j] % mod * invFac[j] % mod) % mod;
        }
        memo[i][leftM][s][leftK] = (int) ret;
        return (int) ret;
    }
}
