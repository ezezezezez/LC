import java.util.*;
import java.io.*;
import java.lang.*;

// 1080. Insufficient Nodes in Root to Leaf Paths

public class P1080 {
    int cur;

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean res = dfs(root, limit);
        return res ? root : null;
    }

    boolean dfs(TreeNode node, int limit) {
        cur += node.val;
        if (node.left == null && node.right == null) {
            boolean ret = cur >= limit;
            cur -= node.val;
            return ret;
        }

        boolean left = false, right = false;
        if (node.left != null) {
            left = dfs(node.left, limit);
            if (!left) node.left = null;
        }

        if (node.right != null) {
            right = dfs(node.right, limit);
            if (!right) node.right = null;
        }

        cur -= node.val;

        return left || right;
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
