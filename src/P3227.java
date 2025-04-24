import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

// 3227. Vowels Game in a String
public class P3227 {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') cnt++;
        }
        return cnt > 0;
    }
}
