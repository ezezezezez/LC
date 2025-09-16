// 3677. Count Binary Palindromic Numbers

public class P3677_2 {
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;
        int ret = 1;
        int len = 64 - Long.numberOfLeadingZeros(n);
        for (int i = 1; i < len; i++) {
            int half = (i + 1) / 2;
            ret += 1 << (half - 1);
        }
        int halfLen = (len + 1) / 2;
        long prefix = n >> (len - halfLen);
        int base = 1 << (halfLen - 1);
        ret += prefix - base;

        long all = prefix, tmp = prefix;
        if ((len & 1) == 1) tmp >>= 1;
        while (tmp > 0) {
            all = (all << 1) | (tmp & 1);
            tmp >>= 1;
        }
        if (all <= n) ret++;

        return ret;
    }
}
