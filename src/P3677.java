// 3677. Count Binary Palindromic Numbers

public class P3677 {
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;
        int ret = 0;
        int len = 64 - Long.numberOfLeadingZeros(n);
        for (int i = 1; i < len; i++) {
            int half = (i + 1) / 2;
            ret += 1 << (half - 1);
        }
        int[] d = new int[len];
        int idx = 0;
        long t = n;
        while (t > 0) {
            d[idx] = (int) (t % 2);
            t /= 2;
            idx++;
        }
        int halfValue = 0;
        for (int i = idx - 1; i >= idx / 2; i--) {
            halfValue = 2 * halfValue + d[i];
        }
        int lb = 1 << ((idx - 1) / 2);
        int sameLenLower = halfValue - lb;
        long fullValue = halfValue;
        for (int i = (idx + 1) / 2; i < idx; i++) {
            fullValue = 2 * fullValue + d[i];
        }
        return 1 + ret + sameLenLower + (fullValue <= n ? 1 : 0);
    }
}
