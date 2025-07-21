// 3614. Process String with Special Operations II
public class P3614 {
    public char processStr(String s, long k) {
        int n = s.length();
        long len = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                len = Math.max(0, len - 1);
            } else if (c == '#') {
                len *= 2;
            } else if (c == '%') {

            } else {
                len++;
            }
        }
        if (k >= len) return '.';
        // System.out.println(k + " " + len);

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                len++;
            } else if (c == '#') {
                len /= 2;
                if (k >= len) k %= len;
            } else if (c == '%') {
                k = len - 1 - k;
            } else {
                if (k == len - 1) return c;
                len--;
            }
        }

        return '.';
    }
}
