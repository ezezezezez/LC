import java.util.*;
import java.io.*;
import java.lang.*;

// 1261. Find Elements in a Contaminated Binary Tree

public class P1261 {
    TreeNode root;
    Set<Integer> set = new HashSet<>();

    public P1261(TreeNode root) {
        this.root = root;
        dfs(root, 0);
    }

    void dfs(TreeNode node, int v) {
        if (node == null) return;
        set.add(v);
        dfs(node.left, 2 * v + 1);
        dfs(node.right, 2 * v + 2);
    }

    public boolean find(int target) {
        return set.contains(target);
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
