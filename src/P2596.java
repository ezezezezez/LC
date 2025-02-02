public class P2596 {
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int cur = 0;
        if (grid[0][0] != 0) return false;
        int[] hdx = new int[]{-2, -2, -1, 1, 2,  2, 1, -1};
        int[] hdy = new int[]{-1,  1,  2, 2, 1, -1, -2, -2};
        int curx = 0, cury = 0;
        while (cur < n * n - 1) {
            boolean flag = false;
            for (int i = 0; i < hdx.length; i++) {
                int nx = curx + hdx[i], ny = cury + hdy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == cur + 1) {
                    flag = true;
                    curx = nx;
                    cury = ny;
                    break;
                }
            }
            if (!flag) return false;
            cur++;
        }
        return true;
    }
}
