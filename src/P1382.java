import java.io.*;
import java.lang.*;
import java.util.*;

// 1382. Balance a Binary Search Tree

public class P1382 {
    Map<TreeNode, Integer> hmap = new HashMap<>();
    TreeNode ret;

    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        return ret;
    }

    void dfs(TreeNode node) {
        if (node == null) return;
        // System.out.println(node.val);
        ret = insert(ret, node.val);
        dfs(node.left);
        dfs(node.right);
    }

    TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            TreeNode t = new TreeNode(val);
            hmap.put(t, 1);
            // System.out.println("1: " + t.val + " " + hmap.get(t));
            return t;
        }
        int cmp = Integer.compare(val, node.val);
        if (cmp < 0) {
            node.left = insert(node.left, val);
            if (hmap.getOrDefault(node.left, 0) - hmap.getOrDefault(node.right, 0) > 1) {
                if (val > node.left.val) {
                    node.left = leftRotate(node.left);
                }
                node = rightRotate(node);
            }
            // System.out.println("2: " + node.val + " " + hmap.get(node));
        } else {
            node.right = insert(node.right, val);
            if (hmap.getOrDefault(node.right, 0) - hmap.getOrDefault(node.left, 0) > 1) {
                if (val < node.right.val) {
                    node.right = rightRotate(node.right);
                }
                node = leftRotate(node);
            }
            // System.out.println("3: " + node.val + " " + hmap.get(node));
        }
        hmap.put(node, Math.max(hmap.getOrDefault(node.left, 0), hmap.getOrDefault(node.right, 0)) + 1);
        return node;
    }

    TreeNode leftRotate(TreeNode node) {
        TreeNode right = node.right;
        node.right = right.left;
        right.left = node;

        hmap.put(node, Math.max(hmap.getOrDefault(node.left, 0), hmap.getOrDefault(node.right, 0)) + 1);
        hmap.put(right, Math.max(hmap.getOrDefault(right.left, 0), hmap.getOrDefault(right.right, 0)) + 1);
        return right;
    }

    TreeNode rightRotate(TreeNode node) {
        TreeNode left = node.left;
        node.left = left.right;
        left.right = node;

        hmap.put(node, Math.max(hmap.getOrDefault(node.left, 0), hmap.getOrDefault(node.right, 0)) + 1);
        hmap.put(left, Math.max(hmap.getOrDefault(left.left, 0), hmap.getOrDefault(left.right, 0)) + 1);
        return left;
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
