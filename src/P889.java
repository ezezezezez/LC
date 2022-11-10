import java.util.*;
import java.io.*;
import java.lang.*;

// 889. Construct Binary Tree from Preorder and Postorder Traversal
public class P889 {
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < preorder.length; i++) {
            map.put(preorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    TreeNode build(int[] preorder, int pres, int pree, int[] postorder, int posts, int poste) {
        if (pree - pres + 1 == 1) {
            return new TreeNode(preorder[pres]);
        }

        int rootVal = preorder[pres];
        TreeNode node = new TreeNode(rootVal);

        int lastVal = postorder[poste - 1];
        int lastPos = map.get(lastVal);
        if (lastPos == pres + 1) {
            node.left = build(preorder, pres + 1, pree, postorder, posts, poste - 1);
        } else {
            int leftLen = lastPos - pres - 1;
            node.left = build(preorder, pres + 1, lastPos - 1, postorder, posts, posts + leftLen - 1);
            node.right = build(preorder, lastPos, pree, postorder, posts + leftLen, poste - 1);
        }
        return node;
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
