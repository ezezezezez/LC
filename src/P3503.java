import java.util.Arrays;

// 3503. Longest Palindrome After Substring Concatenation I
public class P3503 {
    public int longestPalindrome(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        int ret = 1;
        for (int i = 0; i < n1; i++) {
            for (int j = i; j < n1; j++) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    ret = Math.max(ret, j + 1 - i);
                }
            }
        }
        for (int i = 0; i < n2; i++) {
            for (int j = i; j < n2; j++) {
                if (isPalindrome(t.substring(i, j + 1))) {
                    ret = Math.max(ret, j + 1 - i);
                }
            }
        }
        for (int i = 0; i < n1; i++) {
            for (int j = i; j < n1; j++) {
                for (int u = 0; u < n2; u++) {
                    for (int v = u; v < n2; v++) {
                        String str = new StringBuilder(s.substring(i, j + 1)).append(t.substring(u, v + 1)).toString();
                        if (isPalindrome(str)) {
                            ret = Math.max(ret, str.length());
                        }
                    }
                }
            }
        }
        return ret;
    }

    boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) return false;
        }
        return true;
    }
}
