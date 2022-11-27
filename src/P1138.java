import java.util.*;
import java.io.*;
import java.lang.*;

// 1138. Alphabet Board Path

public class P1138 {
    public String alphabetBoardPath(String target) {
        int n = target.length();
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');
            int x = idx / 5, y = idx % 5;
            map.put(c, new int[]{x, y});
            idx++;
        }

        int curx = 0, cury = 0;
        for (int i = 0; i < n; i++) {
            char t = target.charAt(i);
            int[] pos = map.get(t);
            int x = pos[0], y = pos[1];
            if (x != curx || y != cury) {
                if (x != curx && y != cury) {
                    if (curx > x) {
                        if (cury > y) {
                            int dx = curx - x, dy = cury - y;
                            while (dx-- > 0) sb.append('U');
                            while (dy-- > 0) sb.append('L');
                        } else {
                            int dx = curx - x, dy = y - cury;
                            while (dx-- > 0) sb.append('U');
                            while (dy-- > 0) sb.append('R');
                        }
                    } else {
                        if (cury > y) {
                            int dx = x - curx, dy = cury - y;
                            while (dy-- > 0) sb.append('L');
                            while (dx-- > 0) sb.append('D');
                        } else {
                            int dx = x - curx, dy = y - cury;
                            while (dy-- > 0) sb.append('R');
                            while (dx-- > 0) sb.append('D');
                        }
                    }
                } else if (x != curx) {
                    if (x > curx) {
                        int dx = x - curx;
                        while (dx-- > 0) sb.append('D');
                    } else {
                        int dx = curx - x;
                        while (dx-- > 0) sb.append('U');
                    }
                } else {
                    if (y > cury) {
                        int dy = y - cury;
                        while (dy-- > 0) sb.append('R');
                    } else {
                        int dy = cury - y;
                        while (dy-- > 0) sb.append('L');
                    }
                }
            }
            sb.append('!');
            curx = x; cury = y;
        }

        return sb.toString();
    }
}
