import java.io.*;
import java.lang.*;
import java.util.*;

// 1405. Longest Happy String

public class P1405 {
    public String longestDiverseString(int a, int b, int c) {
        char[] map = new char[3];
        map[0] = 'a'; map[1] = 'b'; map[2] = 'c';
        if (a < b) {
            int t = a;
            a = b;
            b = t;
            char tc = map[0];
            map[0] = map[1];
            map[1] = tc;
        }
        if (a < c) {
            int t = a;
            a = c;
            c = t;
            char tc = map[0];
            map[0] = map[2];
            map[2] = tc;
        }
        if (b < c) {
            int t = b;
            b = c;
            c = t;
            char tc = map[1];
            map[1] = map[2];
            map[2] = tc;
        }
        if ((b + c) * 2 + 2 < a) a = (b + c) * 2 + 2;

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < a; i++) ret.append(map[0]);

        int x = Math.min(2, ret.length());
        while (b-- > 0) {
            ret.insert(x, map[1]);
            if (x + 1 == Math.min(x + 3, ret.length())) break;
            x = Math.min(x + 3, ret.length());
        }

        // System.out.println(ret + " " + b);

        if (x + 1 == ret.length()) {
            x = 1;
            while (b-- > 0) {
                ret.insert(x, map[1]);
                x += 4;
            }
            x = ret.length();
        }
        // System.out.println(ret + " " + b);

        while (x != ret.length() && c > 0) {
            ret.insert(x, map[2]);
            x = Math.min(x + 3, ret.length());
            c--;
        }

        x = 0;
        for (int i = 0; i < c; i++) {
            ret.insert(x, map[2]);
            x += 2;
        }

        return ret.toString();
    }

    public String longestDiverseString2(int a, int b, int c) {
        int[][] arr = new int[3][];
        arr[0] = new int[]{'a', a};
        arr[1] = new int[]{'b', b};
        arr[2] = new int[]{'c', c};

        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(arr, (u, v) -> Integer.compare(v[1], u[1]));
            int t = arr[0][0], cnt = arr[0][1];
            if (cnt == 0) break;
            if (sb.length() >= 2 && sb.charAt(sb.length() - 1) == sb.charAt(sb.length() - 2) && sb.charAt(sb.length() - 1) == (char)t) {
                int t2 = arr[1][0], cnt2 = arr[1][1];
                if (cnt2 == 0) break;
                sb.append((char)t2);
                arr[1][1]--;
            } else {
                sb.append((char)t);
                arr[0][1]--;
            }
        }

        return sb.toString();
    }
}
