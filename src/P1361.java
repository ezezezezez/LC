import java.io.*;
import java.lang.*;
import java.util.*;

// 1361. Validate Binary Tree Nodes

public class P1361 {
    int[] left, right;
    boolean[] vis;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        vis = new boolean[n];
        left = leftChild;
        right = rightChild;

        boolean[] notRoot = new boolean[n];
        for (int child : leftChild) {
            if (child != -1) notRoot[child] = true;
        }
        for (int child : rightChild) {
            if (child != -1) notRoot[child] = true;
        }
        int cnt = 0, root = -1;
        for (int i = 0; i < n; i++) {
            if (!notRoot[i]) {
                root = i;
                cnt++;
            }
        }

        if (root == -1 || cnt >= 2) return false;

        if (!dfs(root)) return false;
        for (boolean v : vis) {
            if (!v) return false;
        }
        return true;
    }

    boolean dfs(int node) {
        if (vis[node]) return false;
        vis[node] = true;
        if (left[node] != -1 && !dfs(left[node])) return false;
        if (right[node] != -1 && !dfs(right[node])) return false;
        return true;
    }
}
