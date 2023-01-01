import java.io.*;
import java.lang.*;
import java.util.*;

// 2385. Amount of Time for Binary Tree to Be Infected

public class P2385 {
    Set<Integer>[] g = new Set[100001];
    int n;

    public int amountOfTime(TreeNode root, int start) {
        Arrays.setAll(g, k -> new HashSet<>());
        makeEdge(root);
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] vis = new boolean[100001];
        dq.offer(start);
        // System.out.println(dq);
        // System.out.println(g[3]);
        vis[start] = true;
        int step = 0;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int cur = dq.poll();
                for (int nxt : g[cur]) {
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        dq.offer(nxt);
                    }
                }
            }
            // System.out.println(dq);
            step++;
        }
        return step - 1;
    }

    void makeEdge(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            g[node.val].add(node.left.val);
            g[node.left.val].add(node.val);
        }
        if (node.right != null) {
            g[node.val].add(node.right.val);
            g[node.right.val].add(node.val);
        }
        makeEdge(node.left);
        makeEdge(node.right);
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
