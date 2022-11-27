import java.util.*;
import java.io.*;
import java.lang.*;

// 1145. Binary Tree Coloring Game

public class P1145 {
    TreeNode xNode;
    int x, cnt;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        this.x = x;
        find(root);
        calc(xNode);
        if (n - cnt > cnt) return true;
        cnt = 0;
        calc(xNode.left);
        if (cnt > n - cnt) return true;
        cnt = 0;
        calc(xNode.right);
        if (cnt > n - cnt) return true;
        return false;
    }

    void find(TreeNode node) {
        if (node == null) return;
        if (node.val == x) {
            xNode = node;
            return;
        }
        find(node.left);
        find(node.right);
    }

    void calc(TreeNode node) {
        if (node == null) return;
        cnt++;
        calc(node.left);
        calc(node.right);
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
