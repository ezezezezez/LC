import java.io.*;
import java.lang.*;
import java.util.*;

// 1807. Evaluate the Bracket Pairs of a String

public class P1807 {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        StringBuilder ret = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                StringBuilder token = new StringBuilder();
                while (ret.charAt(ret.length() - 1) != '(') {
                    token.append(ret.charAt(ret.length() - 1));
                    ret.deleteCharAt(ret.length() - 1);
                }
                ret.deleteCharAt(ret.length() - 1);
                ret.append(map.getOrDefault(token.reverse().toString(), "?"));
            } else {
                ret.append(c);
            }
        }
        return ret.toString();
    }
}
