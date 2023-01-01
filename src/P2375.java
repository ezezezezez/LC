import java.io.*;
import java.lang.*;
import java.util.*;

// 2375. Construct Smallest Number From DI String

public class P2375 {
    String ret;
    int n;
    public String smallestNumber(String pattern) {
        n = pattern.length();
        for (int i = 1; i <= 9; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            boolean[] used = new boolean[10];
            used[i] = true;
            dfs(pattern, sb, 0, used);
        }

        return ret;
    }

    void dfs(String pattern, StringBuilder sb, int idx, boolean[] used) {
        if (idx == n) {
            if (ret == null || sb.toString().compareTo(ret) < 0) {
                ret = sb.toString();
            }
            return;
        }
        char c = pattern.charAt(idx);
        int pre = sb.charAt(idx) - '0';
        if (c == 'I') {
            for (int i = pre + 1; i <= 9; i++) {
                if (used[i]) continue;
                sb.append(i);
                used[i] = true;
                dfs(pattern, sb, idx + 1, used);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            for (int i = pre - 1; i >= 1; i--) {
                if (used[i]) continue;
                sb.append(i);
                used[i] = true;
                dfs(pattern, sb, idx + 1, used);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String smallestNumber2(String pattern) {
        int n = pattern.length();
        int num = 1;
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            dq.push(num++);
            if (c == 'I') {
                while (!dq.isEmpty()) ret.append(dq.pop());
            }
        }
        dq.push(num);
        while (!dq.isEmpty()) ret.append(dq.pop());
        return ret.toString();
    }
}
