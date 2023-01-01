import java.io.*;
import java.lang.*;
import java.util.*;

// 2476. Closest Nodes Queries in a Binary Search Tree

public class P2476 {
    List<Integer> vals = new ArrayList<>();

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        int n = queries.size();
        List<List<Integer>> ret = new ArrayList<>();
        dfs(root);
        for (int query : queries) {
            int lo = 0, hi = vals.size() - 1, t1 = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int val = vals.get(mid);
                if (val <= query) {
                    t1 = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            lo = 0;
            hi = vals.size() - 1;
            int t2 = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int val = vals.get(mid);
                if (val >= query) {
                    t2 = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            ret.add(List.of(t1 != -1 ? vals.get(t1) : -1, t2 != -1 ? vals.get(t2) : -1));
        }
        return ret;
    }

    void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        vals.add(node.val);
        dfs(node.right);
    }

    class TreeNode {
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
