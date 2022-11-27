import java.util.*;
import java.io.*;
import java.lang.*;

// 1161. Maximum Level Sum of a Binary Tree

public class P1161 {
    public int maxLevelSum(TreeNode root) {
        int ret = Integer.MIN_VALUE;
        int level = -1;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        int curLevel = 1;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            int sum = 0;
            for (int i = 0; i < sz; i++) {
                TreeNode node = dq.poll();
                sum += node.val;
                if (node.left != null) dq.offer(node.left);
                if (node.right != null) dq.offer(node.right);
            }
            if (sum > ret) {
                ret = sum;
                level = curLevel;
            }
            curLevel++;
        }

        return level;
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
