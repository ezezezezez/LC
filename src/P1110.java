import java.util.*;
import java.io.*;
import java.lang.*;

// 1110. Delete Nodes And Return Forest

public class P1110 {
    Set<Integer> set = new HashSet<>();
    List<TreeNode> ret = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (int x : to_delete) set.add(x);
        dfs(root);
        if (root.left != null && set.contains(root.left.val)) root.left = null;
        if (root.right != null && set.contains(root.right.val)) root.right = null;
        if (!set.contains(root.val)) {
            ret.add(root);
        }
        return ret;
    }

    void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);
        if (node.left != null && set.contains(node.left.val)) node.left = null;
        if (node.right != null && set.contains(node.right.val)) node.right = null;

        if (set.contains(node.val)) {
            if (node.left != null) ret.add(node.left);
            if (node.right != null) ret.add(node.right);
            return;
        }
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
