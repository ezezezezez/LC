import java.io.*;
import java.lang.*;
import java.util.*;

// 2390. Removing Stars From a String

public class P2390 {
    public String removeStars(String s) {
        int n = s.length();
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') dq.pop();
            else dq.push(c);
        }
        StringBuilder ret = new StringBuilder();
        while (!dq.isEmpty()) ret.append(dq.pop());
        return ret.reverse().toString();
    }
}
