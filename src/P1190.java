import java.util.*;
import java.io.*;
import java.lang.*;

// // 1186. Maximum Subarray Sum with One Deletion

public class P1190 {
    public String reverseParentheses(String s) {
        int n = s.length();
        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                List<Character> temp = new ArrayList<>();
                while (dq.peek() != '(') {
                    temp.add(dq.pop());
                }
                dq.pop();
                for (int j = 0; j < temp.size(); j++) {
                    dq.push(temp.get(j));
                }
            } else {
                dq.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) sb.append(dq.pop());
        return sb.reverse().toString();
    }
}
