import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292_7 {
    static class AC {

        private static final int ALPHABET_SIZE = 26;  // works for lowercase letters 'a'-'z'

        // ---------- Helper Node Class ----------
        private static class Node {
            Node[] children = new Node[ALPHABET_SIZE];
            Node failure;       // Failure pointer.
            boolean valid;      // True if this node lies on the path of some inserted word
            // (so the prefix corresponding to this node is valid).
            int depth;          // Number of characters from the root to this node.
            int maxOutput;      // Maximum valid prefix length in this node's failure chain (including this node).

            Node(int depth) {
                this.depth = depth;
                this.valid = false;
                this.failure = null;
                this.maxOutput = 0;
            }
        }
        // ---------- End Helper Node Class ----------

        private Node root;

        public AC() {
            root = new Node(0);
        }

        /**
         * Inserts a dictionary word into the automaton.
         * As you traverse the word, each node is marked as valid because
         * every prefix of an inserted word is allowed.
         */
        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Node(cur.depth + 1);
                }
                cur = cur.children[c];
                cur.valid = true;
            }
        }

        /**
         * Builds the automaton by computing failure pointers via BFS and
         * computing each node’s maxOutput value.
         * The maxOutput of a node is the maximum between (a) the node’s depth (if valid)
         * and (b) the maxOutput of its failure node.
         */
        public void buildFailure() {
            Queue<Node> queue = new LinkedList<>();
            root.failure = root;
            root.maxOutput = 0; // The empty string is not a valid segment.
            for (int c = 0; c < ALPHABET_SIZE; c++) {
                if (root.children[c] != null) {
                    root.children[c].failure = root;
                    root.children[c].maxOutput = root.children[c].valid ? root.children[c].depth : 0;
                    queue.offer(root.children[c]);
                } else {
                    root.children[c] = root; // missing transitions point back to root.
                }
            }
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                for (int c = 0; c < ALPHABET_SIZE; c++) {
                    if (cur.children[c] != null) {
                        Node child = cur.children[c];
                        Node f = cur.failure;
                        child.failure = f.children[c];
                        int candidate = child.valid ? child.depth : 0;
                        child.maxOutput = Math.max(candidate, child.failure.maxOutput);
                        queue.offer(child);
                    } else {
                        cur.children[c] = cur.failure.children[c];
                    }
                }
            }
        }

        /**
         * Processes the given text in one pass.
         * Returns an integer array globalMax[] where globalMax[i] is the maximum valid prefix length
         * (i.e. the length of some inserted word's prefix) that occurs starting at index i.
         */
        public int[] searchGlobalMax(String text) {
            int n = text.length();
            int[] globalMax = new int[n];
            Arrays.fill(globalMax, 0);
            Node state = root;
            for (int j = 0; j < n; j++) {
                int idx = text.charAt(j) - 'a';
                state = state.children[idx]; // Transition (guaranteed non-null by construction).
                int L = state.maxOutput;       // Maximum valid prefix length among those ending at j.
                if (L > 0) {
                    int start = j - L + 1;
                    if (start >= 0 && start < n) {
                        globalMax[start] = Math.max(globalMax[start], L);
                    }
                }
            }
            return globalMax;
        }

        // ------------------ Unit Tests in Main ------------------
        public static void main(String[] args) {
            System.out.println("=== AC Automaton Library Tests ===\n");

            // Test 1: Basic Test.
            System.out.println("Test 1: Basic Test");
            AC ac1 = new AC();
            ac1.insert("hello");
            ac1.insert("hell");
            ac1.insert("he");
            ac1.insert("world");
            ac1.insert("wor");
            ac1.buildFailure();
            String text1 = "helloworld";
            int[] gm1 = ac1.searchGlobalMax(text1);
            System.out.println("Text: \"" + text1 + "\"");
            System.out.println("GlobalMax: " + Arrays.toString(gm1));
            // Expect positions corresponding to occurrences of "he", "hell", "hello", "wor", "world".

            System.out.println("\n---------------------------------\n");

            // Test 2: Overlapping Patterns.
            System.out.println("Test 2: Overlapping Patterns");
            AC ac2 = new AC();
            ac2.insert("abc");
            ac2.insert("abd");  // both share prefix "ab"
            ac2.buildFailure();
            String text2 = "abdbcabc";
            int[] gm2 = ac2.searchGlobalMax(text2);
            System.out.println("Text: \"" + text2 + "\"");
            System.out.println("GlobalMax: " + Arrays.toString(gm2));
            // Look for positions where "abc" or "abd" occur (expected maximum valid prefix length of 3).

            System.out.println("\n---------------------------------\n");

            // Test 3: No Occurrence.
            System.out.println("Test 3: No Occurrence");
            AC ac3 = new AC();
            ac3.insert("xyz");
            ac3.buildFailure();
            String text3 = "abcdef";
            int[] gm3 = ac3.searchGlobalMax(text3);
            System.out.println("Text: \"" + text3 + "\"");
            System.out.println("GlobalMax: " + Arrays.toString(gm3));
            // Expected: all zeros because "xyz" does not occur in "abcdef".

            System.out.println("\n---------------------------------\n");

            // Test 4: Stress Test with long input.
            System.out.println("Test 4: Stress Test");
            AC ac4 = new AC();
            StringBuilder sb = new StringBuilder();
            int len = 100000;
            for (int i = 0; i < len; i++) {
                sb.append('a');
            }
            String longWord = sb.toString();
            ac4.insert(longWord);
            ac4.buildFailure();
            long startTime = System.currentTimeMillis();
            int[] gm4 = ac4.searchGlobalMax(longWord);
            long endTime = System.currentTimeMillis();
            System.out.println("Stress Test: 100k 'a's as text");
            System.out.println("First 10 entries of GlobalMax: " + Arrays.toString(Arrays.copyOf(gm4, 10)));
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
        }
    }

    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        AC ac = new AC();
        // Instead of inserting every prefix, insert each full word.
        for (String word : words) {
            ac.insert(word);
        }
        ac.buildFailure();
        int[] globalMax = ac.searchGlobalMax(target);
        // Greedy jump-game over positions:
        // From position i, if globalMax[i] = L, then one valid segment covers target[i ... i+L-1].
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest) return -1;  // unreachable
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
