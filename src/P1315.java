import java.util.*;
import java.io.*;
import java.lang.*;

// 1315. Sum of Nodes with Even-Valued Grandparent

public class P1315 {
    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return sum;
    }

    void dfs(TreeNode node, TreeNode parent, TreeNode grandParent) {
        if (node == null) return;
        if (grandParent != null && grandParent.val % 2 == 0) sum += node.val;
        if (parent == null) {
            dfs(node.left, node, null);
            dfs(node.right, node, null);
        } else {
            dfs(node.left, node, parent);
            dfs(node.right, node, parent);
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
