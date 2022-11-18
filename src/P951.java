import java.util.*;
import java.io.*;
import java.lang.*;

// 951. Flip Equivalent Binary Trees

public class P951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return false;
        if (root1.val != root2.val) return false;

        if (root1.left == null && root2.left == null) return flipEquiv(root1.right, root2.right);
        if (root1.left == null) {
            TreeNode t = root2.left;
            root2.left = root2.right;
            root2.right = t;
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        }
        if (root2.left == null) {
            TreeNode t = root1.left;
            root1.left = root1.right;
            root1.right = t;
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        }
        if (root1.left.val != root2.left.val) {
            TreeNode t = root1.left;
            root1.left = root1.right;
            root1.right = t;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
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
