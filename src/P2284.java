import java.io.*;
import java.lang.*;
import java.util.*;

// 2284. Sender With Largest Word Count

public class P2284 {
    public String largestWordCount(String[] messages, String[] senders) {
        int n = messages.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String message = messages[i];
            String[] tokens = message.split(" ");
            map.merge(senders[i], tokens.length, Integer::sum);
        }
        int mx = 0;
        String mxKey = "";
        for (String key : map.keySet()) {
            int cnt = map.get(key);
            if (cnt > mx) {
                mx = cnt;
                mxKey = key;
            } else if (cnt == mx && key.compareTo(mxKey) > 0) {
                mxKey = key;
            }
        }
        return mxKey;
    }
}
