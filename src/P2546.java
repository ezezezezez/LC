import java.util.Arrays;
import java.util.Comparator;

public class P2546 {
    public boolean makeStringsEqual(String s, String target) {
        int n = s.length();
        int c1 = 0, d1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') c1++;
            if (target.charAt(i) == '1') d1++;
        }
        if (c1 == 0 && d1 > 0) return false;
        if (d1 == 0 && c1 > 0) return false;
        return true;
    }
}
