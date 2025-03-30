// 3001. Minimum Moves to Capture The Queen
public class P3001_2 {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (a == e && (c != e || !inBetween(b, d, f)) ||
                b == f && (d != f || !inBetween(a, c, e)) ||
                c + d == e + f && (a + b != e + f || !inBetween(c, a, e)) ||
                c - d == e - f && (a - b != e - f || !inBetween(c, a, e))) {
            return 1;
        }
        return 2;
    }

    private boolean inBetween(int l, int m, int r) {
        return Math.min(l, r) < m && m < Math.max(l, r);
    }
}
