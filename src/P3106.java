// 3106. Lexicographically Smallest String After Operations With Constraint
public class P3106 {
    public String getSmallestString(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            boolean found = false;
            for (int j = 'a'; j < c; j++) {
                int dist = Math.min(Math.abs(c - j), Math.abs(j + 26 - c));
                if (dist <= k) {
                    k -= dist;
                    sb.append((char) j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
