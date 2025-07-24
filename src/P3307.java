// 3307. Find the K-th Character in String Game II
public class P3307 {
    public char kthCharacter(long k, int[] operations) {
        int n = operations.length;
        long len = 1;
        int i = 0;
        for (; i < n; i++) {
            int op = operations[i];
            len *= 2;
            if (len >= k) break;
        }

        int offset = 0;
        for (int j = i; j >= 0; j--) {
            int op = operations[j];
            if (op == 0) {
                k -= k > len / 2 ? len / 2 : 0;
            } else {
                if (k > len / 2) {
                    k -= len / 2;
                    offset++;
                }
            }
            len /= 2;
        }

        return (char) (offset % 26 + 'a');
    }
}
