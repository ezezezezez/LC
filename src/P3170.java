import java.util.*;

// 3170. Lexicographically Minimum String After Removing Stars
public class P3170 {
    public String clearStars(String s) {
        int n = s.length();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                pq.poll();
            } else {
                pq.offer(new int[] {c - 'a', i});
            }
        }
        List<int[]> list = new ArrayList<>(pq.size());
        while (!pq.isEmpty()) list.add(pq.poll());
        Collections.sort(list, (a, b) -> Integer.compare(a[1], b[1]));
        StringBuilder sb = new StringBuilder();
        for (int[] pair : list) sb.append((char) (pair[0] + 'a'));
        return sb.toString();
    }
}
