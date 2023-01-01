import java.io.*;
import java.lang.*;
import java.util.*;

// 1372. Longest ZigZag Path in a Binary Tree

public class P1372 {
    int ret;

    public int longestZigZag(TreeNode root) {
        dfs(root, null);
        return ret - 1;
    }

    int dfs(TreeNode node, TreeNode parent) {
        if (node == null) return 0;
        int left = dfs(node.left, node);
        int right = dfs(node.right, node);
        ret = Math.max(ret, left + 1);
        ret = Math.max(ret, right + 1);
        if (parent == null) return -1;
        return (node == parent.left ? right : left) + 1;
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
