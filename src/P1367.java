import java.io.*;
import java.lang.*;
import java.util.*;

// 1367. Linked List in Binary Tree

public class P1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs0(root, head);
    }

    boolean dfs0(TreeNode treeNode, ListNode listHead) {
        if (treeNode == null) return false;
        // System.out.println(treeNode.val);
        if (dfs(treeNode, listHead)) return true;
        if (dfs0(treeNode.left, listHead)) return true;
        return dfs0(treeNode.right, listHead);
    }

    boolean dfs(TreeNode treeCur, ListNode listCur) {
        if (listCur == null) return true;
        if (treeCur == null) return false;
        if (treeCur.val == listCur.val) {
            if (dfs(treeCur.left, listCur.next)) return true;
            return dfs(treeCur.right, listCur.next);
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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
