import java.util.*;

public class P2583 {
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<Long> list = new ArrayList<>();
        while (!q.isEmpty()) {
            int sz = q.size();
            long sum = 0;
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            list.add(sum);
        }
        if (list.size() < k) return -1;
        Collections.sort(list);
        return list.get(list.size() - k);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
