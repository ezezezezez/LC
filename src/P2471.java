import java.io.*;
import java.lang.*;
import java.util.*;

// 2471. Minimum Number of Operations to Sort a Binary Tree by Level

public class P2471 {
    public int minimumOperations(TreeNode root) {
        int ret = 0;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int sz = dq.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = dq.poll();
                list.add(cur.val);
                if (cur.left != null) dq.offer(cur.left);
                if (cur.right != null) dq.offer(cur.right);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) map.put(list.get(i), i);
            List<Integer> list2 = new ArrayList<>(list);
            Collections.sort(list2);
            for (int i = 0; i < list.size(); i++) {
                int a = list.get(i), b = list2.get(i);
                if (a != b) {
                    int idx = map.get(b);
                    list.set(i, b);
                    list.set(idx, a);
                    ret++;
                    map.put(a, idx);
                    map.put(b, i);
                }
            }
        }
        return ret;
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
