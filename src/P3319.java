import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3319. K-th Largest Perfect Subtree Size in Binary Tree
public class P3319 {
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

    static class Res {
        boolean isComplete;
        int size;

        Res(boolean isComplete, int size) {
            this.isComplete = isComplete;
            this.size = size;
        }

        Res() {
        }
    }

    List<Integer> sizes = new ArrayList<>();

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root);
        Collections.sort(sizes);
        return sizes.size() - k >= 0 ? sizes.get(sizes.size() - k) : -1;
    }

    Res dfs(TreeNode node) {
        if (node == null) {
            return new Res(true, 0);
        }
        Res left = dfs(node.left);
        Res right = dfs(node.right);

        Res res = new Res();
        if (left.isComplete && right.isComplete && left.size == right.size) {
            res.isComplete = true;
        } else {
            res.isComplete = false;
        }
        res.size = left.size + right.size + 1;

        if (res.isComplete) sizes.add(res.size);

        return res;
    }
}
