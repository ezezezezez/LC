import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 3292. Minimum Number of Valid Strings to Form Target II
public class P3292_6 {
    // ---------- AC Automaton Node -----------
    class Node {
        Node[] children = new Node[26];
        Node failure;   // failure pointer
        // output flag: true if the node corresponds to a valid prefix (non-empty)
        boolean output;
        int depth;      // length of the string (number of characters) from the root to this node
        // maxOutput = maximum depth value in the chain from this node upward (via failure pointers)
        int maxOutput;

        Node(int depth) {
            this.depth = depth;
            this.output = false;
            this.failure = null;
            this.maxOutput = 0;
        }
    }

    Node root;

    // ---------- Build Trie: Insert all words (and mark every prefix as valid) ----------
    private void buildTrie(String[] words) {
        root = new Node(0);
        // (We do not mark the empty prefix at the root as valid.)
        for (String word : words) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Node(cur.depth + 1);
                }
                cur = cur.children[c];
                // Every non-empty prefix is valid.
                cur.output = true;
            }
        }
    }

    // ---------- Build Failure Pointers and Compute maxOutput for each node ----------
    // Standard BFS building of failure pointers.
    // Also, we set for each node: maxOutput = max( (node.output ? node.depth : 0), failure.maxOutput ).
    private void buildFailureAndComputeMaxOutput() {
        Queue<Node> queue = new LinkedList<>();
        root.failure = root;
        root.maxOutput = 0;  // root does not represent any valid (non-empty) prefix.
        // Initialize children of root.
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                root.children[i].failure = root;
                // For nodes directly under root, if output then maxOutput = node.depth; else 0.
                root.children[i].maxOutput = root.children[i].output ? root.children[i].depth : 0;
                queue.offer(root.children[i]);
            } else {
                root.children[i] = root;  // missing transitions lead back to root.
            }
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 26; i++) {
                Node child = cur.children[i];
                if (child != null) {
                    Node f = cur.failure;
                    child.failure = f.children[i];
                    // Compute child's maxOutput.
                    int candidate = child.output ? child.depth : 0;
                    child.maxOutput = Math.max(candidate, child.failure.maxOutput);
                    queue.offer(child);
                } else {
                    cur.children[i] = cur.failure.children[i];
                }
            }
        }
    }

    // ---------- Segment Tree for Range Minimum Query on dp array ----------
    class SegmentTree {
        int n;
        int[] tree;
        final int INF = Integer.MAX_VALUE / 2;

        SegmentTree(int n) {
            this.n = n;
            // size: next power of 2 * 2
            tree = new int[4 * n];
            Arrays.fill(tree, INF);
        }

        // build tree from initial dp array
        void build(int[] dp) {
            build(0, n - 1, 1, dp);
        }

        private void build(int l, int r, int idx, int[] dp) {
            if (l == r) {
                tree[idx] = dp[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, idx * 2, dp);
            build(mid + 1, r, idx * 2 + 1, dp);
            tree[idx] = Math.min(tree[idx * 2], tree[idx * 2 + 1]);
        }

        // point update: set position pos to value val, then update tree.
        void update(int pos, int val) {
            update(0, n - 1, 1, pos, val);
        }

        private void update(int l, int r, int idx, int pos, int val) {
            if (l == r) {
                tree[idx] = val;
                return;
            }
            int mid = (l + r) >> 1;
            if (pos <= mid)
                update(l, mid, idx * 2, pos, val);
            else
                update(mid + 1, r, idx * 2 + 1, pos, val);
            tree[idx] = Math.min(tree[idx * 2], tree[idx * 2 + 1]);
        }

        // query minimum value in the range [ql, qr]
        int query(int ql, int qr) {
            return query(0, n - 1, 1, ql, qr);
        }

        private int query(int l, int r, int idx, int ql, int qr) {
            if (qr < l || r < ql) return INF;
            if (ql <= l && r <= qr) return tree[idx];
            int mid = (l + r) >> 1;
            return Math.min(query(l, mid, idx * 2, ql, qr),
                    query(mid + 1, r, idx * 2 + 1, ql, qr));
        }
    }

    // ---------- Main Method to Solve Segmentation Using AC Automaton + DP with Segment Tree ----------
    // We first build the automaton and compute maxOutput.
    // Then we process the target once—at each target position j, let state = automaton state.
    // Let best[j] = state.maxOutput, meaning that there is an occurrence ending at j with length = best[j].
    // (In particular, if words are such that every prefix is accepted, then if the target
    // from some position matches many characters, best[j] will be the maximum such length.)
    // Then an occurrence ending at j produces a valid segment covering [j - L + 1, j] where L = best[j].
    // In segmentation, that means dp[j+1] = min_{i in [j - L + 1, j]} (dp[i] + 1).
    // We use a segment tree to perform these range minimum queries.
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        buildTrie(words);
        buildFailureAndComputeMaxOutput();

        // dp[i] = minimum segments to cover target[0...i-1]
        // dp[0] = 0, others INF.
        final int INF = Integer.MAX_VALUE / 2;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // We'll build a segment tree over dp[0 ... n]
        SegmentTree segTree = new SegmentTree(n + 1);
        segTree.build(dp);

        // Process the target with the AC automaton.
        Node state = root;
        // best[j] will be computed on the fly: the maximum occurrence length of any valid segment ending at position j.
        // (If no valid occurrence ends at j, best[j] remains 0.)
        int[] best = new int[n];  // not strictly needed to store all, but for clarity.
        for (int j = 0; j < n; j++) {
            int c = target.charAt(j) - 'a';
            state = state.children[c];  // transition; guaranteed non-null by our construction.
            // Instead of iterating over the entire output chain, use the precomputed maxOutput.
            best[j] = state.maxOutput;  // best occurrence ending at j has length best[j].
            if (best[j] > 0) {
                // This occurrence (of length L = best[j]) covers target substring from
                // start = j - L + 1 up to j.
                int L = best[j];
                int l = Math.max(0, j - L + 1);
                int r = j;  // all possible segment-start positions i for which an occurrence ending at j covers i...j.
                // We need to update dp[j+1] = min( dp[i] ) + 1 for i in [l, r].
                int candidate = segTree.query(l, r) + 1;
                if (candidate < dp[j + 1]) {
                    dp[j + 1] = candidate;
                    segTree.update(j + 1, candidate);
                }
            }
        }
        return dp[n] >= INF ? -1 : dp[n];
    }
}
