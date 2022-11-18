import java.util.*;
import java.io.*;
import java.lang.*;

// 1072. Flip Columns For Maximum Number of Equal Rows

public class P1072 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        Node root = new Node();
        int ret = 0;

        for (int i = 0; i < m; i++) {
            Node a = root, b = root;
            for (int j = 0; j < n; j++) {
                int v = matrix[i][j];
                if (a.nxt[v] == null) a.nxt[v] = new Node();
                a = a.nxt[v];
                if (b.nxt[v ^ 1] == null) b.nxt[v ^ 1] = new Node();
                b = b.nxt[v ^ 1];
            }
            a.cnt++; b.cnt++;
            ret = Math.max(ret, Math.max(a.cnt, b.cnt));
        }

        return ret;
    }

    static class Node {
        Node[] nxt = new Node[2];
        int cnt;
    }
}
