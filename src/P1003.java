import java.util.*;
import java.io.*;
import java.lang.*;

// 1003. Check If Word Is Valid After Substitutions

public class P1003 {
    public boolean isValid(String s) {
        int n = s.length();
        Deque<Character> dq = new ArrayDeque<>();

        String pre = "";
        for (int i = 0; i < n; i++) {
            dq.push(s.charAt(i));
            if (dq.size() >= 3) {
                char c = dq.pop(), b = dq.pop(), a = dq.pop();
                if (c != 'c' || b != 'b' || a != 'a') {
                    dq.push(a);
                    dq.push(b);
                    dq.push(c);
                }
            }
        }
        return dq.isEmpty();
    }
}
