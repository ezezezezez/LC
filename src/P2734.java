import java.util.Arrays;

// 2734. Lexicographically Smallest String After Substring Operation
public class P2734 {
    public String smallestString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < n; i++) {
            if (s.charAt(i) == 'a') {
                sb.append('a');
            } else break;
        }
        if (i == n) {
            sb.setCharAt(sb.length() - 1, 'z');
        }
        for (; i < n; i++) {
            if (s.charAt(i) == 'a') {
                sb.append(s.substring(i));
                break;
            }
            sb.append((char) (s.charAt(i) - 1));
        }
        return sb.toString();
    }
}
