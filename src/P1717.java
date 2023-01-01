import java.io.*;
import java.lang.*;
import java.util.*;

// 1717. Maximum Score From Removing Substrings

public class P1717 {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        if (x < y) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < n; i++) {
                if (sb.charAt(i) == 'a') {
                    sb.setCharAt(i, 'b');
                } else if (sb.charAt(i) == 'b') {
                    sb.setCharAt(i, 'a');
                }
            }
            return maximumGain(sb.toString(), y, x);
        }
        Deque<Character> dq = new ArrayDeque<>();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (dq.isEmpty()) {
                dq.push(s.charAt(i));
            } else if (dq.peek() == 'a' && s.charAt(i) == 'b') {
                ret += x;
                dq.pop();
            } else {
                dq.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pop());
        }
        String str = sb.reverse().toString();
        for (int i = 0; i < str.length(); i++) {
            if (dq.isEmpty()) {
                dq.push(str.charAt(i));
            } else if (dq.peek() == 'b' && str.charAt(i) == 'a') {
                ret += y;
                dq.pop();
            } else {
                dq.push(str.charAt(i));
            }
        }
        return ret;
    }
}
