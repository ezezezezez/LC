import java.io.*;
import java.lang.*;
import java.util.*;

// 2265. Count Nodes Equal to Average of Subtree

public class P2265 {
    int ret = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ret;
    }

    int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int sum = left[0] + right[0] + node.val;
        int cnt = left[1] + right[1] + 1;
        if (sum / cnt == node.val) ret++;
        return new int[]{sum, cnt};
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
