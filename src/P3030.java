import java.util.Arrays;

// 3030. Find the Grid of Region Average
public class P3030 {
    public int[][] resultGrid(int[][] image, int threshold) {
        int m = image.length, n = image[0].length;
        int[][] ret = new int[m][n];
        int[][] avg = new int[m][n];
        for (int[] row : avg) Arrays.fill(row, -1);
        for (int i = 2; i < m; i++) {
            for (int j = 2; j < n; j++) {
                int sum = 0;
                boolean valid = true;
                outer:
                for (int u = 0; u >= -2; u--) {
                    for (int v = 0; v >= -2; v--) {
                        sum += image[i + u][j + v];
                        if (u <= -1) {
                            if (Math.abs(image[i + u][j + v] - image[i + u + 1][j + v]) > threshold) {
                                valid = false;
                                break outer;
                            }
                        }
                        if (v <= -1) {
                            if (Math.abs(image[i + u][j + v] - image[i + u][j + v + 1]) > threshold) {
                                valid = false;
                                break outer;
                            }
                        }
                    }
                }
                if (valid) {
                    avg[i][j] = sum / 9;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                int count = 0;
                for (int u = 0; u <= 2 && i + u < m; u++) {
                    for (int v = 0; v <= 2 && j + v < n; v++) {
                        if (avg[i + u][j + v] != -1) {
                            sum += avg[i + u][j + v];
                            count++;
                        }
                    }
                }
                ret[i][j] = count > 0 ? sum / count : image[i][j];
            }
        }
        return ret;
    }
}
