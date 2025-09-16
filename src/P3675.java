// 3675. Minimum Operations to Transform String

public class P3675 {
    public int minOperations(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean[] seen = new boolean[26];
        for (char c : cs) {
            seen[c - 'a'] = true;
        }
        for (int i = 1; i < 26; i++) {
            if (seen[i]) return 26 - i;
        }
        return 0;
    }
}
