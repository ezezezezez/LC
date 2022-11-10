import java.util.*;
import java.io.*;
import java.lang.*;

// 885. Spiral Matrix III
public class P885 {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    int curdir = 0;
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        List<int[]> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        list.add(new int[]{rStart, cStart});
        set.add(rStart + "#" + cStart);

        int sz = rows * cols;
        int x = rStart, y = cStart;
        while (list.size() < sz) {
            int nxtdir = (curdir + 1) % 4;
            int nx = x + dir[nxtdir], ny = y + dir[nxtdir + 1];
            if (!set.contains(nx + "#" + ny)) {
                x = nx; y = ny;
                set.add(x + "#" + y);
                curdir = nxtdir;
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    list.add(new int[]{x, y});
                }
            } else {
                x = x + dir[curdir]; y = y + dir[curdir + 1];
                set.add(x + "#" + y);
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    list.add(new int[]{x, y});
                }
            }
            // System.out.println(x + " " + y);
        }

        return list.toArray(new int[0][2]);
    }
}
