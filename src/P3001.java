import java.util.ArrayDeque;
import java.util.Deque;

// 3001. Minimum Moves to Capture The Queen
public class P3001 {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (Math.abs(c - e) == Math.abs(d - f)) {
            if ((c - a) * (f - b) == (e - a) * (d - b)) {
                // System.out.println("gg");
                int d1 = Math.abs(c - e);
                int d2 = Math.abs(a - c);
                int d3 = Math.abs(a - e);
                if (d2 > d1 || d3 > d1) return 1;
                return 2;
            }
            return 1;
        }
        if (e == a) {
            if (c == e) {
                if (d > Math.min(b, f) && d < Math.max(b, f)) {
                    int diff = Math.abs(d - f);
                    if (diff % 2 == 0) return 2;
                    return 2;
                }
                return 1;
            }
            return 1;
        } else if (f == b) {
            if (f == d) {
                if (c > Math.min(a, e) && c < Math.max(a, e)) {
                    int diff = Math.abs(c - e);
                    if (diff % 2 == 0) return 2;
                    return 2;
                }
                return 1;
            }
            return 1;
        }
        return 2;
    }
}
