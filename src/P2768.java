import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2768. Number of Black Blocks
public class P2768 {
    int[] dx = new int[] {-1, -1, 0, 0};
    int[] dy = new int[] {-1, 0, -1, 0};
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        int len = coordinates.length;
        long[] ret = new long[5];
        ret[0] = 1L * (m - 1) * (n - 1);
        Map<Long, Integer> map = new HashMap<>();
        for (int[] co : coordinates) {
            long x = co[0], y = co[1];
            for (int i = 0; i < 4; i++) {
                long nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx >= m - 1 || ny < 0 || ny >= n - 1) continue;
                int cnt = map.getOrDefault((nx << 32) | ny, 0);
                // System.out.println(nx + " " + ny);
                ret[cnt]--;
                ret[cnt + 1]++;
                map.merge((nx << 32) | ny, 1, Integer::sum);
                // System.out.println(Arrays.toString(ret));
            }
            // System.out.println("---");
        }
        return ret;
    }
}
