// 3612. Process String with Special Operations I
public class P3612 {
    public String processStr(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
            } else if (c == '#') {
                sb.append(sb);
            } else if (c == '%') {
                sb.reverse();
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
