// 2938. Separate Black and White Balls
public class P2939 {
    public int maximumXorProduct(long a, long b, int n) {
        long[] ac = new long[100], bc = new long[100];
        for (int i = 0; i < 64; i++) {
            ac[i] = ((a >> i) & 1L);
            bc[i] = ((b >> i) & 1L);
        }
        // System.out.println(Arrays.toString(ac));
        // System.out.println(Arrays.toString(bc));
        boolean first = true;
        for (int i = 63; i >= 0; i--) {
            if (i < n) {
                if (ac[i] != bc[i]) {
                    if (first) {
                        first = false;
                        ac[i] = 1;
                        bc[i] = 0;
                    } else {
                        ac[i] = 0;
                        bc[i] = 1;
                    }
                } else {
                    ac[i] = bc[i] = 1;
                }
            } else if (ac[i] != bc[i]) {
                if (ac[i] == 1) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (j < n) {
                            if (ac[j] != bc[j]) {
                                ac[j] = 0;
                                bc[j] = 1;
                            } else {
                                ac[j] = bc[j] = 1;
                            }
                        }
                    }
                } else {
                    for (int j = i - 1; j >= 0; j--) {
                        if (j < n) {
                            if (ac[j] != bc[j]) {
                                ac[j] = 1;
                                bc[j] = 0;
                            } else {
                                ac[j] = bc[j] = 1;
                            }
                        }
                    }
                }
                break;
            }
        }
        // System.out.println();
        // System.out.println(Arrays.toString(ac));
        // System.out.println(Arrays.toString(bc));
        long v1 = 0, v2 = 0;
        for (int i = 0; i < 64; i++) {
            v1 = v1 | (ac[i] << i);
            v2 = v2 | (bc[i] << i);
        }
        long mod = (long) (1e9 + 7);
        return (int) ((v1 % mod) * (v2 % mod) % mod);
    }
}
