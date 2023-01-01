import java.io.*;
import java.lang.*;
import java.util.*;

// 1914. Cyclically Rotating a Grid

public class P1914 {
    int m, n;
    public int[][] rotateGrid(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int[][] ret = new int[m][n];

        int i = 0, j = 0;
        boolean[][] vis = new boolean[m][n];
        boolean[][] curVis = new boolean[m][n];
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        while (i < m / 2 && j < n / 2) {
            int idx = 0;
            int curM = m - 2 * i, curN = n - 2 * j;
            int total = 2 * curM + 2 * (curN - 2);

            int curi = i, curj = j;
            int dirIdx = 2;
            while (idx < k % total) {
                int nxti = curi + dir[dirIdx], nxtj = curj + dir[dirIdx + 1];
                if (nxti < 0 || nxti >= m || nxtj < 0 || nxtj >= n || vis[nxti][nxtj]) {
                    dirIdx = Math.floorMod(dirIdx - 1, 4);
                    nxti = curi + dir[dirIdx];
                    nxtj = curj + dir[dirIdx + 1];
                }
                curi = nxti;
                curj = nxtj;
                idx++;
            }
            // System.out.println(curi + " " + curj);
            int ii = i, jj = j;
            int nowDirIdx = 2;
            int cnt = 0;


            while (cnt < total) {
                ret[curi][curj] = grid[ii][jj];
                cnt++;
                // System.out.println("[curi curj]" + "[" + curi + " " + curj + "]=[ii jj][" + ii + " " + jj + "]");
                // System.out.println(i + " " + j);
                vis[ii][jj] = true;
                curVis[curi][curj] = true;
                // printVis(vis);
                int nowNxti = ii + dir[nowDirIdx], nowNxtj = jj + dir[nowDirIdx + 1];
                // System.out.println(nowNxti + " " + nowNxtj + " " + nowDirIdx + " " + vis[nowNxti][nowNxtj]);
                if (nowNxti < 0 || nowNxti >= m || nowNxtj < 0 || nowNxtj >= n || vis[nowNxti][nowNxtj]) {
                    nowDirIdx = Math.floorMod(nowDirIdx - 1, 4);
                    // nowDirIdx = (nowDirIdx + 1) % 4;
                    nowNxti = ii + dir[nowDirIdx];
                    nowNxtj = jj + dir[nowDirIdx + 1];
                }
                // System.out.println(ii + " " + jj + " " + nowNxti + " " + nowNxtj);
                int curNxti = curi + dir[dirIdx], curNxtj = curj + dir[dirIdx + 1];
                if (curNxti < 0 || curNxti >= m || curNxtj < 0 || curNxtj >= n || curVis[curNxti][curNxtj]) {
                    dirIdx = Math.floorMod(dirIdx - 1, 4);
                    curNxti = curi + dir[dirIdx];
                    curNxtj = curj + dir[dirIdx + 1];
                }
                ii = nowNxti;
                jj = nowNxtj;
                curi = curNxti;
                curj = curNxtj;
                // System.out.println(ii + " " + jj + " " + nowDirIdx);
            }
            i++;
            j++;
        }
        return ret;
    }

    void printVis(boolean[][] grid) {
        for (boolean[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
