import java.util.*;

// 3081. Replace Question Marks in String to Minimize Its Value
public class P3081 {
    public String minimizeStringValue(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int t = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '?') t++;
            else cnt[c - 'a']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (cnt[a] != cnt[b]) return Integer.compare(cnt[a], cnt[b]);
            return Integer.compare(a, b);
        });
        for (int i = 0; i < 26; i++) pq.offer(i);

        List<Integer> values = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '?') {
                int v = pq.poll();
                values.add(v);
                indices.add(i);
                cnt[v]++;
                pq.offer(v);
            }
        }
        Collections.sort(values);
        for (int i = 0; i < values.size(); i++) {
            sb.setCharAt(indices.get(i), (char) (values.get(i) + 'a'));
        }

        return sb.toString();
    }
}
