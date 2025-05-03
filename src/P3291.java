import java.util.Arrays;

// 3291. Minimum Number of Valid Strings to Form Target I
public class P3291 {
    public int minValidStrings(String[] words, String target) {
        int n = words.length;
        for (String word : words) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Node();
                }
                cur = cur.children[c - 'a'];
            }
        }

        memo = new int[target.length()];
        Arrays.fill(memo, -1);
        dfs(target, 0);
        return memo[0] == Integer.MAX_VALUE ? -1 : memo[0];
    }

    int ret = Integer.MAX_VALUE;
    int[] memo;

    int dfs(String target, int idx) {
        if (idx == target.length()) {
            return 0;
        }
        if (memo[idx] != -1) return memo[idx];
        Node cur = root;
        int t = Integer.MAX_VALUE;
        for (int i = idx; i < target.length(); i++) {
            char c = target.charAt(i);
            if (cur.children[c - 'a'] != null) {
                t = Math.min(t, dfs(target, i + 1));
            } else {
                break;
            }
            cur = cur.children[c - 'a'];
        }
        memo[idx] = t == Integer.MAX_VALUE ? t : t + 1;
        return memo[idx];
    }

    Node root = new Node();

    static class Node {
        Node[] children;

        Node() {
            children = new Node[26];
        }
    }
}
