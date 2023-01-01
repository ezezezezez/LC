import java.io.*;
import java.lang.*;
import java.util.*;

// 2201. Count Artifacts That Can Be Extracted

public class P2201 {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int ret = 0, m = dig.length;
        boolean[][] vis = new boolean[n][n];
        for (int[] d : dig) {
            int x = d[0], y = d[1];
            vis[x][y] = true;
        }
        for (int[] art : artifacts) {
            int a = art[0], b = art[1], c = art[2], d = art[3];
            boolean flag = true;
            out:
            for (int i = a; i <= c; i++) {
                for (int j = b; j <= d; j++) {
                    if (!vis[i][j]) {
                        flag = false;
                        break out;
                    }
                }
            }
            if (flag) ret++;
        }
        return ret;
    }
}
