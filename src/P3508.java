import java.util.*;

// 3508. Implement Router
public class P3508 {
    int mem;
    Map<Integer, Map<Integer, Set<Integer>>> packets = new HashMap<>();
    Deque<int[]> dq = new ArrayDeque<>();
    Map<Integer, SparseSegmentTree> map = new HashMap<>();

    public P3508(int memoryLimit) {
        mem = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        packets.computeIfAbsent(source, k -> new HashMap<>());
        Set<Integer> set = packets.get(source).computeIfAbsent(destination, k -> new HashSet<>());
        if (set.contains(timestamp)) {
            return false;
        }
        if (dq.size() == mem) {
            forwardPacket();
        }
        set.add(timestamp);
        dq.offerLast(new int[] {source, destination, timestamp});
        SparseSegmentTree rs = map.computeIfAbsent(destination, k -> new SparseSegmentTree(1, (int)Math.round(1e9)));
        rs.updateRange(timestamp, timestamp, 1);
        return true;
    }

    public int[] forwardPacket() {
        if (dq.isEmpty()) return new int[0];
        int[] ret = dq.pollFirst();
        packets.get(ret[0]).get(ret[1]).remove(ret[2]);
        map.get(ret[1]).updateRange(ret[2], ret[2], -1);
        return ret;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!map.containsKey(destination)) return 0;
        SparseSegmentTree rs = map.get(destination);
        long count = rs.queryRange(startTime, endTime);
        return (int)count;
    }

    class SparseSegmentTree {
        class Node {
            long info;
            long lazy;
            Node left, right;

            Node() {
                info = 0;
                lazy = 0;
            }
        }

        private Node root;
        private int start, end;

        public SparseSegmentTree(int start, int end) {
            this.start = start;
            this.end = end;
            root = new Node();
        }

        private void pushDown(Node node, int l, int r) {
            if (node.lazy != 0) {
                int mid = (l + r) / 2;
                if (node.left == null) node.left = new Node();
                if (node.right == null) node.right = new Node();
                node.left.info += node.lazy * (mid - l + 1);
                node.right.info += node.lazy * (r - mid);
                node.left.lazy += node.lazy;
                node.right.lazy += node.lazy;
                node.lazy = 0;
            }
        }

        public void updateRange(Node node, int l, int r, int a, int b, long val) {
            if (b < l || a > r) return;
            if (a <= l && r <= b) {
                node.info += val * (r - l + 1);
                node.lazy += val;
                return;
            }
            pushDown(node, l, r);
            int mid = (l + r) / 2;
            if (node.left == null) node.left = new Node();
            if (node.right == null) node.right = new Node();
            updateRange(node.left, l, mid, a, b, val);
            updateRange(node.right, mid + 1, r, a, b, val);
            node.info = node.left.info + node.right.info;
        }

        public long queryRange(Node node, int l, int r, int a, int b) {
            if (b < l || a > r) return 0;
            if (a <= l && r <= b) return node.info;
            pushDown(node, l, r);
            int mid = (l + r) / 2;
            long leftSum = node.left == null ? 0 : queryRange(node.left, l, mid, a, b);
            long rightSum = node.right == null ? 0 : queryRange(node.right, mid + 1, r, a, b);
            return leftSum + rightSum;
        }

        public void updateRange(int a, int b, long val) {
            updateRange(root, start, end, a, b, val);
        }

        public long queryRange(int a, int b) {
            return queryRange(root, start, end, a, b);
        }
    }
}
