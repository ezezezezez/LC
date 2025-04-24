// 3525. Find X Value of Array II
public class P3525_2 {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int[] ret = new int[queries.length];
        SegmentTree segTree = new SegmentTree(nums, k);
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int idx = q[0], v = q[1], s = q[2], x = q[3];
            segTree.update(idx, v, 0, n - 1, 0);
            Node result = segTree.query(s, n - 1, 0, n - 1, 0);
            ret[i] = result.freq[x];
        }
        return ret;
    }

    static class SegmentTree {
        int n, k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            n = nums.length;
            this.k = k;
            tree = new Node[4 * n];
            build(nums, 0, n - 1, 0);
        }

        void build(int[] nums, int lo, int hi, int pos) {
            if (lo == hi) {
                tree[pos] = new Node(nums[lo], k);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            build(nums, lo, mid, 2 * pos + 1);
            build(nums, mid + 1, hi, 2 * pos + 2);
            tree[pos] = merge(tree[2 * pos + 1], tree[2 * pos + 2]);
        }

        Node merge(Node left, Node right) {
            Node res = new Node(left.prod * right.prod % k, k);
            for (int i = 0; i < k; i++) {
                res.freq[i] = left.freq[i];
            }
            for (int i = 0; i < k; i++) {
                res.freq[left.prod * i % k] += right.freq[i];
            }
            return res;
        }

        void update(int idx, int value, int lo, int hi, int pos) {
            if (lo == hi) {
                tree[pos] = new Node(value, k);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            if (idx <= mid) {
                update(idx, value, lo, mid, 2 * pos + 1);
            } else {
                update(idx, value, mid + 1, hi, 2 * pos + 2);
            }
            tree[pos] = merge(tree[2 * pos + 1], tree[2 * pos + 2]);
        }

        Node query(int left, int right, int lo, int hi, int pos) {
            if (left > hi || right < lo) return new Node(k);
            if (left <= lo && right >= hi) return tree[pos];
            int mid = lo + (hi - lo) / 2;
            Node leftRes = query(left, right, lo, mid, 2 * pos + 1);
            Node rightRes = query(left, right, mid + 1, hi, 2 * pos + 2);
            return merge(leftRes, rightRes);
        }
    }

    static class Node {
        int prod;
        int[] freq;

        Node(int product, int k) {
            prod = product % k;
            freq = new int[k];
            freq[prod]++;
        }

        Node(int k) {
            prod = 1;
            freq = new int[k];
        }
    }
}
