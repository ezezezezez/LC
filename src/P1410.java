import java.io.*;
import java.lang.*;
import java.util.*;

// 1410. HTML Entity Parser

public class P1410 {
    public String entityParser(String text) {
        int n = text.length();
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c == '&') {
                String sub = text.substring(i, Math.min(i + 4, n));
                if (sub.equals("&gt;")) {
                    ret.append('>');
                    i += 3;
                    continue;
                } else if (sub.equals("&lt;")) {
                    ret.append('<');
                    i += 3;
                    continue;
                }
                sub = text.substring(i, Math.min(i + 5, n));
                if (sub.equals("&amp;")) {
                    ret.append('&');
                    i += 4;
                    continue;
                }
                sub = text.substring(i, Math.min(i + 6, n));
                if (sub.equals("&quot;")) {
                    ret.append('"');
                    i += 5;
                    continue;
                } else if (sub.equals("&apos;")) {
                    ret.append('\'');
                    i += 5;
                    continue;
                }
                sub = text.substring(i, Math.min(i + 7, n));
                if (sub.equals("&frasl;")) {
                    ret.append('/');
                    i += 6;
                    continue;
                }
                ret.append(c);
            } else {
                ret.append(c);
            }
        }

        return ret.toString();
    }
}
