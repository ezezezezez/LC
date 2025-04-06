import java.util.Arrays;

// 3035. Maximum Palindromes After Operations
public class P3035 {
    public int maxPalindromesAfterOperations(String[] words) {
        int n = words.length;
        int ret = 0;
        int odd = 0, even = 0;
        int[] count = new int[26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                count[idx]++;
            }
        }
        for (int cnt : count) {
            odd += cnt % 2;
            even += cnt / 2;
        }
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        boolean hasEvenLeft = false;
        for (String word : words) {
            int len = word.length();
            if (len % 2 == 0) {
                if (len / 2 > even) return ret;
                even -= len / 2;
                ret++;
            } else {
                if (len / 2 > even) return ret;
                even -= len / 2;
                if (odd > 0) {
                    odd--;
                } else if (hasEvenLeft) {
                    hasEvenLeft = false;
                } else if (even > 0) {
                    even--;
                    hasEvenLeft = true;
                } else {
                    return ret;
                }
                ret++;
            }
        }
        return ret;
    }
}
