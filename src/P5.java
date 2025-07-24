// 5. Longest Palindromic Substring

public class P5 {
    public String longestPalindrome(String s) {
        int len = 0;
        String ret = null;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; i - j >= 0 && i + j < n; j++) {
                int l = i - j, r = i + j;
                if (s.charAt(l) != s.charAt(r)) break;
                if (r - l + 1 > len) {
                    len = r - l + 1;
                    ret = s.substring(l, r + 1);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) continue;
            for (int j = 0; i - j >= 0 && i + 1 + j < n; j++) {
                int l = i - j, r = i + 1 + j;
                if (s.charAt(l) != s.charAt(r)) break;
                if (r - l + 1 > len) {
                    len = r - l + 1;
                    ret = s.substring(l, r + 1);
                }
            }
        }

        return ret;
    }
}