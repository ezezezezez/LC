import java.util.*;
import java.io.*;
import java.lang.*;

// 988. Smallest String Starting From Leaf

public class P988 {
    String ret;

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ret;
    }

    void dfs(TreeNode node, StringBuilder sb) {
        TreeNode left = node.left, right = node.right;
        sb.append((char) (node.val + 'a'));
        // System.out.println(sb);
        if (left == null && right == null) {
            if (ret == null) ret = sb.reverse().toString();
            else if (sb.reverse().toString().compareTo(ret) < 0) {
                ret = sb.toString();
            }
            sb.reverse();
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        if (left != null) dfs(node.left, sb);
        if (right != null) dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
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
