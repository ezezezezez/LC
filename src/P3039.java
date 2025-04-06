import java.util.Arrays;

// 3039. Apply Operations to Make String Empty
public class P3039 {
    public String lastNonEmptyString(String s) {
        int n = s.length();
        int mx = 0;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            count[idx]++;
        }
        for (int cnt : count) mx = Math.max(mx, cnt);
        StringBuilder sb = new StringBuilder();
        boolean[] met = new boolean[26];
        for (int i = n - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';
            if (count[idx] == mx && !met[idx]) {
                sb.append(s.charAt(i));
                met[idx] = true;
            }
        }
        return sb.reverse().toString();
    }
}
