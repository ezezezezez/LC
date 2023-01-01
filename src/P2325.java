import java.io.*;
import java.lang.*;
import java.util.*;

// 2325. Decode the Message

public class P2325 {
    public String decodeMessage(String key, String message) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < key.length(); i++) {
            int c = key.charAt(i);
            if (c == ' ') continue;
            if (!set.contains(c)) set.add(c);
        }
        int[] arr = new int[26];
        int idx = 0;
        for (int num : set) {
            arr[num - 'a'] = 'a' + idx++;
        }
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == ' ') sb.append(c);
            else sb.append((char)arr[c - 'a']);
        }
        return sb.toString();
    }
}
