import java.io.*;
import java.lang.*;
import java.util.*;

// 1609. Even Odd Tree

public class P1609 {
    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        int level = 0;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            int pre = level % 2 == 0 ? 0 : (int) 1e7;
            for (int i = 0; i < sz; i++) {
                TreeNode node = dq.poll();
                int val = node.val;
                if (level % 2 == 0) {
                    if (val % 2 == 0 || val <= pre) return false;
                    pre = val;
                } else {
                    if (val % 2 == 1 || val >= pre) return false;
                    pre = val;
                }
                if (node.left != null) dq.offer(node.left);
                if (node.right != null) dq.offer(node.right);
            }
            level++;
        }
        return true;
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
