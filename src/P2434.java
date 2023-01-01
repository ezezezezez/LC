import java.io.*;
import java.lang.*;
import java.util.*;

// 2434. Using a Robot to Print the Lexicographically Smallest String

public class P2434 {
    public String robotWithString(String s) {
        int n = s.length();
        int[] mn = new int[n];
        mn[n - 1] = s.charAt(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            mn[i] = Math.min(mn[i + 1], s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (dq.isEmpty()) {
                if (c == mn[i]) {
                    sb.append(c);
                } else {
                    dq.push(c);
                }
            } else {
                if (mn[i] < dq.peek()) {
                    if (c == mn[i]) {
                        sb.append(c);
                    } else {
                        dq.push(c);
                    }
                } else {
                    while (!dq.isEmpty() && dq.peek() <= mn[i]) {
                        sb.append(dq.pop());
                    }
                    if (c == mn[i]) {
                        sb.append(c);
                    } else {
                        dq.push(c);
                    }
                }
            }
        }
        while (!dq.isEmpty()) sb.append(dq.pop());
        return sb.toString();
    }
}
