import java.io.*;
import java.lang.*;
import java.util.*;

// 2415. Reverse Odd Levels of Binary Tree

public class P2415 {
    public TreeNode reverseOddLevels(TreeNode root) {
        Deque<TreeNode> dq = new ArrayDeque<>();
        int level = 0;
        dq.offer(root);
        while (!dq.isEmpty()) {
            int sz = dq.size();
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = dq.poll();
                if (level % 2 == 1) list.add(cur);
                if (cur.left != null) dq.offer(cur.left);
                if (cur.right != null) dq.offer(cur.right);
            }
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size() / 2; i++) {
                    int t = list.get(i).val;
                    list.get(i).val = list.get(list.size() - 1 - i).val;
                    list.get(list.size() - 1 - i).val = t;
                }
            }
            level++;
        }
        return root;
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
