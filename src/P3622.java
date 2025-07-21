// 3622. Check Divisibility by Digit Sum and Product
public class P3622 {
    public boolean checkDivisibility(int n) {
        int[] d = new int[10];
        int idx = 0;
        int t = n;
        while (t > 0) {
            d[idx++] = t % 10;
            t /= 10;
        }

        int a = 0, b = 1;
        for (int i = 0; i < idx; i++) {
            a += d[i];
            b *= d[i];
        }

        return n % (a + b) == 0;
    }
}
