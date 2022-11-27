import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

// 1123. Lowest Common Ancestor of Deepest Leaves

public class P1123 {
    int d;
    List<TreeNode> nodes = new ArrayList<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        TreeNode cur = null;
        for (TreeNode node : nodes) {
            cur = lowestCommonAncestor(root, cur, node);
        }
        return cur;
    }

    void dfs(TreeNode node, int h) {
        if (node == null) return;
        if (h > d) {
            d = h;
            nodes.clear();
            nodes.add(node);
        } else if (h == d) {
            nodes.add(node);
        }
        dfs(node.left, h + 1);
        dfs(node.right, h + 1);
    }

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        else return right;
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
