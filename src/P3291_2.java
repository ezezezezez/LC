import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 3291. Minimum Number of Valid Strings to Form Target I
public class P3291_2 {
    //--------- AC Automaton Node -----------
    class Node {
        Node[] children = new Node[26]; // children for letters 'a' .. 'z'
        Node failure;                   // standard failure pointer
        Node dictSuffix;                // next node in failure chain that is output (valid)
        boolean output;                 // true if this node represents a valid prefix
        int depth;                      // length from the root

        Node(int depth) {
            this.depth = depth;
            this.output = false;
            this.failure = null;
            this.dictSuffix = null;
        }
    }

    Node root;

    //--------- Build Trie: Insert every word and mark each prefix as valid -----------
    private void buildTrie(String[] words) {
        root = new Node(0);
        for (String word : words) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Node(cur.depth + 1);
                }
                cur = cur.children[c];
                // Mark every prefix (non-empty) as valid.
                cur.output = true;
            }
        }
    }

    //--------- Build Failure Pointers and dictSuffix Links -----------
    private void buildFailureAndDictSuffix() {
        Queue<Node> queue = new LinkedList<>();
        root.failure = root;
        root.dictSuffix = null;  // root is not output
        // For each letter, initialize root's children.
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                root.children[i].failure = root;
                // For children of root, dictSuffix is null (since root is not output).
                root.children[i].dictSuffix = null;
                queue.offer(root.children[i]);
            } else {
                root.children[i] = root;  // missing transitions point to root.
            }
        }
        // Build pointers level by level.
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                Node child = cur.children[i];
                if (child != null) {
                    // Set child's failure pointer.
                    Node f = cur.failure;
                    child.failure = f.children[i];

                    // Set dictSuffix: if child's failure is output, use it;
                    // else, inherit failure's dictSuffix.
                    if (child.failure != root && child.failure.output)
                        child.dictSuffix = child.failure;
                    else
                        child.dictSuffix = child.failure.dictSuffix;

                    queue.offer(child);
                } else {
                    cur.children[i] = cur.failure.children[i];
                }
            }
        }
    }

    //--------- Main Method: Solve the segmentation problem using AC Automaton -----------
    // A valid segment is any substring that is a prefix of some word (i.e. appears in the trie).
    // We precompute for each starting index i in target the maximum length L such that target[i...i+L-1]
    // is a valid segment. Then, using a greedy jump-game, we compute the minimum number of segments.
    public int minValidStrings(String[] words, String target) {
        int n = target.length();

        buildTrie(words);
        buildFailureAndDictSuffix();

        // globalMax[i] will store the maximum length of a valid segment (prefix) starting at index i.
        int[] globalMax = new int[n];
        Arrays.fill(globalMax, 0);

        // Process the target in one pass by simulating the AC automaton.
        Node state = root;
        for (int j = 0; j < n; j++) {
            int c = target.charAt(j) - 'a';
            state = state.children[c]; // transition; by construction non-null.

            // Now, follow the output (dictSuffix) chain starting from the current state.
            // Use a loop that terminates if temp becomes null.
            for (Node temp = state; temp != null && temp != root; temp = temp.dictSuffix) {
                // Only process nodes that represent a valid prefix.
                if (temp.output) {
                    int l = temp.depth;
                    int start = j - l + 1;
                    if (start >= 0 && start < n) {
                        globalMax[start] = Math.max(globalMax[start], l);
                    }
                }
            }
        }

        // Greedy jump game over the target using globalMax[].
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest)
                return -1; // unreachable
            farthest = Math.max(farthest, i + globalMax[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= n)
                    break;
            }
        }
        return farthest < n ? -1 : jumps;
    }
}
