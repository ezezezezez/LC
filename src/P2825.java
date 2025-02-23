import java.util.List;
import java.util.TreeSet;

// 2825. Make String a Subsequence Using Cyclic Increments
public class P2825 {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        if (n2 > n1) return false;
        int i = 0, j = 0;
        for (; j < n2; j++) {
            char c2 = str2.charAt(j);
            while (i < n1 && str1.charAt(i) != c2 && nextChar(str1.charAt(i)) != c2) {
                i++;
            }
            if (i == n1) return false;
            i++;
        }
        return j == n2;
    }

    char nextChar(char c) {
        return (char) ((c - 'a' + 1) % 26 + 'a');
    }
}
