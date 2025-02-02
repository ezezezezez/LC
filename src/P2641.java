import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 2641. Cousins in Binary Tree II
public class P2641 {
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            int sum = 0;
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node.left != null) sum += node.left.val;
                if (node.right != null) sum += node.right.val;
                list.add(node);
            }
            for (TreeNode node : list) {
                int t = sum;
                if (node.left != null) t -= node.left.val;
                if (node.right != null) t -= node.right.val;
                if (node.left != null) {
                    node.left.val = t;
                    q.offer(node.left);
                }
                if (node.right != null) {
                    node.right.val = t;
                    q.offer(node.right);
                }
            }
        }
        return root;
    }

    static class TreeNode {
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
