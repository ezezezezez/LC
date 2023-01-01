import java.io.*;
import java.lang.*;
import java.util.*;

// 2331. Evaluate Boolean Binary Tree

public class P2331 {
    public boolean evaluateTree(TreeNode root) {
        int val = root.val;
        if (val == 0) return false;
        if (val == 1) return true;
        if (val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        } else {
            return evaluateTree(root.left) && evaluateTree(root.right);
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
