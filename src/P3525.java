// 3525. Find X Value of Array II
public class P3525 {
    // Each Node stores:
    //   1. product: the product of all elements in this segment modulo k.
    //   2. freq: an array (of size k) where freq[r] represents the count of non-empty prefixes
    //      (if we processed the segment from left-to-right starting with product 1) that yield remainder r modulo k.
    static class Node {
        int product;
        int[] freq;

        // Leaf node from a single number.
        public Node(int prod, int k) {
            this.product = prod % k;
            freq = new int[k];
            // The only non-empty prefix is the element itself.
            freq[this.product] = 1;
        }

        // Identity node (for an empty segment) with no prefixes.
        public Node(int k) {
            this.product = 1; // identity for multiplication
            freq = new int[k]; // all counts zero
        }
    }

    // Our segment tree supports point updates and range queries.
    static class SegmentTree {
        int n, k;
        Node[] tree;

        public SegmentTree(int[] arr, int k) {
            this.n = arr.length;
            this.k = k;
            // Allocate a safe size for the tree array.
            tree = new Node[4 * n];
            build(arr, 0, n - 1, 0);
        }

        // Recursively build the segment tree.
        private void build(int[] arr, int left, int right, int pos) {
            if (left == right) {
                tree[pos] = new Node(arr[left] % k, k);
                return;
            }
            int mid = (left + right) / 2;
            build(arr, left, mid, 2 * pos + 1);
            build(arr, mid + 1, right, 2 * pos + 2);
            tree[pos] = merge(tree[2 * pos + 1], tree[2 * pos + 2]);
        }

        // Merge two nodes corresponding to contiguous segments.
        // The merged node holds:
        //   - product = (left.product * right.product) % k.
        //   - freq: All non-empty prefixes in the combined segment, where:
        //       • prefixes entirely in left are directly taken.
        //       • for each prefix from the right, when appended after left, its remainder is
        //         (left.product * (right prefix remainder)) mod k.
        private Node merge(Node leftNode, Node rightNode) {
            Node res = new Node(k); // starts with product=1 and empty frequency array.
            // Compute overall product.
            res.product = (leftNode.product * rightNode.product) % k;

            // Add the prefixes coming entirely from the left segment.
            for (int r = 0; r < k; r++) {
                res.freq[r] += leftNode.freq[r];
            }
            // For every non-empty prefix in the right segment, shift its remainder by leftNode.product.
            for (int r = 0; r < k; r++) {
                int count = rightNode.freq[r];
                if (count > 0) {
                    int newRemainder = (leftNode.product * r) % k;
                    res.freq[newRemainder] += count;
                }
            }
            return res;
        }

        // Return an identity Node.
        private Node identity() {
            return new Node(k);
        }

        // Query for the range [queryLeft, queryRight] in the array.
        public Node query(int queryLeft, int queryRight) {
            return queryUtil(queryLeft, queryRight, 0, n - 1, 0);
        }

        private Node queryUtil(int queryLeft, int queryRight, int low, int high, int pos) {
            // No overlap.
            if (queryLeft > high || queryRight < low) {
                return identity();
            }
            // Total overlap.
            if (queryLeft <= low && high <= queryRight) {
                return tree[pos];
            }
            int mid = (low + high) / 2;
            Node leftResult = queryUtil(queryLeft, queryRight, low, mid, 2 * pos + 1);
            Node rightResult = queryUtil(queryLeft, queryRight, mid + 1, high, 2 * pos + 2);
            return merge(leftResult, rightResult);
        }

        // Update the element at a specific index with a new value.
        public void update(int index, int newVal) {
            updateUtil(index, newVal, 0, n - 1, 0);
        }

        private void updateUtil(int index, int newVal, int low, int high, int pos) {
            if (low == high) {
                tree[pos] = new Node(newVal % k, k);
                return;
            }
            int mid = (low + high) / 2;
            if (index <= mid) {
                updateUtil(index, newVal, low, mid, 2 * pos + 1);
            } else {
                updateUtil(index, newVal, mid + 1, high, 2 * pos + 2);
            }
            tree[pos] = merge(tree[2 * pos + 1], tree[2 * pos + 2]);
        }
    }

    /**
     * Given an array of positive integers nums, an integer k, and an array of queries,
     * each query is represented as [indexi, valuei, starti, xi]:
     *
     * 1. Update nums[indexi] to valuei (persisting for subsequent queries).
     * 2. Consider the subarray after removing the prefix nums[0...starti-1].
     * 3. The x-value of the subarray is defined as the number of non-empty prefixes
     *    (i.e. ways to remove a suffix) whose product mod k equals xi.
     *
     * @param nums the original array of numbers
     * @param k the modulo for product computation
     * @param queries the queries to process
     * @return an array where each element is the answer for the corresponding query.
     */
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree segTree = new SegmentTree(nums, k);
        int q = queries.length;
        int[] result = new int[q];

        // Process each query.
        for (int i = 0; i < q; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int xi = queries[i][3];

            // Update the array element at the given index.
            segTree.update(index, value);
            // The update persists for future queries.

            // After an update, we remove the prefix nums[0...start-1]
            // so we query the range [start, n - 1].
            Node nodeResult = segTree.query(start, n - 1);
            // The answer for the query is how many non-empty prefixes (operations) have remainder xi.
            result[i] = nodeResult.freq[xi];
        }

        return result;
    }
}
